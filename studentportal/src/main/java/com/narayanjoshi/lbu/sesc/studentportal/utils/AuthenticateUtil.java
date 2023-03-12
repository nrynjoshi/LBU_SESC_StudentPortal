package com.narayanjoshi.lbu.sesc.studentportal.utils;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.narayanjoshi.lbu.sesc.studentportal.domain.Student;

public class AuthenticateUtil {
	
	public static long getStudentId() {
		Student student = (Student) getAuthentication().getPrincipal();
		return student.getStudentId();
	}

	private static Authentication getAuthentication() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		return auth;
	}
	
}
