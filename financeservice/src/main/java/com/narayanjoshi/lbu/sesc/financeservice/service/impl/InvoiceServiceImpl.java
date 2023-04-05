package com.narayanjoshi.lbu.sesc.financeservice.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.narayanjoshi.lbu.sesc.financeservice.constant.StatusEnum;
import com.narayanjoshi.lbu.sesc.financeservice.doa.AccountRepositoryIfc;
import com.narayanjoshi.lbu.sesc.financeservice.doa.InvoiceRepositoryIfc;
import com.narayanjoshi.lbu.sesc.financeservice.domain.Account;
import com.narayanjoshi.lbu.sesc.financeservice.domain.Invoice;
import com.narayanjoshi.lbu.sesc.financeservice.service.AccountServiceIfc;
import com.narayanjoshi.lbu.sesc.financeservice.service.InvoiceServiceIfc;
import com.narayanjoshi.lbu.sesc.financeservice.utils.Util;

@Service
public class InvoiceServiceImpl implements InvoiceServiceIfc {

	private InvoiceRepositoryIfc invoiceRepositoryIfc;
	private AccountRepositoryIfc accountRepositoryIfc;

	InvoiceServiceImpl(InvoiceRepositoryIfc invoiceRepositoryIfc, AccountRepositoryIfc accountRepositoryIfc) {
		this.invoiceRepositoryIfc = invoiceRepositoryIfc;
		this.accountRepositoryIfc = accountRepositoryIfc;
	}

	@Override
	public Invoice getInvoiceByReferenceId(String referenceId) {
		return invoiceRepositoryIfc.findByReferenceId(referenceId);
	}
	
	@Override
	public void cancelInvoiceByReferenceId(String referenceId) {
		Invoice invoiceByReferenceId = getInvoiceByReferenceId(referenceId);
		invoiceByReferenceId.setStatus(StatusEnum.CANCELLED);
		invoiceRepositoryIfc.save(invoiceByReferenceId);
	}

	@Override
	public void payInvoiceByReferenceId(String referenceId) {
		Invoice invoiceByReferenceId = getInvoiceByReferenceId(referenceId);
		invoiceByReferenceId.setStatus(StatusEnum.PAID);
		invoiceRepositoryIfc.save(invoiceByReferenceId);
	}

	@Override
	public String createInvoice(Invoice invoice) {
		long studentId = invoice.getAccount().getStudentId();
		Account accountByStudentId = accountRepositoryIfc.findByStudentId(studentId);
		invoice.setAccount(accountByStudentId);
		
		invoice.setReferenceId(Util.generateInvoiceReferenceId());
		invoice.setStatus(StatusEnum.OUTSTANDING);
		invoiceRepositoryIfc.save(invoice);
		return invoice.getReferenceId();
	}

	@Override
	public List<Invoice> getInvoicesByAccount(Account account) {
		return invoiceRepositoryIfc.findByAccount(account);
	}
	
	
	@Override
	public List<Invoice> getAllInvoicesByStudentId(long studentId) {
		Account account = accountRepositoryIfc.findByStudentId(studentId);
		List<Invoice> invoicesByAccount = getInvoicesByAccount(account);
		return invoicesByAccount;
	}
	
	@Override
	public List<Invoice> getAllInvoices() {
		List<Invoice> invoicesByAccount = invoiceRepositoryIfc.findAll();
		return invoicesByAccount;
	}
	
	@Override
	public boolean hasOutstandingBalance(long studentId) {
		List<Invoice> allInvoicesByStudentId = getAllInvoicesByStudentId(studentId);
		long outstandingCount = allInvoicesByStudentId.stream().filter(data -> data.getStatus().equals(StatusEnum.OUTSTANDING)).count();
		boolean isOutstandingBalance = outstandingCount> 0;
		return isOutstandingBalance;
	}

}
