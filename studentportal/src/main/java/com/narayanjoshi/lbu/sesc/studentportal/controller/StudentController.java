package com.narayanjoshi.lbu.sesc.studentportal.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
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
import com.narayanjoshi.lbu.sesc.studentportal.service.assembler.StudentModelAssembler;
import com.narayanjoshi.lbu.sesc.studentportal.utils.AuthenticateUtil;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;;

@RestController
@RequestMapping(value = Endpoint.ROOT_API_V1 + Endpoint.STUDENT_URI)
public class StudentController {

	private StudentServiceIfc studentServiceIfc;
	
	@Autowired
	public StudentModelAssembler studentModelAssembler;

	StudentController(StudentServiceIfc studentServiceIfc) {
		this.studentServiceIfc = studentServiceIfc;
	}

	@PostMapping("/login")
	public @ResponseBody ResponseEntity loginApi(@RequestBody Student studentLogin) {
		
		if(!AuthenticateUtil.isAuthenticate()) {
			try {
				AuthenticateUtil.getHttpServletRequest().login(studentLogin.getUsername(), studentLogin.getPassword());
			} catch (ServletException e) {
				throw new AuthenticationException("Invalid username or password");
			}
		}
		
		Student student = new Student();
		student.setStudentId(AuthenticateUtil.getStudentId());
		return new ResponseEntity<>(studentModelAssembler.toModel(student), HttpStatus.OK);
	}

	@GetMapping
	public @ResponseBody ResponseEntity getStudent() {
		Student student = studentServiceIfc.getStudentByIdWithoutPassword(AuthenticateUtil.getStudentId());
		student.add(linkTo(methodOn(StudentController.class).updateStudent(new Student())).withRel("update_profile"));
		return new ResponseEntity<>(student, HttpStatus.OK);
	}

	@PostMapping("/register")
	public @ResponseBody ResponseEntity registerStudent(@RequestBody Student studentRegister) {
		studentServiceIfc.createStudent(studentRegister);
		
		Student student = new Student();
		student.add(linkTo(methodOn(StudentController.class).loginApi(new Student())).withRel("login"));
		
		return new ResponseEntity<>(null, HttpStatus.OK);
	}

	@PutMapping
	public @ResponseBody ResponseEntity updateStudent(@RequestBody Student student) {
		studentServiceIfc.updateStudent(student);
		return new ResponseEntity<>(null, HttpStatus.OK);
	}
}
