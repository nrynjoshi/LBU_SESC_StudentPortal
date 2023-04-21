package com.narayanjoshi.lbu.sesc.studentportal.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.narayanjoshi.lbu.sesc.studentportal.doa.BookRepositoryIfc;
import com.narayanjoshi.lbu.sesc.studentportal.domain.Book;
import com.narayanjoshi.lbu.sesc.studentportal.service.BookServiceIfc;

@Service
public class BookServiceImpl implements BookServiceIfc {

	private BookRepositoryIfc bookRepositoryIfc;

	BookServiceImpl(BookRepositoryIfc bookRepositoryIfc) {
		this.bookRepositoryIfc = bookRepositoryIfc;
	}

	@Override
	public void createBook(Book course) {
		bookRepositoryIfc.save(course);
	}

	@Override
	public List<Book> findAllBook() {
		return bookRepositoryIfc.findAll();
	}

	@Override
	public List<Book> searchBooks(String name) {
		return bookRepositoryIfc.searchByTitleKeyword(name);
	}

}
