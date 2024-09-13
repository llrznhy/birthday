package com.example.StudentCompetency.service;

import com.example.StudentCompetency.result.Result;
import com.example.StudentCompetency.vo.QuestionnaireVO;

import java.util.ArrayList;


public interface ModuleService {

    ArrayList<String> findQuestionnaireNames();

    Result<QuestionnaireVO> findQuestionnaireByQuestionnaireName(String questionnaireName);
}
