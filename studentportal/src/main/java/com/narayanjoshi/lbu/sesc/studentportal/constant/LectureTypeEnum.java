package com.narayanjoshi.lbu.sesc.studentportal.constant;

public enum LectureTypeEnum {

    PHYSICAL("physical"),
    ONLINE("online"),
    RECORDED("recorded");

    private String type;
    LectureTypeEnum(String type) {
        this.type= type;
    }

    public String type(){
        return type;
    }
}
