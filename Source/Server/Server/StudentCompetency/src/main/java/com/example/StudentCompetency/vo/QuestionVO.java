package com.example.StudentCompetency.vo;

import lombok.Data;

import java.util.ArrayList;

@Data
public class QuestionVO {
    private Integer id;
    private String questionContext;
    private String questionCriteria;
    private ArrayList<OptionVO> options;
}
