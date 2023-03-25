package com.narayanjoshi.lbu.sesc.studentportal.thirdPartyApi.service.impl;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.narayanjoshi.lbu.sesc.studentportal.constant.KeyConstant;
import com.narayanjoshi.lbu.sesc.studentportal.thirdPartyApi.constant.PaymentType;
import com.narayanjoshi.lbu.sesc.studentportal.thirdPartyApi.constant.ThirdPartyEndpoint;
import com.narayanjoshi.lbu.sesc.studentportal.thirdPartyApi.service.ThirdPartyAPIServiceIfc;
import com.narayanjoshi.lbu.sesc.studentportal.thirdPartyApi.util.HttpUtil;

@Service
public class ThirdPartyAPIServiceImpl implements ThirdPartyAPIServiceIfc {

	@Autowired
	private HttpUtil httpUtil;

	@Override
	public boolean isEligibleGraduation(long studentId) {
		//
		Map<String, Object> responseMap = httpUtil.get(ThirdPartyEndpoint.FINANCE_GRADUATION_CHECK + "/" + studentId);

		boolean hasOutstandingBalance = (boolean) responseMap.get("hasOutstandingBalance");

		return !hasOutstandingBalance;
	}

	@Override
	public void createFinanceServiceInvoice(long studentId, BigDecimal amount, PaymentType paymentType) {
		Map<String, Object> createFinanceServiceMap = new HashMap<String, Object>();

		createFinanceServiceMap.put("amount", amount);
		createFinanceServiceMap.put("dueDate", "2022-11-06");
		createFinanceServiceMap.put("type", paymentType);

		Map<String, Object> accountMap = new HashMap<String, Object>();
		accountMap.put(KeyConstant.STUDENTID, String.valueOf(studentId));

		createFinanceServiceMap.put("account", accountMap);
		httpUtil.post(ThirdPartyEndpoint.CREATE_FINANCE_INVOICE, createFinanceServiceMap);
	}

	@Override
	public void createFinanceAccount(long studentId) {
		Map<String, Object> requestFinanceAccountCreateMap = new HashMap<String, Object>();
		requestFinanceAccountCreateMap.put(KeyConstant.STUDENTID, String.valueOf(studentId));
		httpUtil.post(ThirdPartyEndpoint.CREATE_FINANCE_ACCOUNT, requestFinanceAccountCreateMap);
	}

	@Override
	public void createLibraryAccount(long studentId) {
		Map<String, Object> requestLibraryAccountCreateMap = new HashMap<String, Object>();
		requestLibraryAccountCreateMap.put(KeyConstant.STUDENTID, String.valueOf(studentId));
		httpUtil.post(ThirdPartyEndpoint.CREATE_LIBRARY_ACCOUNT, requestLibraryAccountCreateMap);
	}
}
