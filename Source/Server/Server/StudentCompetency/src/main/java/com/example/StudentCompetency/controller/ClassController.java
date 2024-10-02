package com.example.StudentCompetency.controller;

import com.example.StudentCompetency.entity.Class;
import com.example.StudentCompetency.result.Result;
import com.example.StudentCompetency.service.ClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/class")
public class ClassController {

    @Autowired
    private ClassService classService;

    @GetMapping("/all")
    public List<Class> getAllStudentsInClass() {
        return classService.getAllStudentsInClass();
    }

    @GetMapping("/unassigned")
    public List<Class> getUnassignedStudents() {
        return classService.getUnassignedStudents();
    }

    @PostMapping("/add/{userId}")
    public String addStudentToClass(@PathVariable Long userId) {
        classService.addStudentToClass(userId);
        return "Student added successfully!";
    }

    @DeleteMapping("/remove/{userId}")
    public String removeStudentFromClass(@PathVariable Long userId) {
        classService.removeStudentFromClass(userId);
        return "Student removed successfully!";
    }



}
