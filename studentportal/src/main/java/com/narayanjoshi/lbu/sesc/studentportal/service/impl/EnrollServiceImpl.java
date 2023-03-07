package com.narayanjoshi.lbu.sesc.studentportal.service.impl;

import com.narayanjoshi.lbu.sesc.studentportal.constant.HostUrl;
import com.narayanjoshi.lbu.sesc.studentportal.constant.IntakeEnum;
import com.narayanjoshi.lbu.sesc.studentportal.doa.EnrollRepositoryIfc;
import com.narayanjoshi.lbu.sesc.studentportal.domain.Course;
import com.narayanjoshi.lbu.sesc.studentportal.domain.Enroll;
import com.narayanjoshi.lbu.sesc.studentportal.service.EnrollServiceIfc;
import com.narayanjoshi.lbu.sesc.studentportal.utils.HttpUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.ServletResponseMethodArgumentResolver;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class EnrollServiceImpl implements EnrollServiceIfc {

    @Autowired private EnrollRepositoryIfc enrollRepositoryIfc;

    @Autowired private HttpUtil httpUtil;

    @Override
    public List<Enroll> getEnrolCourses(long studentId){
        return enrollRepositoryIfc.findByStudentId(studentId);
    }

    @Override
    public void enrolIntoCourse(long studentId, String courseId){
        Enroll enroll= new Enroll();
        enroll.setCourseId(courseId);
        enroll.setStudentId(studentId);
        enroll.setDate(LocalDateTime.now());
        enroll.setIntake(IntakeEnum.JAN);
        enrollRepositoryIfc.save(enroll);

        createFinanceServiceInvoice(studentId);
    }

    /**
     * When you enrol in a course, a request is sent to the Finance microservice to create an invoice.
     * @param studentId
     */
    private void createFinanceServiceInvoice(long studentId) {
        //TODO:
        Map<String, Object> createFinanceServiceMap = new HashMap();
        
        createFinanceServiceMap.put("amount", 100.00);
        createFinanceServiceMap.put("dueDate", "2022-11-06");
        createFinanceServiceMap.put("type", "TUITION_FEES");
        
        Map<String, Object> accountMap = new HashMap();
        accountMap.put("studentId", String.valueOf(studentId));
        
        createFinanceServiceMap.put("account", accountMap);
        httpUtil.post(HostUrl.FINANCE_CREATE_INVOICE, createFinanceServiceMap);
    }

}
