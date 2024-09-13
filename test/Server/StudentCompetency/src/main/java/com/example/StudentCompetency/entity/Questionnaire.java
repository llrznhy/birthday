package com.example.StudentCompetency.entity;

import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
public class Questionnaire {
    private Integer id;
    private String questionnaireName;
    private String description;
}
