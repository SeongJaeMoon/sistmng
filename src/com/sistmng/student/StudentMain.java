package com.sistmng.student;

import java.util.*;

public class StudentMain {

	StudentService sv = new StudentService();
	
	public void menu(Scanner sc) {
		boolean run = true;
		while(run) {
		System.out.println("--------------------------------------");
		System.out.println("1.개인정보 확인 2.성적 조회 0.종료");
		System.out.println("--------------------------------------");
		System.out.print("선택 > ");
			int input = sc.nextInt();
			if(input == 0) {run=false; break;}
			switch(input) {
			case 1: this.sv.menu_1();break;
			case 2: this.sv.menu_2(sc);break;
			}
		}
	}
}
