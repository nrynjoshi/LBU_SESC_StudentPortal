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
	
	@GetMapping("/accounts/student/{studentId}")
	public @ResponseBody ResponseEntity hasOutstandingBalance(@PathVariable String studentId) {
		boolean isOutstandingBalance = invoiceServiceIfc.hasOutstandingBalance(Long.valueOf(studentId));
		
		Map<String, Boolean> map= new HashMap();
		map.put("hasOutstandingBalance", isOutstandingBalance);
		
		return ResponseEntity.status(HttpStatus.OK).body(map);
	}
	
	// invoices all

	// by id
	// by ref id
	// create new invoice
	// cancle invoice
	// pay for invoice

}
