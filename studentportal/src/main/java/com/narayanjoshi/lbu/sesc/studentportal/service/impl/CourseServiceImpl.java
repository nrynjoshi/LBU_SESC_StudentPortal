package com.narayanjoshi.lbu.sesc.studentportal.service.impl;

import com.narayanjoshi.lbu.sesc.studentportal.doa.CourseRepositoryIfc;
import com.narayanjoshi.lbu.sesc.studentportal.domain.Course;
import com.narayanjoshi.lbu.sesc.studentportal.service.CourseServiceIfc;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class CourseServiceImpl implements CourseServiceIfc {

    private CourseRepositoryIfc courseRepositoryIfc;

    CourseServiceImpl(CourseRepositoryIfc courseRepositoryIfc){
        this.courseRepositoryIfc = courseRepositoryIfc;
    }

    @Override
    public void createCourse(Course course){
        courseRepositoryIfc.save(course);
    }

    @Override
    public List<Course> findCourse(){
       return courseRepositoryIfc.findAll();
    }


}
