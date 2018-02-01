package com.sistmng.student;

import java.util.*;

import com.sistmng.SQLConnection;

import java.sql.*;
import java.util.*;

public class StudentDAO {

	public Student menu_1(String mid) {

		Student student = new Student();
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "SELECT name_, ssn, phone, FROM member_ WHERE mid = ?";
		try {
			conn = SQLConnection.connect();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, mid);
			
			ResultSet rs = pstmt.executeQuery();
			
				String name_ = rs.getString("name_");
				String ssn = rs.getString("ssn");
				String phone = rs.getString("phone");
				
				student.setName_(name_);
				student.setSsn(ssn);
				student.setPhone(phone);

				//수강 횟수 계산 필요
				student.setCourseNumber(this.getCourseNumber(mid));
				
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
		return student;
	}
	
	public List<Student> menu_2(String mid) {
		List<Student>result = new ArrayList<Student>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		/*
		CREATE OR REPLACE VIEW studentHistoryView
		AS
		SELECT course_, courseName, openCoCode, openCoCloseDate, className, failureCode
		FROM openCourse_ o, studentHistory_ sh, dropOut_ d, student st, class_ cl
		WHERE o.openCoCode = sh.openCoCode
	 	AND st.mid = sh.mid
	 	AND o.classCode = cl.classCode
	 	AND d.mid = st.mid
	 	AND d.openCoCode = d.openCoCode 
		 */
		
		//과정코드 / 과정명 / 시작일 / 종료일 / 강의실 /수료
		String sql = "SELECT course_, courseName, openCoCode, openCoCloseDate, classCode FROM studentHistroyView WHERE mid = ?";
		try {
			conn = SQLConnection.connect();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, mid);
			
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				
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
		return result;
	}

	//과
	private List<Student> menu_21(String openCoCode){
		List<Student>result = new ArrayList<Student>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		/*
		CREATE OR REPLACE VIEW studentCourseView
		AS
		SELECT openCoCode, subjectName, openSubStartDate, openSubCloseDate, bookName, name, altDistribution, wriDistribution, pracDistribution, attendanceScore, writingScore, practiceScore, testDate, testFile
		FROM
		WHERE 
		
		 */
		String sql = "SELECT FROM WHERE";
		try {
			conn = SQLConnection.connect();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, openCoCode);
			
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				
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
		return result;
	}
	
	private int getCourseNumber(String mid) {
		int result = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "SELECT COUNT(*) AS mid FROM studentHistory_ WHERE = ?";
		try {
			conn = SQLConnection.connect();
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, mid);

			result = pstmt.executeUpdate();
			
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
		return result;
	}
}
