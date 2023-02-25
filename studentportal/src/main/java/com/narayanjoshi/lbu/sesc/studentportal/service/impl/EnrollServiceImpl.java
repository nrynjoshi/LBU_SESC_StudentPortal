package com.narayanjoshi.lbu.sesc.studentportal.service.impl;

import com.narayanjoshi.lbu.sesc.studentportal.constant.IntakeEnum;
import com.narayanjoshi.lbu.sesc.studentportal.doa.EnrollRepositoryIfc;
import com.narayanjoshi.lbu.sesc.studentportal.domain.Course;
import com.narayanjoshi.lbu.sesc.studentportal.domain.Enroll;
import com.narayanjoshi.lbu.sesc.studentportal.service.EnrollServiceIfc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@Service
@Transactional
public class EnrollServiceImpl implements EnrollServiceIfc {

    @Autowired private EnrollRepositoryIfc enrollRepositoryIfc;

    @Override
    public List<Enroll> getEnrolCourses(long studentId){
        return enrollRepositoryIfc.findByStudentId(studentId);
    }

    @Override
    public void enrolIntoCourse(long studentId, String courseId){
        Enroll enroll= new Enroll();
        enroll.setCourseId(courseId);
        enroll.setStudentId(studentId);
        enroll.setDate(LocalDateTime.now());
        enroll.setIntake(IntakeEnum.JAN);
        enrollRepositoryIfc.save(enroll);
    }

}
