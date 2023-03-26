package com.narayanjoshi.lbu.sesc.financeservice.controller;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.narayanjoshi.lbu.sesc.financeservice.domain.Invoice;
import com.narayanjoshi.lbu.sesc.financeservice.service.AccountServiceIfc;
import com.narayanjoshi.lbu.sesc.financeservice.service.InvoiceServiceIfc;

@Controller
@RequestMapping("")
public class PortalController {

	private InvoiceServiceIfc invoiceServiceIfc;
	
	private AccountServiceIfc accountServiceIfc;

	PortalController(InvoiceServiceIfc invoiceServiceIfc, AccountServiceIfc accountServiceIfc) {
		this.invoiceServiceIfc = invoiceServiceIfc;
		this.accountServiceIfc = accountServiceIfc;
	}

	@GetMapping("/")
	public String redirectToLogin() {
		return "redirect:/invoices";
	}

	@GetMapping({ "/invoices" })
	public String profilePortalPage(Model model, @RequestParam(name = "reference_id", required= false) String referenceId, @RequestParam(name = "student_id", required= false) String studentId) {
		
		List<Invoice> allInvoicesByStudentId = Collections.EMPTY_LIST;
		
		if(StringUtils.isNotBlank(studentId)) {
			allInvoicesByStudentId = invoiceServiceIfc.getAllInvoicesByStudentId(Long.valueOf(studentId));
		}else if(StringUtils.isNotBlank(referenceId)){
			allInvoicesByStudentId = Arrays.asList(invoiceServiceIfc.getInvoiceByReferenceId(referenceId));
		}
		
		
		model.addAttribute("invoices", allInvoicesByStudentId);
		return "view-invoices";
	}


	@PostMapping({ "/invoice/pay/{reference_id}" })
	public String payForInvoice(@PathVariable("reference_id") String referenceId) {
		invoiceServiceIfc.payInvoiceByReferenceId(referenceId);
		return "redirect:/search/invoice?reference_id="+referenceId;
	}

}
