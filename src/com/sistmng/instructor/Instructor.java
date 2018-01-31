package com.sistmng.instructor;

public class Instructor {

	private String mid; //회원고유번호(fk)
	private String instructorRegDate; //강사등록일
	
	public Instructor() {
		
	}
	public String getMid() {
		return mid;
	}
	public void setMid(String mid) {
		this.mid = mid;
	}
	public String getInstructorRegDate() {
		return instructorRegDate;
	}
	public void setInstructorRegDate(String instructorRegDate) {
		this.instructorRegDate = instructorRegDate;
	}
	
	
}
