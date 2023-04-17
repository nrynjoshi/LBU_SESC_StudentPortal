package com.narayanjoshi.lbu.sesc.studentportal.integrationtest.controller;

import com.narayanjoshi.lbu.sesc.studentportal.doa.StudentRepositoryIfc;
import com.narayanjoshi.lbu.sesc.studentportal.domain.Student;
import com.narayanjoshi.lbu.sesc.studentportal.service.StudentServiceIfc;
import com.narayanjoshi.lbu.sesc.studentportal.utils.AuthenticateUtil;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import javax.servlet.ServletException;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@TestMethodOrder(MethodOrderer.MethodName.class)
public class CourseControllerTest {

    @Autowired
    private MockMvc mvc;


    @Autowired
    private StudentRepositoryIfc studentRepositoryIfc;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @BeforeEach
    public void setup() throws ServletException {

        Student firstStudentSignUp = new Student();
        firstStudentSignUp.setStudentId(6666666);
        firstStudentSignUp.setPassword(passwordEncoder.encode("njoshi"));
        firstStudentSignUp.setEmail("narayan@mailinator.com");
        firstStudentSignUp.setFullname("Narayan Joshi");
        firstStudentSignUp.setUsername("narayanjoshi");
        firstStudentSignUp.setMobileNumber("1111111");
        studentRepositoryIfc.save(firstStudentSignUp);

        Student secondStudentSignUp = new Student();
        secondStudentSignUp.setStudentId(9999999);
        secondStudentSignUp.setPassword(passwordEncoder.encode("njoshi1"));
        secondStudentSignUp.setEmail("narayan@mailinator.com");
        secondStudentSignUp.setFullname("Narayan Joshi");
        secondStudentSignUp.setUsername("narayanjoshi");
        secondStudentSignUp.setMobileNumber("1111111");
        studentRepositoryIfc.save(secondStudentSignUp);

        AuthenticateUtil.getHttpServletRequest().login("narayanjoshi", "njoshi");

    }

    @Test
    public void getCourses() throws Exception {
        mvc.perform(get("/api/v1/courses")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content()
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.course_id").value("COMP637"));
    }
}
