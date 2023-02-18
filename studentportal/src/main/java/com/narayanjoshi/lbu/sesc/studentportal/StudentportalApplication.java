package com.narayanjoshi.lbu.sesc.studentportal;

import com.narayanjoshi.lbu.sesc.studentportal.domain.Course;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

import java.util.Random;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class, DataSourceTransactionManagerAutoConfiguration.class, HibernateJpaAutoConfiguration.class})
public class StudentportalApplication  { //implements CommandLineRunner

	public static void main(String[] args) {
		SpringApplication.run(StudentportalApplication.class, args);
	}

//	@Override
//	public void run(String... args) throws Exception {
//		Course course= new Course();
//		course.setCourseId("");
//		course.setDescription();
//		course.setLecture();
//		course.setName();
//		course.setLectureType();
//		course.setStartTime();
//		course.setEndTime();
//		course.setLectureRoom();
//	}
}
