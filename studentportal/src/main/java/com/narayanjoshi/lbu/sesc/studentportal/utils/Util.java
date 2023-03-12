package com.narayanjoshi.lbu.sesc.studentportal.utils;

import org.apache.commons.lang3.RandomUtils;

import java.util.Random;

public class Util {

    public static long generateStudentId(){
        Long studentId = RandomUtils.nextLong(0, 999999);
        return studentId;
    }

}
