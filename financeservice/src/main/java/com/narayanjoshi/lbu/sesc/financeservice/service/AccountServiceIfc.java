package com.narayanjoshi.lbu.sesc.financeservice.service;

import com.narayanjoshi.lbu.sesc.financeservice.domain.Account;

public interface AccountServiceIfc {

	void createAccount(Account account);

	Account getAccountByStudentId(long studentId);


	
}
