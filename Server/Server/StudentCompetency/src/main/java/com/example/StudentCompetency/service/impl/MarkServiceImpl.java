package com.example.StudentCompetency.service.impl;

import com.example.StudentCompetency.entity.Mark;
import com.example.StudentCompetency.mapper.MarkMapper;
import com.example.StudentCompetency.service.MarkService;
import com.example.StudentCompetency.vo.MarkVO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class MarkServiceImpl implements MarkService {

    @Resource
    private MarkMapper markMapper;


    @Override
    public void saveStudentGrade(MarkVO markVO) {
        Mark mark = new Mark();
        mark.setStudentId(markVO.getStudentId());
        mark.setQuestionnaireId(markVO.getQuestionnaireId());
        mark.setQuestionId(markVO.getQuestionId());
        mark.setOption(markVO.getOption());

        markMapper.insertGrade(mark);

        Integer score = markMapper.findScoreByOptionAndQuestion(markVO.getOption(), markVO.getQuestionId());
        System.out.println("Score for this option: " + score);
    }

    @Override
    public int calculateTotalScore(Long studentId, Long questionnaireId) {
        Integer totalScore = markMapper.calculateTotalScore(studentId, questionnaireId);

        if (totalScore == null) {
            return 0;
        }
        return totalScore;
    }
}

