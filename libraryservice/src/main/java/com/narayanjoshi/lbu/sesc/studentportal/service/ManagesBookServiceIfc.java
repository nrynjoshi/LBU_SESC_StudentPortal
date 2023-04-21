package com.narayanjoshi.lbu.sesc.studentportal.service;

import java.util.List;

import com.narayanjoshi.lbu.sesc.studentportal.domain.ManagesBook;
import com.narayanjoshi.lbu.sesc.studentportal.exception.AlreadyBorrowedThisBookException;
import com.narayanjoshi.lbu.sesc.studentportal.exception.BookAlreadyReturnException;
import com.narayanjoshi.lbu.sesc.studentportal.exception.BookHasNotBorrowedException;
import com.narayanjoshi.lbu.sesc.studentportal.exception.CourseNotFoundException;

public interface ManagesBookServiceIfc {
	/**
	 * <h1>This method get all enroll course of login user</h1>
	 * 
	 * @return List<Entroll> This will get all enroll course of particular login
	 *         user only
	 */
	List<ManagesBook> getManagesBook();

	/**
	 * <h1>This method enroll particular login user into given course</h1>
	 * 
	 * @param isbn This course Id will be enroll into login user if not already
	 *                 enroll
	 * @throws CourseNotFoundException 
	 * @throws AlreadyBorrowedThisBookException
	 */
	void borrowBook(String isbn) throws CourseNotFoundException, AlreadyBorrowedThisBookException;

	void returnBook(String isbn) throws CourseNotFoundException, BookHasNotBorrowedException, BookAlreadyReturnException;

}
