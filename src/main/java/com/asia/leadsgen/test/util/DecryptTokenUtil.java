package com.asia.leadsgen.test.util;

import lombok.extern.log4j.Log4j2;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.asia.leadsgen.test.exception.ExceptionController;
import com.asia.leadsgen.test.exception.LoginException;
import com.asia.leadsgen.test.model.UserInfo;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.*;

@Component
public class DecryptTokenUtil {

    public UserInfo getUserFromToken(String jwtToken) throws Exception, LoginException {

        Map<String, String> decodeMap = decodeJWT(jwtToken);
        String userId = decodeMap.get("user_id");
        String email = decodeMap.get("email");
        String isOwner = decodeMap.get("owner");
        String exp = decodeMap.get("exp");
        Long expTime  = Long.parseLong(exp);

        UserInfo user = new UserInfo();
        user.setUserId(userId);
        user.setEmail(email);
        user.setOwner(isOwner.equalsIgnoreCase("yes"));
        user.setExp(expTime);

        Set<String> domainNames = new HashSet<>();
        Set<String> module = new HashSet<>();
        Set<String> global = new HashSet<>();

        if (!user.isOwner()) {
            String domains = ParamUtil.getString(decodeMap, AppParams.DOMAINS);
            String modulePermissions = ParamUtil.getString(decodeMap, AppParams.MODULE_PERMISSIONS);
            String globalPermissions = ParamUtil.getString(decodeMap, AppParams.GLOBAL_PERMISSIONS);
            domainNames = StringUtils.isEmpty(domains) ? null : new HashSet<>(Arrays.asList(domains.split(",")));
            module = StringUtils.isEmpty(modulePermissions) ? null : new HashSet<>(Arrays.asList(modulePermissions.split(",")));
            global = StringUtils.isEmpty(globalPermissions) ? null : new HashSet<>(Arrays.asList(globalPermissions.split(",")));

            user.setDomains(domainNames);
            user.setGlobalPermissions(global);
            user.setModulePermissions(module);
        }

        return user;
    }

    public Map<String, String> decodeJWT(String jwtToken) throws Exception {
        String[] split_string = jwtToken.split("\\.");
        String base64EncodedBody = split_string[1];

        Base64 base64Url = new Base64();

        String body = new String((byte[]) base64Url.decode(base64EncodedBody));

        Map payload = JSONStringToMapUtil.toMap(body);
        return encryptMap(payload);
    }

    private String decryptS5(String input) throws Exception {

        byte[] cipherText = java.util.Base64.getDecoder().decode(input);

        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING", "SunJCE");

        SecretKeySpec key = new SecretKeySpec(encryptionKey.getBytes(StandardCharsets.UTF_8), "AES");

        cipher.init(Cipher.DECRYPT_MODE, key, new IvParameterSpec(new byte[16]));

        return new String(cipher.doFinal(cipherText), StandardCharsets.UTF_8);
    }

    private Map<String, String> encryptMap(Map orgMap) throws Exception {
        Map<String, String> resultMap = new HashMap<>();

        Set<String> keySet = orgMap.keySet();
        for (String key : keySet) {
            String newKey = decryptS5(key);
            String value = decryptS5(orgMap.get(key).toString());
            resultMap.put(newKey, value);
        }
        return resultMap;
    }

    private String encryptionKey = "DRclFCoMPpDtZsQ9";
}
