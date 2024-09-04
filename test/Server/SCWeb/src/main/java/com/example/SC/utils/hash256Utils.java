package com.example.SC.utils;

import org.apache.commons.codec.digest.DigestUtils;

public class hash256Utils {
    public static String encodeSHA256(String data){
        return DigestUtils.sha256Hex(data);
    }
}
