package com.narayanjoshi.lbu.sesc.studentportal.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class AppController {

    @Value("${spring.application.name}")
    String appName;


    public AppController(){
        System.out.println("Init App Controller");
    }

    @GetMapping(value = "/app")
    public @ResponseBody ResponseEntity<String> getAppInfo(){
        return new ResponseEntity<>(appName, HttpStatus.OK);
    }

}
