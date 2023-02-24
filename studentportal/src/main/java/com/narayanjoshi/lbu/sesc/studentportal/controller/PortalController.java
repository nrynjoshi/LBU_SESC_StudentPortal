package com.narayanjoshi.lbu.sesc.studentportal.controller;

import com.narayanjoshi.lbu.sesc.studentportal.domain.Student;
import com.narayanjoshi.lbu.sesc.studentportal.service.CourseServiceIfc;
import com.narayanjoshi.lbu.sesc.studentportal.service.EnrollServiceIfc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

//https://www.codejava.net/frameworks/spring-boot/spring-boot-thymeleaf-form-handling-tutorial
@Controller
@RequestMapping("/portal")
public class PortalController {

    @Autowired private CourseServiceIfc courseServiceIfc;

    @Autowired private EnrollServiceIfc enrollServiceIfc;

    @GetMapping({ "/login"})
    public String login(Model model) {
        model.addAttribute("student", new Student());
        return "/login";
    }

    @PostMapping({ "/login"})
    public String loginPortalSubmit(@ModelAttribute Student student, RedirectAttributes redirectAttributes) {
        System.out.println(student.getEmail());
        System.out.println(student.getPassword());
        return "redirect:/dashboard";
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
        System.out.println(student.getEmail());
        redirectAttributes.addFlashAttribute("message", "success");
        return "redirect:/login";
    }

    @GetMapping({ "/courses"})
    public String viewCourse(Model model) {
        model.addAttribute("courses", courseServiceIfc.findAllCourse());
        return "view-courses-and-enrol";
    }

    @GetMapping({ "/enrollments"})
    public String enrollments(Model model) {
        //model.addAttribute("enrollments", enrollServiceIfc.findAllCourse());
        return "view-courses-and-enrol";
    }

    @GetMapping({ "/profile"})
    public String profilePortalPage(Model model) {
        model.addAttribute("student", new Student());
        return "/view_and_update-student-profile";
    }

    @PostMapping({ "/profile"})
    public String profilePortalSubmit(@ModelAttribute Student student, RedirectAttributes redirectAttributes) {
        System.out.println(student.getEmail());
        return "redirect:/profile";
    }

    @GetMapping({ "/graduation"})
    public String graduationPortalPage(Model model) {
        model.addAttribute("student", new Student());
        return "/graduation";
    }
}
