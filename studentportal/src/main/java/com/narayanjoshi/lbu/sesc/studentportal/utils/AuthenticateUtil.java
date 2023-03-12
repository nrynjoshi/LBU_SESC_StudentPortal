package com.narayanjoshi.lbu.sesc.studentportal.utils;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.narayanjoshi.lbu.sesc.studentportal.domain.Student;

public class AuthenticateUtil {
	
	public static boolean isAuthenticate() {
		Authentication authentication = getAuthentication();
		if(authentication !=null) {
			Object principal = authentication.getPrincipal();
			if(principal !=null && principal instanceof Student) {
				return true;
			}
		}
		return  false;
	}
	
	public static long getStudentId() {
		Student student = getPrincipal();
		return student.getStudentId();
	}

	private static Student getPrincipal() {
		Student student = (Student) getAuthentication().getPrincipal();
		return student;
	}

	private static Authentication getAuthentication() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		return auth;
	}
	
}
