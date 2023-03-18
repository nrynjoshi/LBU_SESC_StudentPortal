package com.narayanjoshi.lbu.sesc.studentportal.domain;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
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
@JsonInclude(Include.NON_DEFAULT)
@Table(name = "course")
public class Course extends Common {

	@JsonProperty("course_id")
	@Column(name = "course_id", unique = true, nullable = false)
	private String courseId;

	@JsonProperty("title")
	@Column(name = "title", nullable = false)
	private String title;

	@JsonProperty("description")
	@Column(name = "description", length = 1000, nullable = false)
	private String description;

	@JsonProperty("fee")
	@Column(name = "fee", nullable = false)
	private BigDecimal fee;

}
