package com.narayanjoshi.lbu.sesc.studentportal.thirdPartyApi.constant;

public class ThirdPartyEndpoint {

    private static String FINANCE = "http://localhost:8081";
    private static String LIBRARY = "http://localhost:80";

    public static String CREATE_FINANCE_ACCOUNT = FINANCE+"/accounts";
    public static String CREATE_LIBRARY_ACCOUNT = LIBRARY+"/api/register";

    public static String FINANCE_GRADUATION_CHECK = FINANCE+"/accounts/student/";
    public static String CREATE_FINANCE_INVOICE = FINANCE+"/invoices";

   



}
