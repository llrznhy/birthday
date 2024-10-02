package com.example.StudentCompetency.mapper;

import com.example.StudentCompetency.entity.Question;
import com.example.StudentCompetency.entity.Questionnaire;
import com.example.StudentCompetency.entity.Option;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

import java.util.ArrayList;


public interface ModuleMapper {
    @Insert("INSERT INTO questionnaireName (questionnaireName, description) VALUES (#{questionnaireName}, #{description})")
    @Options(useGeneratedKeys = true, keyColumn = "id", keyProperty = "id")
    void addQuestionnaire(Questionnaire questionnaire);

    @Insert("INSERT INTO question (questionContext, questionCriteria, questionBelongto) VALUES (#{questionContext}, #{questionCriteria},#{questionBelongto})")
    @Options(useGeneratedKeys = true, keyColumn = "id", keyProperty = "id")
    void addQuestion(Question question);

    @Insert("INSERT INTO option (optionContext, optionDetail, score) VALUES (#{optionContext}, #{optionDetail},#{score})")
    @Options(useGeneratedKeys = true, keyColumn = "id", keyProperty = "id")
    void addOption(Option option);

    @Select("SELECT questionnaireName FROM `questionnaire`")
    ArrayList<String>  findQuestionnaireNames();

    @Select("SELECT* FROM `questionnaire` WHERE questionnaireName=#{questionnaireName}")
    Questionnaire findQuestionnaireByQuestionnaireName(String questionnaireName);

    @Select("SELECT* FROM `question` WHERE questionBelongto=#{questionnaireID}")
    ArrayList<Question> findQuestionsByQuestionnaireID(int questionnaireID);

    @Select("SELECT* FROM `option` WHERE optionBelongto=#{QuestionID}")
    ArrayList<Option> findOptionsByQuestionID(int QuestionID);
}
