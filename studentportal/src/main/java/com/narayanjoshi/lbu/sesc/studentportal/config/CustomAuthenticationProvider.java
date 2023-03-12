package com.narayanjoshi.lbu.sesc.studentportal.config;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import com.narayanjoshi.lbu.sesc.studentportal.domain.Student;
import com.narayanjoshi.lbu.sesc.studentportal.service.StudentServiceIfc;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

	 @Autowired private StudentServiceIfc studentServiceIfc;
	
    @Override
    public Authentication authenticate(Authentication authentication) 
      throws AuthenticationException {
 
        String username = authentication.getName();
        String password = authentication.getCredentials().toString();
        
        Student loginStudent = studentServiceIfc.loginStudent(username, password);
        if (loginStudent!= null) { //shouldAuthenticateAgainstThirdPartySystem()
            return new UsernamePasswordAuthenticationToken(
            		loginStudent, password, new ArrayList<>());
        } else {
            return null;
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}