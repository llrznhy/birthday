package com.example.StudentCompetency.vo;

import lombok.Data;

import java.util.ArrayList;

@Data
public class QuestionnaireVO {
    //private Integer id;
    private String questionnaireName;
    private ArrayList<QuestionVO> questions;
}
