package com.sistmng.admin;

import java.time.LocalDate;

import java.util.*;

public class Admin {

	private String mid; // 회원고유번호(pk)

	private String memberStatus; // 회원구분

	private String name_, ssn, phone; // 회원정보

	private LocalDate memberRegDate; // 회원등록일

	private LocalDate adminRegDate; // 관리자 등록일

	private LocalDate sstudentRegDate; // 수강생 등록일

	private LocalDate instructorRegDate; // 강사 등록일

	private String courseCode, courseName; // 과정 관리

	private String subjectCode, subjectName; // 과목 관리

	private String classCode, className; // 강의실 관리
	
	private int classQuota; //강의실 정원

	private String bookCode, bookName, bookPublisher; // 교재 관리

	private String openCoCode; // 개설 과정 코드

	private LocalDate openCoStartDate, openCoCloseDate; // 개설 과정 날짜

	private String openSubCode; // 개설 과목 코드

	private LocalDate openSubStartDate, openSubCloseDate; // 개설 과목 날짜

	private String failureCode; // 중도 탈락 코드

	private LocalDate failureDate; // 중도탈락

	private String scoreCode; // 성적코드

	private int attendanceScore, writingScore, practiceScore; // 출,필,실 점수

	private String disCode; // 배점코드

	private int attDistribution, wriDistribution, pracDistribution; // 출,필,실 배점

	private String testCode; // 시험코드

	private LocalDate testDate; // 시험날짜

	private String testFile; // 시험문제파일

	
	
	public String getMemberStatus() {

		return memberStatus;

	}

	public void setMemberStatus(String memberStatus) {

		this.memberStatus = memberStatus;

	}

	public String getBookCode() {

		return bookCode;

	}

	public void setBookCode(String bookCode) {

		this.bookCode = bookCode;

	}

	public String getBookName() {

		return bookName;

	}

	public void setBookName(String bookName) {

		this.bookName = bookName;

	}

	public String getBookPublisher() {

		return bookPublisher;

	}

	public void setBookPublisher(String bookPublisher) {

		this.bookPublisher = bookPublisher;

	}

	public LocalDate getInstructorRegDate() {

		return instructorRegDate;

	}

	public void setInstructorRegDate(LocalDate instructorRegDate) {

		this.instructorRegDate = instructorRegDate;

	}

	public String getOpenCoCode() {

		return openCoCode;

	}

	public void setOpenCoCode(String openCoCode) {

		this.openCoCode = openCoCode;

	}

	public LocalDate getOpenCoStartDate() {

		return openCoStartDate;

	}

	public void setOpenCoStartDate(LocalDate openCoStartDate) {

		this.openCoStartDate = openCoStartDate;

	}

	public LocalDate getOpenCoCloseDate() {

		return openCoCloseDate;

	}

	public void setOpenCoCloseDate(LocalDate openCoCloseDate) {

		this.openCoCloseDate = openCoCloseDate;

	}

	public String getOpenSubCode() {

		return openSubCode;

	}

	public void setOpenSubCode(String openSubCode) {

		this.openSubCode = openSubCode;

	}

	public LocalDate getOpenSubStartDate() {

		return openSubStartDate;

	}

	public void setOpenSubStartDate(LocalDate openSubStartDate) {

		this.openSubStartDate = openSubStartDate;

	}

	public LocalDate getOpenSubCloseDate() {

		return openSubCloseDate;

	}

	public void setOpenSubCloseDate(LocalDate openSubCloseDate) {

		this.openSubCloseDate = openSubCloseDate;

	}

	public Admin() {

	}

	public String getMid() {

		return mid;

	}

	public void setMid(String mid) {

		this.mid = mid;

	}

	public LocalDate getAdminRegDate() {

		return adminRegDate;

	}

	public void setAdminRegDate(LocalDate adminRegDate) {

		this.adminRegDate = adminRegDate;

	}

	public String getCourseCode() {

		return courseCode;

	}

	public void setCourseCode(String courseCode) {

		this.courseCode = courseCode;

	}

	public String getCourseName() {

		return courseName;

	}

	public void setCourseName(String courseName) {

		this.courseName = courseName;

	}

	public String getSubjectCode() {

		return subjectCode;

	}

	public void setSubjectCode(String subjectCode) {

		this.subjectCode = subjectCode;

	}

	public String getSubjectName() {

		return subjectName;

	}

	public void setSubjectName(String subjectName) {

		this.subjectName = subjectName;

	}

	public String getClassCode() {

		return classCode;

	}

	public void setClassCode(String classCode) {

		this.classCode = classCode;

	}

	public String getClassName() {

		return className;

	}

	public void setClassName(String className) {

		this.className = className;

	}

	

	public int getClassQuota() {
		return classQuota;
	}

	public void setClassQuota(int classQuota) {
		this.classQuota = classQuota;
	}

	public String getName_() {

		return name_;

	}

	public void setName_(String name_) {

		this.name_ = name_;

	}

	public String getSsn() {

		return ssn;

	}

	public void setSsn(String ssn) {

		this.ssn = ssn;

	}

	public String getPhone() {

		return phone;

	}

	public void setPhone(String phone) {

		this.phone = phone;

	}

	public LocalDate getMemberRegDate() {

		return memberRegDate;

	}

	public void setMemberRegDate(LocalDate memberRegDate) {

		this.memberRegDate = memberRegDate;

	}

	public String getFailureCode() {

		return failureCode;

	}

	public void setFailureCode(String failureCode) {

		this.failureCode = failureCode;

	}

	public LocalDate getFailureDate() {

		return failureDate;

	}

	public void setFailureDate(LocalDate failureDate) {

		this.failureDate = failureDate;

	}

	public LocalDate getSstudentRegDate() {

		return sstudentRegDate;

	}

	public void setSstudentRegDate(LocalDate sstudentRegDate) {

		this.sstudentRegDate = sstudentRegDate;

	}

	public String getScoreCode() {

		return scoreCode;

	}

	public void setScoreCode(String scoreCode) {

		this.scoreCode = scoreCode;

	}

	public int getAttendanceScore() {

		return attendanceScore;

	}

	public void setAttendanceScore(int attendanceScore) {

		this.attendanceScore = attendanceScore;

	}

	public int getWritingScore() {

		return writingScore;

	}

	public void setWritingScore(int writingScore) {

		this.writingScore = writingScore;

	}

	public int getPracticeScore() {

		return practiceScore;

	}

	public void setPracticeScore(int practiceScore) {

		this.practiceScore = practiceScore;

	}

	public String getDisCode() {

		return disCode;

	}

	public void setDisCode(String disCode) {

		this.disCode = disCode;

	}

	public int getAttDistribution() {

		return attDistribution;

	}

	public void setAttDistribution(int attDistribution) {

		this.attDistribution = attDistribution;

	}

	public int getWriDistribution() {

		return wriDistribution;

	}

	public void setWriDistribution(int wriDistribution) {

		this.wriDistribution = wriDistribution;

	}

	public int getPracDistribution() {

		return pracDistribution;

	}

	public void setPracDistribution(int pracDistribution) {

		this.pracDistribution = pracDistribution;

	}

	public String getTestCode() {

		return testCode;

	}

	public void setTestCode(String testCode) {

		this.testCode = testCode;

	}

	public LocalDate getTestDate() {

		return testDate;

	}

	public void setTestDate(LocalDate testDate) {

		this.testDate = testDate;

	}

	public String getTestFile() {

		return testFile;

	}

	public void setTestFile(String testFile) {

		this.testFile = testFile;

	}

}