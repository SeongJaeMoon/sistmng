package com.sistmng;

import java.util.*;

public class LoginService {

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
		String password = sc.next();
		
	}
	private void login_menu_1() {
		
	}
}
