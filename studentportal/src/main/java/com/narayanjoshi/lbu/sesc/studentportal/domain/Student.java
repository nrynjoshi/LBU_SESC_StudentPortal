package com.narayanjoshi.lbu.sesc.studentportal.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDate;

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
    private LocalDate dob;

    @Column(name = "mobile_number", unique = true)
    private String mobileNumber;

    @Column(name = "home_address")
    private String homeAddress;

    @Column(name = "email_address", unique = true)
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "student_id", unique = true)
    private long studentId;

    @Column(name = "is_graduate")
    private boolean isGraduate;


}
