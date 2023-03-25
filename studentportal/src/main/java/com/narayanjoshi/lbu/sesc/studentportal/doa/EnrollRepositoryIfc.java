package com.narayanjoshi.lbu.sesc.studentportal.doa;

import java.util.List;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.narayanjoshi.lbu.sesc.studentportal.domain.Course;
import com.narayanjoshi.lbu.sesc.studentportal.domain.Enroll;

@Repository
public interface EnrollRepositoryIfc extends JpaRepository<Enroll, Long> {

	List<Enroll> findByStudentId(@NotNull @NotEmpty Long studentId);

	Enroll findByStudentIdAndCourse(@NotNull @NotEmpty Long studentId,@NotNull Course course);
}
