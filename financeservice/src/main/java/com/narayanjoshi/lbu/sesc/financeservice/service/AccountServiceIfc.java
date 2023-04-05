package com.narayanjoshi.lbu.sesc.financeservice.service;

import java.util.List;

import com.narayanjoshi.lbu.sesc.financeservice.domain.Account;

public interface AccountServiceIfc {

	void createAccount(Account account);

	Account getAccountByStudentId(long studentId);

	List<Account> getAccounts();

	void deleteAccount(long studentId);
	
}
