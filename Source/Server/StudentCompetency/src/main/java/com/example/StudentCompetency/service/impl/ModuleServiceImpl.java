package com.example.StudentCompetency.service.impl;

import com.example.StudentCompetency.entity.Option;
import com.example.StudentCompetency.entity.Question;
import com.example.StudentCompetency.entity.Questionnaire;
import com.example.StudentCompetency.mapper.ModuleMapper;
import com.example.StudentCompetency.result.Result;
import com.example.StudentCompetency.service.ModuleService;
import com.example.StudentCompetency.vo.*;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;

@Service
public class ModuleServiceImpl implements ModuleService {
    @Resource
    private ModuleMapper moduleMapper;

    @Override
    public void addQuestionnaire(Questionnaire questionnaire) {
        moduleMapper.addQuestionnaire(questionnaire);
    }

    @Override
    public void addQuestion(Question question) {
        moduleMapper.addQuestion(question);
    }

    @Override
    public void addOption(Option option) {
        moduleMapper.addOption(option);
    }

    @Override
    public ArrayList<String> findQuestionnaireNames() {
        ArrayList<String> questionnaireNames;
        questionnaireNames = moduleMapper.findQuestionnaireNames();
        return questionnaireNames;
    }

    @Override
    public Result<QuestionnaireVO> findQuestionnaireByQuestionnaireName(String questionnaireName) {
        Questionnaire questionnaire = moduleMapper.findQuestionnaireByQuestionnaireName(questionnaireName);

        if(questionnaire.getQuestionnaireName().isEmpty()){
            return Result.error(401, "inValid questionnaireName");
        }

        ArrayList<Question> questions= moduleMapper.findQuestionsByQuestionnaireID(questionnaire.getId());

        QuestionnaireVO questionnaireVO = new QuestionnaireVO();
        BeanUtils.copyProperties(questionnaire, questionnaireVO);

        ArrayList<QuestionVO> questionVOS = new ArrayList<>();
        questionnaireVO.setQuestions(questionVOS);

        // fill questions
        for(Question question : questions){
            QuestionVO questionVO = new QuestionVO();
            BeanUtils.copyProperties(question, questionVO);
            questionVOS.add(questionVO);

            ArrayList<Option> options = moduleMapper.findOptionsByQuestionID(question.getId());
            ArrayList<OptionVO> optionVOS = new ArrayList<>();
            questionVO.setOptions(optionVOS);

            // fill options
            for(Option option : options){
                OptionVO optionVO = new OptionVO();
                BeanUtils.copyProperties(option, optionVO);
                optionVOS.add(optionVO);
            }
        }

        return Result.success(questionnaireVO);
    }

}
