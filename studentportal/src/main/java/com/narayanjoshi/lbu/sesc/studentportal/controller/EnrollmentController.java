package com.narayanjoshi.lbu.sesc.studentportal.controller;

import com.narayanjoshi.lbu.sesc.studentportal.constant.Endpoint;
import com.narayanjoshi.lbu.sesc.studentportal.domain.Course;
import com.narayanjoshi.lbu.sesc.studentportal.dto.ResponseDTO;
import com.narayanjoshi.lbu.sesc.studentportal.service.CourseServiceIfc;
import com.narayanjoshi.lbu.sesc.studentportal.service.EnrollServiceIfc;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = Endpoint.ROOT_API_V1 + Endpoint.ENROLLMENT_URI)
public class EnrollmentController {

    private EnrollServiceIfc enrollServiceIfc;

    EnrollmentController(EnrollServiceIfc  enrollServiceIfc){
        this.enrollServiceIfc = enrollServiceIfc;
    }

    @GetMapping
    public @ResponseBody ResponseEntity<ResponseDTO> getEnrollments(){
        return new ResponseEntity<ResponseDTO>(new ResponseDTO(null), HttpStatus.OK);
    }

    @PostMapping
    public @ResponseBody ResponseEntity<ResponseDTO> enrollIntoCourse(@RequestParam String name){
        return new ResponseEntity<ResponseDTO>(new ResponseDTO(null), HttpStatus.OK);
    }


}
