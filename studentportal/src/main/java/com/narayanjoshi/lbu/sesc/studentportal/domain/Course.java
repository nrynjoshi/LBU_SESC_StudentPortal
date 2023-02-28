package com.narayanjoshi.lbu.sesc.studentportal.domain;

import com.narayanjoshi.lbu.sesc.studentportal.constant.LectureTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "course")
public class Course extends Common{

    @Column(name = "name")
    private String name;

    @Column(name = "description", length = 1000)
    private String description;

    @Column(name = "lecture")
    private String lecture;

    @Column(name = "lecture_room")
    private String lectureRoom;

    @Column(name = "lecture_type")
    @Enumerated(EnumType.STRING)
    private LectureTypeEnum lectureType;

    @Column(name = "starttime")
    private LocalTime startTime;

    @Column(name = "end_time")
    private LocalTime endTime;

    @Column(name = "course_id", unique = true)
    private String courseId;



}
