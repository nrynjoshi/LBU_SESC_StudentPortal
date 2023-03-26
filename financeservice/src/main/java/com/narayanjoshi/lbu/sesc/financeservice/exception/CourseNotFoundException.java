package com.narayanjoshi.lbu.sesc.financeservice.exception;

public class CourseNotFoundException extends Exception{

	public CourseNotFoundException(String courseId){
		super("Course does not exist "+courseId);
	}
}
