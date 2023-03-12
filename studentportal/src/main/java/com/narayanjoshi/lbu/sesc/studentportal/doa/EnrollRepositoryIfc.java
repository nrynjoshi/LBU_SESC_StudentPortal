package com.narayanjoshi.lbu.sesc.studentportal.doa;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.narayanjoshi.lbu.sesc.studentportal.domain.Course;
import com.narayanjoshi.lbu.sesc.studentportal.domain.Enroll;

@Repository
public interface EnrollRepositoryIfc extends JpaRepository<Enroll, Long> {

    List<Enroll> findByStudentId(Long studentId);
    Enroll findByStudentIdAndCourse(Long studentId, Course course);
}
