package com.example.SC.result;

public enum ResultMsgEnum {
    SUCCESS(0, "success"),
    FAIL(-1, "error"),
    REGISTER_USERNAME_ERROR(2000,"The username must contain only letters, numbers, _- symbols, or spaces."),
    REGISTER_USERNAME_EXSIST(2001,"User Name exsist"),
    REGISTER_PASSWORD_ERROR(2002,"password should contain at least 3 of: uppercase letters, lowercase letters, numbers, symbols"),
    LOGIN_PASSWORD_ERROR(3000,"Username or password is wrong."),
    LOGIN_USERNAME_ERROR(3001,"Username not found."),
    ADD_FRIEND_ERROR(4001,"You have already added this friend."),
    ADD_SELF_ERROR(4002,"You cannot add yourself as a friend."),
    FRIEND_LIST_EMPTY(4003,"You don't have any friend yet."),
    DELETE_FRIEND_ERROR(4004,"Delete fail, don't find this user in your fiend list"),
    NO_USER_FOUND(4005,"No user was found"),
    MAX_FRIEND_ERROR(4006,"Your friend list is full"),
    REQUEST_EXIST_ERROR(4007,"The request has been sent, please be patient and wait for a response");

    private int code;
    private String message;

    ResultMsgEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return this.code;
    }

    public String getMessage() {
        return this.message;
    }
}
