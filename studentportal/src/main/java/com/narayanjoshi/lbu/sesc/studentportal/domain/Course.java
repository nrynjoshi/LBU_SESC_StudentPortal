package com.narayanjoshi.lbu.sesc.studentportal.domain;

import com.narayanjoshi.lbu.sesc.studentportal.constant.LectureTypeEnum;
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
@Table(name = "course")
public class Course extends Common{

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "lecture")
    private String lecture;

    @Column(name = "lecture_room")
    private String lectureRoom;

    @Column(name = "lecture_type")
    private LectureTypeEnum lectureType;

    @Column(name = "starttime")
    private String startTime;

    @Column(name = "end_time")
    private String endTime;

    @Column(name = "course_id")
    private long courseId;



}
