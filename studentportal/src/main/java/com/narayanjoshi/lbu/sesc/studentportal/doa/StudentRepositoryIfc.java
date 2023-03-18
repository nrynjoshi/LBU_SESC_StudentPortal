package com.narayanjoshi.lbu.sesc.studentportal.doa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.narayanjoshi.lbu.sesc.studentportal.domain.Student;

@Repository
public interface StudentRepositoryIfc extends JpaRepository<Student, Long> {

	Student findByStudentId(long studentId);

	Student findByUsername(String username);

}
