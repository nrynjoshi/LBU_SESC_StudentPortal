package com.narayanjoshi.lbu.sesc.studentportal.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

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
@Table(name = "account")
public class Account extends Common {
	
	@JsonProperty("student_id")
	@Column(name = "studentId", unique = true, nullable = false)
	@NotNull
	private long studentId;

	private String password; //this will be dummy password from the default for now
	
	@Transient
	private String username;
}
