package com.example.SC.utils;

import org.apache.commons.lang3.RandomStringUtils;

public class PasswordUtils {

    public static String getSalt(){
        return RandomStringUtils.randomAlphabetic(16);
    }
}
