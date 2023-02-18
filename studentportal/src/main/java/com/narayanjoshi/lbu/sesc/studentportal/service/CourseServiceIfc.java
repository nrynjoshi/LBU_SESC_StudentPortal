package com.narayanjoshi.lbu.sesc.studentportal.service;

import com.narayanjoshi.lbu.sesc.studentportal.domain.Course;

import java.util.List;

public interface CourseServiceIfc {
    void createCourse(Course course);

    List<Course> findCourse();
}
