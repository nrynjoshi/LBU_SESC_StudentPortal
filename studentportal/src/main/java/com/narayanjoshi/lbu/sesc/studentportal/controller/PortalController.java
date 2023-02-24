package com.narayanjoshi.lbu.sesc.studentportal.controller;

import com.narayanjoshi.lbu.sesc.studentportal.domain.Student;
import com.narayanjoshi.lbu.sesc.studentportal.service.CourseServiceIfc;
import com.narayanjoshi.lbu.sesc.studentportal.service.EnrollServiceIfc;
import com.narayanjoshi.lbu.sesc.studentportal.service.StudentServiceIfc;
import com.narayanjoshi.lbu.sesc.studentportal.utils.CookieUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.Cookie;

//https://www.codejava.net/frameworks/spring-boot/spring-boot-thymeleaf-form-handling-tutorial
@Controller
@RequestMapping("/portal")
public class PortalController {

    @Autowired private CourseServiceIfc courseServiceIfc;

    @Autowired private EnrollServiceIfc enrollServiceIfc;

    @Autowired private StudentServiceIfc studentServiceIfc;

    @GetMapping({ "/login"})
    public String login(Model model) {
        //clear cookies if any exist.
        CookieUtils.deleteCookie("studentId");

        model.addAttribute("student", new Student());
        return "/login";
    }

    @PostMapping({ "/login"})
    public String loginPortalSubmit(@ModelAttribute Student student, RedirectAttributes redirectAttributes) throws Exception {
        System.out.println(student.getEmail());
        System.out.println(student.getPassword());
        long studentId = studentServiceIfc.loginStudent(student);

        CookieUtils.setCookie("studentId", studentId);

        return "redirect:/portal/dashboard";
    }

    @GetMapping({ "/dashboard"})
    public String dashboardPortalPage(@CookieValue(value = "studentId") long studentId, Model model) {
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
        System.out.println(student.getEmail());
        studentServiceIfc.createStudent(student);
        redirectAttributes.addFlashAttribute("message", "success");
        return "redirect:/portal/login";
    }

    @GetMapping({ "/courses"})
    public String viewCourse(@CookieValue(value = "studentId") long studentId, Model model) {
        model.addAttribute("courses", courseServiceIfc.findAllCourse());
        return "view-courses-and-enrol";
    }

    @GetMapping({ "/enrollments"})
    public String enrollments(@CookieValue(value = "studentId") long studentId, Model model) {
        model.addAttribute("enrollments", enrollServiceIfc.getEnrolCourses());
        return "view-courses-and-enrol";
    }

    @GetMapping({ "/profile"})
    public String profilePortalPage(@CookieValue(value = "studentId") long studentId, Model model) {
        model.addAttribute("student", studentServiceIfc.getStudentById(studentId));
        return "/view_and_update-student-profile";
    }

    @PostMapping({ "/profile"})
    public String profilePortalSubmit(@CookieValue(value = "studentId") long studentId, @ModelAttribute Student student, RedirectAttributes redirectAttributes) {
        System.out.println(student.getEmail());

        student.setStudentId(studentId);
        studentServiceIfc.updateStudent(student);
        return "redirect:/portal/profile";
    }

    @GetMapping({ "/graduation"})
    public String graduationPortalPage(@CookieValue(value = "studentId") long studentId, Model model) {
        model.addAttribute("student", studentServiceIfc.getGraduation(studentId));
        return "/graduation";
    }

    @GetMapping({ "/logout"})
    public String logout(@CookieValue(value = "studentId") long studentId, Model model) {
        CookieUtils.deleteCookie("studentId");
        return "return:/";
    }
}
