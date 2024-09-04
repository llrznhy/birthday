package com.example.SC.controller;

import com.example.SC.entity.User;
import com.example.SC.result.Result;
import com.example.SC.result.ResultMsgEnum;
import com.example.SC.service.UserService;
import com.example.SC.utils.TokenUtils;
import com.example.SC.vo.UserVO;
import org.apache.commons.mail.EmailException;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.UUID;

@RestController
@RequestMapping("/user")
@CrossOrigin
public class UserController {
    @Resource
    private UserService userService;

    // Get all users
    @GetMapping("/all")
    public Result<ArrayList> getUserList() {
        ArrayList<UserVO> voList = new ArrayList<>();
        userService.findAll().forEach(user -> {
            UserVO userVO = new UserVO();
            BeanUtils.copyProperties(user, userVO);
            voList.add(userVO);
        });
        return Result.success(voList);
    }

    // Get user by ID
    @GetMapping("/id={id}")
    public Result<UserVO> getUserById(@PathVariable("id") Long id) {
        User user = userService.findById(id);
        UserVO userVO = new UserVO();
        BeanUtils.copyProperties(user, userVO);

        return Result.success(userVO);
    }
    @PostMapping("/getCurrentUser")
    public Result<UserVO> postUserById() {
        User currentUser = TokenUtils.getCurrentUser();
        Long id = Long.valueOf(currentUser.getId());
        User user = userService.findById(id);
        UserVO userVO = new UserVO();
        BeanUtils.copyProperties(user, userVO);

        return Result.success(userVO);
    }
    @GetMapping("/getAvatar={id}")
    public Result<String> getAvatar(@PathVariable("id") Long id){
        User user = userService.findById(id);
        if(user.getAvatar()!=null){
            String base64String = Base64.getEncoder().encodeToString(user.getAvatar());
            return Result.success(base64String);
        }else return Result.success(null);

    }

    // Get user by username
    @GetMapping("/userName={userName}")
    public Result<UserVO> getUserByUserName(@PathVariable("userName") String userName) {
        User user = userService.findByUserName(userName);
        UserVO userVO = new UserVO();
        BeanUtils.copyProperties(user, userVO);
        return Result.success(userVO);
    }

    // Add a new user
    @PostMapping("/import")
    public String addUser(@RequestBody User user) {
        userService.save(user);
        return "success";
    }

    // Update user information
    @PutMapping("/update")
    public String updateUser(@RequestParam(value = "avatar", required = false) MultipartFile Multavatar,
                             @RequestParam(value = "age", required = false) Integer age,
                             @RequestParam(value = "gender", required = false) String gender,
                             @RequestParam(value = "email", required = false) String email) {
        User user = TokenUtils.getCurrentUser();
        assert user != null;

        if (Multavatar != null) {
            try {
                byte[] avatar = Multavatar.getBytes();
                user.setAvatar(avatar);
            } catch (IOException e) {
                throw new RuntimeException("Error processing avatar file", e);
            }
        }

        if (age != null) {
            user.setAge(age);
        }

        if (gender != null) {
            user.setGender(gender);
        }

        if (email != null) {
            user.setEmail(email);
        }

        userService.updateById(user);
        return "success";
    }


    // Delete user by ID
    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable("id") Long id) {
        userService.deleteById(id);
        return "success";
    }

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

    // Check Users friend list
    @GetMapping("/getFriendList")
    public Result<ArrayList> getFriendList() {

        User currentUser = TokenUtils.getCurrentUser();

        ArrayList<UserVO> voList = new ArrayList<>();
        ArrayList<User> list= userService.getFriendList(currentUser);
        if(!list.isEmpty()){
            list.forEach(user -> {
                UserVO userVO = new UserVO();
                BeanUtils.copyProperties(user, userVO);
                voList.add(userVO);
            });
            return Result.success(voList);
        }
        else {
            return Result.error(ResultMsgEnum.FRIEND_LIST_EMPTY.getCode(), ResultMsgEnum.FRIEND_LIST_EMPTY.getMessage());
        }
    }

    // Search friend by name or id
    @PostMapping("/searchFriend={searchStr}")
    public Result<ArrayList> searchFriend(@PathVariable("searchStr") String str) {

        User currentUser = TokenUtils.getCurrentUser();

        ArrayList<UserVO> voList = new ArrayList<>();
        ArrayList<User> list=userService.searchFriend(currentUser,str);
        if (list.isEmpty()){
            return Result.error(ResultMsgEnum.NO_USER_FOUND.getCode(),ResultMsgEnum.NO_USER_FOUND.getMessage());
        }
        list.forEach(user -> {
            UserVO userVO = new UserVO();
            BeanUtils.copyProperties(user, userVO);
            voList.add(userVO);
        });
        return Result.success(voList);
    }

    @GetMapping("/getFriendRequest")
    public Result<ArrayList> getFriendRequest() {

        User currentUser = TokenUtils.getCurrentUser();

        ArrayList<UserVO> voList = new ArrayList<>();
        userService.getFriendRequest(currentUser).forEach(user -> {
            UserVO userVO = new UserVO();
            BeanUtils.copyProperties(user, userVO);
            voList.add(userVO);
        });
        return Result.success(voList);
    }

    @PostMapping("/sendFriendRequest")
    public Result<UserVO> sendFriendRequest(@RequestBody User user){
        User currentUser = TokenUtils.getCurrentUser();
        return userService.sendFriendRequest(currentUser,user);
    }

    // Add two user as friends
    @PostMapping("/addFriend")
    public Result<UserVO> addFriend(@RequestBody User user) {
        User currentUser = TokenUtils.getCurrentUser();
        return userService.addFriend(currentUser,user);
    }
    @PostMapping("/rejestFriend")
    public Result<UserVO> rejestFriend(@RequestBody User user) {
        User currentUser = TokenUtils.getCurrentUser();
        return userService.rejestFriend(currentUser,user);
    }

    @PostMapping("/deleteFriend")
    public Result<UserVO> deleteFriend(@RequestBody User user) {
        User currentUser = TokenUtils.getCurrentUser();
        return userService.deleteFriend(currentUser,user);
    }
    @PostMapping("/sendVerificationCode")
    public Result<String> sendVerificationCode(@RequestBody User user){
        String email = user.getEmail();

        if (email==null){
            return Result.error(ResultMsgEnum.FAIL.getCode(), ResultMsgEnum.FAIL.getMessage());
        }else{
            UUID uuid = UUID.randomUUID();
            String verificationCode = uuid.toString().replaceAll("-", "");
            verificationCode = verificationCode.substring(0, 6);
            try {
                userService.sendVerificationCode(email,verificationCode);
            } catch (EmailException e) {
                throw new RuntimeException(e);
            }
            return Result.success(verificationCode);
        }
    }

    // Test endpoint
    @RequestMapping("/test")
    public String test() {
        return "test";
    }


}
