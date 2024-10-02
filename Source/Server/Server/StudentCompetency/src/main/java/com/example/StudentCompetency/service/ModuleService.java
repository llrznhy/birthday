package com.example.StudentCompetency.service;

import com.example.StudentCompetency.entity.Option;
import com.example.StudentCompetency.entity.Question;
import com.example.StudentCompetency.entity.Questionnaire;
import com.example.StudentCompetency.result.Result;
import com.example.StudentCompetency.vo.QuestionnaireVO;

import java.util.ArrayList;


public interface ModuleService {

    void addQuestionnaire(Questionnaire questionnaire);

    void addQuestion(Question question);

    void addOption(Option option);

    ArrayList<String> findQuestionnaireNames();

    Result<QuestionnaireVO> findQuestionnaireByQuestionnaireName(String questionnaireName);
}
