package com.narayanjoshi.lbu.sesc.studentportal.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.narayanjoshi.lbu.sesc.studentportal.domain.Account;
import com.narayanjoshi.lbu.sesc.studentportal.domain.ManagesBook;
import com.narayanjoshi.lbu.sesc.studentportal.exception.CourseNotFoundException;
import com.narayanjoshi.lbu.sesc.studentportal.exception.UserAlreadyEnrollIntoCourseException;
import com.narayanjoshi.lbu.sesc.studentportal.service.BookServiceIfc;
import com.narayanjoshi.lbu.sesc.studentportal.service.ManagesBookServiceIfc;
import com.narayanjoshi.lbu.sesc.studentportal.utils.AuthenticateUtil;

@Controller
@RequestMapping("")
public class PortalController {

	private BookServiceIfc bookServiceIfc;

	private ManagesBookServiceIfc managesBookServiceIfc;

	PortalController(BookServiceIfc bookServiceIfc, ManagesBookServiceIfc managesBookServiceIfc) {
		this.bookServiceIfc = bookServiceIfc;
		this.managesBookServiceIfc = managesBookServiceIfc;
	}

	@GetMapping("/")
	public String redirectToLogin() {
		return "redirect:/login";
	}

	@GetMapping({ "login" })
	public String login(Model model) {
		if (AuthenticateUtil.isAuthenticate()) {
			return "redirect:/dashboard";
		}
		model.addAttribute("student", new Account());
		return "index";
	}

	@GetMapping({ "/dashboard" })
	public String dashboardPortalPage(Model model) {
		model.addAttribute("student", new Account());
		return "dashboard";
	}

	@GetMapping({ "/books" })
	public String viewBooks(Model model) {
		model.addAttribute("books", bookServiceIfc.findAllBook());
		return "view-books-and-borrow";
	}

	@GetMapping({ "/logout" })
	public String logout(Model model) {
		return "redirect:/login";
	}
	
	@GetMapping({ "/my-books" })
	public String viewMyBooks(Model model) {
		List<ManagesBook> books = managesBookServiceIfc.getManagesBook();
		model.addAttribute("books", books);
		return "manage-my-books";
	}

	@GetMapping({ "/books/borrow/{isbn}" })
	public String borrowBook(@PathVariable("isbn") String isbn) throws CourseNotFoundException, UserAlreadyEnrollIntoCourseException {
		managesBookServiceIfc.borrowBook(isbn);
		return "redirect:/enrollments";
	}
	
	@GetMapping({ "/books/return/{isbn}" })
	public String returnBook(@PathVariable("isbn") String isbn) throws CourseNotFoundException, UserAlreadyEnrollIntoCourseException {
		managesBookServiceIfc.returnBook(isbn);
		return "redirect:/enrollments";
	}

}
