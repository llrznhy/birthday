package com.example.StudentCompetency.entity;

import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
public class Mark {
    private Integer id;
    private Long studentId;
    private Long questionnaireId;
    private Long questionId;
    private String option;

}
