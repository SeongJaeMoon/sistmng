package com.sistmng.instructor;

import java.util.Scanner;

public class InstructorMain {

	private InstructorService inss = new InstructorService();

	public void instructorMain(Scanner sc) {

		boolean run = true;

		while (run) {
			System.out.println();
			System.out.println("----------------------------------------------------------------------");
			System.out.println("1. 강사정보 확인 2. 강의스케줄 조회 3. 배점 관리 4.성적 관리 0. 종료");
			System.out.println("----------------------------------------------------------------------");
			System.out.print("선택 > ");

			int sn = sc.nextInt();
			sc.nextLine();

			if (sn == 0)
				break;

			switch (sn) {
			case 1:
				inss.menu_1(sc);
				break;
			case 2:
				inss.menu_2(sc);
				break;
			case 3:
				inss.menu_3(sc);
				break;
			case 4:
				inss.menu_4(sc);
				break;

			}

		}
		System.out.println("로그아웃 되었습니다.");
	}

}
