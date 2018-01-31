package com.sistmng.student;

import java.time.LocalDate;
import java.util.*;

public class Student {

	//회원고유번호(pk)
	private String mid;
	//수강생 등록일
	private LocalDate studentRegDate;
	//과정코드(pk), 과정명
	private String courseCode, courserName;
	//개설과정코드(pk), 강의실고유번호(pk), 강의실명, 정원
	private String openCourseCode, classCode, className, classQuota;
	//개설 과정 기간 시작일, 개설 과정 기간 종료일
	private LocalDate openCourseStartDate, openCourseCloseDate;
	//과목코드(pk), 과목명
	private String subjectCode, subjectName; 
	//탈락코드(pk), 탈락날짜
	private String failureCode;
	private LocalDate failureDate;
	//시험코드(pk), 시험날짜, 시험문제파일
	private String testCode, testFile;
	private LocalDate testDate;
	//배점코드(pk), 출석배점, 필기배점, 실기배점
	private String distributionCode;
	private int attendanceDistribution, writingDistribution, practiceDistribution;
	//성적코드(pk), 출결점수, 필기점수, 실기점수
	private String scoreCode;
	private int attendanceScore, writingScore, practiceScore;
	
	public Student() {
		
	}

	public String getMid() {
		return mid;
	}

	public void setMid(String mid) {
		this.mid = mid;
	}

	public LocalDate getStudentRegDate() {
		return studentRegDate;
	}

	public void setStudentRegDate(LocalDate studentRegDate) {
		this.studentRegDate = studentRegDate;
	}

	public String getCourseCode() {
		return courseCode;
	}

	public void setCourseCode(String courseCode) {
		this.courseCode = courseCode;
	}

	public String getCourserName() {
		return courserName;
	}

	public void setCourserName(String courserName) {
		this.courserName = courserName;
	}

	public String getOpenCourseCode() {
		return openCourseCode;
	}

	public void setOpenCourseCode(String openCourseCode) {
		this.openCourseCode = openCourseCode;
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

	public String getClassQuota() {
		return classQuota;
	}

	public void setClassQuota(String classQuota) {
		this.classQuota = classQuota;
	}

	public LocalDate getOpenCourseStartDate() {
		return openCourseStartDate;
	}

	public void setOpenCourseStartDate(LocalDate openCourseStartDate) {
		this.openCourseStartDate = openCourseStartDate;
	}

	public LocalDate getOpenCourseCloseDate() {
		return openCourseCloseDate;
	}

	public void setOpenCourseCloseDate(LocalDate openCourseCloseDate) {
		this.openCourseCloseDate = openCourseCloseDate;
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

	public String getTestCode() {
		return testCode;
	}

	public void setTestCode(String testCode) {
		this.testCode = testCode;
	}

	public String getTestFile() {
		return testFile;
	}

	public void setTestFile(String testFile) {
		this.testFile = testFile;
	}

	public LocalDate getTestDate() {
		return testDate;
	}

	public void setTestDate(LocalDate testDate) {
		this.testDate = testDate;
	}

	public String getDistributionCode() {
		return distributionCode;
	}

	public void setDistributionCode(String distributionCode) {
		this.distributionCode = distributionCode;
	}

	public int getAttendanceDistribution() {
		return attendanceDistribution;
	}

	public void setAttendanceDistribution(int attendanceDistribution) {
		this.attendanceDistribution = attendanceDistribution;
	}

	public int getWritingDistribution() {
		return writingDistribution;
	}

	public void setWritingDistribution(int writingDistribution) {
		this.writingDistribution = writingDistribution;
	}

	public int getPracticeDistribution() {
		return practiceDistribution;
	}

	public void setPracticeDistribution(int practiceDistribution) {
		this.practiceDistribution = practiceDistribution;
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
	
	
}
