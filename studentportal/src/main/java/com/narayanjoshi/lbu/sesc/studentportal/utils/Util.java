package com.narayanjoshi.lbu.sesc.studentportal.utils;

import java.util.Random;

public class Util {

    public static long generateStudentId(){
        Random rnd = new Random();
        Long studentId = rnd.nextLong(999999);
        return studentId;
    }

}
