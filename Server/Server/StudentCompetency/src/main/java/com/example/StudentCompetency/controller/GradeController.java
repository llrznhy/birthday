package com.example.StudentCompetency.controller;

import com.example.StudentCompetency.entity.Grade;
import com.example.StudentCompetency.service.GradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/grades")
public class GradeController {

    @Autowired
    private GradeService gradeService;

    // 前端通过传递studentId和moduleId查询相关选项
    @GetMapping("/options")
    public List<Grade> getOptionsByStudentAndModule(@RequestParam Long studentId, @RequestParam Long moduleId) {
        return gradeService.getOptionsByStudentAndModule(studentId, moduleId);
    }
}
