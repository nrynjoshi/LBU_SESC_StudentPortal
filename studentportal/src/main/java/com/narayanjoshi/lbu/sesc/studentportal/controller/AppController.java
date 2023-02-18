package com.narayanjoshi.lbu.sesc.studentportal.controller;

import com.narayanjoshi.lbu.sesc.studentportal.constant.Endpoint;
import com.narayanjoshi.lbu.sesc.studentportal.dto.ResponseDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = Endpoint.ROOT_API_V1)
public class AppController {

    @Value("${spring.application.name}")
    String appName;

    @GetMapping(value = Endpoint.APP_URI)
    public @ResponseBody ResponseEntity<ResponseDTO> getAppInfo(){
        Map<String, Object> map = new HashMap<>();
        map.put("app_name", appName);
        return new ResponseEntity<ResponseDTO>(new ResponseDTO(map), HttpStatus.OK);
    }

}
