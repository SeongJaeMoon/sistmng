package com.sistmng.admin;

import java.util.*;

public class AdminMain {
	
public void adminMenu(Scanner sc) {
		
		AdminService service = new AdminService();
		
		boolean run = true;
		
		while(run) {
		
		System.out.println("----------------------------------------------------------------------------------------------------");
		System.out.println("1.기초 정보 관리 2.강사 계정 관리 3.개설 과정 관리 4.개설 과목 관리 5.수강생 관리 6.성적 조회 0.종료");
		System.out.println("----------------------------------------------------------------------------------------------------");
		
		int num = sc.nextInt();
		sc.nextLine();
		
		switch(num) {
		
		case 0 : run=false; break;
		case 1: service.basicInfoMenu(sc); break;
		case 2: service.InstructorMenu(sc); break;
		case 3: break;
		case 4: break;
		case 5: break;
		case 6: break;
		
		
		}
			
		}
		
	}
}
