package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.junit.Test;

import domain.Detailed;
import domain.User;

public class DetailedDAO extends DaoBase{
	@Test
	public void insert() {
		Connection conn = null;
		PreparedStatement pStatement = null;
		ResultSet rs = null;
		
		Detailed detailed = new Detailed("chenwanjing", "daurum", "1998-7-20" , "陈婉菁", "350702199807201826",(long)20);

		try {
			conn = getConnection();
			String sql = null;
			if(!detailed.getGender().equals("empty")) {
				sql = "insert into detailed values(?,?,?,?,?,?,?)";
				pStatement = conn.prepareStatement(sql);
				pStatement.setString(1, detailed.getUserID());
				pStatement.setString(2, detailed.getNickName());
				pStatement.setString(3, detailed.getGender());
				pStatement.setString(4, detailed.getBirthday());
				pStatement.setString(5, detailed.getTrueName());
				pStatement.setString(6, detailed.getIdNumber());
				pStatement.setLong(7, detailed.getJdBean());
			}
			else {
				sql = "insert into detailed(userID,nickName,birthday,trueName,idNumber,jdBean) values(?,?,?,?,?,?)";
				pStatement = conn.prepareStatement(sql);
				pStatement.setString(1, detailed.getUserID());
				pStatement.setString(2, detailed.getNickName());
				pStatement.setString(3, detailed.getBirthday());
				pStatement.setString(4, detailed.getTrueName());
				pStatement.setString(5, detailed.getIdNumber());
				pStatement.setLong(6, detailed.getJdBean());
			}
			
			int row =  pStatement.executeUpdate();
			
			if(row > 0) {
				System.out.println("insert successfully");
			}
			else {
				System.out.println("insert failed");
			}
			
		}catch(Exception sqlException) {
			sqlException.printStackTrace();
		}finally {
			try {
				release(conn, pStatement, rs);
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	@Test
	public void search() {
		Connection conn = null;
		PreparedStatement pStatement = null;
		ResultSet rs = null;
		
		String username = "chenwanjing";
		String password = "222222";
		
		try {
			conn = getConnection();
			String sql = "select * from user where userID=?";
			pStatement = conn.prepareStatement(sql);
			pStatement.setString(1, username);
			rs = pStatement.executeQuery();
			
			if(rs.next()) {
				if(rs.getString("password").equals(password)) {
					sql = "select * from detailed where userID=?";
					pStatement = conn.prepareStatement(sql);
					pStatement.setString(1, username);
					
					rs = pStatement.executeQuery();
					if(rs.next()) {
						System.out.println("search successfully");
						System.out.println("detailed info");
						System.out.println("username:" + rs.getString("userID") + " nickname:" + rs.getString("nickName") + " gender:" + rs.getString("gender") + 
								" birthday:" + rs.getString("birthday") + " trueName:" + rs.getString("trueName") + " ID:" + rs.getString("idNumber") + " jdBeans:" + rs.getLong("jdBean"));
					}
					else {
						System.out.println("search failed");
					}
				}
				else {
					System.out.println("password Error!!!");
				}
			}
			else {
				System.out.println("no user named " + username);
			}
		}catch(Exception sqlException) {
			sqlException.printStackTrace();
		}finally {
			try {
				release(conn, pStatement, rs);
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	@Test
	public void updateNickName() {
		Connection conn = null;
		PreparedStatement pStatement = null;
		ResultSet rs = null;
		
		String username = "heyulin";
		String password = "yolane980401";
		String nickName = "hegiu";
		
		try {
			conn = getConnection();
			String sql = "select * from user where userID=?";
			pStatement = conn.prepareStatement(sql);
			pStatement.setString(1, username);
			rs = pStatement.executeQuery();
			
			if(rs.next()) {
				if(rs.getString("password").equals(password)) {
					sql = "update detailed set nickName=? where userID=?";
					pStatement = conn.prepareStatement(sql);
					pStatement.setString(1, nickName);
					pStatement.setString(2, username);
					
					int row = pStatement.executeUpdate();
					if(row > 0) {
						System.out.println("update nickName successfully");
					}
					else {
						System.out.println("update nickName failed");
					}
				}
				else {
					System.out.println("password Error!!!");
				}
			}
			else {
				System.out.println("no user named " + username);
			}
		}catch(Exception sqlException) {
			sqlException.printStackTrace();
		}finally {
			try {
				release(conn, pStatement, rs);
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	@Test
	public void updateJdBean() {
		Connection conn = null;
		PreparedStatement pStatement = null;
		ResultSet rs = null;
		
		String username = "heyulin";
		String password = "yolane980401";
		Long jdBean = (long)30;
		
		try {
			conn = getConnection();
			String sql = "select * from user where userID=?";
			pStatement = conn.prepareStatement(sql);
			pStatement.setString(1, username);
			rs = pStatement.executeQuery();
			
			if(rs.next()) {
				if(rs.getString("password").equals(password)) {
					sql = "update detailed set jdBean=? where userID=?";
					pStatement = conn.prepareStatement(sql);
					pStatement.setLong(1, jdBean);
					pStatement.setString(2, username);
					
					int row = pStatement.executeUpdate();
					if(row > 0) {
						System.out.println("update jdBean successfully");
					}
					else {
						System.out.println("update jdBean failed");
					}
				}
				else {
					System.out.println("password Error!!!");
				}
			}
			else {
				System.out.println("no user named " + username);
			}
		}catch(Exception sqlException) {
			sqlException.printStackTrace();
		}finally {
			try {
				release(conn, pStatement, rs);
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
}
