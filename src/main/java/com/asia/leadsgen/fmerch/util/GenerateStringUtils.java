package com.asia.leadsgen.fmerch.util;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Component;

@Component
public class GenerateStringUtils {

    public String generateId(){
        return RandomStringUtils.randomAlphanumeric(16);
    }
}
