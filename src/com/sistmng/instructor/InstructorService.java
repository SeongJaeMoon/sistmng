package com.sistmng.instructor;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

import com.sistmng.Current;

public class InstructorService {

	private InstructorDAO insDAO = new InstructorDAO();

	private LocalDate today = LocalDate.now();

	// 1. 강사정보 확인
	public void menu_1(Scanner sc) {

		List<Instructor> instructorInfo = this.insDAO.instructorInfo(Current.getInstance().getCurrent());

		Instructor i = instructorInfo.get(0);

		System.out.printf("강사명 : %s%n주민번호 : %s%n전화번호 : %s%n강의 가능 목록 : ", i.getName_(), i.getSsn(), i.getPhone());

		for (Instructor instructor : instructorInfo) {
			System.out.printf("[" + instructor.getSubjectCode() + "]" + instructor.getSubjectName() + " / ");

		}

		System.out.printf("%n등록일 : %s%n", i.getInstructorRegDate());

	}

	// 2. 강의스케줄 조회 / 강의 과목 목록 (강의예정 / 강의중 / 강의종료 리스트) 보기
	public void menu_2(Scanner sc) {

		List<Instructor> subjectList = this.insDAO.subjectListByInstructor(Current.getInstance().getCurrent());

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

			String disAction = "";

			switch (sn) {

			case 1:
				disAction = "add";
				this.menu_31(sc, disAction);
				break;

			case 2:
				disAction = "delete";
				this.menu_31(sc, disAction);
				break;
			}

		}

	}

	// 3-1. 배점관리 - 배점등록 - 성적(&시험) 정보가 포함된 과목 리스트 보기(// 과목 종료일이 지난 리스트만 출력하기)
	private void menu_31(Scanner sc, String disAction) {
		List<Instructor> subjectListWithTestInfo = this.insDAO
				.subjectListWithTestInfo(Current.getInstance().getCurrent());

		// 과목코드 / 과목명 / 과목시작일 / 과목종료일 / 교재명 / 과정코드 / 과정명 / 과정시작일 / 과정종료일 / 강의실 / 시험 코드 /
		// 시험날짜 / 출결배점 / 필기배점 / 실기배점 / 시험문제

		System.out.println(
				"--------------------------------------------------------------------------------------------------------------------------------------------------------------------");
		System.out.println("과목코드 / 과목명 / 과목시작일 / 과목종료일 / 교재명 / 과정코드 / 과정명 / 과정시작일 / 과정종료일 / 강의실 ");
		System.out.println(
				"--------------------------------------------------------------------------------------------------------------------------------------------------------------------");

		for (Instructor i : subjectListWithTestInfo) {

			System.out.printf("%s / %s / %s / %s / %s / %s / %s / %s / %s / %s%n", i.getOpenSubCode(),
					i.getSubjectName(), i.getOpenSubStartDate(), i.getOpenSubCloseDate(), i.getBookName(),
					i.getOpenCocode(), i.getCourseName(), i.getOpenCoStartDate(), i.getOpenCoCloseDate(),
					i.getClassName());

		}

		this.menu_310(sc, disAction);

	}

	// 3-1. 배점관리 - 배점등록 - 배점 등록할 과목 선택후 과목에 등록된 시험 리스트 출력하기
	private void menu_310(Scanner sc, String disAction) {
		System.out.println();

		String disActionInKorean = "";
		if (disAction.equals("add")) {
			disActionInKorean = "등록";
		} else if (disAction.equals("delete")) {
			disActionInKorean = "삭제";
		}

		System.out.printf("배점을 %s할 과목 코드를 입력해 주세요 > ", disActionInKorean);

		String openSubCode = sc.nextLine();

		List<Instructor> testListBySubject = this.insDAO.testListBySubject(openSubCode);

		System.out.printf("[ %s / %s ]%n", testListBySubject.get(0).getSubjectCode(),
				testListBySubject.get(0).getSubjectName());

		// 과목코드 / 과목명 / 시험코드 / 시험날짜 / 출결배점 / 실기배점 / 시험 문제

		System.out.println("---------------------------------------------------------------------");
		System.out.println("시험코드 / 시험날짜 / 출결배점 / 필기배점 / 실기배점 / 시험 문제");
		System.out.println("---------------------------------------------------------------------");

		for (Instructor i : testListBySubject) {
			System.out.printf("%s / %s / %s / %s / %s / %s%n", i.getTestCode(), i.getTestDate(),
					i.getAttendanceDistribution(), i.getWritingDistribution(), i.getPracticeDistribution(),
					i.getTestFile());
		}

		if (disAction.equals("add")) {
			this.menu_311(sc, openSubCode);
		} else if (disAction.equals("delete")) {
			this.menu_312(sc);
		}

	}

	// 3-1. 배점관리 - 배점등록 - 성적(&시험) 정보가 포함된 과목 리스트 보고난 후 배점 등록할 과목 선택하고 배점 입력하기
	private void menu_311(Scanner sc, String openSubCode) {

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
		i.setAttendanceDistribution(attendance);
		i.setPracticeDistribution(practice);
		i.setWritingDistribution(writing);
		i.setTestDate(testDate);
		i.setTestFile(testFile);
		i.setOpenSubCode(openSubCode);

		int result = this.insDAO.addDistribution(i);

		if (result == 0) {
			System.out.println("배점(시험 문제) 등록이 실패했습니다.");
		} else {
			System.out.println("배점(시험 문제) 등록이 완료 되었습니다.");
		}

		System.out.println();

	}

	// 3-2. 배점관리 - 배점삭제 가능한 리스트만 보고난 후 배점 삭제할 과목 선택하고 배점 삭제하기
	private void menu_312(Scanner sc) {

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
		String action = "";

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
				action = "addScore";
				this.menu_40(sc, action);
				break;

			case 2:
				action = "deleteScore";
				this.menu_40(sc, action);
				break;
			}

		}

	}

	// 4-1 & 4-2. 성적 등록 / 삭제에 같은 리스트보여주기
	private void menu_40(Scanner sc, String action) {

		List<Instructor> subjectListByNumberOfStudent = this.insDAO
				.subjectListByNumberOfStudent(Current.getInstance().getCurrent());

		System.out.println(
				"---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
		System.out.println(
				"시험코드 / 시험날짜 / 출결배점 / 필기배점 /  실기배점 / 과목코드 / 과목명 / 과목시작일 / 과목종료일 / 교재명 / 과정코드 / 과정명 / 과정시작일 / 과정종료일 / 강의실 / 성적 등록 인원");
		System.out.println(
				"---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");

		for (Instructor i : subjectListByNumberOfStudent) {
			System.out.printf("%s / %s / %d / %d / %d / %s / %s / %s / %s / %s / %s / %s / %s / %s / %s / %d%n",
					i.getTestCode(), i.getTestDate(), i.getAttendanceDistribution(), i.getWritingDistribution(),
					i.getPracticeDistribution(), i.getOpenSubCode(), i.getSubjectName(), i.getOpenSubStartDate(),
					i.getOpenSubCloseDate(), i.getBookName(), i.getOpenCocode(), i.getCourseName(),
					i.getOpenCoStartDate(), i.getOpenCoCloseDate(), i.getClassName(), i.getNumberOfStuHaveScore());
		}
		System.out.println();

		String actionInKorean = "";

		if (action.equals("addScore")) {
			actionInKorean = "조회";
		} else if (action.endsWith("deleteScore")) {
			actionInKorean = "삭제";
		}

		System.out.printf("성적을 %s할 시험코드를 입력해 주세요 > ", actionInKorean);

		String testCode = sc.nextLine();

		this.menu_400(sc, testCode, action);

	}

	// 4-1 & 4-2 성적 등록 / 삭제할 학생 선택할 학생정보 리스트도 동일
	private void menu_400(Scanner sc, String testCode, String action) {

		List<Instructor> scoreInfoByStudents = this.insDAO.scoreInfoByStudents(testCode);

		System.out.println(
				"---------------------------------------------------------------------------------------------");
		System.out.println("회원코드 / 이름 / 전화번호 / 등록일 / 수료 / 출결점수 / 필기점수 / 실기점수 / 총점");
		System.out.println(
				"---------------------------------------------------------------------------------------------");

		for (Instructor i : scoreInfoByStudents) {

			if (i.getStudentStatus().equals("noFailureCode")) {

				i.setStudentStatus("수료");

			} else {

				i.setStudentStatus("중도탈락" + " (" + i.getFailureDate() + ")");

			}

			System.out.printf("%s / %s / %s / %s / %s / %d / %d / %d / %d%n", i.getMid(), i.getName_(), i.getPhone(),
					i.getStudentRegDate(), i.getStudentStatus(), i.getAttendanceScore(), i.getWritingScore(),
					i.getPracticeScore(), i.getAttendanceScore() + i.getWritingScore() + i.getPracticeScore());

		}

		if (action.equals("addScore"))

		{
			this.menu_41(sc, testCode);
		} else if (action.endsWith("deleteScore")) {
			this.menu_42(sc, testCode);
		}

	}

	// 4-1. 성적 등록 입력받기
	private void menu_41(Scanner sc, String testCode) {

		System.out.println();

		System.out.print("성적을 입력할 수강생의 회원코드를 입력해 주세요 > ");

		String mid = sc.nextLine();

		System.out.printf("[ %s ] 수강생의 성적 점수 입력%n", mid);

		System.out.println();

		System.out.print("출결 > ");
		int attendanceScore = sc.nextInt();
		sc.nextLine();

		System.out.print("필기 > ");
		int writingScore = sc.nextInt();
		sc.nextLine();

		System.out.print("실기 > ");
		int practiceScore = sc.nextInt();
		sc.nextLine();

		Instructor i = new Instructor();
		i.setMid(mid);
		i.setWritingScore(attendanceScore);
		i.setWritingScore(writingScore);
		i.setPracticeScore(practiceScore);
		i.setTestCode(testCode);

		int result = this.insDAO.addScore(i);

		if (result == 0) {
			System.out.println("성적 정보 입력에 실패했습니다.");
		} else {
			System.out.println("성적 정보 입력이 완료되었습니다.");
		}
	}

	// 4-2. 성적 삭제 입력받기
	private void menu_42(Scanner sc, String testCode) {

		System.out.println();

		System.out.print("성적을 삭제할 수강생의 회원코드를 입력해 주세요 > ");

		String mid = sc.nextLine();

		System.out.println();

		System.out.printf("[ %s ] 학생의 성적을 삭제하시겠습니까(Y/N)? > ", mid);

		String yesOrNo = sc.nextLine();

		if (yesOrNo.equalsIgnoreCase("y")) {

			int result = this.insDAO.deleteScore(mid, testCode);

			if (result == 0) {
				System.out.println("성적 삭제를 실패했습니다.");
			} else {
				System.out.println("성적을 삭제 했습니다.");
			}

		} else {
			System.out.println("성적 삭제를 종료합니다.");
		}

	}

}
