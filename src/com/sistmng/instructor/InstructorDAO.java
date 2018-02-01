package com.sistmng.instructor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class InstructorDAO {

	public String instructorInfo() {
		StringBuilder sb = new StringBuilder();

		return sb.toString();
	}

	public List<Instructor> courseList() {

		List<Instructor> courseList = new ArrayList<Instructor>();

		return courseList;

	}

	public Instructor selectedSubject(String subCode) {
		Instructor i = new Instructor();

		String sql = "";

		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			// conn = DBConnection.connect();
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, subCode);

			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {

				i.setOpenSubCode(rs.getString("openSubCode"));
				i.setSubjectName(rs.getString("subjectName"));
				i.setOpenCourseStartDate(rs.getDate("openSubStarDate").toLocalDate());
				i.setOpenCourseCloseDate(rs.getDate("openCourseStartDate").toLocalDate());

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
				// DBConnection.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}
		}
		

		return i;
	}

	public List<Instructor> studentListByCourse(String subCode) {

		List<Instructor> studentListByCourse = new ArrayList<Instructor>();

		String sql = "";

		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			// conn = DBConnection.connect();
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, subCode);

			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {

				Instructor i = new Instructor();

				i.setMid(rs.getString("mid"));
				i.setName_(rs.getString("name_"));
				i.setPhone(rs.getString("phone"));
				// i.setStudentStatus();

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
				// DBConnection.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}
		}

		return studentListByCourse;
	}

}
