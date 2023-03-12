package com.narayanjoshi.lbu.sesc.studentportal.service;

import com.narayanjoshi.lbu.sesc.studentportal.domain.Student;

public interface StudentServiceIfc {

    void createStudent(Student student);

    void updateStudent(Student student);

    Student getStudentById(long studentId);

	Student loginStudent(String username, String password);

	boolean isEligibleGraduation();
}
