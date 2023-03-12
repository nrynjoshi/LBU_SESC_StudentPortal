package com.narayanjoshi.lbu.sesc.studentportal.service.impl;

import java.math.BigDecimal;

import javax.transaction.Transactional;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.narayanjoshi.lbu.sesc.studentportal.doa.StudentRepositoryIfc;
import com.narayanjoshi.lbu.sesc.studentportal.domain.Student;
import com.narayanjoshi.lbu.sesc.studentportal.exception.AuthenticationException;
import com.narayanjoshi.lbu.sesc.studentportal.service.EnrollServiceIfc;
import com.narayanjoshi.lbu.sesc.studentportal.service.StudentServiceIfc;
import com.narayanjoshi.lbu.sesc.studentportal.thirdPartyApi.constant.PaymentType;
import com.narayanjoshi.lbu.sesc.studentportal.thirdPartyApi.service.ThirdPartyAPIServiceIfc;
import com.narayanjoshi.lbu.sesc.studentportal.utils.AuthenticateUtil;
import com.narayanjoshi.lbu.sesc.studentportal.utils.Util;

@Service
@Transactional
public class StudentServiceImpl implements StudentServiceIfc {

    @Autowired private StudentRepositoryIfc studentRepositoryIfc;
    @Autowired private EnrollServiceIfc enrollServiceIfc;
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    @Autowired private ThirdPartyAPIServiceIfc thirdPartyAPIServiceIfc;

    @Override
    public Student loginStudent(String username, String password) throws AuthenticationException {
        Student dbStudentRecord = studentRepositoryIfc.findByUsername(username);
        if(dbStudentRecord!=null) {
        	 if(passwordEncoder.matches(password, dbStudentRecord.getPassword())){
                 return dbStudentRecord;
             }
        	 throw new AuthenticationException("Credential does not match.");
        }
       
        throw new AuthenticationException("User does not exist. Please register");
    }

    @Override
    public void createStudent(Student student){
        long studentId = Util.generateStudentId();
        student.setStudentId(studentId);
        student.setPassword(passwordEncoder.encode(student.getPassword()));
        studentRepositoryIfc.save(student);


        thirdPartyAPIServiceIfc.createFinanceAccount(studentId);
        thirdPartyAPIServiceIfc.createLibraryAccount(studentId);
        thirdPartyAPIServiceIfc.createFinanceServiceInvoice(studentId, new BigDecimal(1500.00), PaymentType.TUITION_FEES);
    }




    @Override
    public void updateStudent(Student student){
    	
    	long studentId =  AuthenticateUtil.getStudentId();
    	
        Student dbStudentRecord = getStudentById(student.getStudentId());
        if(StringUtils.isBlank(student.getPassword())){
            dbStudentRecord.setPassword(dbStudentRecord.getPassword());
        }else{
            dbStudentRecord.setPassword(passwordEncoder.encode(student.getPassword()));
        }

        dbStudentRecord.setMobileNumber(student.getMobileNumber());
        dbStudentRecord.setHomeAddress(student.getHomeAddress());
        dbStudentRecord.setDob(student.getDob());
        dbStudentRecord.setFullname(student.getFullname());
        dbStudentRecord.setUsername(student.getUsername());
        dbStudentRecord.setEmail(student.getEmail());
        dbStudentRecord.setStudentId(studentId);

        studentRepositoryIfc.save(dbStudentRecord);
    }

    @Override
    public Student getStudentById(long studentId){
        Student student = studentRepositoryIfc.findByStudentId(studentId);
        return student;
    }
    
    @Override
	public boolean isEligibleGraduation() {
		long studentId = AuthenticateUtil.getStudentId();
		return thirdPartyAPIServiceIfc.isEligibleGraduation(studentId);
	}

    

}
