package com.example.StudentCompetency;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.example.StudentCompetency.entity")
@ComponentScan("com.example.StudentCompetency.service")
@ComponentScan("com.example.StudentCompetency.controller")
@MapperScan("com.example.StudentCompetency.mapper")
public class StudentCompetencyApplication {

	public static void main(String[] args) {
		System.out.println("project start");
		SpringApplication.run(StudentCompetencyApplication.class, args);
	}

}
