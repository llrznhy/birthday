package com.example.StudentCompetency.mapper;

import com.example.StudentCompetency.entity.Mark;
import org.apache.ibatis.annotations.*;

@Mapper
public interface MarkMapper {

    @Insert("INSERT INTO marking (studentId, questionnaireId, questionId, `option`) VALUES (#{studentId}, #{questionnaireId}, #{questionId}, #{option})")
    void insertGrade(Mark mark);

    @Select("SELECT `score` FROM `option` WHERE `optionContext` = CONCAT('option ', #{option}) AND `optionBelongto` = #{questionId}")
    Integer findScoreByOptionAndQuestion(@Param("option") String option, @Param("questionId") Long questionId);

    @Select("SELECT SUM(o.`score`) FROM `marking` m " +
            "JOIN `option` o ON m.`option` = o.`optionContext` " +
            "AND m.`questionId` = o.`optionBelongto` " +
            "WHERE m.`studentId` = #{studentId} AND m.`questionnaireId` = #{questionnaireId}")
    Integer calculateTotalScore(@Param("studentId") Long studentId, @Param("questionnaireId") Long questionnaireId);


}

