package com.sistmng;

import java.util.*;

public class Sist {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		LoginService login = new LoginService();
		boolean run = true;
		try{
			while(run) {
				int input = sc.nextInt();
				if(input == 0) {
					run = false; break;
				}else if(input == 1) {
					login.login(sc);
				}else {
					System.out.println("알 수 없는 입력입니다.");
				}
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		sc.close();
		System.out.println("성적 처리 프로그램을 종료합니다.");
	}
}
