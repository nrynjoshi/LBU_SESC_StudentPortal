package com.narayanjoshi.lbu.sesc.studentportal.controller;

import com.narayanjoshi.lbu.sesc.studentportal.constant.Endpoint;
import com.narayanjoshi.lbu.sesc.studentportal.dto.ResponseDTO;
import com.narayanjoshi.lbu.sesc.studentportal.service.EnrollServiceIfc;
import com.narayanjoshi.lbu.sesc.studentportal.service.StudentServiceIfc;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = Endpoint.ROOT_API_V1 + Endpoint.STUDENT_URI)
public class StudentController {

    private StudentServiceIfc studentServiceIfc;

    StudentController(StudentServiceIfc  studentServiceIfc){
        this.studentServiceIfc = studentServiceIfc;
    }

    @GetMapping
    public @ResponseBody ResponseEntity<ResponseDTO> getStudent(){
        return new ResponseEntity<ResponseDTO>(new ResponseDTO(null), HttpStatus.OK);
    }

    @PostMapping
    public @ResponseBody ResponseEntity<ResponseDTO> registerStudent(){
        return new ResponseEntity<ResponseDTO>(new ResponseDTO(null), HttpStatus.OK);
    }

    @PutMapping
    public @ResponseBody ResponseEntity<ResponseDTO> updateStudent(){
        return new ResponseEntity<ResponseDTO>(new ResponseDTO(null), HttpStatus.OK);
    }
}
