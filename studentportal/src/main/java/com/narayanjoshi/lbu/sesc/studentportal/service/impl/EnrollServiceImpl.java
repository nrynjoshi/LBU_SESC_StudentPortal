package com.narayanjoshi.lbu.sesc.studentportal.service.impl;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.narayanjoshi.lbu.sesc.studentportal.constant.IntakeEnum;
import com.narayanjoshi.lbu.sesc.studentportal.doa.CourseRepositoryIfc;
import com.narayanjoshi.lbu.sesc.studentportal.doa.EnrollRepositoryIfc;
import com.narayanjoshi.lbu.sesc.studentportal.domain.Course;
import com.narayanjoshi.lbu.sesc.studentportal.domain.Enroll;
import com.narayanjoshi.lbu.sesc.studentportal.service.EnrollServiceIfc;
import com.narayanjoshi.lbu.sesc.studentportal.thirdPartyApi.constant.PaymentType;
import com.narayanjoshi.lbu.sesc.studentportal.thirdPartyApi.constant.ThirdPartyEndpoint;
import com.narayanjoshi.lbu.sesc.studentportal.thirdPartyApi.service.ThirdPartyAPIServiceIfc;
import com.narayanjoshi.lbu.sesc.studentportal.thirdPartyApi.util.HttpUtil;
import com.narayanjoshi.lbu.sesc.studentportal.utils.AuthenticateUtil;

@Service
public class EnrollServiceImpl implements EnrollServiceIfc {

	@Autowired
	private EnrollRepositoryIfc enrollRepositoryIfc;

	@Autowired
	private CourseRepositoryIfc courseRepositoryIfc;

	@Autowired
	private ThirdPartyAPIServiceIfc thirdPartyAPIServiceIfc;

	@Override
	public List<Enroll> getEnrolCourses() {
		long studentId = AuthenticateUtil.getStudentId();
		return enrollRepositoryIfc.findByStudentId(studentId);
	}

	@Override
	public void enrolIntoCourse(String courseId) {
		Course course = courseRepositoryIfc.findByCourseId(courseId);
		long studentId = AuthenticateUtil.getStudentId();

		Enroll alreadyEnroll = enrollRepositoryIfc.findByStudentIdAndCourse(studentId, course);

		if (alreadyEnroll == null) {
			Enroll enroll = new Enroll();
			enroll.setStudentId(studentId);
			enroll.setDate(LocalDateTime.now());
			enroll.setIntake(IntakeEnum.JAN);
			enroll.setCourse(course);
			enrollRepositoryIfc.save(enroll);

			thirdPartyAPIServiceIfc.createFinanceServiceInvoice(studentId, course.getFee(), PaymentType.TUITION_FEES);
		}

	}

	/**
	 * When you enrol in a course, a request is sent to the Finance microservice to
	 * create an invoice.
	 * 
	 * @param studentId
	 */

}
