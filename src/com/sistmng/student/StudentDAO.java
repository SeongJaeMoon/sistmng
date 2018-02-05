package com.sistmng.student;

import java.util.*;
import com.sistmng.SQLConnection;
import java.sql.*;
import java.time.LocalDate;

public class StudentDAO {

	//회원정보출력
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
	
	//과정정보출력
	public List<Student> menu_2(String mid) {
		List<Student>result = new ArrayList<Student>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		//개설과정, 
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

	//과목 정보 전체 출력
	public List<Student> menu_21(String openCoCode){
		List<Student>result = new ArrayList<Student>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		//개설과정, 개설과목, 과목, 시험, 배점, 회원
		//openSubject_, subject_, course_, score_, test_, distribution_, member_, books_
		/*
		CREATE OR REPLACE VIEW studentSubjectView
		SELECT os.openSubCode, su.subjectName, os.openSubStartDate, os.openSubCloseDate, b.bookName, m.name_, di.attDistribution, di.wriDistribution, di.pracDistribution, sc.attendanceScore, sc.writingScore, sc.practiceScore,
		ts.testDate, ts.testFile
		FROM openSubject_ os, subject_ su, score_ sc, test_ ts, distribution_ di, member_ m, books_ b, openCourse_ oc, student_ st
		WHERE os.subjectCode = su.subjectCode
		AND os.mid = m.mid
		AND os.bookCode = b.bookCode
		AND os.openSubCode = ts.openSubCode
		AND ts.testCode = di.testCode
		AND sc.testCode = di.testCode
		AND oc.openCoCode = os.openCoCode
		--AND os.openCoCode = '?'
		AND st.mid = sc.mid
		AND ts.testCode = sc.testCode;
		 */
		//과목코드 / 과목명 / 시작일 / 종료일 / 교재명 / 강사명 / 출결배점 / 출결점수 / 실기배점 / 실기점수 / 필기배점 / 필기점수 / 시험날짜 /시험문제
		String sql = "SELECT openSubCode, subjectName, openSubStartDate, openSubCloseDate, bookName, name_, attDistribution, wriDistribution, pracDistribution, attendanceScore, writingScore, practiceScore, testDate, testFile FROM studentSubjectView WHERE = ?";
		try {
			conn = SQLConnection.connect();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, openCoCode);
			
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				String openSubCode = rs.getString("openSubCode");
				String subjectName = rs.getString("subjectName");
				LocalDate openSubStartDate = rs.getDate("openSubStartDate").toLocalDate();
				LocalDate openSubCloseDate = rs.getDate("openSubCloseDate").toLocalDate();
				String bookName = rs.getString("bookName");
				String name_ = rs.getString("name_");
				int attDistribution = rs.getInt("attDistribution");
				int wriDistribution = rs.getInt("wriDistribution");
				int pracDistribution = rs.getInt("pracDistribution");
				int attendanceScore = rs.getInt("attendanceScore");
				int writingScore = rs.getInt("writingScore");
				int practiceScore = rs.getInt("practiceScore");
				
				Student st = new Student();
				st.setOpenSubCode(openSubCode);
				st.setSubjectName(subjectName);
				st.setOpenSubStartDate(openSubStartDate);
				st.setOpenSubCloseDate(openSubCloseDate);
				st.setBookName(bookName);
				st.setName_(name_);
				st.setattDistribution(attDistribution);
				st.setwriDistribution(wriDistribution);
				st.setpracDistribution(pracDistribution);
				st.setAttendanceScore(attendanceScore);
				st.setWritingScore(writingScore);
				st.setPracticeScore(practiceScore);
				
				result.add(st);
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
		String sql = "SELECT COUNT(*) AS mid FROM studentHistory_ WHERE mid = ?";
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
