package com.example.StudentCompetency.mapper;

import com.example.StudentCompetency.entity.Grade;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import java.util.List;

@Mapper
public interface GradeMapper {

    // SQL 查询，根据studentId和moduleId获取选项
    @Select("SELECT * FROM grade WHERE studentId = #{studentId} AND moduleId = #{moduleId}")
    List<Grade> getOptionsByStudentAndModule(@Param("studentId") Long studentId, @Param("moduleId") Long moduleId);
}
