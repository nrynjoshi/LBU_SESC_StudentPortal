package com.narayanjoshi.lbu.sesc.studentportal.config;

import com.narayanjoshi.lbu.sesc.studentportal.constant.LectureTypeEnum;
import com.narayanjoshi.lbu.sesc.studentportal.domain.Course;
import com.narayanjoshi.lbu.sesc.studentportal.service.CourseServiceIfc;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.time.LocalTime;
import java.util.List;

@Component
public class StudentPortalInitializer {

    private CourseServiceIfc courseServiceIfc;

    StudentPortalInitializer(CourseServiceIfc courseServiceIfc) {
        this.courseServiceIfc = courseServiceIfc;
    }


    @EventListener(ApplicationReadyEvent.class)
    public void appInit() {
        loadCourse();
    }

    private void loadCourse() {
       List<Course> courseList = courseServiceIfc.findAllCourse();
       if(courseList.isEmpty()){
           Course cloudComputingCourse= new Course("Cloud Computing Development", "Software engineering course", "John", "JG001", LectureTypeEnum.PHYSICAL, LocalTime.of(00,00), LocalTime.of(00,00), "COMP637");
           this.courseServiceIfc.createCourse(cloudComputingCourse);

           Course databaseCourse= new Course("Database Systems", "Software engineering course", "John", null, LectureTypeEnum.RECORDED,LocalTime.of(00,00), LocalTime.of(00,00), "COMP712");
           this.courseServiceIfc.createCourse(databaseCourse);

           Course advanceSoftwareCourse= new Course("Advanced Software Engineering", "Software engineering course", "John", "JG005", LectureTypeEnum.PHYSICAL,LocalTime.of(00,00), LocalTime.of(00,00), "COMP725");
           this.courseServiceIfc.createCourse(advanceSoftwareCourse);

           Course researchCourse= new Course("Research Practice", "Research course", "John", null, LectureTypeEnum.ONLINE,LocalTime.of(00,00), LocalTime.of(00,00), "COMP738");
           this.courseServiceIfc.createCourse(researchCourse);

           Course projectCourse= new Course("Project Management", "Management course", "John", "JG012 Computer Lab", LectureTypeEnum.PHYSICAL,LocalTime.of(00,00), LocalTime.of(00,00), "COMP753");
           this.courseServiceIfc.createCourse(projectCourse);

           Course serviceComputingCourse= new Course("Software Engineering for Service Computing", "Service computing course", "John", null, LectureTypeEnum.ONLINE,LocalTime.of(00,00), LocalTime.of(00,00), "COMP758");
           this.courseServiceIfc.createCourse(serviceComputingCourse);

           Course dissertationCourse= new Course("Msc Dissertation", "Dissertation course", "John", null, LectureTypeEnum.ONLINE,LocalTime.of(00,00), LocalTime.of(00,00), "COMP763");
           this.courseServiceIfc.createCourse(dissertationCourse);

           Course softwareCourse= new Course("Software And System", "IOT course", "John", "JG001", LectureTypeEnum.PHYSICAL,LocalTime.of(00,00), LocalTime.of(00,00), "COMP703");
           this.courseServiceIfc.createCourse(softwareCourse);

           Course datawareHourseCourse= new Course("Data ware house", "Big data course", "John", "JG001", LectureTypeEnum.PHYSICAL,LocalTime.of(00,00), LocalTime.of(00,00), "COMP713");
           this.courseServiceIfc.createCourse(datawareHourseCourse);
       }
    }

}
