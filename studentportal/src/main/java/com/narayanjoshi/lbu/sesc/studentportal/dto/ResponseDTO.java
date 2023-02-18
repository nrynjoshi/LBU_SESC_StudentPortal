package com.narayanjoshi.lbu.sesc.studentportal.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseDTO<T> {

    private int error_code = 0;

    private String error;
    Map<String, String> detail;
    private T item;
    private List<T> items;

    public ResponseDTO(T item){
        this.item = item;
    }

}
