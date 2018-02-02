package com.sistmng.instructor;

import com.connection.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class InstructorDAO {

	private String loginInstructor = "MEM002";

	public List<Instructor> instructorInfo() {

		List<Instructor> instructorInfo = new ArrayList<>();

		String sql = "SELECT m.name_ insName , m.phone insPhone , m.ssn insSSN , i.instructorRegDate insRegDate, c.subjectCode insSubCode, s.subjectName insSubName FROM member_ m , instructor_ i, checkSubject_ c , subject_ s WHERE m.mid = i.mid AND i.mid = c.mid  AND s.subjectCode = c.subjectCode AND m.mid = ?";

		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = SQLConnection.connect();
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, this.loginInstructor);

			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {

				Instructor i = new Instructor();

				// 강사 이름
				i.setName_(rs.getString("insName"));

				// 강사 주민번호
				i.setSsn(rs.getString("insSSN"));

				// 강사 전화번호
				i.setPhone(rs.getString("insPhone"));

				// 강의 가능 목록 과목 코드, 과목명
				i.setSubjectCode(rs.getString("insSubCode"));
				i.setSubjectName(rs.getString("insSubName"));

				// 강사 등록일
				i.setInstructorRegDate(rs.getDate("insRegDate").toLocalDate());

				instructorInfo.add(i);

			}
			rs.close();

		} catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
			} catch (SQLException se) {
			}
			try {
				SQLConnection.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}
		}

		return instructorInfo;
	}

	public List<Instructor> subjectListByInstructor() {

		List<Instructor> subjectListByInstructor = new ArrayList<Instructor>();

		String sql = "SELECT openSubCode , subjectName , openSubStartDate , openSubCloseDate , bookName , openCoCode , courseName , openCoStartDate , openCoCloseDate , className FROM subjectListByInstructorView WHERE mid = ?";

		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = SQLConnection.connect();
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, this.loginInstructor);

			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {

				Instructor i = new Instructor();

				i.setOpenSubCode(rs.getString("openSubCode"));
				i.setSubjectName(rs.getString("subjectName"));
				i.setOpenSubStartDate(rs.getDate("openSubStartDate").toLocalDate());
				i.setOpenSubcloseDate(rs.getDate("openSubCloseDate").toLocalDate());
				i.setBookName(rs.getString("bookName"));
				i.setCourseCode(rs.getString("openCocode"));
				i.setCourserName(rs.getString("courseName"));
				i.setOpenCoStartDate(rs.getDate("openCoStartDate").toLocalDate());
				i.setOpenCoCloseDate(rs.getDate("openCoCloseDate").toLocalDate());
				i.setClassName(rs.getString("className"));

				subjectListByInstructor.add(i);

			}
			rs.close();

		} catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
			} catch (SQLException se) {
			}
			try {
				SQLConnection.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}
		}

		return subjectListByInstructor;

	}

	public Instructor selectedSubject(String subCode) {
		Instructor i = new Instructor();

		String sql = "";

		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = SQLConnection.connect();
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, subCode);

			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {

				i.setOpenSubCode(rs.getString("openSubCode"));
				i.setSubjectName(rs.getString("subjectName"));
				i.setOpenCoStartDate(rs.getDate("openSubStarDate").toLocalDate());
				i.setOpenCoCloseDate(rs.getDate("openCourseStartDate").toLocalDate());

			}
			rs.close();

		} catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
			} catch (SQLException se) {
			}
			try {
				SQLConnection.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}
		}

		return i;
	}

	public List<Instructor> studentListBySubject(String subCode) {

		List<Instructor> studentListBySubject = new ArrayList<Instructor>();

		String sql = "";

		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = SQLConnection.connect();
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, subCode);

			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {

				Instructor i = new Instructor();

				i.setMid(rs.getString("mid"));
				i.setName_(rs.getString("name_"));
				i.setPhone(rs.getString("phone"));

				// failureCode(탈락코드)가 없으면 수강중인 상태
				i.setStudentStatus(rs.getString("failureCode"));

				i.setFailureDate(rs.getDate("failureDate").toLocalDate());

				studentListBySubject.add(i);

			}
			rs.close();

		} catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
			} catch (SQLException se) {
			}
			try {
				SQLConnection.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}
		}

		return studentListByCourse;
	}

	public List<Instructor> subjectListWithTestInfo() {

		List<Instructor> subjectListWithTestInfo = new ArrayList<Instructor>();

		// 과목코드 / 과목명 / 과목시작일 / 과목종료일 / 교재명 / 과정코드 / 과정명 / 과정시작일 / 과정종료일 / 강의실 / 출결배점 /
		// 필기배점 / 실기배점
		// 과목종료이 오늘 이전인 것들만 조인해달라고 하기!

		String sql = "";

		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = SQLConnection.connect();
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, this.loginInstructor);

			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {

				Instructor i = new Instructor();

				i.setMid(rs.getString("mid"));
				i.setName_(rs.getString("name_"));
				i.setPhone(rs.getString("phone"));

				// failureCode(탈락코드)가 없으면 수강중인 상태
				i.setStudentStatus(rs.getString("failureCode"));

				i.setFailureDate(rs.getDate("failureDate").toLocalDate());

			}
			rs.close();

		} catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
			} catch (SQLException se) {
			}
			try {
				SQLConnection.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}
		}

		return subjectListWithTestInfo;
	}

}
