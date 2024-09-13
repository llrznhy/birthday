package com.example.StudentCompetency.mapper;

import com.example.StudentCompetency.entity.Question;
import com.example.StudentCompetency.entity.Questionnaire;
import com.example.StudentCompetency.entity.Option;
import org.apache.ibatis.annotations.Select;

import java.util.ArrayList;


public interface ModuleMapper {
    @Select("SELECT questionnaireName FROM `questionnaire`")
    ArrayList<String>  findQuestionnaireNames();

    @Select("SELECT* FROM `questionnaire` WHERE questionnaireName=#{questionnaireName}")
    Questionnaire findQuestionnaireByQuestionnaireName(String questionnaireName);

    @Select("SELECT* FROM `question` WHERE questionBelongto=#{questionnaireID}")
    ArrayList<Question> findQuestionsByQuestionnaireID(int questionnaireID);

    @Select("SELECT* FROM `option` WHERE optionBelongto=#{QuestionID}")
    ArrayList<Option> findOptionsByQuestionID(int QuestionID);
}
