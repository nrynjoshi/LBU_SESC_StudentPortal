package com.narayanjoshi.lbu.sesc.studentportal.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.MissingRequestCookieException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.narayanjoshi.lbu.sesc.studentportal.exception.AuthenticationException;
import com.narayanjoshi.lbu.sesc.studentportal.utils.AuthenticateUtil;

@ControllerAdvice
public class GlobalExceptionHandler {
	
	String error_redirect_url = "/error";

	@ExceptionHandler(MissingRequestCookieException.class)
	public void missingRequestCookie(MissingRequestCookieException exception, HttpServletResponse response,
			RedirectAttributes redirectAttributes) throws IOException {
		exception.printStackTrace();
		String redirectPath = "/";
		if (!AuthenticateUtil.isAuthenticate()) {
			redirectAttributes.addFlashAttribute("error_msg", "Please authenticate before accessing this page.");
			redirectPath = error_redirect_url;

		}
		response.sendRedirect(redirectPath);
	}
	
	
	
	@ExceptionHandler(org.springframework.web.client.ResourceAccessException.class)
	public void missingRequestCookie(org.springframework.web.client.ResourceAccessException exception, HttpServletResponse response,
			RedirectAttributes redirectAttributes) throws IOException {
		exception.printStackTrace();
		redirectAttributes.addFlashAttribute("error_msg", "Internal Services are not connected yet. Please try again later!");
		response.sendRedirect(error_redirect_url);
	}
	
	@ExceptionHandler(AuthenticationException.class)
	public void missingRequestCookie(AuthenticationException exception, HttpServletResponse response,
			RedirectAttributes redirectAttributes) throws IOException {
		exception.printStackTrace();
		redirectAttributes.addFlashAttribute("error_msg", exception.getMessage());
		response.sendRedirect(error_redirect_url);
	}

	@ExceptionHandler(Exception.class)
	public void throwable(Exception exception, HttpServletResponse response, RedirectAttributes redirectAttributes)
			throws IOException {
		exception.printStackTrace();
		redirectAttributes.addFlashAttribute("error_msg", exception.getMessage());
		response.sendRedirect(error_redirect_url);
	}
}
