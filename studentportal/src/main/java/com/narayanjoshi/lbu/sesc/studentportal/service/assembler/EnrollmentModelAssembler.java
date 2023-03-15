package com.narayanjoshi.lbu.sesc.studentportal.service.assembler;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import com.narayanjoshi.lbu.sesc.studentportal.domain.Course;
import com.narayanjoshi.lbu.sesc.studentportal.domain.Enroll;

@Component
public class EnrollmentModelAssembler implements RepresentationModelAssembler<Enroll, EntityModel<Enroll>>{

	@Override
	public EntityModel<Enroll> toModel(Enroll entity) {
		// TODO Auto-generated method stub
		return null;
	}

}
