package com.narayanjoshi.lbu.sesc.studentportal.service;

import com.narayanjoshi.lbu.sesc.studentportal.domain.Course;
import com.narayanjoshi.lbu.sesc.studentportal.domain.Enroll;
import com.narayanjoshi.lbu.sesc.studentportal.thirdPartyApi.constant.PaymentType;

import java.math.BigDecimal;
import java.util.List;

public interface EnrollServiceIfc {
    List<Enroll> getEnrolCourses();

    void enrolIntoCourse(String courseId);

}
