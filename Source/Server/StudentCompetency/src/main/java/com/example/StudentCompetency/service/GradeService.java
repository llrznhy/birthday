package com.example.StudentCompetency.service;

import com.example.StudentCompetency.entity.Grade;

import java.util.List;

public interface GradeService {

    // 定义根据studentId和moduleId查询选项的方法
    List<Grade> getOptionsByStudentAndModule(Long studentId, Long questionnaireId);

    void saveStudentGrade(Grade grade);

    int calculateTotalScore(Long studentId, Long questionnaireId);

}
