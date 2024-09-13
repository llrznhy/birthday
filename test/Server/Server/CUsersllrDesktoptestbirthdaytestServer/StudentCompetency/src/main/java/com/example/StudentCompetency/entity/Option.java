package com.example.StudentCompetency.entity;

import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
public class Option {
    private Integer id;
    private String optionContext;
    private String optionDetail;
    private int optionBelongto;
    private int score;
}
