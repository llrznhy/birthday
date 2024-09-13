package com.example.StudentCompetency.service.impl;
import com.example.StudentCompetency.entity.Grade;
import com.example.StudentCompetency.mapper.GradeMapper;
import com.example.StudentCompetency.service.GradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class GradeServiceImpl implements GradeService {

    @Autowired
    private GradeMapper gradeMapper;

    // 实现接口中的方法，调用Mapper层查询
    @Override
    public List<Grade> getOptionsByStudentAndModule(Long studentId, Long moduleId) {
        return gradeMapper.getOptionsByStudentAndModule(studentId, moduleId);
    }
}