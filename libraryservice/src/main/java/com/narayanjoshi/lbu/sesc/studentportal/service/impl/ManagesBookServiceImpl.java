package com.narayanjoshi.lbu.sesc.studentportal.service.impl;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.narayanjoshi.lbu.sesc.studentportal.doa.BookRepositoryIfc;
import com.narayanjoshi.lbu.sesc.studentportal.doa.ManagesBookRepositoryIfc;
import com.narayanjoshi.lbu.sesc.studentportal.domain.Book;
import com.narayanjoshi.lbu.sesc.studentportal.domain.ManagesBook;
import com.narayanjoshi.lbu.sesc.studentportal.exception.CourseNotFoundException;
import com.narayanjoshi.lbu.sesc.studentportal.exception.UserAlreadyEnrollIntoCourseException;
import com.narayanjoshi.lbu.sesc.studentportal.service.ManagesBookServiceIfc;
import com.narayanjoshi.lbu.sesc.studentportal.thirdPartyApi.constant.PaymentType;
import com.narayanjoshi.lbu.sesc.studentportal.thirdPartyApi.service.ThirdPartyAPIServiceIfc;
import com.narayanjoshi.lbu.sesc.studentportal.utils.AuthenticateUtil;

@Service
public class ManagesBookServiceImpl implements ManagesBookServiceIfc {

	private ManagesBookRepositoryIfc managesBookRepositoryIfc;

	private BookRepositoryIfc bookRepositoryIfc;

	private ThirdPartyAPIServiceIfc thirdPartyAPIServiceIfc;
	
	private final static int NUMBER_OF_DAYS_BOOK_BORROW = 10;

	ManagesBookServiceImpl(ManagesBookRepositoryIfc managesBookRepositoryIfc, BookRepositoryIfc bookRepositoryIfc,
			ThirdPartyAPIServiceIfc thirdPartyAPIServiceIfc) {
		this.managesBookRepositoryIfc = managesBookRepositoryIfc;
		this.bookRepositoryIfc = bookRepositoryIfc;
		this.thirdPartyAPIServiceIfc = thirdPartyAPIServiceIfc;
	}

	@Override
	public List<ManagesBook> getManagesBook() {
		long studentId = AuthenticateUtil.getStudentId();
		return managesBookRepositoryIfc.findByStudentId(studentId);
	}

	@Transactional
	@Override
	public void borrowBook(String isbn) throws CourseNotFoundException, UserAlreadyEnrollIntoCourseException {
		Book book = bookRepositoryIfc.findByIsbn(isbn);
		if (book == null) {
			throw new CourseNotFoundException(isbn);
		}

		long studentId = AuthenticateUtil.getStudentId();

		// check student is already enroll into course
		ManagesBook alreadyEnroll = managesBookRepositoryIfc.findByStudentIdAndBook(studentId, book);
		if (alreadyEnroll != null) {
			throw new UserAlreadyEnrollIntoCourseException(book.getTitle());
		}

		ManagesBook managesBook = new ManagesBook();
		managesBook.setStudentId(studentId);
		managesBook.setDateBorrow(LocalDateTime.now());
		managesBook.setBook(book);
		managesBookRepositoryIfc.save(managesBook);

		thirdPartyAPIServiceIfc.createFinanceServiceInvoice(studentId, new BigDecimal(1.00), PaymentType.LIBRARY_FEES);
		
		int remainingCopies = book.getCopies() - 1;
		book.setCopies(remainingCopies);
		bookRepositoryIfc.save(book);

	}
	
	@Transactional
	@Override
	public void returnBook(String isbn) throws CourseNotFoundException, UserAlreadyEnrollIntoCourseException {
		Book book = bookRepositoryIfc.findByIsbn(isbn);
		if (book == null) {
			throw new CourseNotFoundException(isbn);
		}

		long studentId = AuthenticateUtil.getStudentId();

		// check student is already enroll into course
		ManagesBook alreadyEnroll = managesBookRepositoryIfc.findByStudentIdAndBook(studentId, book);
		if(alreadyEnroll == null) {
			//throw error by saying not 
		}
		
		if(alreadyEnroll.getDateReturn() != null) {
			//book already returned
		}
		
		LocalDateTime todayDate = LocalDateTime.now();
		alreadyEnroll.setDateReturn(todayDate);
		managesBookRepositoryIfc.save(alreadyEnroll);

		//for fine purpose
		LocalDateTime dateBorrow = alreadyEnroll.getDateBorrow();
		
		// if return date - NUMBER_OF_DAYS_BOOK_BORROW > dateBorrow then books does not return in time
		if(todayDate.minusDays(NUMBER_OF_DAYS_BOOK_BORROW).isAfter(dateBorrow)) {
			thirdPartyAPIServiceIfc.createFinanceServiceInvoice(studentId, new BigDecimal(3.00), PaymentType.LIBRARY_FINE);
		}
		
		int remainingCopies = book.getCopies() + 1;
		book.setCopies(remainingCopies);
		bookRepositoryIfc.save(book);
	}

}
