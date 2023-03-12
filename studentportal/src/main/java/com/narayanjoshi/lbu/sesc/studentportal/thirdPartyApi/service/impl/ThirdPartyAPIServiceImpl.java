package com.narayanjoshi.lbu.sesc.studentportal.thirdPartyApi.service.impl;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.narayanjoshi.lbu.sesc.studentportal.thirdPartyApi.constant.PaymentType;
import com.narayanjoshi.lbu.sesc.studentportal.thirdPartyApi.constant.ThirdPartyEndpoint;
import com.narayanjoshi.lbu.sesc.studentportal.thirdPartyApi.service.ThirdPartyAPIServiceIfc;
import com.narayanjoshi.lbu.sesc.studentportal.thirdPartyApi.util.HttpUtil;
import com.narayanjoshi.lbu.sesc.studentportal.utils.AuthenticateUtil;

//https://www.youtube.com/watch?v=6Z2XXVkB3gk
//https://www.youtube.com/watch?v=oLiHyhMdSwA
@Service
public class ThirdPartyAPIServiceImpl implements ThirdPartyAPIServiceIfc {

	@Autowired
	private HttpUtil httpUtil;

	@Override
	public boolean isEligibleGraduation(long studentId) {
		// TODO: Upon checking the eligibility to graduate, a request is sent to the
		// Finance microservice to see if there are any outstanding invoices.
		Map<String, Object> responseMap = httpUtil.get(ThirdPartyEndpoint.FINANCE_GRADUATION_CHECK + "/" + studentId);

		boolean hasOutstandingBalance = (boolean) responseMap.get("hasOutstandingBalance");

		return !hasOutstandingBalance;
	}

	@Override
	public void createFinanceServiceInvoice(long studentId, BigDecimal amount, PaymentType paymentType) {
		// TODO:
		Map<String, Object> createFinanceServiceMap = new HashMap();

		createFinanceServiceMap.put("amount", amount);
		createFinanceServiceMap.put("dueDate", "2022-11-06");
		createFinanceServiceMap.put("type", paymentType);

		Map<String, Object> accountMap = new HashMap();
		accountMap.put("studentId", String.valueOf(studentId));

		createFinanceServiceMap.put("account", accountMap);
		httpUtil.post(ThirdPartyEndpoint.CREATE_FINANCE_INVOICE, createFinanceServiceMap);
	}

	@Override
	public void createFinanceAccount(long studentId) {
		Map<String, Object> requestFinanceAccountCreateMap = new HashMap();
		requestFinanceAccountCreateMap.put("studentId", String.valueOf(studentId));
		httpUtil.post(ThirdPartyEndpoint.CREATE_FINANCE_ACCOUNT, requestFinanceAccountCreateMap);
	}

	@Override
	public void createLibraryAccount(long studentId) {
		Map<String, Object> requestLibraryAccountCreateMap = new HashMap();
		requestLibraryAccountCreateMap.put("studentId", String.valueOf(studentId));
		httpUtil.post(ThirdPartyEndpoint.CREATE_LIBRARY_ACCOUNT, requestLibraryAccountCreateMap);
	}
}
