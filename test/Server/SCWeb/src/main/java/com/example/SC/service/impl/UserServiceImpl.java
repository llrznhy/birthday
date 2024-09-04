package com.example.SC.service.impl;

import com.example.SC.entity.User;
import com.example.SC.mapper.UserMapper;
import com.example.SC.result.Result;
import com.example.SC.result.ResultMsgEnum;
import com.example.SC.service.UserService;
import com.example.SC.utils.PasswordUtils;
import com.example.SC.utils.TokenUtils;
import com.example.SC.utils.charCheckUtils;
import com.example.SC.utils.hash256Utils;
import com.example.SC.vo.UserVO;
import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Objects;

@Service
public class UserServiceImpl implements UserService {
    @Resource
    private UserMapper userMapper;

    // Get all users
    public ArrayList<User> findAll() {
        return userMapper.findAll();
    }

    // Save a new user
    public void save(User user) {
        userMapper.save(user);
    }

    // Update user by ID
    public void updateById(User user) {
        userMapper.updateById(user);
    }

    // Delete user by ID
    public void deleteById(Long id) {
        userMapper.deletebyId(id);
    }

    // Find user by ID
    public User findById(Long id) {
        return userMapper.findById(id);
    }

    // Find user by username
    public User findByUserName(String userName) {
        return userMapper.findByUserName(userName);
    }

    // Create a new user account
    public Result<UserVO> creatAccount(User user) {
        if (!charCheckUtils.userNameCharCheck(user.getUserName())) {
            return Result.error(ResultMsgEnum.REGISTER_USERNAME_ERROR.getCode(), ResultMsgEnum.REGISTER_USERNAME_ERROR.getMessage());
        } else if (!charCheckUtils.pwsCharCheck(user.getPassword())) {
            return Result.error(ResultMsgEnum.REGISTER_PASSWORD_ERROR.getCode(), ResultMsgEnum.REGISTER_PASSWORD_ERROR.getMessage());
        } else {
            User userCheck = findByUserName(user.getUserName());
            if (userCheck == null) {
                String salt = PasswordUtils.getSalt();
                user.setUserName(user.getUserName().toLowerCase());
                user.setPassword(hash256Utils.encodeSHA256(user.getPassword() + salt));
                user.setSalt(salt);
                save(user);
                User newUser = findByUserName(user.getUserName());
                UserVO userVO = new UserVO();
                BeanUtils.copyProperties(newUser, userVO);
                return Result.success(userVO);
            } else {
                return Result.error(ResultMsgEnum.REGISTER_USERNAME_EXSIST.getCode(), ResultMsgEnum.REGISTER_USERNAME_EXSIST.getMessage());
            }
        }
    }

    // Check user login credentials
    public Result<UserVO> loginCheck(String userName, String password) {
        User user = findByUserName(userName);
        if (user != null) {
            String salt = user.getSalt();
            String encodedPassword = hash256Utils.encodeSHA256(password + salt);
            if (encodedPassword.equals(user.getPassword())) {
                UserVO userVO = new UserVO();
                BeanUtils.copyProperties(user, userVO);

                String token = TokenUtils.createToken(String.valueOf(user.getId()), encodedPassword);
                userVO.setToken(token);

                return Result.success(userVO);
            } else {
                return Result.error(ResultMsgEnum.LOGIN_PASSWORD_ERROR.getCode(), ResultMsgEnum.LOGIN_PASSWORD_ERROR.getMessage());
            }
        } else
            return Result.error(ResultMsgEnum.LOGIN_USERNAME_ERROR.getCode(), ResultMsgEnum.LOGIN_USERNAME_ERROR.getMessage());
    }

    // Get User's friend list

    public ArrayList<User> getFriendList(User currentUser){
        ArrayList<Integer> list = userMapper.getFriendList(Long.valueOf(currentUser.getId()));
        ArrayList<User> UserList = new ArrayList<>();
        for (Integer id:list){
            User user = findById(Long.valueOf(id));
            UserList.add(user);
        }
        return UserList;
    }

    public ArrayList<User> searchFriend(User currentUser,String str){
        if(currentUser.getRole().equals("a")){
            return userMapper.searchFriendA(Long.valueOf(currentUser.getId()),str);
        }
        if (currentUser.getRole().equals("b")){
            return userMapper.searchFriendB(Long.valueOf(currentUser.getId()),str);
        }else return null;
    }

