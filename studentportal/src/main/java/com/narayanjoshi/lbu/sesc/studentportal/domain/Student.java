package com.narayanjoshi.lbu.sesc.studentportal.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "student")
public class Student  extends Common{

	@JsonProperty("fullname")
    @Column(name = "fullname", nullable = false)
    private String fullname;

	@JsonProperty("mobile_number")
    @Column(name = "mobile_number", unique = true)
    private String mobileNumber;

	@JsonProperty("home_address")
    @Column(name = "home_address")
    private String homeAddress;

	@JsonProperty("email_address")
    @Column(name = "email_address", unique = true, nullable = false)
    private String email;
    
	@JsonProperty("username")
    @Column(name = "username", unique = true, nullable = false)
    private String username;

	@JsonProperty("password")
    @Column(name = "password", nullable = false)
    private String password;

	@JsonProperty("student_id")
    @Column(name = "student_id", unique = true, nullable = false)
    private long studentId;

}
