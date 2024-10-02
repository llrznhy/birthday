package com.example.StudentCompetency.controller;

import com.example.StudentCompetency.entity.Option;
import com.example.StudentCompetency.entity.Question;
import com.example.StudentCompetency.entity.Questionnaire;
import com.example.StudentCompetency.result.Result;
import com.example.StudentCompetency.service.ModuleService;
import com.example.StudentCompetency.vo.QuestionnaireVO;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;

@RestController
@RequestMapping(value = "/module")
@CrossOrigin
public class ModuleServiceController {
    @Resource
    private ModuleService moduleService;

    @PostMapping(value = "/addQuestionnaire")
    public void addModule(Questionnaire questionnaire) {
        moduleService.addQuestionnaire(questionnaire);
    }

    @PostMapping(value = "/addQuestion")
    public void addQuestion(Question question) {
        moduleService.addQuestion(question);
    }

    @PostMapping(value = "/addOption")
    public void addOption(Option option) {
        moduleService.addOption(option);
    }

    @PostMapping(value = "/getModuleNames")
    public ArrayList<String> getModuleNames() {
        return moduleService.findQuestionnaireNames();
    }

    @PostMapping(value = "/getModule")
    public Result<QuestionnaireVO> getModule(@RequestBody Questionnaire questionnaire) {
        return moduleService.findQuestionnaireByQuestionnaireName(questionnaire.getQuestionnaireName());
    }

}
