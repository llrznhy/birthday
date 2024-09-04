package com.example.SC.service;

import com.example.SC.entity.User;
import com.example.SC.result.Result;
import com.example.SC.vo.UserVO;
import org.apache.commons.mail.EmailException;

import java.util.ArrayList;


public interface UserService {
    ArrayList<User> findAll();
    void save(User user);

    void updateById(User user);

    void deleteById(Long id);

    User findById(Long id);

    User findByUserName(String userName);

    Result<UserVO> loginCheck(String userName, String password);

    Result<UserVO> creatAccount(User user);

    ArrayList<User> getFriendList(User currentUser);

    ArrayList<User> searchFriend(User currentUser,String str);

    Result<UserVO> addFriend(User currentUser, User user);

    Result<UserVO> sendFriendRequest(User currentUser, User user);

    ArrayList<User> getFriendRequest(User currentUser);

    Result<UserVO> deleteFriend(User currentUser, User user);

    Result<UserVO> rejestFriend(User currentUser, User user);

    void sendVerificationCode(String emailAddress, String verificationCode) throws EmailException;
}
