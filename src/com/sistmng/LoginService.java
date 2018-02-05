package com.sistmng;

import java.util.*;

import com.sistmng.admin.AdminMain;
import com.sistmng.instructor.InstructorMain;
import com.sistmng.student.StudentMain;

public class LoginService {

	LoginDAO dao = new LoginDAO();
	StudentMain st = new StudentMain();
	InstructorMain in = new InstructorMain();
	AdminMain ad = new AdminMain();
	
	public void login(Scanner sc){
		System.out.println("==================================");
		System.out.println("성적 처리 시스템");
		System.out.println("==================================");
		System.out.println("1.로그인 0.종료");
		System.out.print("선택 > ");	
		this.login_menu(sc);
	}
	
	private void login_menu(Scanner sc) {
		System.out.print("이름 >");
		String name_ = sc.next();
		System.out.print("비밀번호(주민번호 뒤 7자리) >");
		String ssn = sc.next();
		Member m = new Member();
		m.setName(name_);
		m.setSsn(ssn);
		m = this.dao.login_menu(m);
		if(m!=null) {
			Current current = Current.getInstance();
			current.setCurrent(m.getMid());
			current.setStatus(m.getMemberStatus());
			this.login_menu_1(current.getStatus(), sc);
			System.out.printf("(%s)님이(%s)로 로그인 중 입니다.", m.getName(), m.getMemberStatus());
		}else {
			System.out.println("존재하지 않는 회원입니다. 확인해주세요.");
		}
	}
	
	private void login_menu_1(String status, Scanner sc) {
		switch(status) {
		case "수강생": st.menu(sc); break;
		}
	}
}
