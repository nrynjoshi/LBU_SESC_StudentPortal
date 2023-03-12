package com.narayanjoshi.lbu.sesc.studentportal.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

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

    @Column(name = "fullname")
    private String fullname;

    @Column(name = "dob")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    private Date dob;

    @Column(name = "mobile_number", unique = true)
    private String mobileNumber;

    @Column(name = "home_address")
    private String homeAddress;

    @Column(name = "email_address", unique = true)
    private String email;
    
    @Column(name = "username", unique = true)
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "student_id", unique = true)
    private long studentId;

}
