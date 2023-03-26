package com.narayanjoshi.lbu.sesc.financeservice.controller;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.narayanjoshi.lbu.sesc.financeservice.constant.Endpoint;
import com.narayanjoshi.lbu.sesc.financeservice.domain.Invoice;
import com.narayanjoshi.lbu.sesc.financeservice.service.InvoiceServiceIfc;

@RestController
@RequestMapping(value = Endpoint.ROOT_API_V1)
public class InvoiceController {

	private InvoiceServiceIfc invoiceServiceIfc;
	
	InvoiceController(InvoiceServiceIfc invoiceServiceIfc) {
		this.invoiceServiceIfc = invoiceServiceIfc;
	}
	
	@PostMapping(Endpoint.INVOICES_CREATE_OR_GETS)
	public @ResponseBody ResponseEntity createInvoiceAccount(@RequestBody Invoice invoice) {
		invoiceServiceIfc.createInvoice(invoice);
		return ResponseEntity.status(HttpStatus.OK).build();
	}
	
	// invoices all

	// by id
	// by ref id
	// create new invoice
	// cancle invoice
	// pay for invoice

}
