package com.narayanjoshi.lbu.sesc.studentportal.controller;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.MissingRequestCookieException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MissingRequestCookieException.class)
    public void missingRequestCookie(MissingRequestCookieException exception, HttpServletResponse response, RedirectAttributes redirectAttributes) throws IOException {
        exception.printStackTrace();
        String redirectPath = "/";
        if(StringUtils.equals(exception.getCookieName(),"studentId")){
            redirectAttributes.addFlashAttribute("error", "Please authenticate before accessing this page.");
            redirectPath = "/portal/login";

        }
        response.sendRedirect(redirectPath);
    }


    @ExceptionHandler(Exception.class)
    public void throwable(Exception exception, HttpServletResponse response, RedirectAttributes redirectAttributes) throws IOException {
        exception.printStackTrace();
        redirectAttributes.addFlashAttribute("error", exception.getMessage());
        response.sendRedirect("/");
    }
}
