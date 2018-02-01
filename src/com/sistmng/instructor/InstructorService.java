package com.sistmng.instructor;

import java.util.List;
import java.util.Scanner;

public class InstructorService {

	private InstructorDAO insDAO = new InstructorDAO();

	public void menu_1(Scanner sc) {

		System.out.println(this.insDAO.instructorInfo());
	}

	public void menu_2(Scanner sc) {

		List<Instructor> courseList = this.insDAO.courseList();

		System.out.println(
				"-----------------------------------------------------------------------------------------------------------------------------------");

		System.out.println("과목코드 / 과목명 / 과목 시작일 / 과목 종료일 / 교재명/ 과정코드 / 과정명 / 과정 시작일/ 과정 종료일 / 강의실 / 등록인원 / 상태");
		System.out.println(
				"-----------------------------------------------------------------------------------------------------------------------------------");

		for (Instructor i : courseList) {
			System.out.printf("%s / %s / %s / %s / %s / %s / %s / %s / %s / %s / %s / %s%n", i.getOpenSubCode(),
					i.getSubjectName(), i.getOpenCourseStartDate(), i.getOpenCourseCloseDate(), i.getBookName(),
					i.getOpenCocode(), i.getCourserName(), i.getOpenCourseStartDate(), i.getOpenCourseCloseDate(),
					i.getClassName(), i.getOpenCoStatus());

		}

		System.out.print("과목코드 >");

		String subCode = sc.nextLine();

		Instructor selectedSubject = this.insDAO.selectedSubject(subCode);
		System.out.printf("[ %s / %s / %s ~ %s ]%n", selectedSubject.getOpenSubCode(), selectedSubject.getSubjectName(),
				selectedSubject.getOpenSubStartDate(), selectedSubject.getOpenSubcloseDate());

		List<Instructor> studentListbyCourse = this.insDAO.studentListByCourse(subCode);

		System.out.println("--------------------------------------------");
		System.out.println("회원코드 / 이름 / 전화번호 / 등록일 / 수료");
		System.out.println("--------------------------------------------");

		for (Instructor i : studentListbyCourse) {
			System.out.printf("%s / %s / %s / %s / %s%n", i.getMid(), i.getName_(), i.getPhone(),
					i.getInstructorRegDate(), i.getStudentStatus());
		}

	}

}
