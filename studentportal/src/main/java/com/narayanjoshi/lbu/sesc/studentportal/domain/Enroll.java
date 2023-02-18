package com.narayanjoshi.lbu.sesc.studentportal.domain;

import com.narayanjoshi.lbu.sesc.studentportal.constant.IntakeEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.sql.Timestamp;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "enroll")
public class Enroll  extends Common{

    @Column(name = "student_id")
    private String studentId;
    @Column(name = "course_id")
    private String courseId;
    @Column(name = "intake")
    private IntakeEnum intake;
    @Column(name = "date")
    private Timestamp date;

}
