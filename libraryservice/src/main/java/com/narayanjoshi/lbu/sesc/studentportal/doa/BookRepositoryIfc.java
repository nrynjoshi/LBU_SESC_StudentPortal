package com.narayanjoshi.lbu.sesc.studentportal.doa;

import java.util.List;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.narayanjoshi.lbu.sesc.studentportal.domain.Book;

@Repository
public interface BookRepositoryIfc extends JpaRepository<Book, Long> {

	@Query("FROM Book WHERE title LIKE %:title%")
	@Modifying
	List<Book> searchByTitleKeyword(@NotNull @NotEmpty String title);

	Book findByIsbn(@NotNull @NotEmpty String isbn);
}
