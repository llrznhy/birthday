package com.example.StudentCompetency;

import com.example.StudentCompetency.controller.GradeController;
import com.example.StudentCompetency.controller.ModuleServiceController;
import com.example.StudentCompetency.controller.UserCotroller;
import com.example.StudentCompetency.entity.*;
import com.example.StudentCompetency.result.Result;
import com.example.StudentCompetency.utils.PasswordUtils;
import com.example.StudentCompetency.utils.charCheckUtils;
import com.example.StudentCompetency.utils.hash256Utils;
import com.example.StudentCompetency.vo.QuestionnaireVO;
import com.example.StudentCompetency.vo.UserVO;
import org.apache.catalina.startup.UserConfig;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class StudentCompetencyApplicationTests {
	@Test
	void contextLoads() {
	}

	@Test
	void accountTest(){
		UserCotroller userCotroller = new UserCotroller();
		User user = new User();
		user.setAge(18);
		user.setUserName("User1");
		user.setPassword("123qweASD.");
		user.setRole("student");
		Result<UserVO> result = userCotroller.createUser(user);
		assert(result.getCode() == 0);
		result = userCotroller.loginUser(user);
		assert(result.getCode() == 0);
	}

	@Test
	void gradeTest(){
		// add module
		ModuleServiceController moduleServiceController = new ModuleServiceController();
		Questionnaire questionnaire = new Questionnaire();
		questionnaire.setDescription("");
		questionnaire.setQuestionnaireName("test questionnaire");
		moduleServiceController.addModule(questionnaire);

		Result<QuestionnaireVO> result = moduleServiceController.getModule(questionnaire);

		assert(result.getCode() != 0);

		int moduleId = result.getData().getId();

		// add question
		Question question = new Question();
		question.setQuestionBelongto(moduleId);
		question.setQuestionContext("Q1");
		question.setQuestionCriteria("");
		moduleServiceController.addQuestion(question);

		// add option
		Option option = new Option();
		option.setOptionBelongto(question.getId());
		option.setOptionContext("OptionA");
		option.setOptionDetail("");
		option.setScore(1);
		moduleServiceController.addOption(option);

		// grade
		GradeController gradeController = new GradeController();
		Grade grade = new Grade();
		grade.setStudentId(99L);
		grade.setTeacherId(99L);
		grade.setQuestionnaireId(Long.valueOf(questionnaire.getId()));
		grade.setQuestionId(Long.valueOf(question.getId()));
		grade.setOptionId(Long.valueOf(option.getId()));
		gradeController.submitGrade(grade);

		gradeController.calculateTotalScore(99L, 99L);
		List<Grade> grades = gradeController.getOptionsByStudentAndModule(99L, 99L);

		assert(grades.size() == 1);

		int score = gradeController.calculateTotalScore(99L, Long.valueOf(questionnaire.getId()));
		assert(score == 1);
	}
}
