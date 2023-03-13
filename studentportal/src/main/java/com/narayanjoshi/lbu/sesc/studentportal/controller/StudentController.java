package com.narayanjoshi.lbu.sesc.studentportal.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.narayanjoshi.lbu.sesc.studentportal.constant.Endpoint;
import com.narayanjoshi.lbu.sesc.studentportal.domain.Student;
import com.narayanjoshi.lbu.sesc.studentportal.exception.AuthenticationException;
import com.narayanjoshi.lbu.sesc.studentportal.service.StudentServiceIfc;
import com.narayanjoshi.lbu.sesc.studentportal.utils.AuthenticateUtil;

@RestController
@RequestMapping(value = Endpoint.ROOT_API_V1 + Endpoint.STUDENT_URI)
public class StudentController {

	private StudentServiceIfc studentServiceIfc;

	StudentController(StudentServiceIfc studentServiceIfc) {
		this.studentServiceIfc = studentServiceIfc;
	}

	@PostMapping("/login")
	public @ResponseBody ResponseEntity loginApi(@RequestBody Student student, HttpServletRequest request) {
		
		if(!AuthenticateUtil.isAuthenticate()) {
			try {
				request.login(student.getUsername(), student.getPassword());
			} catch (ServletException e) {
				throw new AuthenticationException("Invalid username or password");
			}
		}
		
		Map<String, Object> responseMap = new HashMap<>();
		responseMap.put("student_id", AuthenticateUtil.getStudentId());
		return new ResponseEntity<>(responseMap, HttpStatus.OK);
	}

	@GetMapping
	public @ResponseBody ResponseEntity getStudent() {
		Student student = studentServiceIfc.getStudentByIdWithoutPassword(AuthenticateUtil.getStudentId());
		return new ResponseEntity<>(student, HttpStatus.OK);
	}

	@PostMapping("/register")
	public @ResponseBody ResponseEntity registerStudent(@RequestBody Student student) {
		studentServiceIfc.createStudent(student);
		return new ResponseEntity<>(null, HttpStatus.OK);
	}

	@PutMapping
	public @ResponseBody ResponseEntity updateStudent(@RequestBody Student student) {
		studentServiceIfc.updateStudent(student);
		return new ResponseEntity<>(null, HttpStatus.OK);
	}
}
