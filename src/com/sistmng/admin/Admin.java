package com.sistmng.admin;

import java.time.LocalDate;
import java.util.*;

public class Admin {

	private String mid; //회원고유번호(fk)
	private LocalDate adminRegDate; //관리자 등록일
	
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
	
	
}
