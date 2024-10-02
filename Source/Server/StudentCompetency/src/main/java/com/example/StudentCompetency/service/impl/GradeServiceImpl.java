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
    public List<Grade> getOptionsByStudentAndModule(Long studentId, Long questionnaireId) {
        return gradeMapper.getOptionsByStudentAndModule(studentId, questionnaireId);
    }

    @Override
    public void saveStudentGrade(Grade grade) {
        //GradeVO gradeVO = new GradeVO();
        //BeanUtils.copyProperties(grade, gradeVO);
        //mark.setStudentId(grade.getStudentId());
        //mark.setQuestionnaireId(grade.getModuleId());
        //mark.setQuestionId(grade.getQuestionId());
        //mark.setOption(grade.getOptioncontext());

        gradeMapper.insertGrade(grade);

        Integer score = gradeMapper.findScoreByOptionAndQuestion(grade.getOptionId(), grade.getQuestionId());
        System.out.println("Score for this option: " + score);
    }

    @Override
    public int calculateTotalScore(Long studentId, Long questionnaireId) {
        Integer totalScore = gradeMapper.calculateTotalScore(studentId, questionnaireId);

        if (totalScore == null) {
            return 0;
        }
        return totalScore;
    }
}