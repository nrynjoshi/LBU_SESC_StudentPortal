package com.narayanjoshi.lbu.sesc.studentportal.config;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import com.narayanjoshi.lbu.sesc.studentportal.domain.Course;
import com.narayanjoshi.lbu.sesc.studentportal.service.CourseServiceIfc;

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

           Course cloudComputingCourse= new Course("COMP637", "Cloud Computing Development", null, new BigDecimal(350.00) );
           this.courseServiceIfc.createCourse(cloudComputingCourse);

           Course databaseCourse= new Course("COMP712", "Database Systems", null, new BigDecimal(230.30));
           this.courseServiceIfc.createCourse(databaseCourse);

           Course advanceSoftwareCourse= new Course("COMP725", "Advanced Software Engineering", null, new BigDecimal(350.00));
           this.courseServiceIfc.createCourse(advanceSoftwareCourse);

           Course researchCourse= new Course("COMP738", "Research Practice", null, new BigDecimal(480.50));
           this.courseServiceIfc.createCourse(researchCourse);

           Course projectCourse= new Course("COMP753", "Project Management", null, new BigDecimal(360.00));
           this.courseServiceIfc.createCourse(projectCourse);

           Course serviceComputingCourse= new Course("COMP758", "Software Engineering for Service Computing", null, new BigDecimal(615.00));
           this.courseServiceIfc.createCourse(serviceComputingCourse);

           Course dissertationCourse= new Course("COMP763", "Msc Dissertation", null, new BigDecimal(550.00));
           this.courseServiceIfc.createCourse(dissertationCourse);

           Course softwareCourse= new Course("COMP703", "Software And System", null, new BigDecimal(200.18));
           this.courseServiceIfc.createCourse(softwareCourse);

           Course datawareHourseCourse= new Course("COMP713", "Data ware house", null, new BigDecimal(700.12));
           this.courseServiceIfc.createCourse(datawareHourseCourse);
       }
    }

}
