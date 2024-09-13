package com.example.StudentCompetency.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Grade {

    private Long id;           // 主键ID
    private Long studentId;     // 学生ID
    private Long moduleId;      // 课程ID
    private int optionID;
    private String Optioncontext;  // 分数
}
