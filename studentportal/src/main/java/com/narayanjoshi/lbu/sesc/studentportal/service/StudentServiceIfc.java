package com.narayanjoshi.lbu.sesc.studentportal.service;

import com.narayanjoshi.lbu.sesc.studentportal.domain.Student;

public interface StudentServiceIfc {
    long loginStudent(Student student) throws Exception;

    void createStudent(Student student);

    void updateStudent(Student student);

    Student getStudentById(long studentId);

    boolean getGraduation(long studentId);
}
