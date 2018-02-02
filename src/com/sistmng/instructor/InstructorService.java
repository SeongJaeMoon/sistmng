package com.sistmng.instructor;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

public class InstructorService {

	private InstructorDAO insDAO = new InstructorDAO();

	private LocalDate today = LocalDate.now();

	// 1. 강사정보 확인
	public void menu_1(Scanner sc) {

		List<Instructor> instructorInfo = this.insDAO.instructorInfo();

		Instructor i = instructorInfo.get(0);

		System.out.printf("강사명 : %s%n주민번호 : %s%n전화번호 : %s%n강의 가능 목록 : ", i.getName_(), i.getSsn(), i.getPhone());

		for (Instructor instructor : instructorInfo) {
			System.out.printf("[" + instructor.getSubjectCode() + "]" + instructor.getSubjectName() + " / ");

		}

		System.out.printf("%n등록일 : %s%n%n", i.getInstructorRegDate());

	}

	// 2. 강의스케줄 조회 / 강의 과목 목록 (강의예정 / 강의중 / 강의종료 리스트) 보기
	public void menu_2(Scanner sc) {

		List<Instructor> subjectList = this.insDAO.subjectListByInstructor();

		System.out.println(
				"--------------------------------------------------------------------------------------------------------------------------------------------------------------------------");

		System.out.println("과목코드 / 과목명 / 과목 시작일 / 과목 종료일 / 교재명/ 과정코드 / 과정명 / 과정 시작일/ 과정 종료일 / 강의실 / 등록인원 / 상태");
		System.out.println(
				"--------------------------------------------------------------------------------------------------------------------------------------------------------------------------");

		for (Instructor i : subjectList) {
			
			
			
			
			System.out.printf("%s / %s / %s / %s / %s / %s / %s / %s / %s / %s / %s / %s%n", i.getOpenSubCode(),
					i.getSubjectName(), i.getOpenCoStartDate(), i.getOpenCoCloseDate(), i.getBookName(),
					i.getOpenCocode(), i.getCourserName(), i.getOpenCoStartDate(), i.getOpenCoCloseDate(),
					i.getClassName(), "등록인원아직몰라요더고민해보아요", "시작상태는날짜를구해야겠죠");

		}

		this.menu_21(sc);

	}

	// 2-1. 강의 스케줄 조회 / 강의 과목 목록에서 과목 선택하면 해당 과목을 듣고 있는 학생 리스트 보기
	private void menu_21(Scanner sc) {

		System.out.println();
		System.out.print("과목코드 > ");

		String subCode = sc.nextLine();

		// [ 과목코드 / 과목명 / 과목시작일 / 과목종료일 ]
		Instructor selectedSubject = this.insDAO.selectedSubject(subCode);
		System.out.printf("[ %s / %s / %s ~ %s ]%n", selectedSubject.getOpenSubCode(), selectedSubject.getSubjectName(),
				selectedSubject.getOpenSubStartDate(), selectedSubject.getOpenSubcloseDate());

		// 선택한 과목의 수강학생 리스트 보기
		List<Instructor> studentListbySubject = this.insDAO.studentListBySubject(subCode);

		System.out.println("--------------------------------------------");
		System.out.println("회원코드 / 이름 / 전화번호 / 등록일 / 수료");
		System.out.println("--------------------------------------------");

		for (Instructor i : studentListbySubject) {

			// 만약 studentStatus에 값(탈락코드)이 null이 아니면 중도탈락 + 중도탈락일을 다시 studetntStatus에 추가
			if (i.getStudentStatus() != null) {
				i.setStudentStatus("중도탈락" + " (" + i.getFailureDate() + ")");

				// 그게 아니면 수강중이거나 수료 / 수강중인것은 과목 종료일이 현재랑 같거나 더 작을 때, 수료 인것은 과목 종료일이 현재 이전일때
			} else {

				if (i.getOpenSubcloseDate().isBefore(today)) {
					i.setStudentStatus("수료");
				} else {
					i.setStudentStatus("수강중");
				}

			}

			System.out.printf("%s / %s / %s / %s / %s%n", i.getMid(), i.getName_(), i.getPhone(),
					i.getInstructorRegDate(), i.getStudentStatus());
		}

	}

	// 3. 배점관리
	public void menu_3(Scanner sc) {

		boolean run = true;

		while (run) {
			System.out.println("----------------------------------------------------------------------");
			System.out.println("1. 배점 등록 2. 배점 삭제 0.나가기");
			System.out.println("----------------------------------------------------------------------");
			System.out.print("선택 > ");

			int sn = sc.nextInt();
			sc.nextLine();

			if (sn == 0)
				break;

			switch (sn) {

			case 1:
				this.menu_31(sc);
				break;

			case 2:
				this.menu_32(sc);
				break;
			}

		}

	}

	// 3-1. 배점관리 - 배점등록 - 성적(&시험) 정보가 포함된 과목 리스트 보기

	// 과목 종료일이 지난 리스트만 출력하기

	private void menu_31(Scanner sc) {
		List<Instructor> courseList = this.insDAO.subjectListWithTestInfo();

		System.out.println(
				"------------------------------------------------------------------------------------------------------------------------------------------------");

		System.out.println("과목코드 / 과목명 / 과목시작일 / 과목종료일 / 교재명 / 과정코드 / 과정명 / 과정시작일 /과정종료일 / 강의실 / 출결배점 / 필기배점 / 실기배점");
		System.out.println(
				"------------------------------------------------------------------------------------------------------------------------------------------------");

		for (Instructor i : courseList) {

			System.out.printf("%s / %s / %s / %s / %s / %s / %s / %s / %s / %s / %s / %s/ %s%n", i.getOpenSubCode(),
					i.getSubjectName(), i.getOpenSubStartDate(), i.getOpenSubcloseDate(), i.getBookName(),
					i.getOpenCocode(), i.getCourserName(), i.getOpenCoStartDate(), i.getOpenCoCloseDate(),
					i.getClassName(), i.getAttendanceDistribution(), i.getWritingDistribution(),
					i.getPracticeDistribution());

		}

		this.menu_311(sc);

	}

	// 3-1. 배점관리 - 배점등록 - 성적(&시험) 정보가 포함된 과목 리스트 보기난 후 배점 등록할 과목 선택하고 배점 입력하기
	private void menu_311(Scanner sc) {

		System.out.printf("배점을 등록할 과목 코드를 입력해 주세요 > ");

		String subCode = sc.nextLine();

	}

	private void menu_32(Scanner sc) {
		// TODO Auto-generated method stub

	}

}
