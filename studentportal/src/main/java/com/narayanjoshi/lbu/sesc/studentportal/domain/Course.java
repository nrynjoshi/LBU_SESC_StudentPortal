package com.narayanjoshi.lbu.sesc.studentportal.domain;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "course")
public class Course extends Common{
	
	@Column(name = "course_id", unique = true)
    private String courseId;

    @Column(name = "title")
    private String title;

    @Column(name = "description", length = 1000)
    private String description;
    
    @Column(name = "fee")
    private BigDecimal fee;

    

}
