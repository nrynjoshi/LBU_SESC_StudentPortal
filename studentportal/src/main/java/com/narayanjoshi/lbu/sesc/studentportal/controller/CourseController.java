package com.narayanjoshi.lbu.sesc.studentportal.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.narayanjoshi.lbu.sesc.studentportal.constant.Endpoint;
import com.narayanjoshi.lbu.sesc.studentportal.domain.Course;
import com.narayanjoshi.lbu.sesc.studentportal.dto.ResponseDTO;
import com.narayanjoshi.lbu.sesc.studentportal.service.CourseServiceIfc;

@RestController
@RequestMapping(value = Endpoint.ROOT_API_V1)
public class CourseController {

    private CourseServiceIfc  courseServiceIfc;

    CourseController(CourseServiceIfc  courseServiceIfc){
        this.courseServiceIfc = courseServiceIfc;
    }

    @GetMapping(value = Endpoint.VIEW_COURSE_URI)
    public @ResponseBody ResponseEntity<ResponseDTO> getCourses(){
       List<Course> courseList = this.courseServiceIfc.findAllCourse();
        return new ResponseEntity<ResponseDTO>(new ResponseDTO(courseList), HttpStatus.OK);
    }

    @GetMapping(value = Endpoint.SEARCH_COURSE_URI)
    public @ResponseBody ResponseEntity<ResponseDTO> searchCourses(@RequestParam String name){
        List<Course> courseList = this.courseServiceIfc.searchCourses(name);
        return new ResponseEntity<ResponseDTO>(new ResponseDTO(courseList), HttpStatus.OK);
    }

}
