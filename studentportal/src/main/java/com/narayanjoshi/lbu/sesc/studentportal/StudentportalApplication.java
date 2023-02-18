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

@SpringBootApplication
public class StudentportalApplication  { 

	public static void main(String[] args) {
		SpringApplication.run(StudentportalApplication.class, args);
	}

}
