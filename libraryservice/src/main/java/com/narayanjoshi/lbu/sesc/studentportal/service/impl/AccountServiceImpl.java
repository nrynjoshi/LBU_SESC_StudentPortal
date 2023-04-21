package com.narayanjoshi.lbu.sesc.studentportal.service.impl;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.narayanjoshi.lbu.sesc.studentportal.doa.AccountRepositoryIfc;
import com.narayanjoshi.lbu.sesc.studentportal.domain.Account;
import com.narayanjoshi.lbu.sesc.studentportal.exception.AuthenticationException;
import com.narayanjoshi.lbu.sesc.studentportal.service.AccountServiceIfc;

@Service
public class AccountServiceImpl implements AccountServiceIfc {

	private AccountRepositoryIfc accountRepositoryIfc;
	private PasswordEncoder passwordEncoder;

	AccountServiceImpl(AccountRepositoryIfc accountRepositoryIfc, PasswordEncoder passwordEncoder) {
		this.accountRepositoryIfc = accountRepositoryIfc;
		this.passwordEncoder = passwordEncoder;
	}

	@Override
	public void createAccount(Account account) {
		account.setPassword(passwordEncoder.encode("pass123")); //we are setting default password but may be used student service api for this one
		accountRepositoryIfc.save(account);
	}

	@Override
	public Account getAccountByStudentId(long studentId) {
		return accountRepositoryIfc.findByStudentId(studentId);
	}
	
	@Override
	public Account loginStudent(@NotNull @NotEmpty  long studentId,@NotNull @NotEmpty String password) throws AuthenticationException {
		Account dbStudentRecord = accountRepositoryIfc.findByStudentId(studentId);
		if (dbStudentRecord != null) {
			if (passwordEncoder.matches(password, dbStudentRecord.getPassword())) {
				return dbStudentRecord;
			}
			throw new AuthenticationException(studentId);
		}

		throw new AuthenticationException();
	}
	
	
	
	
}
