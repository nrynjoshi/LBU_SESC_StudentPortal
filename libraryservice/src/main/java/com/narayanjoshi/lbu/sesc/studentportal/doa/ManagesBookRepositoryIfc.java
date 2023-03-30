package com.narayanjoshi.lbu.sesc.studentportal.doa;

import java.util.List;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.narayanjoshi.lbu.sesc.studentportal.domain.Book;
import com.narayanjoshi.lbu.sesc.studentportal.domain.ManagesBook;

@Repository
public interface ManagesBookRepositoryIfc extends JpaRepository<ManagesBook, Long> {

	List<ManagesBook> findByStudentId(@NotNull @NotEmpty Long studentId);

	ManagesBook findByStudentIdAndBook(@NotNull @NotEmpty Long studentId,@NotNull Book book);
}
