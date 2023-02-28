package com.narayanjoshi.lbu.sesc.studentportal.service;

import com.narayanjoshi.lbu.sesc.studentportal.domain.Course;
import com.narayanjoshi.lbu.sesc.studentportal.domain.Enroll;

import java.util.List;

public interface EnrollServiceIfc {
    List<Enroll> getEnrolCourses(long studentId);

    void enrolIntoCourse(long studentId, String courseId);
}
