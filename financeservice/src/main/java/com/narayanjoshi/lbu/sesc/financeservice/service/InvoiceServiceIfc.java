package com.narayanjoshi.lbu.sesc.financeservice.service;

import java.util.List;

import com.narayanjoshi.lbu.sesc.financeservice.domain.Account;
import com.narayanjoshi.lbu.sesc.financeservice.domain.Invoice;

public interface InvoiceServiceIfc {
	
	
	/**
	 * <h1>This method get invoice by particular reference Id</h1>
	 * 
	 * @param referenceId hold particular id of invoice 
	 */
	Invoice getInvoiceByReferenceId(String referenceId);

	void payInvoiceByReferenceId(String referenceId);

	String createInvoice(Invoice invoice);
	
	List<Invoice> getInvoicesByAccount(Account account);

	List<Invoice> getAllInvoicesByStudentId(long studentId);

	boolean hasOutstandingBalance(long studentId);

	List<Invoice> getAllInvoices();

	void cancelInvoiceByReferenceId(String referenceId);

}
