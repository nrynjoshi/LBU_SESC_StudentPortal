package com.narayanjoshi.lbu.sesc.studentportal.doa;

import com.narayanjoshi.lbu.sesc.studentportal.domain.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRepositoryIfc extends JpaRepository<Course, Long> {

	@Query("FROM Course WHERE title LIKE %:title%")
	@Modifying
	List<Course> searchByTitleKeyword(String title);

	Course findByCourseId(String courseId);
}
