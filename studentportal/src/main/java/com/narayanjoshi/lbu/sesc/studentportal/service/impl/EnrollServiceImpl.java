package com.narayanjoshi.lbu.sesc.studentportal.service.impl;

import com.narayanjoshi.lbu.sesc.studentportal.domain.Course;
import com.narayanjoshi.lbu.sesc.studentportal.service.EnrollServiceIfc;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.List;

@Service
@Transactional
public class EnrollServiceImpl implements EnrollServiceIfc {

    @Override
    public List<Course> getEnrolCourses(){
        return Arrays.asList();
    }

}
