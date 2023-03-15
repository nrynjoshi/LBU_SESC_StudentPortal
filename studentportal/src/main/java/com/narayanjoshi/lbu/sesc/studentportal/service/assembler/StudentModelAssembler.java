package com.narayanjoshi.lbu.sesc.studentportal.service.assembler;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import com.narayanjoshi.lbu.sesc.studentportal.controller.CourseController;
import com.narayanjoshi.lbu.sesc.studentportal.controller.EnrollmentController;
import com.narayanjoshi.lbu.sesc.studentportal.controller.StudentController;
import com.narayanjoshi.lbu.sesc.studentportal.domain.Student;

@Component
public class StudentModelAssembler implements RepresentationModelAssembler<Student, EntityModel<Student>>{

	@Override
	public EntityModel<Student> toModel(Student student) {
		EntityModel<Student> enityModel = EntityModel.of(student);
		enityModel.add(linkTo(methodOn(StudentController.class).getStudent()).withRel("get_profile"));
		enityModel.add(linkTo(methodOn(StudentController.class).updateStudent(new Student())).withRel("update_profile"));
		enityModel.add(linkTo(methodOn(EnrollmentController.class).getEnrollments()).withRel("my_enrollments"));
		enityModel.add(linkTo(methodOn(CourseController.class).getCourses()).withRel("courses"));
		return enityModel;
	}

}