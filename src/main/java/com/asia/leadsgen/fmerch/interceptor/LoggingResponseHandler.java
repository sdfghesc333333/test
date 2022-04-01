package com.asia.leadsgen.fmerch.interceptor;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.util.logging.Logger;

@ControllerAdvice
public class LoggingResponseHandler implements ResponseBodyAdvice<Object> {

    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        return true;
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            String responseString =  mapper.writeValueAsString(body);
            if (responseString.length() > 2000) {
                responseString = responseString.substring(0, 2000) + " ...";
            }

            logger.info("[RESPONSE] BODY : " + responseString);
            logger.info("[RESPONSE] ****************************** DONE ******************************\n");
        } catch (Exception e) {

        }

        return body;
    }

    private Logger logger = Logger.getLogger(LoggingResponseHandler.class.getName());
}