    public Result<UserVO> addFriend(User currentUser,User user){
        
        if (currentUser.getId()==user.getId()){
            return Result.error(ResultMsgEnum.ADD_SELF_ERROR.getCode(),ResultMsgEnum.ADD_SELF_ERROR.getMessage());
        }

        ArrayList<Integer> list=userMapper.getFriendList(Long.valueOf(currentUser.getId()));
        for(Integer friend:list){
            if (friend==user.getId()){
                userMapper.deleteFriendRequest(Long.valueOf(currentUser.getId()),Long.valueOf(user.getId()));
                return Result.error(ResultMsgEnum.ADD_FRIEND_ERROR.getCode(),ResultMsgEnum.ADD_FRIEND_ERROR.getMessage());
            }
        }
        userMapper.addFriend(Long.valueOf(currentUser.getId()),Long.valueOf(user.getId()));
        userMapper.antiAddFriend(Long.valueOf(currentUser.getId()),Long.valueOf(user.getId()));
        userMapper.deleteFriendRequest(Long.valueOf(currentUser.getId()),Long.valueOf(user.getId()));
        UserVO userVO = new UserVO();
        BeanUtils.copyProperties(user, userVO);
        return Result.success(userVO);
    }

    public Result<UserVO> sendFriendRequest(User currentUser,User user){

        if (currentUser.getId()==user.getId()){
            return Result.error(ResultMsgEnum.ADD_SELF_ERROR.getCode(),ResultMsgEnum.ADD_SELF_ERROR.getMessage());
        }

        ArrayList<Integer> list=userMapper.getFriendList(Long.valueOf(currentUser.getId()));
        if(list.size()>=50){
            return Result.error(ResultMsgEnum.MAX_FRIEND_ERROR.getCode(), ResultMsgEnum.MAX_FRIEND_ERROR.getMessage());
        }
        ArrayList<Long> RequestList=userMapper.checkFriendRequest(Long.valueOf(currentUser.getId()),Long.valueOf(user.getId()));
        if(!RequestList.isEmpty()){
            return Result.error(ResultMsgEnum.REQUEST_EXIST_ERROR.getCode(),ResultMsgEnum.REQUEST_EXIST_ERROR.getMessage());
        }
        for(Integer friend:list){
            if (Objects.equals(friend, user.getId())){
                return Result.error(ResultMsgEnum.ADD_FRIEND_ERROR.getCode(),ResultMsgEnum.ADD_FRIEND_ERROR.getMessage());
            }
        }
        userMapper.friendRequest(Long.valueOf(currentUser.getId()),Long.valueOf(user.getId()));
        UserVO userVO = new UserVO();
        BeanUtils.copyProperties(user, userVO);
        return Result.success(userVO);
    }
    public ArrayList<User> getFriendRequest(User currentUser){
        ArrayList<Long> list = userMapper.getFriendRequest(Long.valueOf(currentUser.getId()));
        ArrayList<User> UserList = new ArrayList<>();
        for (Long id:list){
            User user = findById(id);
            UserList.add(user);
        }
        return UserList;
    }
    public Result<UserVO> deleteFriend(User currentUser, User user){
        ArrayList<User> friendList = getFriendList(currentUser);
        for (User friend:friendList) {
            if (friend.getId() == user.getId()) {
                userMapper.deleteFriend(Long.valueOf(currentUser.getId()), Long.valueOf(user.getId()));
                userMapper.antiAeleteFriend(Long.valueOf(currentUser.getId()), Long.valueOf(user.getId()));
                UserVO userVO = new UserVO();
                BeanUtils.copyProperties(user, userVO);
                return Result.success(userVO);
            }
        }
            return Result.error(ResultMsgEnum.DELETE_FRIEND_ERROR.getCode(),ResultMsgEnum.DELETE_FRIEND_ERROR.getMessage());
    }

    public Result<UserVO> rejestFriend(User currentUser, User user){
        if (currentUser.getId()==user.getId()){
            return Result.error(ResultMsgEnum.ADD_SELF_ERROR.getCode(),ResultMsgEnum.ADD_SELF_ERROR.getMessage());
        }
        userMapper.deleteFriendRequest(Long.valueOf(currentUser.getId()),Long.valueOf(user.getId()));
        UserVO userVO = new UserVO();
        BeanUtils.copyProperties(user, userVO);
        return Result.success(userVO);
    }

    public void sendVerificationCode(String emailAddress, String verificationCode) throws EmailException {
        HtmlEmail email = new HtmlEmail();
        email.setHostName("smtp.gmail.com");
        email.setSmtpPort(587);
        email.setAuthenticator(new DefaultAuthenticator("your-email@gmail.com", "your-password"));
        email.setStartTLSEnabled(true);

        email.setFrom("your-email@gmail.com", "Your Name");
        email.addTo(emailAddress);
        email.setSubject("Verification Code");


        email.setHtmlMsg("<p>Your verification code is: " + verificationCode + "</p>");

        email.send();
    }
}
