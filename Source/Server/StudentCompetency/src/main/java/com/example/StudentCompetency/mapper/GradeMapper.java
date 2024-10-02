package com.example.StudentCompetency.mapper;

import com.example.StudentCompetency.entity.Grade;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import java.util.List;

@Mapper
public interface GradeMapper {

    // SQL 查询，根据studentId和moduleId获取选项
    @Select("SELECT * FROM grade WHERE studentId = #{studentId} AND questionnaireId = #{questionnaireId}")
    List<Grade> getOptionsByStudentAndModule(@Param("studentId") Long studentId, @Param("questionnaireId") Long questionnaireId);

    @Insert("INSERT INTO grade (studentId, teacherId, questionnaireId, questionId, `optionId`) VALUES (#{studentId}, #{teacherId},#{questionnaireId}, #{questionId}, #{optionId})")
    void insertGrade(Grade grade);

    @Select("SELECT `score` FROM `option` WHERE `id` = #{optionId} AND `optionBelongto` = #{questionId}")
    Integer findScoreByOptionAndQuestion(@Param("optionId") Long optionId, @Param("questionId") Long questionId);

    @Select("SELECT SUM(o.`score`) FROM `grade` m " +
            "JOIN `option` o ON m.`optionId` = o.`id` " +
            "WHERE m.`studentId` = #{studentId} AND m.`questionnaireId` = #{questionnaireId}")
    Integer calculateTotalScore(@Param("studentId") Long studentId, @Param("questionnaireId") Long questionnaireId);


}
