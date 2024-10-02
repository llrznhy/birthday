package com.example.StudentCompetency.service.impl;

import com.example.StudentCompetency.entity.Class;
import com.example.StudentCompetency.mapper.ClassMapper;
import com.example.StudentCompetency.service.ClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClassServiceImpl implements ClassService {

    @Autowired
    private ClassMapper classMapper;

    @Override
    public List<Class> getAllStudentsInClass() {
        return classMapper.getAllStudentsInClass();
    }

    @Override
    public List<Class> getUnassignedStudents() {
        return classMapper.getUnassignedStudents();
    }

    @Override
    public void addStudentToClass(Long userId) {
        Class student = classMapper.getUserInfoByUserId(userId);

        if (student != null) {
            classMapper.addStudentToClass(student);
        }
    }

    @Override
    public void removeStudentFromClass(Long userId) {
        classMapper.removeStudentFromClass(userId);
    }
}
