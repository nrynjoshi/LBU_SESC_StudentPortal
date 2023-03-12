package com.narayanjoshi.lbu.sesc.studentportal.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.narayanjoshi.lbu.sesc.studentportal.constant.Endpoint;
import com.narayanjoshi.lbu.sesc.studentportal.dto.ResponseDTO;
import com.narayanjoshi.lbu.sesc.studentportal.service.EnrollServiceIfc;

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
