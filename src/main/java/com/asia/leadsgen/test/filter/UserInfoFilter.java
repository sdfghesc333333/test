package com.asia.leadsgen.test.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import com.asia.leadsgen.test.model.UserInfo;
import com.asia.leadsgen.test.util.AppConstants;
import com.asia.leadsgen.test.util.DecryptTokenUtil;

import lombok.SneakyThrows;

@Component
public class UserInfoFilter implements Filter {

    @Autowired
    DecryptTokenUtil decryptTokenUtil;

    @SneakyThrows
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        String jwtToken = request.getHeader(AppConstants.AUTHORIZATION_HEADER);

        if (!StringUtils.isEmpty(jwtToken)) {
            int endOfBearer = "Bearer ".length();
            String token = jwtToken.substring(endOfBearer, jwtToken.length());
            UserInfo userInfo = decryptTokenUtil.getUserFromToken(token);

            if (userInfo.getExp() <= System.currentTimeMillis()) {
                HttpServletResponse httpResponse = (HttpServletResponse) response;
                httpResponse.sendError(HttpStatus.UNAUTHORIZED.value(), "Session Expired");
                return;
            }
            request.setAttribute("user_info", userInfo);

        }

        filterChain.doFilter(request, response);
    }

}
