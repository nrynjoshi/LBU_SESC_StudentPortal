package com.narayanjoshi.lbu.sesc.studentportal.config;

import java.util.ArrayList;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import com.narayanjoshi.lbu.sesc.studentportal.domain.Account;
import com.narayanjoshi.lbu.sesc.studentportal.service.AccountServiceIfc;

@Component
public class CustomPortalAuthenticationProvider implements AuthenticationProvider {

	private AccountServiceIfc accountServiceIfc;

	CustomPortalAuthenticationProvider(AccountServiceIfc accountServiceIfc) {
		this.accountServiceIfc = accountServiceIfc;
	}

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {

		String username = authentication.getName();
		String password = authentication.getCredentials().toString();
		
		Account loginStudent = accountServiceIfc.loginStudent(Long.valueOf(username), password);
		return new UsernamePasswordAuthenticationToken(loginStudent, password, new ArrayList<>());

	}

	@Override
	public boolean supports(Class<?> authentication) {
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}
}