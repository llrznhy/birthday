package com.example.StudentCompetency.vo;

import lombok.Data;

@Data
public class UserVO {
    private Integer id;
    private String userName;
    private String password;
    private String email;
    private String token;
    private String role;
    private String gender;
    private Integer age;
//    private Blob avatarBlob;
    private byte[] avatar;
}
