package com.narayanjoshi.lbu.sesc.studentportal.service.impl;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

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

		ManagesBook enroll = new ManagesBook();
		enroll.setStudentId(studentId);
		enroll.setDateBorrow(LocalDateTime.now());
		enroll.setBook(book);
		managesBookRepositoryIfc.save(enroll);

		thirdPartyAPIServiceIfc.createFinanceServiceInvoice(studentId, new BigDecimal(10.00), PaymentType.LIBRARY_FEES);

	}
	
	@Override
	public void returnBook(String isbn) throws CourseNotFoundException, UserAlreadyEnrollIntoCourseException {
		Book book = bookRepositoryIfc.findByIsbn(isbn);
		if (book == null) {
			throw new CourseNotFoundException(isbn);
		}

		long studentId = AuthenticateUtil.getStudentId();

		// check student is already enroll into course
		ManagesBook alreadyEnroll = managesBookRepositoryIfc.findByStudentIdAndBook(studentId, book);
		
		alreadyEnroll.setDateReturn(LocalDateTime.now());
		managesBookRepositoryIfc.save(alreadyEnroll);

		thirdPartyAPIServiceIfc.createFinanceServiceInvoice(studentId, new BigDecimal(10.00), PaymentType.LIBRARY_FEES);

	}

}
