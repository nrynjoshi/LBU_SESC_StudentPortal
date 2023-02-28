package com.narayanjoshi.lbu.sesc.studentportal.service.impl;

import com.narayanjoshi.lbu.sesc.studentportal.constant.HostUrl;
import com.narayanjoshi.lbu.sesc.studentportal.doa.StudentRepositoryIfc;
import com.narayanjoshi.lbu.sesc.studentportal.domain.Student;
import com.narayanjoshi.lbu.sesc.studentportal.service.StudentServiceIfc;
import com.narayanjoshi.lbu.sesc.studentportal.utils.HttpUtil;
import com.narayanjoshi.lbu.sesc.studentportal.utils.Util;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

@Service
@Transactional
public class StudentServiceImpl implements StudentServiceIfc {

    @Autowired private StudentRepositoryIfc studentRepositoryIfc;
    @Autowired private HttpUtil httpUtil;

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
        long studentId = Util.generateStudentId();
        student.setStudentId(studentId);
        studentRepositoryIfc.save(student);


        createFinanceAccount(studentId);
        createLibraryAccount(studentId);
    }

    /**
     * When a student is created, a request is sent to the Library microservice to create an account.
     * @param studentIds
     */
    private void createLibraryAccount(long studentId) {
        Map<String, Object> requestLibraryAccountCreateMap = new HashMap();
        requestLibraryAccountCreateMap.put("studentId", String.valueOf(studentId));
        httpUtil.post(HostUrl.LIBRARY_ACCOUNT_CREATE, requestLibraryAccountCreateMap);
    }

    /**
     * When a student is created, a request is sent to the Finance microservice to create an account.
     * @param studentId
     */
    private void createFinanceAccount(long studentId) {
        Map<String, Object> requestFinanceAccountCreateMap = new HashMap();
        requestFinanceAccountCreateMap.put("studentId", String.valueOf(studentId));
        httpUtil.post(HostUrl.FINANCE_ACCOUNT_CREATE, requestFinanceAccountCreateMap);
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
        Map<String,Object> responseMap =httpUtil.get(HostUrl.FINANCE_GRADUATION_CHECK+"/"+studentId);
       List<Map<String,Object>> list = (List<Map<String, Object>>) ((Map<String,Object>) responseMap.get("_embedded")).get("invoiceList");

       boolean isGraduate = true;
       for(Map<String,Object> map: list){
           String status = (String) map.get("status");
           if(!StringUtils.equalsIgnoreCase(status, "PAID")){
               isGraduate = false;
           }
       }

       return isGraduate;
    }

}
