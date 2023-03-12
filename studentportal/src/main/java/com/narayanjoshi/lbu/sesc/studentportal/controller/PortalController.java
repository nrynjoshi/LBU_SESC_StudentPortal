package com.narayanjoshi.lbu.sesc.studentportal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.narayanjoshi.lbu.sesc.studentportal.domain.Student;
import com.narayanjoshi.lbu.sesc.studentportal.service.CourseServiceIfc;
import com.narayanjoshi.lbu.sesc.studentportal.service.EnrollServiceIfc;
import com.narayanjoshi.lbu.sesc.studentportal.service.StudentServiceIfc;
import com.narayanjoshi.lbu.sesc.studentportal.utils.AuthenticateUtil;

@Controller
@RequestMapping("")
public class PortalController {

    @Autowired private CourseServiceIfc courseServiceIfc;

    @Autowired private EnrollServiceIfc enrollServiceIfc;

    @Autowired private StudentServiceIfc studentServiceIfc;

    @GetMapping("/")
    public String redirectToLogin(){
        return "redirect:/login";
    }

    @GetMapping({ "login"})
    public String login(Model model) {
        model.addAttribute("student", new Student());
        return "index";
    }


    @GetMapping({ "/dashboard"})
    public String dashboardPortalPage(Model model) {
        model.addAttribute("student", new Student());
        return "dashboard";
    }

    @GetMapping({ "/register"})
    public String registerPortalPage(Model model) {
        model.addAttribute("student", new Student());
        return "/register";
    }

    @PostMapping({ "/register"})
    public String registerPortalSubmit(@ModelAttribute Student student, RedirectAttributes redirectAttributes) {
        System.out.println(student.getDob());
        studentServiceIfc.createStudent(student);
        redirectAttributes.addFlashAttribute("message", "success");
        return "redirect:/login";
    }

    @GetMapping({ "/courses"})
    public String viewCourse(Model model) {
        model.addAttribute("courses", courseServiceIfc.findAllCourse());
        return "view-courses-and-enrol";
    }



    @GetMapping({ "/profile"})
    public String profilePortalPage(Model model) {
    	long studentId =  AuthenticateUtil.getStudentId();
        model.addAttribute("student", studentServiceIfc.getStudentById(Long.valueOf(studentId)));
        return "/view_and_update-student-profile";
    }

    @PostMapping({ "/profile"})
    public String profilePortalSubmit(@ModelAttribute Student student, RedirectAttributes redirectAttributes) {

        studentServiceIfc.updateStudent(student);
        return "redirect:/profile";
    }

    @GetMapping({ "/graduation"})
    public String graduationPortalPage(Model model) {
        model.addAttribute("is_eligible_graduated", studentServiceIfc.isEligibleGraduation());
        return "/graduation";
    }

    @GetMapping({ "/logout"})
    public String logout(Model model) {
        return "redirect:/login";
    }

    @GetMapping({ "/enrol/{course_id}"})
    public String enrollIntoCourse(@PathVariable("course_id") String course_id) {
        enrollServiceIfc.enrolIntoCourse(course_id);
        return "redirect:/enrollments";
    }

    @GetMapping({ "/enrollments"})
    public String enrollments(Model model) {
        model.addAttribute("enrollments", enrollServiceIfc.getEnrolCourses());
        return "view-enrollments";
    }
}
