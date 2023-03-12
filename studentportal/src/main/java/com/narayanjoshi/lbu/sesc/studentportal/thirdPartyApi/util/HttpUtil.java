package com.narayanjoshi.lbu.sesc.studentportal.thirdPartyApi.util;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Component
public class HttpUtil {


    public  void post(String endpoint, Object requestBody){
        RestTemplate restTemplate = new RestTemplate();

        HttpEntity request = new HttpEntity(requestBody);
        Map<String,Object> response = restTemplate.postForObject(endpoint, request, Map.class);
    }

    public Map<String,Object> get(String endpoint){
        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<Map> response
                = restTemplate.getForEntity(endpoint, Map.class);
       return response.getBody();
    }
}
