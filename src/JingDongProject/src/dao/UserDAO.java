package dao;

import java.sql.Connection;
import java.util.LinkedList;

import org.junit.Test;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import domain.*;

public class UserDAO extends DaoBase{
	@org.junit.Test
	public void insert() {
		Connection conn = null;
		PreparedStatement pStatement = null;
		ResultSet rSet = null;
		
		User user = new User("songminghui","13269016600","","6543321");
		
		String userID = user.getUserID();
		String mail = user.getMail();
		String password = user.getPassword();
		String phone = user.getPhone();
		
		try {
			conn = getConnection();
			String sql = "insert into user values(?,?,?,?)";
			pStatement = conn.prepareStatement(sql);
			pStatement.setString(1, userID);
			pStatement.setString(2, password);
			pStatement.setString(3, phone);
			pStatement.setString(4, mail);
			
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
				release(conn, pStatement, null);
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	// login by userID
	@org.junit.Test
	public void searchByUserID() {
		Connection conn = null;
		PreparedStatement pStatement = null;
		ResultSet rs = null;
		
		String username = "chenwanjing";
		String truePassword = "222222";
		String falsePassword = "111111";
		
		try {
			conn = getConnection();
			String sql = "select * from user where userID=" + "'" + username + "'" + " and password=" + "'" + truePassword + "'";
			pStatement = conn.prepareStatement(sql);
			rs = pStatement.executeQuery();
			
			if(rs.next()) {
				System.out.println("login Success");
				System.out.println("login Info");
				System.out.println("username:" + rs.getString("userID") + " phone:" + rs.getString("phone") + " mail:" + rs.getString("mail") + " password:" + rs.getString("password"));
			}
			else {
				System.out.println("login Error");
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
	
	// login by mail
	@org.junit.Test
	public void searchByMail() {
		Connection conn = null;
		PreparedStatement pStatement = null;
		ResultSet rs = null;
		
		String mail = "";
		String truePassword = "654321";
		String falsePassword = "111111";
		
		try {
			if(mail.equals("")) {
				System.out.println("current user's mail information is empty,login error");
			}
			else {
				conn = getConnection();
				String sql = "select * from user where mail=" + "'" + mail + "'" + " and password=" + "'" + truePassword + "'";
				pStatement = conn.prepareStatement(sql);
				rs = pStatement.executeQuery();
				
				if(rs.next()) {
					System.out.println("login Success");
					System.out.println("login Info");
					System.out.println("username:" + rs.getString("userID") + " phone:" + rs.getString("phone") + " mail:" + rs.getString("mail") + " password:" + rs.getString("password"));
				}
				else {
					System.out.println("login Error");
				}
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
	
	// login by phone
	@Test
	public void searchByPhone() {
		Connection conn = null;
		PreparedStatement pStatement = null;
		ResultSet rs = null;
		
		String phone = "13269016600";
		String truePassword = "654321";
		String falsePassword = "111111";
		
		try {
			conn = getConnection();
			String sql = "select * from user where phone=" + "'" + phone + "'" + " and password=" + "'" + truePassword + "'";
			pStatement = conn.prepareStatement(sql);
			rs = pStatement.executeQuery();
			
			if(rs.next()) {
				System.out.println("login Success");
				System.out.println("login Info");
				System.out.println("username:" + rs.getString("userID") + " phone:" + rs.getString("phone") + " mail:" + rs.getString("mail") + " password:" + rs.getString("password"));
			}
			else {
				System.out.println("login Error");
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
	public void updateMail() {
		Connection conn = null;
		PreparedStatement pStatement = null;
		ResultSet rs = null;
		
		String username = "heyulin";
		String mail = "yolanehe@163.com";
		
		try {
			conn = getConnection();
			String sql = "select * from user where userID=?";
			pStatement = conn.prepareStatement(sql);
			pStatement.setString(1, username);
			rs = pStatement.executeQuery();
			
			if(rs.next()) {
				sql = "update user set mail=? where userID=?";
				pStatement = conn.prepareStatement(sql);
				pStatement.setString(1, mail);
				pStatement.setString(2, username);
				
				int row = pStatement.executeUpdate();
				if(row > 0) {
					System.out.println("update mail successfully");
				}
				else {
					System.out.println("update mail failed");
				}
			}
			else {
				System.out.println("no users named " + username);
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
	public void updatePhone() {
		Connection conn = null;
		PreparedStatement pStatement = null;
		ResultSet rs = null;
		
		String username = "heyulin";
		String phone = "15113158871";
		
		try {
			conn = getConnection();
			String sql = "select * from user where userID=?";
			pStatement = conn.prepareStatement(sql);
			pStatement.setString(1, username);
			rs = pStatement.executeQuery();
			
			if(rs.next()) {
				sql = "update user set phone=? where userID=?";
				pStatement = conn.prepareStatement(sql);
				pStatement.setString(1, phone);
				pStatement.setString(2, username);
				
				int row = pStatement.executeUpdate();
				if(row > 0) {
					System.out.println("update phone successfully");
				}
				else {
					System.out.println("update phone failed");
				}
			}
			else {
				System.out.println("no users named " + username);
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
