package com.narayanjoshi.lbu.sesc.studentportal.service.impl;

import com.narayanjoshi.lbu.sesc.studentportal.doa.CourseRepositoryIfc;
import com.narayanjoshi.lbu.sesc.studentportal.domain.Course;
import com.narayanjoshi.lbu.sesc.studentportal.service.CourseServiceIfc;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseServiceImpl implements CourseServiceIfc {

    private CourseRepositoryIfc courseRepositoryIfc;

    CourseServiceImpl(CourseRepositoryIfc courseRepositoryIfc){
        this.courseRepositoryIfc = courseRepositoryIfc;
    }

    public void createCourse(Course course){
        courseRepositoryIfc.save(course);
    }

    public List<Course> findCourse(){
       return courseRepositoryIfc.findAll();
    }


}
