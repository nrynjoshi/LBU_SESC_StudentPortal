package com.narayanjoshi.lbu.sesc.financeservice.exception;

public class InvoiceNotFoundException extends Exception{

	public InvoiceNotFoundException(String invoiceId){
		super("Course does not exist "+invoiceId);
	}
}
