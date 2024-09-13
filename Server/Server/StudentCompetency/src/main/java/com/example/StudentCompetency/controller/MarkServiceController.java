package com.example.StudentCompetency.controller;

import com.example.StudentCompetency.service.MarkService;
import com.example.StudentCompetency.vo.MarkVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/grade")
public class MarkServiceController {

    @Autowired
    private MarkService markService;


    @PostMapping("/submit")
    public String submitGrade(@RequestBody MarkVO markVO) {
        markService.saveStudentGrade(markVO);
        return "Grade submitted successfully!";
    }

//    Calculate score based on student id and questionnaire id
    @GetMapping("/calculate/{studentId}/{questionnaireId}")
    public int calculateTotalScore(@PathVariable Long studentId, @PathVariable Long questionnaireId) {
        return markService.calculateTotalScore(studentId, questionnaireId);
    }
}
