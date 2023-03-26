package com.narayanjoshi.lbu.sesc.studentportal.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;

import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.narayanjoshi.lbu.sesc.studentportal.constant.Endpoint;
import com.narayanjoshi.lbu.sesc.studentportal.constant.KeyConstant;
import com.narayanjoshi.lbu.sesc.studentportal.domain.Enroll;
import com.narayanjoshi.lbu.sesc.studentportal.exception.CourseNotFoundException;
import com.narayanjoshi.lbu.sesc.studentportal.exception.UserAlreadyEnrollIntoCourseException;
import com.narayanjoshi.lbu.sesc.studentportal.service.EnrollServiceIfc;

@RestController
@RequestMapping(value = Endpoint.ROOT_API_V1 + Endpoint.ENROLLMENT_URI)
public class EnrollmentController {

	private EnrollServiceIfc enrollServiceIfc;

	EnrollmentController(EnrollServiceIfc enrollServiceIfc) {
		this.enrollServiceIfc = enrollServiceIfc;
	}

	@GetMapping
	public @ResponseBody ResponseEntity<CollectionModel<Enroll>> getEnrollments() {

		List<Enroll> enrolCourses = enrollServiceIfc.getEnrolCourses();
		CollectionModel<Enroll> collectionModel = CollectionModel.of(enrolCourses);
		collectionModel.add(linkTo(methodOn(EnrollmentController.class).getEnrollments()).withSelfRel());

		return ResponseEntity.status(HttpStatus.OK).body(collectionModel);
	}

	@PostMapping
	public @ResponseBody ResponseEntity enrollIntoCourse(@RequestParam(KeyConstant.COURSE_ID) String courseId) throws CourseNotFoundException, UserAlreadyEnrollIntoCourseException {
		enrollServiceIfc.enrolIntoCourse(courseId);
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}

}
