package com.example.StudentCompetency;

import com.example.StudentCompetency.controller.UserCotroller;
import com.example.StudentCompetency.utils.PasswordUtils;
import com.example.StudentCompetency.utils.charCheckUtils;
import com.example.StudentCompetency.utils.hash256Utils;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class StudentCompetencyApplicationTests {
	@Test
	void contextLoads() {
	}
	@Test
	void passwordTest(){
		String salt = PasswordUtils.getSalt();
		System.out.println(salt);
	}
	@Test
	void pwsCharCheckTest(){
		System.out.println(charCheckUtils.pwsCharCheck("Az12345678"));
	}
	@Test
	void usernameCharTest(){
		System.out.println(charCheckUtils.userNameCharCheck("UserA"));
	}
	@Test
	void hash256Test(){
		System.out.println(hash256Utils.encodeSHA256("123456"));
	}
//	@Test
//	void picTest ()throws IOException {
//		String s = VideoUtils.fetchFrame("/Users/qiaoxinying/Desktop/video2/public/movie.mp4");
//
//		VideoUtils.base64ToMultipart(s).transferTo(new File("/Users/qiaoxinying/Desktop/video2/public/666.jpg"));
//	}
}
