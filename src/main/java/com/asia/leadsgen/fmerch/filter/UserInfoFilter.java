package com.asia.leadsgen.fmerch.filter;

import com.asia.leadsgen.fmerch.controller.SimpleController;
import com.asia.leadsgen.fmerch.model.UserInfo;
import com.asia.leadsgen.fmerch.util.AppConstants;
import com.asia.leadsgen.fmerch.util.DecryptTokenUtil;
import lombok.SneakyThrows;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.logging.Logger;

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
    
    private Logger logger = Logger.getLogger(SimpleController.class.getName());
}
