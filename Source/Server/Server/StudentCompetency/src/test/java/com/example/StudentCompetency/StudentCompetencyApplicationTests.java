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
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import static org.hamcrest.Matchers.isIn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import java.util.List;

@SpringBootTest
@AutoConfigureMockMvc
class StudentCompetencyApplicationTests {
	@Autowired
    private MockMvc mockMvc;

	 @Test
    public void testGetStudents() throws Exception {
		mockMvc.perform(get("/user/getStudents")) // 这里调用你的 Controller 接口
                .andExpect(status().isOk()) // 断言返回状态码是 200 OK
                .andExpect(jsonPath("$[-1].userName").exists()); // 校验返回的 JSON 内容
    }

    @Test
    public void testLoginUser() throws Exception {
		String registerRequestBody = "{ \"userName\": \"newUser\", \"password\": \"newPassword1\", \"email\": \"user@example.com\", \"role\": \"a\", \"age\": 25, \"salt\": 1 }";

        String loginRequestBody = "{ \"userName\": \"newUser\", \"password\": \"newPassword1\" }";

        mockMvc.perform(post("/user/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(registerRequestBody))
                .andExpect(status().isOk())  // 期望返回状态码200
                .andExpect(jsonPath("$.message").value(isIn(new String[]{"User Name exsist", "success"})));


        mockMvc.perform(post("/user/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(loginRequestBody))
                .andExpect(status().isOk())  // 期望返回状态码200
                .andExpect(jsonPath("$.message").value("success"))  // 假设返回结果有success字段，值为true
                .andExpect(jsonPath("$.data.userName").value("newuser"));  // 验证返回的用户信息
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
