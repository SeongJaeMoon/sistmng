package com.sistmng.student;

import java.util.*;

import com.sistmng.Current;

public class StudentService {
	
	StudentDAO dao = new StudentDAO();
	
	public void menu_1() {
		System.out.println("");
		Student student = this.dao.menu_1(Current.getInstance().getCurrent());
		System.out.printf("이름: %s%n",student.getName_());
		System.out.printf("주민번호: %s%n", student.getSsn());
		System.out.printf("전화번호: %s%n", student.getPhone());
		System.out.printf("과정수강횟수: %d%n", student.getCourseNumber());
	}
	
	public void menu_2(Scanner sc) {
		List<Student> studentList = this.dao.menu_2(Current.getInstance().getCurrent());
		System.out.println("성적을 조회합니다.");
		if(studentList.size() == 0) {
			System.out.println("조회된 정보가 없습니다.");
		}else {
		System.out.println("------------------------------------------------------------------------------------------------------");
		System.out.println("과정코드 / 과정명 / 시작일 / 종료일 / 강의실 /수료");
		System.out.println("------------------------------------------------------------------------------------------------------");
		for(Student s : studentList) {
			System.out.printf("%s / %s / %s / %s / %s / %s%n", s.getOpenCourseCode(), s.getCourseName(), s.getOpenCourseStartDate(), s.getOpenCourseCloseDate(), s.getClassName(), s.getCompletionCheck());
		}
			System.out.println("------------------------------------------------------------------------------------------------------");
			System.out.println("1.과정 상세보기 0.나가기");
			System.out.print("선택 > ");
			int input = sc.nextInt();
			switch (input) {
			case 1:
				this.menu_21(sc);
				break;
			case 0:
				break;
			}
		}
	}
	
	public void menu_21(Scanner sc) {
		System.out.println("과정코드 > ");
		String openCoCode = sc.next();
		List<Student>studentList = this.dao.menu_21(openCoCode);
		if(studentList.size() == 0 ) {
			System.out.println("조회된 정보가 없습니다.");
		}else {
		System.out.println(this.dao.getCourseInfo(openCoCode));
		System.out.println("-----------------------------------------------------------------------------------------------------------------");
		System.out.println("과목코드 / 과목명 / 시작일 / 종료일 / 교재명 / 강사명 / 출결배점 / 출결점수 / 실기배점 / 실기점수 / 필기배점 / 필기점수 / 시험날짜 /시험문제");
		System.out.println("-----------------------------------------------------------------------------------------------------------------");
		for(Student s : studentList) {
			System.out.printf("%s / %s / %s / %s / %s / %s / %s / %s / %s / %s / %s / %s / %s%n", 
					s.getOpenSubCode(), s.getSubjectName(), s.getOpenSubStartDate(), s.getOpenSubCloseDate(), s.getBookName(), s.getName_(), s.getattDistribution(), s.getAttendanceScore(), s.getwriDistribution(), s.getWritingScore(), s.getpracDistribution(), s.getPracticeScore(), s.getTestFile());
			}
		}
	}
}
