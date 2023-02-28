package com.narayanjoshi.lbu.sesc.studentportal.controller;

import com.narayanjoshi.lbu.sesc.studentportal.constant.KeyConstant;
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

@Controller
@RequestMapping("/portal")
public class PortalController {

    @Autowired private CourseServiceIfc courseServiceIfc;

    @Autowired private EnrollServiceIfc enrollServiceIfc;

    @Autowired private StudentServiceIfc studentServiceIfc;

    @GetMapping({ "/login"})
    public String login(Model model) {
        //clear cookies if any exist.
        if(CookieUtils.isCookieExist(KeyConstant.STUDENT_ID)){
            return "redirect:/portal/dashboard";
        }

        model.addAttribute("student", new Student());
        return "/login";
    }

    @PostMapping({ "/login"})
    public String loginPortalSubmit(@ModelAttribute Student student, RedirectAttributes redirectAttributes) throws Exception {
        System.out.println(student.getEmail());

        System.out.println(student.getPassword());
        long studentId = studentServiceIfc.loginStudent(student);

        CookieUtils.setCookie(KeyConstant.STUDENT_ID, studentId);

        return "redirect:/portal/dashboard";
    }

    @GetMapping({ "/dashboard"})
    public String dashboardPortalPage(@CookieValue(value = KeyConstant.STUDENT_ID) String studentId, Model model) {
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
        return "redirect:/portal/login";
    }

    @GetMapping({ "/courses"})
    public String viewCourse(@CookieValue(value = KeyConstant.STUDENT_ID) String studentId, Model model) {
        model.addAttribute("courses", courseServiceIfc.findAllCourse());
        return "view-courses-and-enrol";
    }



    @GetMapping({ "/profile"})
    public String profilePortalPage(@CookieValue(value = KeyConstant.STUDENT_ID) String studentId, Model model) {
        model.addAttribute("student", studentServiceIfc.getStudentById(Long.valueOf(studentId)));
        return "/view_and_update-student-profile";
    }

    @PostMapping({ "/profile"})
    public String profilePortalSubmit(@CookieValue(value = KeyConstant.STUDENT_ID) String studentId, @ModelAttribute Student student, RedirectAttributes redirectAttributes) {
        System.out.println(student.getEmail());

        student.setStudentId(Long.valueOf(studentId));
        studentServiceIfc.updateStudent(student);
        return "redirect:/portal/profile";
    }

    @GetMapping({ "/graduation"})
    public String graduationPortalPage(@CookieValue(value = KeyConstant.STUDENT_ID) String studentId, Model model) {
        model.addAttribute("is_graduated", studentServiceIfc.getGraduation(Long.valueOf(studentId)));
        return "/graduation";
    }

    @GetMapping({ "/logout"})
    public String logout(@CookieValue(value = KeyConstant.STUDENT_ID) String studentId, Model model) {
        CookieUtils.deleteCookie(KeyConstant.STUDENT_ID);
        return "redirect:/portal/login";
    }

    @GetMapping({ "/enrol/{course_id}"})
    public String enrollIntoCourse(@CookieValue(value = KeyConstant.STUDENT_ID) String studentId, @PathVariable("course_id") String course_id) {

        enrollServiceIfc.enrolIntoCourse(Long.valueOf(studentId), course_id);
        return "redirect:/portal/enrollments";
    }

    @GetMapping({ "/enrollments"})
    public String enrollments(@CookieValue(value = KeyConstant.STUDENT_ID) String studentId, Model model) {
        model.addAttribute("enrollments", enrollServiceIfc.getEnrolCourses(Long.valueOf(studentId)));
        return "view-enrollments";
    }
}
