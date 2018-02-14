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

	public void login(Scanner sc) {
		System.out.print("이름 > ");
		String name_ = sc.next();
		System.out.print("비밀번호(주민번호 뒤 7자리) > ");
		String ssn = sc.next();

		Member m = new Member();
		
		m.setName("장혜진");
		m.setSsn("2222222");

		Member mem = this.dao.login_menu(m);

		if (mem != null) {

			Current current = Current.getInstance();
			current.setCurrent(mem.getMid());
			current.setStatus(mem.getMemberStatus());
			System.out.printf("(%s)님이(%s)로 로그인 중 입니다.%n", mem.getName(), mem.getMemberStatus());

			this.login_menu_1(current.getStatus(), sc);
		} else {
			System.out.println("존재하지 않는 회원입니다. 확인해주세요.");
		}
	}

	private void login_menu_1(String status, Scanner sc) {
		switch (status) {
		case "수강생":
			st.studentMain(sc);
			break;
		case "관리자":
			ad.adminMain(sc);
			break;
		case "강사":
			in.instructorMain(sc);
			break;
		}
	}
}
