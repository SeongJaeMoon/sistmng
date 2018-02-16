package com.sistmng.admin;

import java.util.*;

import com.sistmng.SQLConnection;

import java.sql.*;
import java.time.LocalDate;

public class AdminDAO {


	//현재 날짜 구하는 변수
	private LocalDate now = LocalDate.now();
	
	public List<Admin> info() {
		
		List<Admin> result = new ArrayList<Admin>();
		
		String sql = "";

		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = SQLConnection.connect();
			
			pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			
			while (rs.next()) {
				
				String courseCode = rs.getString("courseCode");
				String courseName = rs.getString("courseName");
				
				Admin m = new Admin();
				
				m.setCourseCode(courseCode);
				m.setCourseName(courseName);
				
				result.add(m);
				
			}
			rs.close();
			
		} catch (SQLException se) {
			System.out.print(se.getMessage());
		} catch (Exception e) {
			System.out.print(e.getMessage());
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
			} catch (SQLException se) {
			}
			try {
				SQLConnection.close();
			} catch (SQLException se) {
				System.out.print(se.getMessage());
			}
		}
		
		return result;
		
		
	}

	// -----------------------------------------------

	// 1. 기초정보관리 메뉴

	// 1.1 과정관리

	public List<Admin> courseList() {
		List<Admin> result = new ArrayList<Admin>();
		
		String sql = "SELECT courseCode, coursename FROM course_";

		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = SQLConnection.connect();
			
			pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			
			while (rs.next()) {
				
				String courseCode = rs.getString("courseCode");
				String courseName = rs.getString("courseName");
				
				Admin m = new Admin();
				
				m.setCourseCode(courseCode);
				m.setCourseName(courseName);
				
				result.add(m);
				
			}
			rs.close();
			
		} catch (SQLException se) {
			System.out.print(se.getMessage());
		} catch (Exception e) {
			System.out.print(e.getMessage());
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
			} catch (SQLException se) {
			}
			try {
				SQLConnection.close();
			} catch (SQLException se) {
				System.out.print(se.getMessage());
			}
		}
		
		return result;
	}

	// 1.1.1 과정입력

	public int courseAdd(String value) {
		
		int result = 0;
		
		//코스코드, 코스이름
		String sql = "INSERT INTO course_(courseCode,courseName) VALUES ((SELECT CONCAT('COU', LPAD(NVL(SUBSTR(MAX(mid), 4), 0) + 1, 3, 0)) AS newMid FROM course_),?)";

		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = SQLConnection.connect();
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1,value);
			
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException se) {
			System.out.print(se.getMessage());
			
		} catch (Exception e) {
			System.out.print(e.getMessage());
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
			} catch (SQLException se) {
			}
			try {
				SQLConnection.close();
			} catch (SQLException se) {
				System.out.print(se.getMessage());
			}
		}
		
		return result;
		
	}

	// 1.1.2 과정삭제

	public int courseDelete(String value) {

		int result = 0;
		
		//코스코드
		String sql = "DELETE FROM course_ WHERE courseCode = ?";

		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = SQLConnection.connect();
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1,value);
			
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException se) {
			System.out.print(se.getMessage());
			
		} catch (Exception e) {
			System.out.print(e.getMessage());
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
			} catch (SQLException se) {
			}
			try {
				SQLConnection.close();
			} catch (SQLException se) {
				System.out.print(se.getMessage());
			}
		}
		
		return result;

	}
	
	
	//1.1.2 과정 삭제 가능 리스트
	
	public List<Admin> courseDeleteList() {
		List<Admin> result = new ArrayList<Admin>();
		
		//삭제 가능 목록 리스트
		//코스코드, 코스이름
		String sql = "SELECT c.courseCode, c.CourseName FROM course_ c, openCourse_ oc WHERE  c.courseCode = oc.courseCode(+) AND oc.courseCode IS NULL GROUP BY c.courseCode, c.courseName";

		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = SQLConnection.connect();
			
			pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			
			while (rs.next()) {
				
				String courseCode = rs.getString("courseCode");
				String courseName = rs.getString("courseName");
				
				Admin m = new Admin();
				
				m.setCourseCode(courseCode);
				m.setCourseName(courseName);
				
				result.add(m);
				
			}
			rs.close();
			
		} catch (SQLException se) {
			System.out.print(se.getMessage());
		} catch (Exception e) {
			System.out.print(e.getMessage());
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
			} catch (SQLException se) {
			}
			try {
				SQLConnection.close();
			} catch (SQLException se) {
				System.out.print(se.getMessage());
			}
		}
		
		return result;
	}
	
	//과정 이름 물어보는 메소드
	public List<Admin>courseNameList(String value) {
		

		List<Admin> result = new ArrayList<Admin>();
		
		//코스 코드, 코스 이름
		String sql = "SELECT courseCode, courseName FROM course_ WHERE courseCode =?";

		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = SQLConnection.connect();
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, value);
			
			ResultSet rs = pstmt.executeQuery();
			
			while (rs.next()) {
				
				String courseCode = rs.getString("courseCode");
				String courseName = rs.getString("courseName");
				
				Admin m = new Admin();
				
				m.setCourseCode(courseCode);
				m.setCourseName(courseName);
				
				
				
				result.add(m);
				
			}
			rs.close();
			
		} catch (SQLException se) {
			System.out.print(se.getMessage());
		} catch (Exception e) {
			System.out.print(e.getMessage());
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
			} catch (SQLException se) {
			}
			try {
				SQLConnection.close();
			} catch (SQLException se) {
				System.out.print(se.getMessage());
			}
		}
		
		return result;

		

	}

	// 1.2 과목관리

	public List<Admin> subjectList() {

		List<Admin> result = new ArrayList<Admin>();
		
		String sql = "SELECT subjectCode, subjectName FROM subject_";

		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = SQLConnection.connect();
			
			pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			
			while (rs.next()) {
				
				String subjectCode = rs.getString("subjectCode");
				String subjectName = rs.getString("subjectName");
				String bookName = rs.getString("bookName");
				
				Admin m = new Admin();
				
				m.setSubjectCode(subjectCode);
				m.setSubjectName(subjectName);
				m.setBookName(bookName);
				
				result.add(m);
				
			}
			rs.close();
			
		} catch (SQLException se) {
			System.out.print(se.getMessage());
		} catch (Exception e) {
			System.out.print(e.getMessage());
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
			} catch (SQLException se) {
			}
			try {
				SQLConnection.close();
			} catch (SQLException se) {
				System.out.print(se.getMessage());
			}
		}
		
		return result;

	}

	// 1.2.1 과목입력

	public int subjectAdd(String value) {
		
		int result = 0;
		
		//subjectCode자동증가
		//과목코드, 과목이름
		String sql = "INSERT INTO subject_(subjectCode,subjectName) VALUES ((SELECT CONCAT('SUB', LPAD(NVL(SUBSTR(MAX(mid), 4), 0) + 1, 3, 0)) AS newMid FROM member_),?)";

		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = SQLConnection.connect();
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, value);
			
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException se) {
			System.out.print(se.getMessage());
			
		} catch (Exception e) {
			System.out.print(e.getMessage());
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
			} catch (SQLException se) {
			}
			try {
				SQLConnection.close();
			} catch (SQLException se) {
				System.out.print(se.getMessage());
			}
		}
		
		return result;
		
	}

	// 1.2.2 과목삭제

	public int subjectDelete(String value) {

		int result = 0;
		
		String sql = "DELETE FROM subject_ WHERE subjectCode = ?";

		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = SQLConnection.connect();
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1,value);
			
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException se) {
			System.out.print(se.getMessage());
			
		} catch (Exception e) {
			System.out.print(e.getMessage());
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
			} catch (SQLException se) {
			}
			try {
				SQLConnection.close();
			} catch (SQLException se) {
				System.out.print(se.getMessage());
			}
		}
		
		return result;

	}
	
	// 1.2.2 과목 삭제 가능 리스트
	
	public List<Admin> subjectDeleteList() {

		List<Admin> result = new ArrayList<Admin>();
		
		String sql = "SELECT s.subjectCode, s.subjectName FROM subject_ s, openSubject_ os WHERE  s.subjectCode = os.subjectCode(+) AND os.subjectCode IS NULL GROUP BY  s.subjectCode, s.subjectName";

		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = SQLConnection.connect();
			
			pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			
			while (rs.next()) {
				
				String subjectCode = rs.getString("subjectCode");
				String subjectName = rs.getString("subjectName");
				
				Admin m = new Admin();
				
				m.setSubjectCode(subjectCode);
				m.setSubjectName(subjectName);
				
				result.add(m);
				
			}
			rs.close();
			
		} catch (SQLException se) {
			System.out.print(se.getMessage());
		} catch (Exception e) {
			System.out.print(e.getMessage());
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
			} catch (SQLException se) {
			}
			try {
				SQLConnection.close();
			} catch (SQLException se) {
				System.out.print(se.getMessage());
			}
		}
		
		return result;

	}
	
	//과목이름 물어보는 메소드
	public Admin subjectList(String sc) {
		

		Admin result = new Admin();
		
		String sql = "SELECT subjectCode, subJectName FROM subject_ WHERE subjectCode = ? ";
	
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = SQLConnection.connect();
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, sc);
			
			ResultSet rs = pstmt.executeQuery();
			
			while (rs.next()) {
				
				String subjectCode = rs.getString("subjectCode");
				String subjectName = rs.getString("subjectName");
				
				result.setSubjectCode(subjectCode);
				result.setSubjectName(subjectName);
				
			}
			rs.close();
			
		} catch (SQLException se) {
			System.out.print(se.getMessage());
		} catch (Exception e) {
			System.out.print(e.getMessage());
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
			} catch (SQLException se) {
			}
			try {
				SQLConnection.close();
			} catch (SQLException se) {
				System.out.print(se.getMessage());
			}
		}
		
		return result;

	}

	// 1.3 강의실 관리

	public List<Admin> classList() {

		List<Admin> result = new ArrayList<Admin>();
		
		//강의실 코드, 강의실 이름, 강의실 정원
		String sql = "SELECT classCode, className, classQuota FROM class_";

		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = SQLConnection.connect();
			
			pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			
			while (rs.next()) {
				
				String classCode = rs.getString("classCode");
				String className = rs.getString("className");
				String classQuota = rs.getString("classQuota");
				
				Admin m = new Admin();
				
				m.setClassCode(classCode);
				m.setClassName(className);
				m.setClassQuota(Integer.parseInt(classQuota));
				
				result.add(m);
				
			}
			rs.close();
			
		} catch (SQLException se) {
			System.out.print(se.getMessage());
		} catch (Exception e) {
			System.out.print(e.getMessage());
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
			} catch (SQLException se) {
			}
			try {
				SQLConnection.close();
			} catch (SQLException se) {
				System.out.print(se.getMessage());
			}
		}
		
		return result;

	}

	// 1.3.1 강의실 입력

	public int classAdd(String value1,int value2) {

		int result = 0;
		
		//강의실 코드, 관리자 코드(회원코드), 강의실 이름, 강의실 정원
		String sql = "INSERT INTO class_(classCode,mid,className,classQuota) VALUES ((SELECT CONCAT('CLA', LPAD(NVL(SUBSTR(MAX(mid), 4), 0) + 1, 3, 0)) AS newMid FROM member_),?,?,?)";


		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = SQLConnection.connect();
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1,value1);
			pstmt.setInt(2,value2);
			
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException se) {
			System.out.print(se.getMessage());
			
		} catch (Exception e) {
			System.out.print(e.getMessage());
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
			} catch (SQLException se) {
			}
			try {
				SQLConnection.close();
			} catch (SQLException se) {
				System.out.print(se.getMessage());
			}
		}
		
		return result;

	}

	// 1.3.2 강의실 삭제

	public int classDelete(String value) {

		int result = 0;
		
		String sql = "DELETE FROM class_ WHERE classCode = ?";

		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = SQLConnection.connect();
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1,value);
			
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException se) {
			System.out.print(se.getMessage());
			
		} catch (Exception e) {
			System.out.print(e.getMessage());
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
			} catch (SQLException se) {
			}
			try {
				SQLConnection.close();
			} catch (SQLException se) {
				System.out.print(se.getMessage());
			}
		}
		
		return result;
	}
	
	//1.3.2 강의실 삭제 가능 리스트
	public List<Admin> classDeleteList() {
		
		List<Admin> result = new ArrayList<Admin>();
		
		//삭제 쿼리
		//삭제 가능 강의실 목록
		//강의실 코드, 강의실 이름
		String sql = "SELECT c.classCode, c.className FROM class_ c, openCourse_ oc WHERE  c.classCode = oc.classCode(+) AND oc.classCode IS NULL GROUP BY  c.classCode, c.className";

		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = SQLConnection.connect();
			
			pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			
			while (rs.next()) {
				
				String classCode = rs.getString("classCode");
				String className = rs.getString("className");
				//String classQuota = rs.getString("classQuota");
				
				Admin m = new Admin();
				
				m.setClassCode(classCode);
				m.setClassName(className);
				//m.setClassQuota(Integer.parseInt(classQuota));
				
				result.add(m);
				
			}
			rs.close();
			
		} catch (SQLException se) {
			System.out.print(se.getMessage());
		} catch (Exception e) {
			System.out.print(e.getMessage());
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
			} catch (SQLException se) {
			}
			try {
				SQLConnection.close();
			} catch (SQLException se) {
				System.out.print(se.getMessage());
			}
		}
		
		return result;

	}
	
	//강의실 물어보는 메소드
	public List<Admin>classList(String value) {
		

		List<Admin> result = new ArrayList<Admin>();
		
		//강의실 코드, 강의실 이름
		String sql = "SELECT classCode, className FROM class_ WHERE classCode =?";
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = SQLConnection.connect();
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, value);
			
			ResultSet rs = pstmt.executeQuery();
			
			while (rs.next()) {
				
				String classCode = rs.getString("classCode");
				String className = rs.getString("className");
				
				Admin m = new Admin();
				
				m.setClassCode(classCode);
				m.setClassName(className);
				
				
				
				result.add(m);
				
			}
			rs.close();
			
		} catch (SQLException se) {
			System.out.print(se.getMessage());
		} catch (Exception e) {
			System.out.print(e.getMessage());
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
			} catch (SQLException se) {
			}
			try {
				SQLConnection.close();
			} catch (SQLException se) {
				System.out.print(se.getMessage());
			}
		}
		
		return result;

		

	}

	// 1.4 교재 관리

	public List<Admin> bookList() {
		
		List<Admin> result = new ArrayList<Admin>();
		
		//교재 번호, 교재 이름, 출판사
		String sql = "SELECT bookCode, bookName, bookPublisher FROM books_";

		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = SQLConnection.connect();
			
			pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			
			while (rs.next()) {
				
				String bookCode = rs.getString("bookCode");
				String bookName = rs.getString("bookName");
				String bookPublisher = rs.getString("bookPublisher");
				
				Admin m = new Admin();
				
				m.setBookCode(bookCode);
				m.setBookName(bookName);
				m.setBookPublisher(bookPublisher);
				
				result.add(m);
				
			}
			rs.close();
			
		} catch (SQLException se) {
			System.out.print(se.getMessage());
		} catch (Exception e) {
			System.out.print(e.getMessage());
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
			} catch (SQLException se) {
			}
			try {
				SQLConnection.close();
			} catch (SQLException se) {
				System.out.print(se.getMessage());
			}
		}
		
		return result;


	}

	// 1.4.1 교재 입력

	public int bookAdd(String value1,String value2) {

		int result = 0;
		//교재이름, 출판사
		String sql = "INSERT INTO books_(bookCode,bookName,bookPublisher) VALUES ((SELECT CONCAT('BOK', LPAD(NVL(SUBSTR(MAX(mid), 4), 0) + 1, 3, 0)) AS newMid FROM member_),?,?)";

		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = SQLConnection.connect();
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1,value1);
			pstmt.setString(2,value2);
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException se) {
			System.out.print(se.getMessage());
			
		} catch (Exception e) {
			System.out.print(e.getMessage());
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
			} catch (SQLException se) {
			}
			try {
				SQLConnection.close();
			} catch (SQLException se) {
				System.out.print(se.getMessage());
			}
		}
		
		return result;
	}

	// 1.4.2 교재 삭제

	public int bookDelete(String value) {
		
		int result = 0;
		
		//교재 코드 입력
		String sql = "DELETE FROM books_ WHERE bookCode = ?";


		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = SQLConnection.connect();
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1,value);
			
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException se) {
			System.out.print(se.getMessage());
			
		} catch (Exception e) {
			System.out.print(e.getMessage());
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
			} catch (SQLException se) {
			}
			try {
				SQLConnection.close();
			} catch (SQLException se) {
				System.out.print(se.getMessage());
			}
		}
		
		return result;
	}
	
	// 1.4.2 교재 삭제 가능 목록
	public List<Admin> bookDeleteList() {
		
		List<Admin> result = new ArrayList<Admin>();
		
		//삭제 가능 교재 목록
        //교재 코드, 교재 이름
		String sql = "SELECT b.bookCode, b.bookName FROM books_ b, openSubject_ os WHERE  b.bookCode = os.bookCode(+) AND  os.bookCode IS NULL GROUP BY  b.bookCode, b.bookName";


		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = SQLConnection.connect();
			
			pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			
			while (rs.next()) {
				
				String bookCode = rs.getString("bookCode");
				String bookName = rs.getString("bookName");
				String bookPublisher = rs.getString("bookPublisher");
				
				Admin m = new Admin();
				
				m.setBookCode(bookCode);
				m.setBookName(bookName);
				m.setBookPublisher(bookPublisher);
				
				result.add(m);
				
			}
			rs.close();
			
		} catch (SQLException se) {
			System.out.print(se.getMessage());
		} catch (Exception e) {
			System.out.print(e.getMessage());
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
			} catch (SQLException se) {
			}
			try {
				SQLConnection.close();
			} catch (SQLException se) {
				System.out.print(se.getMessage());
			}
		}
		
		return result;


	}
	
	//교재 물어보는 메소드
	public List<Admin>bookList(String value) {
		

		List<Admin> result = new ArrayList<Admin>();
		
		//책 코드, 책 이름, 출판사
		String sql = "SELECT bookCode, bookName, bookPublisher FROM books_ WHERE bookCode = ?";
				
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = SQLConnection.connect();
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, value);
			
			ResultSet rs = pstmt.executeQuery();
			
			while (rs.next()) {
				
				String bookCode = rs.getString("bookCode");
				String bookName = rs.getString("bookName");
				String bookPublisher = rs.getString("bookPublisher");
				
				Admin m = new Admin();
				
				m.setBookCode(bookCode);
				m.setBookName(bookName);
				m.setBookPublisher(bookPublisher);
				
				
				
				result.add(m);
				
			}
			rs.close();
			
		} catch (SQLException se) {
			System.out.print(se.getMessage());
		} catch (Exception e) {
			System.out.print(e.getMessage());
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
			} catch (SQLException se) {
			}
			try {
				SQLConnection.close();
			} catch (SQLException se) {
				System.out.print(se.getMessage());
			}
		}
		
		return result;

		

	}

	// -----------------------------------------------

	// 2. 강사 계정 관리
	
	//강사 회원번호, 이름 구하는 메소드
	public Admin midNameList(String key) {	

		Admin result = new Admin();
		
		String sql = "SELECT mid, name_ FROM member_ WHERE mid = ?";
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = SQLConnection.connect();
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, key);
			
			ResultSet rs = pstmt.executeQuery();
			
			while (rs.next()) {
				
				String mid = rs.getString("mid");
				String name_ = rs.getString("name_");
				
				Admin m = new Admin();
				
				result.setMid(mid); 
				result.setName_(name_);
				
			}
			rs.close();
			
		} catch (SQLException se) {
			System.out.print(se.getMessage());
		} catch (Exception e) {
			System.out.print(e.getMessage());
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
			} catch (SQLException se) {
			}
			try {
				SQLConnection.close();
			} catch (SQLException se) {
				System.out.print(se.getMessage());
			}
		}
		
		return result;
	}
	
	// 2.1 강사 목록
	public List<Admin>InstructorList() {
		
		List<Admin> result = new ArrayList<Admin>();
		
		/*
		SELECT m.mid, m.name_, m.ssn, m.phone, s.subjectName, i.instructorRegDate
        FROM member_ m, instructor_ i, checkSubject_ c, subject_ s
        WHERE m.mid = i.mid
        AND c.mid = i.mid
        AND s.subjectCode = c.subjectCode
        ORDER BY m.mid;
		 */
		
		//강사고유번호, 이름, 주민등록번호 뒷자리, 전화번호, 강의가능과목, 강사등록일
		String sql = "SELECT m.mid, m.name_, m.ssn, m.phone, s.subjectName, i.instructorRegDate FROM member_ m, instructor_ i, checkSubject_ c, subject_ s WHERE m.mid = i.mid AND c.mid = i.mid AND s.subjectCode = c.subjectCode ORDER BY m.mid";
		int i = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = SQLConnection.connect();
			pstmt = conn.prepareStatement(sql);
			
			ResultSet rs = pstmt.executeQuery();
	
			while (rs.next()) {
				
				String mid = rs.getString("mid");
				String name_ = rs.getString("name_");
				String ssn = rs.getString("ssn");
				String phone = rs.getString("phone");
				String subjectName = rs.getString("subjectName");
				LocalDate instructorRegDate = rs.getDate("instructorRegDate").toLocalDate();
				
				
				Admin m = new Admin();
				
				m.setMid(mid); 
				m.setName_(name_);
				m.setSsn(ssn);
				m.setPhone(phone);
				m.setSubjectName(subjectName);
				m.setInstructorRegDate(instructorRegDate);
				
				if(result.size() == 0) {
					result.add(m);
				}else if(result.get(i).getMid().equals(mid)) {
					result.get(i).setSubjectName(String.format("%s / %s", result.get(i).getSubjectName(), subjectName));
				}else {
					result.add(m);
					++i;
				}
	
			}
			rs.close();
			
		} catch (SQLException se) {
			System.out.print(se.getMessage());
		} catch (Exception e) {
			System.out.print(e.getMessage());
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
			} catch (SQLException se) {
			}
			try {
				SQLConnection.close();
			} catch (SQLException se) {
				System.out.print(se.getMessage());
			}
		}
		
		return result;
	}
	
	// 2.1.1 강사상세보기

	public List<Admin> InstructorSubjectDetailList(String mid) {
		
		List<Admin> result = new ArrayList<Admin>();
		//[MEM002 / 장혜진]강사님
		//강의 과목 / 개설 과목 코드 / 과목 시작일 / 과목 종료일 / 개설 과정 / 개설과정명 / 과정 시작일 / 과정 종료일 / 강의실 / 강의상태						
		/*
		CREATE OR REPLACE VIEW detailInstructor
        AS
        SELECT s.subjectName, p.openSubCode, p.openSubStartDate, p.openSubCloseDate, o.openCoCode, c.courseName, o.openCoStartDate, o.openCoCloseDate, l.className, k.mid
        FROM checkSubject_ k, subject_ s, openCourse_ o, course_ c, class_ l, openSubject_ p
        WHERE p.mid = k.mid
        AND k.subjectCode = s.subjectCode
        AND s.subjectCode = p.subjectCode
        AND o.openCoCode = p.openCoCode
        AND o.classCode = l.classCode
        AND o.courseCode = c.courseCode
        AND p.subjectCode = s.subjectCode; 
		 */
		String sql = "SELECT subjectName, openSubCode, openSubStartDate, openSubCloseDate, openCoCode, courseName, openCoStartDate, openCoCloseDate, className FROM detailInstructor WHERE mid = ?";
		 
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = SQLConnection.connect();
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, mid);
			
			ResultSet rs = pstmt.executeQuery();
			
			while (rs.next()) {
				
				String subjectName = rs.getString("subjectName");
				String openSubCode = rs.getString("openSubCode");
				LocalDate openSubStartDate = rs.getDate("openSubStartDate").toLocalDate();
				LocalDate openSubCloseDate = rs.getDate("openSubCloseDate").toLocalDate();
				String courseName = rs.getString("courseName");
				LocalDate openCoStartDate = rs.getDate("openCoStartDate").toLocalDate();
				LocalDate openCoCloseDate = rs.getDate("openCoCloseDate").toLocalDate();
				String className = rs.getString("className");
				
				Admin m = new Admin();
				
				m.setSubjectName(subjectName);
				m.setOpenSubCode(openSubCode);
				m.setOpenSubStartDate(openSubStartDate);
				m.setOpenSubCloseDate(openSubCloseDate);
				m.setCourseName(courseName);
				m.setOpenCoStartDate(openCoStartDate);
				m.setOpenCoCloseDate(openCoCloseDate);
				m.setClassName(className);
				
				if(m.getOpenCoCloseDate().isAfter(now)) {
					m.setInstStatus("강의종료");
				} else if (m.getOpenCoCloseDate().isBefore(now) && m.getOpenCoStartDate().isBefore(now)) {
					m.setInstStatus("강의중");
				} else {
					m.setInstStatus("강의 예정");
				}
				
				result.add(m);
				
			}
			rs.close();
			
		} catch (SQLException se) {
			System.out.print(se.getMessage());
		} catch (Exception e) {
			System.out.print(e.getMessage());
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
			} catch (SQLException se) {
			}
			try {
				SQLConnection.close();
			} catch (SQLException se) {
				System.out.print(se.getMessage());
			}
		}
		
		return result;

	}

	// 2.1.2 강의가능과목 추가

	public int InstructorSubjectAdd(String mid, String subjectCode) {

		int result = 0;
		
		String sql = "INSERT INTO checkSubject_(mid,subjectCode) VALUES (?,?)";
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = SQLConnection.connect();
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1,mid);
			pstmt.setString(2,subjectCode);
				
			result = pstmt.executeUpdate();
			
		} catch (SQLException se) {
			System.out.print(se.getMessage());
			
		} catch (Exception e) {
			System.out.print(e.getMessage());
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
			} catch (SQLException se) {
			}
			try {
				SQLConnection.close();
			} catch (SQLException se) {
				System.out.print(se.getMessage());
			}
		}
		
		return result;

	}

	// 2.1.3 강의가능과목 삭제 전(삭제 가능 여부 물어보기)
	public List<Admin> instructorSubjectDelete(){
		
		List<Admin> result = new ArrayList<Admin>();
		/*
		-강의 가능 과목 삭제시, 개설 과목에 해당 과목이 들어있는지 확인 작업이 필요. (삭제 가능한 것만 보여주기.)
        --현재 날짜가 개설 과목 시작, 종료일 사이에 들어있는 과목이 아닌 경우만 
        SELECT che.subjectCode, che.mid
        FROM instructor_ ins, checkSubject_ che, subject_ sub
        WHERE (sub.subjectCode, ins.mid) NOT IN ( 
            SELECT openS.subjectCode, openS.mid
            FROM instructor_ ins, subject_ sub, openSubject_ openS, checkSubject_ che
            WHERE ins.mid = che.mid
            AND openS.mid = ins.mid
            AND sub.subjectCode = che.subjectCode
            AND openS.openSubStartDate <= TO_CHAR(SYSDATE, 'YYYY-MM-DD')
            AND openS.openSubCloseDate >= TO_CHAR(SYSDATE, 'YYYY-MM-DD'))
            AND che.mid = ins.mid
            AND sub.subjectCode = che.subjectCode;
		 */
		String sql = "SELECT che.subjectCode, che.mid FROM instructor_ ins, checkSubject_ che, subject_ sub WHERE (sub.subjectCode, ins.mid) NOT IN (SELECT openS.subjectCode, openS.mid FROM instructor_ ins, subject_ sub, openSubject_ openS, checkSubject_ che WHERE ins.mid = che.mid AND openS.mid = ins.mid AND sub.subjectCode = che.subjectCode AND openS.openSubStartDate <= TO_CHAR(SYSDATE, 'YYYY-MM-DD') AND openS.openSubCloseDate >= TO_CHAR(SYSDATE, 'YYYY-MM-DD')) AND che.mid = ins.mid AND sub.subjectCode = che.subjectCode";
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = SQLConnection.connect();
			
			pstmt = conn.prepareStatement(sql);
			
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				String subjectCode = rs.getString("subjectCode");
				String mid = rs.getString("mid");
				
				Admin a = new Admin();
				a.setSubjectCode(subjectCode);
				a.setMid(mid);
				
				result.add(a);
			}
			
			rs.close();
		} catch (SQLException se) {
			System.out.print(se.getMessage());
			
		} catch (Exception e) {
			System.out.print(e.getMessage());
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
			} catch (SQLException se) {
			}
			try {
				SQLConnection.close();
			} catch (SQLException se) {
				System.out.print(se.getMessage());
			}
		}
		
		return result;
	}
	
	// 2.1.3 강의가능과목 삭제
	public int InstructorSubjectDelete(String mid,String subjectCode) {

		int result = 0;
		
		String sql = "DELETE FROM checkSubject_ WHERE mid = ? AND subjectCode = ?";


		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = SQLConnection.connect();
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1,mid);
			pstmt.setString(2,subjectCode);
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException se) {
			System.out.print(se.getMessage());
			
		} catch (Exception e) {
			System.out.print(e.getMessage());
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
			} catch (SQLException se) {
			}
			try {
				SQLConnection.close();
			} catch (SQLException se) {
				System.out.print(se.getMessage());
			}
		}
		
		return result;

	}
	
	//2.2 강사 등록 출력 리스트
	public List<Admin> InstructorAddList(String value) {
		
		List<Admin> result = new ArrayList<Admin>();
		/*
		  SELECT mid, name_, ssn, phone, memberRegDate, memberStatus
        	  FROM member_
        	  WHERE name_ = ?;
		 */
		
		//회원 고유번호, 회원 이름, 회원 주민번호, 회원 전화번호, 회원 등록일, 회원 상태
		String sql = "SELECT mid, name_, ssn, phone, memberRegDate, memberStatus FROM member_ WHERE name_ = ?";
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = SQLConnection.connect();
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, value);
			
			ResultSet rs = pstmt.executeQuery();
			
			while (rs.next()) {
				
				String mid = rs.getString("mid");
				String name_ = rs.getString("name_");
				String ssn = rs.getString("ssn");
				String phone = rs.getString("phone");
				String memberRegDate = rs.getString("memberRegDate");
				String memberStatus = rs.getString("memberStatus");
				
				Admin m = new Admin();
				
				m.setMid(mid);
				m.setName_(name_);
				m.setSsn(ssn);
				m.setPhone(phone);
				m.setMemberRegDate(LocalDate.parse(memberRegDate));
				m.setMemberStatus(memberStatus);

				result.add(m);
				
			}
			rs.close();
			
		} catch (SQLException se) {
			System.out.print(se.getMessage());
		} catch (Exception e) {
			System.out.print(e.getMessage());
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
			} catch (SQLException se) {
			}
			try {
				SQLConnection.close();
			} catch (SQLException se) {
				System.out.print(se.getMessage());
			}
		}
		
		return result;

	}

	// 2.2 강사 등록(기존회원)
	public int InstructorAdd(String value) {

		int result1 = 0;
		int result2 = 0;
		
		String sql = "INSERT INTO instructor_(mid, instructorRegDate) VALUES (?,?)";
		String sql2 = "UPDATE member_ SET memberStatus = 'I' WHERE  mid = ?";

		Connection conn = null;
		PreparedStatement pstmt = null;
		PreparedStatement pstmt1 = null;
		try {
			conn = SQLConnection.connect();
			conn.setAutoCommit(false);
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1,value);
			pstmt.setString(2, String.valueOf(now));
			
			result1 = pstmt.executeUpdate();
			
			pstmt1 = conn.prepareStatement(sql2);
			pstmt1.setString(1, value);
			
			result2 = pstmt1.executeUpdate();
			
			conn.commit();
		} catch (SQLException se) {
			System.out.print(se.getMessage());
			try {
				conn.rollback();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (Exception e) {
			System.out.print(e.getMessage());
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
			} catch (SQLException se) {
			}
			try {
				SQLConnection.close();
			} catch (SQLException se) {
				System.out.print(se.getMessage());
			}
		}
		
		return result1+result2;

	}
	
	//2.2 강사등록 (신규회원)
	public int InstructorAddNew(String name_,String ssn,String phone,String memberRegDate) {

		int result1 = 0;
		int result2 = 0;
		/*
		 --2)이름 검색 -> 검색 결과 없음 -> 이름, 주민번호, 전화번호, 등록일
		-- (1,?) : 회원번호
		-- (2,?) : 등록날짜 -> 2번째 INSERT문의 (3,?)자리에 중복 입력
		 */
		String sql = "INSERT INTO instructor_(mid,instructorRegDate) VALUES (?,?)";
		String sql2 = "INSERT INTO member_(mid,memberStatus,name_,ssn,phone,memberRegDate) VALUES ((SELECT CONCAT('MEM', LPAD(NVL(SUBSTR(MAX(mid), 4), 0) + 1, 3, 0)) AS newMid FROM member_),'I',?,?,?,?)";

		Connection conn = null;
		PreparedStatement pstmt = null;
		PreparedStatement pstmt1 = null;
		try {
			conn = SQLConnection.connect();
			conn.setAutoCommit(false);
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, name_);
			pstmt.setString(2, String.valueOf(now));
			
			result1 = pstmt.executeUpdate();
			
			pstmt1 = conn.prepareStatement(sql2);
			
			pstmt1.setString(1, name_);
			pstmt1.setString(2, ssn);
			pstmt1.setString(3, phone);
			pstmt1.setString(4, String.valueOf(now));
			
			result2 = pstmt.executeUpdate();
			conn.commit();
		} catch (SQLException se) {
			System.out.print(se.getMessage());
			try {
				conn.rollback();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (Exception e) {
			System.out.print(e.getMessage());
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
			} catch (SQLException se) {
			}
			try {
				SQLConnection.close();
			} catch (SQLException se) {
				System.out.print(se.getMessage());
			}
		}
		
		return result1+result2;

	}

	// 2.3 강사 삭제
	
	//2.2 강사 삭제 가능만 출력 리스트 
	// [삭제 가능 강사] -> (cf.개설 과목 등록 여부로 판단, 들어 있으면 삭제 불가)
	public List<Admin> InstructorDelete() {
		
		List<Admin> result = new ArrayList<Admin>();
		/*
		 SELECT i.mid, m.name_, m.ssn, m.phone,  i.instructorRegDate , s.subjectName
		FROM openSubject_ os, instructor_ i, member_ m, checkSubject_ cs, subject_ s
		WHERE i.mid = os.mid(+)
		AND m.mid = i.mid
		AND cs.mid = i.mid
		AND cs.subjectCode = s.subjectCode
		AND  os.mid IS NULL
		GROUP BY  i.mid, m.name_, m.ssn, m.phone,  i.instructorRegDate , s.subjectName
		ORDER BY i.mid;
		 */
		String sql = "SELECT i.mid, m.name_, m.ssn, m.phone, s.subjectName, i.instructorRegDate FROM openSubject_ os, instructor_ i, member_ m, checkSubject_ cs, subject_ s WHERE i.mid = os.mid(+) AND m.mid = i.mid AND cs.mid = i.mid AND cs.subjectCode = s.subjectCode AND  os.mid IS NULL GROUP BY  i.mid, m.name_, m.ssn, m.phone,  i.instructorRegDate , s.subjectName ORDER BY i.mid";

		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = SQLConnection.connect();
			
			pstmt = conn.prepareStatement(sql);
			
			
			ResultSet rs = pstmt.executeQuery();
			
			while (rs.next()) {
				
				String mid = rs.getString("mid");
				String name_ = rs.getString("name_");
				String ssn = rs.getString("ssn");
				String phone = rs.getString("phone");
				String subjectName = rs.getString("subjectName");
				LocalDate instructorRegDate = rs.getDate("instructorRegDate").toLocalDate();
				
				Admin m = new Admin();
				
				m.setMid(mid);
				m.setName_(name_);
				m.setSsn(ssn);
				m.setPhone(phone);
				m.setSubjectName(subjectName);
				m.setInstructorRegDate(instructorRegDate);

				result.add(m);
				
			}
			rs.close();
			
		} catch (SQLException se) {
			System.out.print(se.getMessage());
		} catch (Exception e) {
			System.out.print(e.getMessage());
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
			} catch (SQLException se) {
			}
			try {
				SQLConnection.close();
			} catch (SQLException se) {
				System.out.print(se.getMessage());
			}
		}
		
		return result;

	}
	
	//강사 삭제
	public int InstructorDelete(String value) {

		int result = 0;
		
		String sql = "DELETE FROM instructor WHERE mid = ?";
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = SQLConnection.connect();
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1,value);
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException se) {
			System.out.print(se.getMessage());
			
		} catch (Exception e) {
			System.out.print(e.getMessage());
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
			} catch (SQLException se) {
			}
			try {
				SQLConnection.close();
			} catch (SQLException se) {
				System.out.print(se.getMessage());
			}
		}

		return result;

	}
	

	// -----------------------------------------------

	// 3.개설 과정 관리

	// 3.1 개설 과정 출력

	public List<Admin> openCourseList() {

		List<Admin> result = new ArrayList<Admin>();	
		
		/*
		
		 */

		//개설과정코드, 과정이름, 과정 시작일, 과정 종료일, 강의실 이름, 등록 과목 수, 등록 인원 수
		String sql = " SELECT oc.openCoCode, co.courseName, oc.openCoStartDate, oc.openCoCloseDate, cl.className, NVL(count_,0) count_, NVL(count_studentHistory,0) count_studentHistory FROM openCourse_ oc, course_ co, class_ cl, count_, count_studentHistory WHERE oc.courseCode = co.courseCode AND oc.classCode = cl.classCode AND  oc.openCoCode = count_.openCoCode(+) AND oc.openCoCode = count_studentHistory.openCoCode(+) ORDER BY oc.openCoCode";

		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = SQLConnection.connect();
			pstmt = conn.prepareStatement(sql);
			
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				String openCoCode = rs.getString("openCoCode");
				String courseName = rs.getString("courseName");
				LocalDate openCoStartDate = rs.getDate("openCoStartDate").toLocalDate();
				LocalDate openCoCloseDate = rs.getDate("openCoCloseDate").toLocalDate();
				String className = rs.getString("className");
				int count_ = rs.getInt("count_"); //등록 과목 갯수
				int count_studentHistory = rs.getInt("count_studentHistory");//개설 과정 수강생 등록인원
				
				Admin a = new Admin();
				a.setOpenCoCode(openCoCode);
				a.setCourseName(courseName);
				a.setOpenCoStartDate(openCoStartDate);
				a.setOpenCoCloseDate(openCoCloseDate);
				a.setClassName(className);
				a.setCount_(count_);
				a.setCount_studentHistory(count_studentHistory);
				
				result.add(a);
			}
	
			
		} catch (SQLException se) {
			System.out.print(se.getMessage());
			
		} catch (Exception e) {
			System.out.print(e.getMessage());
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
			} catch (SQLException se) {
			}
			try {
				SQLConnection.close();
			} catch (SQLException se) {
				System.out.print(se.getMessage());
			}
		}
		
		return result;
	}

	// 3.1.1 개설 과정 상세보기

	public List<Admin> openCourseDetailList(String value) {
		
		//개설 과정 코드, 과정 이름, 과정 시작일, 과정 종료일, 강의실 이름
		List<Admin> result = new ArrayList<Admin>();
        String sql = "SELECT oc.openCoCode, co.courseName, oc.openCoStartDate, oc.openCoCloseDate, cl.className FROM openCourse_ oc, course_ co, class_ cl WHERE oc.courseCode = co.courseCode AND oc.classCode = cl.classCode AND oc.openCoCode = ?";
        
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
        	conn = SQLConnection.connect();
        	pstmt = conn.prepareStatement(sql);
        	pstmt.setString(1,value);
        	ResultSet rs = pstmt.executeQuery();
        	while(rs.next()) {
        		String openCoCode = rs.getString("openCoCode");
        		String courseName = rs.getString("courseName");
        		LocalDate openCoStartDate = rs.getDate("openCoStartDate").toLocalDate();
        		LocalDate openCoCloseDate = rs.getDate("openCoCloseDate").toLocalDate();
        		String className = rs.getString("className");
        		
        		Admin m = new Admin();
        		m.setOpenCoCode(openCoCode);
        		m.setCourseName(courseName);
        		m.setOpenCoStartDate(openCoStartDate);
        		m.setOpenCoCloseDate(openCoCloseDate);
        		m.setClassName(className);
        		result.add(m);        		
        	}
        	rs.close();	
        } catch(SQLException se) {
        	System.out.println(se.getMessage());
        } catch(Exception e) {
        	System.out.println(e.getMessage());
        } finally {
        	try {
        		if(pstmt != null)
        			pstmt.close();
        	} catch(SQLException se) {
        	}
        	try {
        		SQLConnection.close();
        	} catch (SQLException se) {
        		System.out.println(se.getMessage());
        	}
        }
		return result;
	}


	// 3.1.1.1 과목 상세보기

	public List<Admin> subjectDetailList(String value) {

	List<Admin> result = new ArrayList<Admin>();
    //과목이름, 과목 시작일, 과목 종료일, 책 이름, 강사이름
    String sql = "SELECT subjectName, openSubStartDate, openSubCloseDate, bookName, name_ FROM openCourse_ oc, openSubject_ os, subject_ s, books_ b, member_ m WHERE oc.openCoCode = os.openCoCode AND os.subjectCode = s.subjectCode AND os.mid = m.mid AND os.bookCode = b.bookCode AND os.openCoCode = ?";
    
    Connection conn = null;
    PreparedStatement pstmt = null;
    try {
    	conn = SQLConnection.connect();
    	pstmt = conn.prepareStatement(sql);
    	pstmt.setString(1,value);
    	ResultSet rs = pstmt.executeQuery();
    	while(rs.next()) {
    		String subjectName = rs.getString("subjectName");
    		String bookName = rs.getString("bookName");
    		LocalDate openSubStartDate = rs.getDate("openSubStartDate").toLocalDate();
    		LocalDate openSubCloseDate = rs.getDate("openSubCloseDate").toLocalDate();
    		String name_ = rs.getString("name_");
    		
    		Admin m = new Admin();
    		m.setSubjectName(subjectName);
    		m.setBookName(bookName);
    		m.setOpenSubStartDate(openSubStartDate);
    		m.setOpenSubCloseDate(openSubCloseDate);
    		m.setName_(name_);
    		result.add(m);        		
    	}
    	rs.close();	
    } catch(SQLException se) {
    	System.out.println(se.getMessage());
    } catch(Exception e) {
    	System.out.println(e.getMessage());
    } finally {
    	try {
    		if(pstmt != null)
    			pstmt.close();
    	} catch(SQLException se) {
    	}
    	try {
    		SQLConnection.close();
    	} catch (SQLException se) {
    		System.out.println(se.getMessage());
    	}
    }
	return result;
}


