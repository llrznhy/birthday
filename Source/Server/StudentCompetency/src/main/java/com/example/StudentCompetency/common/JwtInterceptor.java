package com.example.StudentCompetency.common;

import com.example.StudentCompetency.mapper.UserMapper;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class JwtInterceptor implements HandlerInterceptor {

    // Inject UserMapper to retrieve user information
    @Resource
    private UserMapper userMapper;

    // Intercept incoming HTTP requests before they reach the controller
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
//        // Extract the JWT token from the request header or parameter
//        String token = request.getHeader("token");
//        if (token == null) {
//            token = request.getParameter("token");
//        }
//
//        // If the token is not present, throw an exception indicating unauthorized access
//        if (token == null) {
//            throw new ServiceException("401", "Please login");
//        }
//        String userId;
//
//        try {
//            // Decode the token and retrieve the user ID
//            userId = JWT.decode(token).getAudience().get(0);
//        } catch (JWTDecodeException e) {
//            // If there's an issue decoding the token, throw an exception indicating unauthorized access
//            throw new ServiceException("401", "Please login");
//        }
//
//        // Retrieve the user from the database based on the decoded user ID
//        User user = userMapper.findById(Long.valueOf(userId));
//
//        // If the user does not exist, throw an exception indicating unauthorized access
//        if (user == null) {
//            throw new ServiceException("401", "Please login");
//        }
//
//        // Create a JWT verifier using the user's password
//        JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(user.getPassword())).build();
//
//        try {
//            // Verify the token using the JWT verifier
//            jwtVerifier.verify(token);
//        } catch (JWTVerificationException e) {
//            // If there's an issue verifying the token, throw an exception indicating unauthorized access
//            throw new ServiceException("401", "Please login");
//        }

        // If all checks pass, allow the request to proceed
        return true;
    }
}
