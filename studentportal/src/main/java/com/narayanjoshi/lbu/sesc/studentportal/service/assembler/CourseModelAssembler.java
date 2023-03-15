package com.narayanjoshi.lbu.sesc.studentportal.service.assembler;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import com.narayanjoshi.lbu.sesc.studentportal.domain.Course;

@Component
public class CourseModelAssembler implements RepresentationModelAssembler<Course, EntityModel<Course>>{

	@Override
	public EntityModel<Course> toModel(Course entity) {
		// TODO Auto-generated method stub
		return null;
	}

}