//3.1.1.2 수강생 보기
// 중도 탈락문제

	public void test(String value) {
	
	}

	public List<Admin> studentDetailList(String value) {

	List<Admin> result = new ArrayList<Admin>();		
    //수강생 이름, 주민번호, 전화번호, 학생 등록일, 수료일
    String sql = "SELECT m.mid mid, m.name_ name_, m.ssn ssn, m.phone phone, s.sstudentRegDate sstudentRegDate, DECODE(d.mid, NULL, TO_CHAR(oc.openCoCloseDate, 'YY/MM/DD'),  TO_CHAR(d.failuredate, 'YY/MM/DD') ||' - ' ||'중도탈락' ) completionCheck FROM member_ m, student_ s, openCourse_ oc, studentHistory_ sh , dropOut_ d WHERE s.mid = sh.mid AND sh.openCoCode = oc.openCoCode AND sh.mid = m.mid AND sh.mid = d.mid(+) AND sh.openCoCode = ?";
    
    Connection conn = null;
    PreparedStatement pstmt = null;
    try {
    	conn = SQLConnection.connect();
    	pstmt = conn.prepareStatement(sql);
    	pstmt.setString(1,value);
    	ResultSet rs = pstmt.executeQuery();
    	while(rs.next()) {

    		String name_ = rs.getString("name_");
    		String ssn = rs.getString("ssn");
    		String phone = rs.getString("phone");
    		LocalDate sstudentRegDate = rs.getDate("sstudentRegDate").toLocalDate();
    		String completionCheck = rs.getString("completionCheck");
    		
    		Admin m = new Admin();
    		m.setName_(name_);
    		m.setSsn(ssn);
    		m.setSstudentRegDate(sstudentRegDate);
    		m.setPhone(phone);
    		m.setCompletionCheck(completionCheck);
    		result.add(m);        		
    	}
    	rs.close();	
    } catch(SQLException se) {
    	System.out.println(se.getMessage());
    } catch(Exception e) {
    	System.out.println(e.getMessage());
    } finally {
    	try {
    		if(pstmt != null)
    			pstmt.close();
    	} catch(SQLException se) {
    	}
    	try {
    		SQLConnection.close();
    	} catch (SQLException se) {
    		System.out.println(se.getMessage());
    	}
    }
	return result;
}

	//3.2 개설 과정 등록

	// courseList() 메소드 호출

	// classList() 메소드 호출
	
	// 클래스 중복 체크 메소드
	private int checkClass(String classCodeSc){
		//검색을 해서 값이 있으면 1 없으면 0
		int result = 0;	
		String sql = "SELECT classCode, className, classQuota FROM class_ WHERE classCode =?";

		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = SQLConnection.connect();
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,classCodeSc);
			
			
			ResultSet rs = pstmt.executeQuery();
			
			while (rs.next()) {
				
				String classCode = rs.getString("classCode");
				String className = rs.getString("className");
				
				Admin m = new Admin();
				
				m.setClassCode(classCode);
				m.setClassName(className);
			
				
			}
			rs.close();
			
		} catch (SQLException se) {
			System.out.print(se.getMessage());
		} catch (Exception e) {
			System.out.print(e.getMessage());
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
			} catch (SQLException se) {
			}
			try {
				SQLConnection.close();
			} catch (SQLException se) {
				System.out.print(se.getMessage());
			}
		}
		
		return result;
		
	}

	public int openCourseAdd(Admin m) {

        int result = 0;
	
        //개설 과정 코드, 코스 코드, 강의실 코드, 과정 시작일, 과정 종료일
		String sql = "INSERT INTO openCourse_(openCoCode,courseCode,classCode,openCoStartDate,openCoCloseDate) VALUES ((SELECT CONCAT('OCO', LPAD(NVL(SUBSTR(MAX(openCoCode), 4), 0) + 1, 3, 0)) AS newMid FROM openCourse_),?,?,?,?)";

		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = SQLConnection.connect();
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1,m.getCourseCode());
			pstmt.setString(2,m.getClassCode());
			pstmt.setString(3,m.getOpenCoStartDate().toString());
			pstmt.setString(4,m.getOpenCoCloseDate().toString());
