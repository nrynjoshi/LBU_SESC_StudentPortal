package com.narayanjoshi.lbu.sesc.studentportal.config;

import com.narayanjoshi.lbu.sesc.studentportal.constant.LectureTypeEnum;
import com.narayanjoshi.lbu.sesc.studentportal.domain.Course;
import com.narayanjoshi.lbu.sesc.studentportal.service.CourseServiceIfc;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

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
       List<Course> courseList = courseServiceIfc.findCourse();
       if(courseList.isEmpty()){
           Course cloudComputingCourse= new Course("Cloud Computing Development", "Software engineering course", "John", "JG001", LectureTypeEnum.PHYSICAL,"9:00 am", "10:00 am", "COMP637");
           this.courseServiceIfc.createCourse(cloudComputingCourse);

           Course databaseCourse= new Course("Database Systems", "Software engineering course", "John", null, LectureTypeEnum.RECORDED,"10:00 am", "11:00 am", "COMP712");
           this.courseServiceIfc.createCourse(databaseCourse);

           Course advanceSoftwareCourse= new Course("Advanced Software Engineering", "Software engineering course", "John", "JG005", LectureTypeEnum.PHYSICAL,"11:00 am", "12:00 pm", "COMP725");
           this.courseServiceIfc.createCourse(advanceSoftwareCourse);

           Course researchCourse= new Course("Research Practice", "Research course", "John", null, LectureTypeEnum.ONLINE,"12:00 pm", "1:00 pm", "COMP738");
           this.courseServiceIfc.createCourse(researchCourse);

           Course projectCourse= new Course("Project Management", "Management course", "John", "JG012 Computer Lab", LectureTypeEnum.PHYSICAL,"1:00 pm", "2:00 pm", "COMP753");
           this.courseServiceIfc.createCourse(projectCourse);

           Course serviceComputingCourse= new Course("Software Engineering for Service Computing", "Service computing course", "John", null, LectureTypeEnum.ONLINE,"2:00 pm", "3:00 pm", "COMP758");
           this.courseServiceIfc.createCourse(serviceComputingCourse);

           Course dissertationCourse= new Course("Msc Dissertation", "Dissertation course", "John", null, LectureTypeEnum.ONLINE,"3:00 pm", "4:00 pm", "COMP763");
           this.courseServiceIfc.createCourse(dissertationCourse);

           Course softwareCourse= new Course("Software And System", "IOT course", "John", "JG001", LectureTypeEnum.PHYSICAL,"4:00 am", "5:00 am", "COMP703");
           this.courseServiceIfc.createCourse(softwareCourse);

           Course datawareHourseCourse= new Course("Data ware house", "Big data course", "John", "JG001", LectureTypeEnum.PHYSICAL,"5:00 pm", "6:00 pm", "COMP713");
           this.courseServiceIfc.createCourse(datawareHourseCourse);
       }
    }

}
