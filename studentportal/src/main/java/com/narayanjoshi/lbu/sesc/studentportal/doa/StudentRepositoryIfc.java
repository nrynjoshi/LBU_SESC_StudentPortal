package com.narayanjoshi.lbu.sesc.studentportal.doa;

import com.narayanjoshi.lbu.sesc.studentportal.domain.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepositoryIfc extends JpaRepository<Student, Long> {

    Student findByStudentId(long studentId);

    Student findByEmail(String email);

}