//			pstmt.setDate(3, (java.sql.Date)Date.from(m.getOpenCoStartDate().atStartOfDay(ZoneId.systemDefault()).toInstant()));
//			pstmt.setDate(4, (java.sql.Date)Date.from(m.getOpenCoCloseDate().atStartOfDay(ZoneId.systemDefault()).toInstant()));	
			result = pstmt.executeUpdate();
			
		} catch (SQLException se) {
			System.out.print(se.getMessage());
			
		} catch (Exception e) {
			System.out.print(e.getMessage());
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
			} catch (SQLException se) {
			}
			try {
				SQLConnection.close();
			} catch (SQLException se) {
				System.out.print(se.getMessage());
			}
		}
		return result;
	}

	// 3.3 개설 과정 삭제
	// openCourseList() 메소드 호출
	public List<Admin> openCourseDelete() {

			List<Admin> result = new ArrayList<Admin>();
		    //개설 과정 코드, 코스 이름, 개설 과정 시작일, 개설 과정 종료일, 강의실 이름, 등록 과목 수, 등록 인원 수, 삭제가능여부

	        String sql = "SELECT oc.openCoCode, c.courseName, oc.openCoStartDate, oc.openCoCloseDate, ca.className, NVL(count_, '0') count_, NVL(count_studentHistory,'0') count_studentHistory, NVL2(sh.openCoCode, '삭제 불가', '삭제 가능') checkDelOpenCourse FROM openCourse_ oc, course_ c, class_ ca, studentHistory_ sh, count_ cu, count_studentHistory csh WHERE oc.courseCode = c.courseCode AND oc.classCode = ca.classCode AND oc.openCoCode = cu.openCoCode(+) AND oc.openCoCode =  csh.openCoCode(+) AND oc.openCoCode = sh.openCoCode(+) GROUP BY  oc.openCoCode, c.courseName, oc.openCoStartDate, oc.openCoCloseDate, ca.className, sh.openCoCode, count_, count_studentHistory ORDER BY  oc.openCoCode";
	        
	        Connection conn = null;
	        PreparedStatement pstmt = null;
	        try {
	        	conn = SQLConnection.connect();
	        	pstmt = conn.prepareStatement(sql);
	        	ResultSet rs = pstmt.executeQuery();
	        	while(rs.next()) {
	        		
	        		String openCoCode = rs.getString("openCoCode");
	        		String courseName = rs.getString("courseName");
	        		String className = rs.getString("className");
	        		LocalDate openCoStartDate = rs.getDate("openCoStartDate").toLocalDate();
	        		LocalDate openCoCloseDate = rs.getDate("openCoCloseDate").toLocalDate();
	        		int count_ = rs.getInt("count_");
	        		int count_studentHistory = rs.getInt("count_studentHistory");
	        		String checkDelOpenCourse = rs.getString("checkDelOpenCourse");
	   	
	        		Admin m = new Admin();
	        		m.setOpenCoCode(openCoCode);
	        		m.setCourseName(courseName);
	        		m.setClassName(className);
	        		m.setOpenCoStartDate(openCoStartDate);
	        		m.setOpenCoCloseDate(openCoCloseDate);
	        		m.setCount_(count_);
	        		m.setCount_studentHistory(count_studentHistory);
	        		m.setCheckDelOpenCourse(checkDelOpenCourse);
	        		
	        		result.add(m);        		
	        	}
	        	rs.close();	
	        } catch(SQLException se) {
	        	System.out.println(se.getMessage());
	        } catch(Exception e) {
	        	System.out.println(e.getMessage());
	        } finally {
	        	try {
	        		if(pstmt != null)
	        			pstmt.close();
	        	} catch(SQLException se) {
	        	}
	        	try {
	        		SQLConnection.close();
	        	} catch (SQLException se) {
	        		System.out.println(se.getMessage());
	        	}
	        }
			return result;
		}

	//3.3개설 과정 삭제 액션
	public int deleteOpenCourse(String value) {
			
			int result = 0;
			//개설 과정 코드 입력받아 해당 과정 삭제
	        String sql = "DELETE FROM openSubject_ WHERE openCoCode = ?";
	        
	        Connection conn = null;
	        PreparedStatement pstmt = null;
	        try {
	        	conn = SQLConnection.connect();
	        	pstmt = conn.prepareStatement(sql);
	        	pstmt.setString(1,value);
	        	ResultSet rs = pstmt.executeQuery();
	       	    rs.close();	
	        } catch(SQLException se) {
	        	System.out.println(se.getMessage());
	        } catch(Exception e) {
	        	System.out.println(e.getMessage());
	        } finally {
	        	try {
	        		if(pstmt != null)
	        			pstmt.close();
	        	} catch(SQLException se) {
	        	}
	        	try {
	        		SQLConnection.close();
	        	} catch (SQLException se) {
	        		System.out.println(se.getMessage());
	        	}
	        }
			return result;
		}
		
	// -----------------------------------------------

	// 4. 개설 과목 관리
	// 개설 과목 상세 보기할 때, 제목 보여주기
	//ex. [ COU006 / 빅데이터 엔지니어 양성 과정 / 2018-01-31 - 2018-06-30  / 6강의실 ]				
	public Admin openCourseTitle(String value) {
		/*
		 SELECT openC.openCoCode , cou.courseName , openC.openCoStartDate , openC.openCoCloseDate , cla.className
		 FROM openCourse_ openC, course_ cou, class_ cal
		 WHERE openC.courseCode = cou.courseCode
		 AND openC.classCode = cla.classCode
		 AND openC.openCoCode = '?'
		 */
		Admin result = new Admin();
		String sql = "SELECT openC.openCoCode , cou.courseName , openC.openCoStartDate , openC.openCoCloseDate , cla.className FROM openCourse_ openC, course_ cou, class_ cal WHERE openC.courseCode = cou.courseCode AND openC.classCode = cla.classCode AND openC.openCoCode = '?'";
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = SQLConnection.connect();
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, value);
			
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				String openCoCode = rs.getString("openCoCode");
				String courseName = rs.getString("courseName");
				LocalDate openCoStartDate = rs.getDate("openCoStartDate").toLocalDate();
				LocalDate openCoCloseDate = rs.getDate("openCoCloseDate").toLocalDate();
				String className = rs.getString("className");
				
				result.setOpenCoCode(openCoCode);
				result.setCourseName(courseName);
				result.setOpenCoStartDate(openCoStartDate);
				result.setOpenCoCloseDate(openCoCloseDate);
				result.setClassName(className);
				
			}
			
	} catch (SQLException se) {
		System.out.print(se.getMessage());
		
	} catch (Exception e) {
		System.out.print(e.getMessage());
	} finally {
		try {
			if (pstmt != null)
				pstmt.close();
		} catch (SQLException se) {
		}
		try {
			SQLConnection.close();
		} catch (SQLException se) {
			System.out.print(se.getMessage());
		}
	}
		return result;
	}

	// 4.1 과정 상세보기
	
	public List<Admin> openSubjectDetailList(String value) {
		
		List<Admin>result = new ArrayList<Admin>();
		//과목코드 / 과목명 / 과목시작일 / 과목종료일 / 교재명 / 강사명
		/*
		 SELECT sub.subjectCode , sub.subjectName , openS.openSubStartDate , openS.openSubCloseDate , bok.bookName , mem.name_
		FROM subject_ sub , openSubject_ openS , books_ bok , instructor_ ins , member_ mem
		WHERE mem.mid = ins.mid
		AND ins.mid = openS.mid
		AND bok.bookCode = openS.bookCode
		AND sub.subjectCode = openS.subjectCode
		AND openS.openCoCode = '?'
		 */
		String sql = "SELECT sub.subjectCode , sub.subjectName , openS.openSubStartDate , openS.openSubCloseDate , bok.bookName , mem.name_ FROM subject_ sub , openSubject_ openS , books_ bok , instructor_ ins , member_ mem WHERE mem.mid = ins.mid AND ins.mid = openS.mid AND bok.bookCode = openS.bookCode AND sub.subjectCode = openS.subjectCode AND openS.openCoCode = '?'";
		
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = SQLConnection.connect();
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, value);
			
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				String subjectCode = rs.getString("subjectCode");
				String subjectName = rs.getString("subjectName");
				LocalDate openSubStartDate = rs.getDate("openSubStartDate").toLocalDate();
				LocalDate openSubCloseDate = rs.getDate("openSubCloseDate").toLocalDate();
				String bookName = rs.getString("bookName");
				String name_ = rs.getString("name_");
				
				Admin a = new Admin();
				
				a.setSubjectCode(subjectCode);
				a.setSubjectName(subjectName);
				a.setOpenSubStartDate(openSubStartDate);
				a.setOpenSubCloseDate(openSubCloseDate);
				a.setBookName(bookName);
				a.setName_(name_);
			
				result.add(a);
			}
			
	} catch (SQLException se) {
		System.out.print(se.getMessage());
		
	} catch (Exception e) {
		System.out.print(e.getMessage());
	} finally {
		try {
			if (pstmt != null)
				pstmt.close();
		} catch (SQLException se) {
		}
		try {
			SQLConnection.close();
		} catch (SQLException se) {
			System.out.print(se.getMessage());
		}
	}
		return result;

	}

	// 4.2 과목 등록

	public int openSubjectAdd(String subjectCode, String openCoCode, String openSubStartDate, String openSubCloseDate, String mid, String bookCode) {

		int result1 = 0;
		/*
		 INSERT INTO openSubject_ (openSubCode , mid , openCoCode , bookCode , subjectCode , openSubStartDate , openSubCloseDate)
		 VALUES((SELECT NVL(LPAD(SUBSTR(MAX(openSubCode),4,3)+1,6,'OSU000'),'OSU001') FROM openSubject_),'','','','','','');
		 */
		String sql = " INSERT INTO openSubject_ (openSubCode , mid , openCoCode , bookCode , subjectCode , openSubStartDate , openSubCloseDate) VALUES ((SELECT NVL(LPAD(SUBSTR(MAX(openSubCode),4,3)+1,6,'OSU000'),'OSU001') FROM openSubject_),?,?,?,?,?,?)";
		
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = SQLConnection.connect();
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, mid);
			pstmt.setString(2, openCoCode);
			pstmt.setString(3, bookCode);
			pstmt.setString(4, subjectCode);
			pstmt.setString(4, openSubStartDate);
			pstmt.setString(5, openSubCloseDate);
			
		} catch (SQLException se) {
			System.out.print(se.getMessage());
		} catch (Exception e) {
			System.out.print(e.getMessage());
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
			} catch (SQLException se) {
			}
			try {
				SQLConnection.close();
			} catch (SQLException se) {
				System.out.print(se.getMessage());
			}
		}
		
		return result1;
	}
	
	// 4.3 개설 과목 삭제 전 과목 삭제 가능 리스트 보여주기
	public List<Admin>openSubjectDeleteList(){
		List<Admin> result = new ArrayList<Admin>();
		/*
		 *  CREATE OR REPLACE VIEW openSubDeleteView
            AS
            SELECT openS.subjectCode, sub.subjectName, openS.openSubStartDate, openS.openSubCloseDate, bok.bookName, mem.name_
            FROM instructor_ ins, checkSubject_ che, subject_ sub, openCourse_ openC, openSubject_ openS, member_ mem, books_ bok
            WHERE openS.openSubCode NOT IN ( SELECT openS.openSubCode
            FROM instructor_ ins, subject_ sub, openSubject_ openS, checkSubject_ che
            WHERE ins.mid = che.mid
            AND openS.mid = ins.mid
            AND sub.subjectCode = che.subjectCode
            AND openS.openSubStartDate <= TO_CHAR(SYSDATE, 'YYYY-MM-DD')
            AND openS.openSubCloseDate >= TO_CHAR(SYSDATE, 'YYYY-MM-DD'))
            AND bok.bookCode = openS.bookCode
            AND ins.mid = openS.mid
            AND ins.mid = mem.mid
            AND openC.openCoCode = openS.openCoCode
            AND che.mid = ins.mid
            AND sub.subjectCode = che.subjectCode
            AND openS.subjectCode = sub.subjectCode
            AND openS.openSubStartDate >= TO_CHAR(SYSDATE, 'YYYY-MM-DD')
            ORDER BY openS.subjectCode;
		 */
		//--개설과목이 현재 시작하지 않은 경우만 출력한다.
        //--과목코드/ 과목명 / 과목시작일 / 과목종료일 / 교재명 / 강사명
		String sql = "SELECT subjectCode, subjectName, openSubStartDate, openSubCloseDate, bookName, name_ FROM openSubDeleteView";
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = SQLConnection.connect();
			pstmt = conn.prepareStatement(sql);
			
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				String openSubCode = rs.getString("openSubCode");
				String subjectName = rs.getString("subjectName");
				LocalDate openSubStartDate = rs.getDate("openSubStartDate").toLocalDate();
				LocalDate openSubCloseDate = rs.getDate("openSubCloseDate").toLocalDate();
				String bookName = rs.getString("bookName");
				String name_ = rs.getString("name_");
				
				Admin a = new Admin();
				
				a.setOpenSubCode(openSubCode);
				a.setSubjectName(subjectName);
				a.setOpenSubStartDate(openSubStartDate);
				a.setOpenSubCloseDate(openSubCloseDate);
				a.setBookName(bookName);
				a.setName_(name_);
				
				result.add(a);
			}
			
		} catch (SQLException se) {
			System.out.print(se.getMessage());
			
		} catch (Exception e) {
			System.out.print(e.getMessage());
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
			} catch (SQLException se) {
			}
			try {
				SQLConnection.close();
			} catch (SQLException se) {
				System.out.print(se.getMessage());
			}
		}

		return result;
	}
	
	// 4.3 과목 삭제
	public int openSubjectDelete(String value) {

		int result = 0;
		/*
		 DELETE
		 FROM openSubject_
		 WHERE openSubCode = ?;
		 */
		String sql = "DELETE FFROM openSubject_ WHERE openSubCode = ?";
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = SQLConnection.connect();
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, value);
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException se) {
			System.out.print(se.getMessage());
			
		} catch (Exception e) {
			System.out.print(e.getMessage());
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
			} catch (SQLException se) {
			}
			try {
				SQLConnection.close();
			} catch (SQLException se) {
				System.out.print(se.getMessage());
			}
		}

		return result;

	}

	// -----------------------------------------------

	// 5.수강생 관리

	public String studentList() {

		String result = null;

		return result;

	}

	public String studentSearch() {

		String result = null;

		return result;

	}

	// 개설과정에 등록되지 않은 수강생 목록

	public String studentListWithOutDropOut() {

		String result = null;

		return result;

	}

	// 등록 가능한 개설 과정 리스트

	public String openCourseAddList() {

		String result = null;

		return result;

	}

	// 5.1 수강생 등록

	public String studentAdd() {

		// studentSearch() 호출

		// openCourseList() 호출

		String result = null;

		return result;

	}

	// 5.2 수강생 출력(중도탈락 관리)

	public String studentSearch1() {

		// dropOut() 메소드 호출

		String result = null;

		return result;

	}

	public String dropOut() {

		String result = null;

		return result;

	}

	// 5.3 수강생 과정 등록

	public String studentCourseAdd() {

		// studentListWithOutDropOut() 호출

		// openCourseAddList() 호출

		String result = null;

		return result;

	}

	// 5.4 수강생 삭제

	public String studentDelete1() {

		String result = null;

		return result;

	}

	// 5.4.1 수강생 과정 삭제

	public String studentCourseDelete() {

		// studentSearch() 호출

		String result = null;

		return result;

	}

	// 5.4.2 수강생 삭제

	public String studentDelete2() {

		// studentSearch() 호출

		String result = null;

		return result;

	}

	// -----------------------------------------------

	// 6. 성적 조회

	public String scoreSearch() {

		String result = null;

		return result;

	}

	// 6.1 개설 과정 보기

	public String scoreOpenCourseList() {

		// searchScoreOpenCourseDetailList() 호출

		String result = null;

		return result;

	}

	// 6.1.1 상세보기

	public String scoreOpenCourseDetailList() {

		String result = null;

		return result;

	}

	// 6.1.1.1 과목별 성적 보기

	public String scoreOpenSubjectList() {

		String result = null;

		return result;

	}

	// 6.2 수강생 검색

	public String searchStudent() {

		String result = null;

		return result;

	}

	// 6.2.1 상세보기 (과정)

	public String studentOpenCourseDetail() {

		String result = null;

		return result;

	}

	// 6.2.1.1 상세보기 (과목)

	public String studentOpenSubjectDetail() {

		String result = null;

		return result;

	}

	// 6.3 성적 정보

	public String scoreInfo() {

		String result = null;

		return result;

	}

	// 6.3.1 성적 전체 출력

	public String viewAllScoreList() {

		String result = null;

		return result;

	}

	// 6.3.2 이름 검색

	public String searchNameScoreList() {

		String result = null;

		return result;

	}

	// 6.3.3 시험 코드 검색

	public String searchTestCodeScoreList() {

		String result = null;

		return result;

	}
}
