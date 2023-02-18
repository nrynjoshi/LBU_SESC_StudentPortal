package com.narayanjoshi.lbu.sesc.studentportal.doa;

import com.narayanjoshi.lbu.sesc.studentportal.domain.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepositoryIfc extends JpaRepository<Course, Long> {
}
