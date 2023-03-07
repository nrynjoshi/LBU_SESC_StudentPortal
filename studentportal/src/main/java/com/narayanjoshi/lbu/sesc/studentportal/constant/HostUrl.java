package com.narayanjoshi.lbu.sesc.studentportal.constant;

public class HostUrl {

    private static String FINANCE = "http://localhost:8081";
    private static String LIBRARY = "http://localhost:80";

    public static String FINANCE_ACCOUNT_CREATE = FINANCE+"/accounts";

    public static String FINANCE_GRADUATION_CHECK = FINANCE+"/accounts/student/";
    public static String FINANCE_CREATE_INVOICE = FINANCE+"/invoices";

    public static String LIBRARY_ACCOUNT_CREATE = LIBRARY+"/api/register";



}
