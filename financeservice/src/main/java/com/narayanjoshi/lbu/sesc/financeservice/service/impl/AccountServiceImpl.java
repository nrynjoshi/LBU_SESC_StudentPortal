package com.narayanjoshi.lbu.sesc.financeservice.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.narayanjoshi.lbu.sesc.financeservice.constant.StatusEnum;
import com.narayanjoshi.lbu.sesc.financeservice.doa.AccountRepositoryIfc;
import com.narayanjoshi.lbu.sesc.financeservice.domain.Account;
import com.narayanjoshi.lbu.sesc.financeservice.domain.Invoice;
import com.narayanjoshi.lbu.sesc.financeservice.service.AccountServiceIfc;
import com.narayanjoshi.lbu.sesc.financeservice.service.InvoiceServiceIfc;

@Service
public class AccountServiceImpl implements AccountServiceIfc {

	private AccountRepositoryIfc accountRepositoryIfc;

	AccountServiceImpl(AccountRepositoryIfc accountRepositoryIfc) {
		this.accountRepositoryIfc = accountRepositoryIfc;
	}

	@Override
	public void createAccount(Account account) {
		accountRepositoryIfc.save(account);
	}

	@Override
	public Account getAccountByStudentId(long studentId) {
		return accountRepositoryIfc.findByStudentId(studentId);
	}
	
	
	
	
}
