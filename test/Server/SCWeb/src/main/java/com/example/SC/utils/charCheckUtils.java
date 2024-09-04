package com.example.SC.utils;

public class charCheckUtils {

    public static boolean pwsCharCheck(String s){
        String str = "^(?![a-zA-Z]+$)(?![A-Z0-9]+$)(?![A-Z\\W_!@#$%^&*`~()-+=]+$)(?![a-z0-9]+$)(?![a-z\\W_!@#$%^&*`~()-+=]+$)(?![0-9\\W_!@#$%^&*`~()-+=]+$)[a-zA-Z0-9\\W_!@#$%^&*`~()-+=]{8,30}$";
        if (!s.matches(str)) {
            return false;
            //"password should contain at least 3 of: uppercase letters, lowercase letters, numbers, symbols"
        } else {
            return true;
            //"Vaild password"
        }
    }

    public static boolean userNameCharCheck(String s){
        if (!s.matches("^([a-zA-Z0-9 _-]*$){1,30}$")) {
            return false;
            //"The username must contain only letters, numbers, _- symbols, or spaces."
        }else {
            return true;
            //"Vaild username"
        }
    }
}
