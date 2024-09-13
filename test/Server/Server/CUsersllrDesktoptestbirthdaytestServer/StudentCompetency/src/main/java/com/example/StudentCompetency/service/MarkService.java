package com.example.StudentCompetency.service;

import com.example.StudentCompetency.vo.MarkVO;

public interface MarkService {

    void saveStudentGrade(MarkVO markVO);

    int calculateTotalScore(Long studentId, Long questionnaireId);
}
