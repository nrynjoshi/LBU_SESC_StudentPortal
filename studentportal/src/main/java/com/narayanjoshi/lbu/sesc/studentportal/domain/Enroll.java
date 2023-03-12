package com.narayanjoshi.lbu.sesc.studentportal.domain;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.narayanjoshi.lbu.sesc.studentportal.constant.IntakeEnum;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "enroll")
public class Enroll  extends Common{

    @Column(name = "student_id")
    private long studentId;

    @Column(name = "intake")
    @Enumerated(EnumType.STRING)
    private IntakeEnum intake;
    @Column(name = "date")
    private LocalDateTime date;

    @OneToOne
    @JoinColumn(name="course_id",referencedColumnName="course_id")
    @ToString.Exclude
    private Course course = new Course();
    
    @Transient
    private String courseId;
}
