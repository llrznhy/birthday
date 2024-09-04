package com.example.SC;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.example.SC.mapper")
public class ScWebApplication {

	public static void main(String[] args) {
		System.out.println("project start");
		SpringApplication.run(ScWebApplication.class, args);
	}

}
