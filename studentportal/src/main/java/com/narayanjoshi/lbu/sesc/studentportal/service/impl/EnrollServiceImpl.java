package com.narayanjoshi.lbu.sesc.studentportal.service.impl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.narayanjoshi.lbu.sesc.studentportal.constant.IntakeEnum;
import com.narayanjoshi.lbu.sesc.studentportal.doa.CourseRepositoryIfc;
import com.narayanjoshi.lbu.sesc.studentportal.doa.EnrollRepositoryIfc;
import com.narayanjoshi.lbu.sesc.studentportal.domain.Course;
import com.narayanjoshi.lbu.sesc.studentportal.domain.Enroll;
import com.narayanjoshi.lbu.sesc.studentportal.service.EnrollServiceIfc;
import com.narayanjoshi.lbu.sesc.studentportal.thirdPartyApi.constant.PaymentType;
import com.narayanjoshi.lbu.sesc.studentportal.thirdPartyApi.service.ThirdPartyAPIServiceIfc;
import com.narayanjoshi.lbu.sesc.studentportal.utils.AuthenticateUtil;

@Service
public class EnrollServiceImpl implements EnrollServiceIfc {

	private EnrollRepositoryIfc enrollRepositoryIfc;

	private CourseRepositoryIfc courseRepositoryIfc;

	private ThirdPartyAPIServiceIfc thirdPartyAPIServiceIfc;

	EnrollServiceImpl(EnrollRepositoryIfc enrollRepositoryIfc, CourseRepositoryIfc courseRepositoryIfc,
			ThirdPartyAPIServiceIfc thirdPartyAPIServiceIfc) {
		this.enrollRepositoryIfc = enrollRepositoryIfc;
		this.courseRepositoryIfc = courseRepositoryIfc;
		this.thirdPartyAPIServiceIfc = thirdPartyAPIServiceIfc;
	}

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

}
