package com.narayanjoshi.lbu.sesc.studentportal.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.narayanjoshi.lbu.sesc.studentportal.doa.CourseRepositoryIfc;
import com.narayanjoshi.lbu.sesc.studentportal.domain.Course;
import com.narayanjoshi.lbu.sesc.studentportal.service.CourseServiceIfc;

@Service
public class CourseServiceImpl implements CourseServiceIfc {

	private CourseRepositoryIfc courseRepositoryIfc;

	CourseServiceImpl(CourseRepositoryIfc courseRepositoryIfc) {
		this.courseRepositoryIfc = courseRepositoryIfc;
	}

	@Override
	public void createCourse(Course course) {
		courseRepositoryIfc.save(course);
	}

	@Override
	public List<Course> findAllCourse() {
		return courseRepositoryIfc.findAll();
	}

	@Override
	public List<Course> searchCourses(String name) {
		return courseRepositoryIfc.searchByTitleKeyword(name);
	}

}
