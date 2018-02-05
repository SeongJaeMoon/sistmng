package com.sistmng;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginDAO {

	public Member login_menu(Member m) {
		Member member = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "SELECT mid, name_, ssn, memberStatus FROM member_ WHERE name_ = ? AND ssn = ?";
		try {
			conn = SQLConnection.connect();
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, m.getName());
			pstmt.setString(2, m.getSsn());
			
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				String mid = rs.getString("mid");
				String name_ = rs.getString("name_");
				String ssn = rs.getString("ssn");
				String memberStatus = rs.getString("memberStatus");
				member = new Member();
				member.setMid(mid);
				member.setName(name_);
				member.setSsn(ssn);
				String status = null;
				switch(memberStatus) {
				case "A" : status = "관리자";break;
				case "I" : status = "강사"; break;
				case "S" : status = "수강생"; break;
				}
				member.setMemberStatus(status);
			}
			
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
		return member;
	}
	
}
