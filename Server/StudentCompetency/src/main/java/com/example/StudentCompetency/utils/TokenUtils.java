package com.example.StudentCompetency.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.example.StudentCompetency.entity.User;
import com.example.StudentCompetency.mapper.UserMapper;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Calendar;

@Component
public class TokenUtils {
    // Static reference to UserMapper for accessing it in static methods
    private static UserMapper staticUserMapper;

    // Instance variable for non-static methods
    @Resource
    private UserMapper userMapper;

    // Initialize staticUserMapper with the injected UserMapper instance
    @PostConstruct
    public void setUserService() {
        staticUserMapper = userMapper;
    }

    // Create a JWT token with the given user ID and expiration time
    public static String createToken(String userId, String sign) {
        Calendar instance = Calendar.getInstance();
        instance.add(Calendar.HOUR, 8);
        return JWT.create().withAudience(userId)
                .withExpiresAt(instance.getTime())
                .sign(Algorithm.HMAC256(sign));
    }

    // Get the current user based on the JWT token in the request header
    public static User getCurrentUser() {
        try {
            // Get the current HTTP request
            HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();

            // Extract the token from the request header
            String token = request.getHeader("token");

            // If the token exists, decode it and retrieve the user ID
            if (token != null) {
                Long userId = Long.valueOf(JWT.decode(token).getAudience().get(0));

                // Use the staticUserMapper to find and return the user by ID
                return staticUserMapper.findById(userId);
            }
        } catch (Exception e) {
            // Handle exceptions, if any
            return null;
        }
        return null;
    }
}
