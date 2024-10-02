package com.example.StudentCompetency.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Grade {

    private Long id;
    private Long studentId;
    private Long teacherId;
    private Long questionnaireId;
    private Long questionId;
    private Long optionId;
    //private String optionContext;
}
