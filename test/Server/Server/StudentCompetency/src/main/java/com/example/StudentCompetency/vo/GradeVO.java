package com.example.StudentCompetency.vo;


import lombok.Data;
public class GradeVO {
    private Long id;           // 主键ID
    private Long studentId;     // 学生ID
    private Long moduleId;      // 课程ID
    private int optionID;
    private String Optioncontext;       // 分数

}
