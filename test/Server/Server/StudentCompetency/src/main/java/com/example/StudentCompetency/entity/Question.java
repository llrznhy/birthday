package com.example.StudentCompetency.entity;

import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
public class Question {
    private Integer id;
    private String questionContext;
    private String questionCriteria;
    private int questionBelongto;
}
