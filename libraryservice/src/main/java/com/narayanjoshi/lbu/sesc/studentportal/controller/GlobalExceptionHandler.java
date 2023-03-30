package com.narayanjoshi.lbu.sesc.studentportal.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.MissingRequestCookieException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.narayanjoshi.lbu.sesc.studentportal.utils.AuthenticateUtil;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(MissingRequestCookieException.class)
	public void missingRequestCookie(MissingRequestCookieException exception, HttpServletResponse response,
			RedirectAttributes redirectAttributes) throws IOException {
		exception.printStackTrace();
		String redirectPath = "/";
		if (!AuthenticateUtil.isAuthenticate()) {
			redirectAttributes.addFlashAttribute("error", "Please authenticate before accessing this page.");
			redirectPath = "/portal/error";

		}
		response.sendRedirect(redirectPath);
	}

	@ExceptionHandler(Exception.class)
	public void throwable(Exception exception, HttpServletResponse response, RedirectAttributes redirectAttributes)
			throws IOException {
		exception.printStackTrace();
		redirectAttributes.addFlashAttribute("error", exception.getMessage());
		response.sendRedirect("/");
	}
}
