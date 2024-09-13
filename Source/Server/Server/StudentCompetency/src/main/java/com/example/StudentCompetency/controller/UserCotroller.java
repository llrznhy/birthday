package com.example.StudentCompetency.controller;

import com.example.StudentCompetency.entity.User;
import com.example.StudentCompetency.result.Result;
import com.example.StudentCompetency.service.UserService;
import com.example.StudentCompetency.vo.UserVO;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/user")
@CrossOrigin
public class UserCotroller {
    @Resource
    private UserService userService;

    // User login
    @PostMapping("/login")
    public Result<UserVO> loginUser(@RequestBody User user) {
        return userService.loginCheck(user.getUserName(), user.getPassword());
    }

    // User registration
    @PostMapping("/register")
    public Result<UserVO> createUser(@RequestBody User user) {
        return userService.creatAccount(user);
    }

}
