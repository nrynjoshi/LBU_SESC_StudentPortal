package com.narayanjoshi.lbu.sesc.studentportal.doa;

import com.narayanjoshi.lbu.sesc.studentportal.domain.Enroll;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EnrollRepositoryIfc extends JpaRepository<Enroll, Long> {

    List<Enroll> findByStudentId(Long studentId);
}
