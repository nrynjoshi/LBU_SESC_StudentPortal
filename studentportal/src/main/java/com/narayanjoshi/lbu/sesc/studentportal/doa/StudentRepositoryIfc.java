package com.narayanjoshi.lbu.sesc.studentportal.doa;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.narayanjoshi.lbu.sesc.studentportal.domain.Student;

@Repository
public interface StudentRepositoryIfc extends JpaRepository<Student, Long> {

	Student findByStudentId(@NotNull @NotEmpty long studentId);

	Student findByUsername(@NotNull @NotEmpty String username);

}
