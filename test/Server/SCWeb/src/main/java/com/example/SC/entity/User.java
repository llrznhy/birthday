package com.example.SC.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class User {
    private Integer id;
    private String userName;
    private String password;
    private String email;
    private String salt;
    private String role;
    private String gender;
    private Integer age;
    private byte[] avatar;
}
