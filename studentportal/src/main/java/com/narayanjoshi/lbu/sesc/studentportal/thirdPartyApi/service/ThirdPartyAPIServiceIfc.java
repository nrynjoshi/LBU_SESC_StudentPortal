package com.narayanjoshi.lbu.sesc.studentportal.thirdPartyApi.service;

import java.math.BigDecimal;

import com.narayanjoshi.lbu.sesc.studentportal.thirdPartyApi.constant.PaymentType;

public interface ThirdPartyAPIServiceIfc {

	void createFinanceServiceInvoice(long studentId, BigDecimal amount, PaymentType paymentType);

	void createFinanceAccount(long studentId);

	void createLibraryAccount(long studentId);
	
	boolean isEligibleGraduation(long studentId);

}
