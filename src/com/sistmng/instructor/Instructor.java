package com.sistmng.instructor;

import java.time.LocalDate;

public class Instructor {

	// 회원고유번호(pk),회원구분, 이름, 주민번호, 전화번호, 회원등록일
	private String mid, memberStatus, name_, ssn, phone;
	
	
	private LocalDate studentRegDate;

	// 강사 등록일
	private LocalDate instructorRegDate;

	// 과정코드(pk), 과정명
	private String courseCode, courseName;
	
	
	

	// 강의실고유번호(pk), 강의실명
	private String classCode, className;

	// 개설과정 코드, 개설과정 상태
	private String openCocode, openCoStatus;

	// 개설과정 등록인원
	private int classQuota;

	// 개설 과정 기간 시작일, 개설 과정 기간 종료일
	private LocalDate openCoStartDate, openCoCloseDate;
	
	
	

	// 과목코드(pk), 과목명
	private String subjectCode, subjectName;

	// 개설 과목(pk), 교재번호(fk))
	private String openSubCode, bookCode, openSubSatus;

	// 개설 과목 시작일, 개설 과목 종료일
	private LocalDate openSubStartDate, openSubCloseDate;

	// 교재명, 출판사명
	private String bookName, bookPublisher;

	// 수료상태
	private String studentStatus;

	// 탈락코드(pk), 탈락날짜
	private String failureCode;
	private LocalDate failureDate;

	// 시험코드(pk), 시험날짜, 시험문제파일
	private String testCode, testFile;
	private LocalDate testDate;

	// 배점코드(pk), 출석배점, 필기배점, 실기배점
	private String distributionCode;
	private int attendanceDistribution, writingDistribution, practiceDistribution;

	// 성적코드(pk)
	private String scoreCode;

	// 성적 출결점수 ,필기점수, 실기점수, 총점, 성적 등록인원
	private int attendanceScore, writingScore, practiceScore, totalScore, numberOfStuHaveScore;

	public String getMid() {
		return mid;
	}

	public void setMid(String mid) {
		this.mid = mid;
	}

	public String getMemberStatus() {
		return memberStatus;
	}

	public void setMemberStatus(String memberStatus) {
		this.memberStatus = memberStatus;
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

	public LocalDate getInstructorRegDate() {
		return instructorRegDate;
	}

	public void setInstructorRegDate(LocalDate instructorRegDate) {
		this.instructorRegDate = instructorRegDate;
	}

	public String getCourseCode() {
		return courseCode;
	}

	public void setCourseCode(String courseCode) {
		this.courseCode = courseCode;
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

	public String getOpenCocode() {
		return openCocode;
	}

	public void setOpenCocode(String openCocode) {
		this.openCocode = openCocode;
	}

	public String getOpenCoStatus() {
		return openCoStatus;
	}

	public void setOpenCoStatus(String openCoStatus) {
		this.openCoStatus = openCoStatus;
	}

	public int getClassQuota() {
		return classQuota;
	}

	public void setClassQuota(int classQuota) {
		this.classQuota = classQuota;
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

	public String getOpenSubCode() {
		return openSubCode;
	}

	public void setOpenSubCode(String openSubCode) {
		this.openSubCode = openSubCode;
	}

	public String getBookCode() {
		return bookCode;
	}

	public void setBookCode(String bookCode) {
		this.bookCode = bookCode;
	}

	public LocalDate getOpenSubStartDate() {
		return openSubStartDate;
	}

	public void setOpenSubStartDate(LocalDate openSubStartDate) {
		this.openSubStartDate = openSubStartDate;
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

	public String getStudentStatus() {
		return studentStatus;
	}

	public void setStudentStatus(String studentStatus) {
		this.studentStatus = studentStatus;
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

	public int getTotalScore() {
		return totalScore;
	}

	public void setTotalScore(int totalScore) {
		this.totalScore = totalScore;
	}


	public Instructor() {

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

	public String getOpenSubSatus() {
		return openSubSatus;
	}

	public void setOpenSubSatus(String openSubSatus) {
		this.openSubSatus = openSubSatus;
	}

	public int getNumberOfStuHaveScore() {
		return numberOfStuHaveScore;
	}

	public void setNumberOfStuHaveScore(int numberOfStuHaveScore) {
		this.numberOfStuHaveScore = numberOfStuHaveScore;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public LocalDate getOpenSubCloseDate() {
		return openSubCloseDate;
	}

	public void setOpenSubCloseDate(LocalDate openSubCloseDate) {
		this.openSubCloseDate = openSubCloseDate;
	}

	public LocalDate getStudentRegDate() {
		return studentRegDate;
	}

	public void setStudentRegDate(LocalDate studentRegDate) {
		this.studentRegDate = studentRegDate;
	}

}
