package com.narayanjoshi.lbu.sesc.studentportal.utils;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.Optional;
import java.util.stream.Collectors;

public class CookieUtils {

    private static final String PATH = "/portal";

    public static void setCookie(String key, Object value){
        Cookie cookie = new Cookie(key, String.valueOf(value));
        cookie.setMaxAge(7 * 24 * 60 * 60); // expires in 7 days
//        cookie.setSecure(true);
        cookie.setHttpOnly(true); //used to prevent cross-site scripting (XSS) attacks
        cookie.setPath(PATH);
        getHttpServletResponse().addCookie(cookie);
    }

    public static boolean isCookieExist(String key){
       String cookie =  getCookie(key);
       return StringUtils.isNotBlank(cookie);
    }

    public  static String getCookie(String key){

        Cookie[] cookies = getHttpServletRequest().getCookies();
        if (cookies != null) {
            Optional<String> optional = Arrays.stream(cookies).filter(c -> StringUtils.equals(key, c.getName()))
                    .map(c -> c.getValue()).findFirst();
            if(optional.isPresent()){
                return optional.get();
            }
        }

        return null;
    }

    public static void deleteCookie(String key){
        // create a cookie
        Cookie cookie = new Cookie(key, null);
        cookie.setMaxAge(0);
//        cookie.setSecure(true);
        cookie.setHttpOnly(true);
        cookie.setPath(PATH);

//add cookie to response
        getHttpServletResponse().addCookie(cookie);
    }

    public static HttpServletResponse getHttpServletResponse(){
        HttpServletResponse request =
                ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
                        .getResponse();
        return request;
    }

    public static HttpServletRequest getHttpServletRequest(){
        HttpServletRequest response =
                ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
                        .getRequest();
        return response;
    }
}
