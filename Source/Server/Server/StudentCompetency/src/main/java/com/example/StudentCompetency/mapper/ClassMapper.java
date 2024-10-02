package com.example.StudentCompetency.mapper;

import com.example.StudentCompetency.entity.Class;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ClassMapper {

    // Get information about students in the class table.
    @Select("SELECT * FROM `class`")
    List<Class> getAllStudentsInClass();

    // Get information about students in the f_user table who are not in the class table.
    @Select("SELECT `id` as userId, `userName`as username , `age` " +
            "FROM `f_user` " +
            "WHERE `role` = 'a' AND `id` NOT IN (SELECT `userId` FROM `class`)")
    List<Class> getUnassignedStudents();

    @Select("SELECT `id` as userId, `userName` as username, `age` " +
            "FROM `f_user` WHERE `id` = #{userId}")
    Class getUserInfoByUserId(@Param("userId") Long userId);

    //add student
    @Insert("INSERT INTO `class` (`classId`, `userId`, `username`, `age`) " +
            "VALUES (1, #{userId}, #{username}, #{age})")
    void addStudentToClass(Class student);

    // remove student
    @Delete("DELETE FROM `class` WHERE `userId` = #{userId}")
    void removeStudentFromClass(@Param("userId") Long userId);
}
