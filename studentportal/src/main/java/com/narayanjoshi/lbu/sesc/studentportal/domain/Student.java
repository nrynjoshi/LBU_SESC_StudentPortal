package com.narayanjoshi.lbu.sesc.studentportal.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "student")
public class Student  extends Common{

    @Column(name = "name")
    private String name;

    @Column(name = "dob")
    private String dob;

    @Column(name = "mobile_number")
    private String mobileNumber;

    @Column(name = "home_address")
    private String homeAddress;

    @Column(name = "email_address")
    private String email;

    @Column(name = "student_id")
    private String studentId;

    @Column(name = "is_graduate")
    private boolean isGraduate;


}
