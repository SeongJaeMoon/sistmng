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
				"--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");

		System.out.println("과목코드 / 과목명 / 과목 시작일 / 과목 종료일 / 교재명/ 과정코드 / 과정명 / 과정 시작일/ 과정 종료일 / 강의실 / 등록인원 / 상태");
		System.out.println(
				"--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");

		for (Instructor i : subjectList) {

			// 만약 오늘이 개설 과목을 종료일 이후이면 강의가 종료된 상태
			if (today.isAfter(i.getOpenCoCloseDate())) {
				i.setOpenSubSatus("강의종료");

				// 만약 오늘이 개설 과목 종료일 이전이면 강의중이거나 강의 예정인 상태인데
			} else if (today.isBefore(i.getOpenSubCloseDate())) {

				// 그중에 만약 오늘이 개설 과목 시작일 이후면 강의중 상태이고
				if (today.isAfter(i.getOpenSubStartDate())) {
					i.setOpenSubSatus("강의중");

					// 만약 오늘이 개설 과목 시작일 이전이면
				} else if (today.isBefore(i.getOpenSubStartDate())) {

					i.setOpenSubSatus("강의예정");
				}

			}

			System.out.printf("%s / %s / %s / %s / %s / %s / %s / %s / %s / %s / %s / %s%n", i.getOpenSubCode(),
					i.getSubjectName(), i.getOpenCoStartDate(), i.getOpenCoCloseDate(), i.getBookName(),
					i.getOpenCocode(), i.getCourseName(), i.getOpenCoStartDate(), i.getOpenCoCloseDate(),
					i.getClassName(), i.getNumberOfStuHaveScore() + "명", i.getOpenSubSatus());

		}

		this.menu_21(sc);

	}

	// 2-1. 강의 스케줄 조회 / 강의 과목 목록에서 과목 선택하면 해당 과목을 듣고 있는 학생 리스트 보기
	private void menu_21(Scanner sc) {

		while (true) {

			System.out.println();
			System.out.print("과목코드 > ");

			String subCode = sc.nextLine();

			// [ 과목코드 / 과목명 / 과목시작일 / 과목종료일 ]
			Instructor selectedSubjectByInstructor = this.insDAO.selectedSubjectByInstructor(subCode);
			System.out.printf("[ %s / %s / %s ~ %s ]%n", selectedSubjectByInstructor.getOpenSubCode(),
					selectedSubjectByInstructor.getSubjectName(), selectedSubjectByInstructor.getOpenSubStartDate(),
					selectedSubjectByInstructor.getOpenSubCloseDate());

			// 선택한 과목의 수강학생 리스트 보기
			List<Instructor> studentListbySubject = this.insDAO.studentListBySubject(subCode);

			System.out.println("--------------------------------------------------------------------------------");
			System.out.println("회원코드 / 이름 / 전화번호 / 등록일 / 수료");
			System.out.println("--------------------------------------------------------------------------------");

			for (Instructor i : studentListbySubject) {

				// 만약 studentStatus에 "nofailureCode" 이면

				if (i.getStudentStatus().equals("noFailureCode")) {

					if (i.getOpenSubCloseDate().isBefore(today)) {
						i.setStudentStatus("수료");
					} else if (i.getOpenSubCloseDate().isAfter(today)) {
						i.setStudentStatus("수강중");
					}

				} else {

					i.setStudentStatus("중도탈락" + " (" + i.getFailureDate() + ")");

				}

				System.out.printf("%s / %s / %s / %s / %s%n", i.getMid(), i.getName_(), i.getPhone(),
						i.getStudentRegDate(), i.getStudentStatus());
			}

			System.out.println();
			System.out.println("---------------------------");
			System.out.println("1.다른 과목 보기 0.나가기");
			System.out.println("---------------------------");
			System.out.print("선택 > ");

			int selectNo = sc.nextInt();
			sc.nextLine();
			if (selectNo == 0) {
				break;
			}

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
		List<Instructor> subjectListWithTestInfo = this.insDAO.subjectListWithTestInfo();

		// 과목코드 / 과목명 / 과목시작일 / 과목종료일 / 교재명 / 과정코드 / 과정명 / 과정시작일 / 과정종료일 / 강의실 / 시험 코드 /
		// 시험날짜 / 출결배점 / 필기배점 / 실기배점 / 시험문제

		System.out.println(
				"------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");

		System.out.println(
				"과목코드 / 과목명 / 과목시작일 / 과목종료일 / 교재명 / 과정코드 / 과정명 / 과정시작일 / 과정종료일 / 강의실 / 시험코드 / 시험날짜 / 출결배점 / 필기배점 / 실기배점 / 시험문제");
		System.out.println(
				"------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");

		for (Instructor i : subjectListWithTestInfo) {

			System.out.printf("%s / %s / %s / %s / %s / %s / %s / %s / %s / %s / %s / %s / %d / %d / %d / %s%n",
					i.getOpenSubCode(), i.getSubjectName(), i.getOpenSubStartDate(), i.getOpenSubCloseDate(),
					i.getBookName(), i.getOpenCocode(), i.getCourseName(), i.getOpenCoStartDate(),
					i.getOpenCoCloseDate(), i.getClassName(), i.getTestCode(), i.getTestDate(),
					i.getAttendanceDistribution(), i.getWritingDistribution(), i.getPracticeDistribution(),
					i.getTestFile());

		}

		this.menu_311(sc);

	}

	// 3-1. 배점관리 - 배점등록 - 성적(&시험) 정보가 포함된 과목 리스트 보기난 후 배점 등록할 과목 선택하고 배점 입력하기
	private void menu_311(Scanner sc) {

		System.out.println();

		System.out.printf("배점을 등록할 과목 코드를 입력해 주세요 > ");

		String openSubCode = sc.nextLine();

		System.out.println();

		System.out.printf("[ %s ]의 배점 등록%n", openSubCode);

		System.out.println();
		System.out.print("출결배점 > ");
		int attendance = sc.nextInt();
		sc.nextLine();

		System.out.print("실기배점 > ");
		int practice = sc.nextInt();
		sc.nextLine();

		System.out.print("시험배점 > ");
		int writing = sc.nextInt();
		sc.nextLine();

		System.out.print("시험날짜(YYYY-MM-DD) > ");
		String inputTestDate = sc.nextLine();

		LocalDate testDate = LocalDate.parse(inputTestDate);

		System.out.print("시험문제 > ");
		String testFile = sc.nextLine();

		Instructor i = new Instructor();
		i.setOpenSubCode(openSubCode);
		i.setAttendanceDistribution(attendance);
		i.setPracticeDistribution(practice);
		i.setWritingDistribution(writing);
		i.setTestDate(testDate);
		i.setTestFile(testFile);

		int result = this.insDAO.addDistribution(i);

		if (result == 0) {
			System.out.println("배점(시험 문제) 등록이 실패했습니다.");
		} else {
			System.out.println("배점(시험 문제) 등록이 완료 되었습니다.");
		}

		System.out.println();

	}

	private void menu_32(Scanner sc) {
		List<Instructor> subjectListWithTestInfo = this.insDAO.subjectListWithTestInfo();

		// 과목코드 / 과목명 / 과목시작일 / 과목종료일 / 교재명 / 과정코드 / 과정명 / 과정시작일 / 과정종료일 / 강의실 / 시험 코드 /
		// 시험날짜 / 출결배점 / 필기배점 / 실기배점 / 시험문제

		System.out.println(
				"------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");

		System.out.println(
				"시험코드 / 시험날짜 / 출결배점 / 필기배점 / 실기배점 / 시험문제 / 과목코드 / 과목명 / 과목시작일 / 과목종료일 / 교재명 / 과정코드 / 과정명 / 과정시작일 / 과정종료일 / 강의실 ");
		System.out.println(
				"------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");

		for (Instructor i : subjectListWithTestInfo) {

			System.out.printf("%s / %s / %d / %d / %d / %s / %s / %s / %s / %s / %s / %s / %s / %s / %s / %s%n",
					i.getTestCode(), i.getTestDate(), i.getAttendanceDistribution(), i.getWritingDistribution(),
					i.getPracticeDistribution(), i.getTestFile(), i.getOpenSubCode(), i.getSubjectName(),
					i.getOpenSubStartDate(), i.getOpenSubCloseDate(), i.getBookName(), i.getOpenCocode(),
					i.getCourseName(), i.getOpenCoStartDate(), i.getOpenCoCloseDate(), i.getClassName());

		}

		this.menu_321(sc);

	}

	private void menu_321(Scanner sc) {

		System.out.println();

		System.out.print("배점을 삭제할 시험 코드를 입력해 주세요 > ");

		String testCode = sc.nextLine();

		System.out.printf("정말로 [ %s ] 과목의 배점을 삭제 하시겠습니까(Y/N)? > ", testCode);

		String yesOrNo = sc.nextLine();

		if (yesOrNo.equalsIgnoreCase("y")) {
			int result = this.insDAO.deleteTest(testCode);

			if (result == 0) {
				System.out.println("배점 정보 삭제를 실패했습니다.");
				System.out.println();
			} else {
				System.out.println("배점 정보를 삭제했습니다.");
				System.out.println();
			}

		} else {
			System.out.println("배점(시험) 삭제를 종료합니다..");
			System.out.println();
		}

	}

	// 4. 성적관리
	public void menu_4(Scanner sc) {

		boolean run = true;

		while (run) {
			System.out.println("----------------------------------------------------------------------");
			System.out.println("1. 성적 등록 2. 성적 삭제 0.나가기");
			System.out.println("----------------------------------------------------------------------");
			System.out.print("선택 > ");

			int sn = sc.nextInt();
			sc.nextLine();

			if (sn == 0)
				break;

			switch (sn) {

			case 1:
				this.menu_41(sc);
				break;

			case 2:
				this.menu_42(sc);
				break;
			}

		}

	}

	private void menu_42(Scanner sc) {
		// 과목코드 / 과목명 / 과목시작일 / 과목종료일 / 교재명 / 과정코드 / 과정명 / 과정시작일 / 과정종료일 / 강의실 / 출결배점 /
		// 필기배점 / 실기배점 / 시험날짜 / 성적 등록 인원

		List<Instructor> subjectListByNumberOfStudent = this.insDAO.subjectListByNumberOfStudent();

	}

	private void menu_41(Scanner sc) {

	}

}
