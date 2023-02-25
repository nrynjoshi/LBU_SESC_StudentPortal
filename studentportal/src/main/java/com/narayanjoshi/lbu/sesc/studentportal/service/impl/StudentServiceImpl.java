package com.narayanjoshi.lbu.sesc.studentportal.service.impl;

import com.narayanjoshi.lbu.sesc.studentportal.doa.StudentRepositoryIfc;
import com.narayanjoshi.lbu.sesc.studentportal.domain.Student;
import com.narayanjoshi.lbu.sesc.studentportal.service.StudentServiceIfc;
import com.narayanjoshi.lbu.sesc.studentportal.utils.Util;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Random;

@Service
@Transactional
public class StudentServiceImpl implements StudentServiceIfc {

    @Autowired private StudentRepositoryIfc studentRepositoryIfc;

    @Override
    public long loginStudent(Student student) throws Exception {
        Student dbStudentRecord = studentRepositoryIfc.findByEmail(student.getEmail());
        if(dbStudentRecord!=null && StringUtils.equals(student.getPassword(), dbStudentRecord.getPassword())){
            return dbStudentRecord.getStudentId();
        }
        throw new Exception("Login Failed.");
    }

    @Override
    public void createStudent(Student student){
        student.setStudentId(Util.generateStudentId());
        studentRepositoryIfc.save(student);

        //TODO: When a student is created, a request is sent to the Finance microservice to create an account.

        //TODO: When a student is created, a request is sent to the Library microservice to create an account.
    }

    @Override
    public void updateStudent(Student student){
        Student dbStudentRecord = getStudentById(student.getStudentId());
        if(StringUtils.isBlank(student.getPassword())){
            dbStudentRecord.setPassword(dbStudentRecord.getPassword());
        }else{
            dbStudentRecord.setPassword(student.getPassword());
        }

        dbStudentRecord.setMobileNumber(student.getMobileNumber());
        dbStudentRecord.setHomeAddress(student.getHomeAddress());
        dbStudentRecord.setDob(student.getDob());
        dbStudentRecord.setFullname(student.getFullname());
        dbStudentRecord.setEmail(student.getEmail());

        studentRepositoryIfc.save(dbStudentRecord);
    }

    @Override
    public Student getStudentById(long studentId){
        Student student = studentRepositoryIfc.findByStudentId(studentId);
        return student;
    }

    @Override
    public boolean getGraduation(long studentId){
        //TODO: Upon checking the eligibility to graduate, a request is sent to the Finance microservice to see if there are any outstanding invoices.
        return false;
    }

}
