package com.example.StudentCompetency.service;

import com.example.StudentCompetency.entity.Class;

import java.util.List;

public interface ClassService {

    List<Class> getAllStudentsInClass();

    List<Class> getUnassignedStudents();

    void addStudentToClass(Long userId);

    void removeStudentFromClass(Long userId);
}
