package com.example.SC.exceptions;

import lombok.Getter;

@Getter
public class ServiceException extends RuntimeException{

    private final String code;

    public ServiceException (String msg){
        super(msg);
        this.code="-1";
    }
    public ServiceException (String code, String msg){
        super(msg);
        this.code=code;
    }

}
