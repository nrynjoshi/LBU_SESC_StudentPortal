package com.narayanjoshi.lbu.sesc.studentportal.doa;

import com.narayanjoshi.lbu.sesc.studentportal.domain.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface StudentRepositoryIfc extends JpaRepository<Student, Long> {

    Student findByStudentId(long studentId);

    Student findByUsername(String username);

}
