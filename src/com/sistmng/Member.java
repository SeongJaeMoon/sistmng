package com.sistmng;

import java.time.LocalDate;

public class Member {

	//회원고유번호(pk), 회원구분, 이름, 주민번호, 전화번호
	private String mid, memberStatus, name, ssn, phone;
	//회원등록일
	private LocalDate memberRegDate;
	
	public Member() {
		
	}
	
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
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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
	
	
}
