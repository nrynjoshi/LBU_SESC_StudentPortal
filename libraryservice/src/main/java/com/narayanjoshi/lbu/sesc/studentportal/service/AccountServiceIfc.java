package com.narayanjoshi.lbu.sesc.studentportal.service;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.narayanjoshi.lbu.sesc.studentportal.domain.Account;
import com.narayanjoshi.lbu.sesc.studentportal.exception.AuthenticationException;

public interface AccountServiceIfc {

	void createAccount(Account account);

	Account getAccountByStudentId(long studentId);

	Account loginStudent(@NotNull @NotEmpty long studentId, @NotNull @NotEmpty String password)
			throws AuthenticationException;


	
}
