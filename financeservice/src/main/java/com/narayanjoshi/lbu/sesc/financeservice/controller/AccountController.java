package com.narayanjoshi.lbu.sesc.financeservice.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.narayanjoshi.lbu.sesc.financeservice.constant.Endpoint;
import com.narayanjoshi.lbu.sesc.financeservice.domain.Account;
import com.narayanjoshi.lbu.sesc.financeservice.service.AccountServiceIfc;
import com.narayanjoshi.lbu.sesc.financeservice.service.InvoiceServiceIfc;

@RestController
@RequestMapping(value = Endpoint.ROOT_API_V1)
public class AccountController {

	private AccountServiceIfc accountServiceIfc;
//	private InvoiceServiceIfc invoiceServiceIfc;

	AccountController(AccountServiceIfc accountServiceIfc, InvoiceServiceIfc invoiceServiceIfc) {
		this.accountServiceIfc = accountServiceIfc;
//		this.invoiceServiceIfc = invoiceServiceIfc;
	}

	@PostMapping(Endpoint.ACCOUNT_CREATE_OR_GETS)
	public @ResponseBody ResponseEntity<Map> createFinanceAccount(@RequestBody Account account) {
		accountServiceIfc.createAccount(account);
		return ResponseEntity.status(HttpStatus.OK).build();
	}

	@GetMapping("/accounts/student/{student_id}")
	public @ResponseBody ResponseEntity<Map> isEligibleForGradution(@PathVariable("student_id") String studentId) {
		boolean hasOutstandingBalance =  false;//invoiceServiceIfc.hasOutstandingBalance(Long.valueOf(studentId));
		Map<String, Object> map= new HashMap();
		map.put("hasOutstandingBalance", hasOutstandingBalance);
		return ResponseEntity.status(HttpStatus.OK).body(map);
	}

	// get all accounts

	// get account details by student

	// create new account
}
