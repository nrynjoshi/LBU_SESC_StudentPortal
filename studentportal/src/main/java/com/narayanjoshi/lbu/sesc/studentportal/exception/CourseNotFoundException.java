package com.narayanjoshi.lbu.sesc.studentportal.exception;

public class CourseNotFoundException extends Exception{

	public CourseNotFoundException(String courseId){
		super("Course does not exist "+courseId);
	}
}
