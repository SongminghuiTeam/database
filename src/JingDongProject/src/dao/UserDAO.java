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
	public void insert(User user) {
		Connection conn = null;
		PreparedStatement pStatement = null;
		ResultSet rSet = null;
		
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
	
	public void search(String userID) {
		Connection conn = null;
		PreparedStatement pStatement = null;
		ResultSet rs = null;
		
		try {
			conn = getConnection();
			
			String sql = "select * from user where userID=?";
			pStatement = conn.prepareStatement(sql);
			pStatement.setString(1, userID);
			rs = pStatement.executeQuery();
			
			if(rs.next()) {
				System.out.println("userID:" + rs.getString("userID") + "   password:" + rs.getString("password"));
				System.out.println("mail:" + rs.getString("mail") + "   phone:" + rs.getString("phone"));
				System.out.print("\n");
			}
			else {
				System.out.println("no such user");
				System.out.print("\n");
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
	
	public void update(String userID,User user) {
		Connection conn = null;
		PreparedStatement pStatement = null;
		ResultSet rs = null;
		
		try {
			conn = getConnection();
			String sql = "select * from user where userID=?";
			pStatement = conn.prepareStatement(sql);
			pStatement.setString(1, userID);
			
			rs = pStatement.executeQuery();
			if(rs.next()) {
				sql = "update user set phone=?, mail=?, password=? where userID=?";
				pStatement = conn.prepareStatement(sql);
				pStatement.setString(1, user.getPhone());
				pStatement.setString(2, user.getMail());
				pStatement.setString(3, user.getPassword());
				pStatement.setString(4, userID);
				
				int row = pStatement.executeUpdate();
				
				if(row > 0) {
					System.out.println("update successfully!");
				}
				else {
					System.out.println("update failed!");
				}
			}
			else {
				System.out.println("no such user");
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
	
	// delete column in detailed with foreign-key at the same time
	public void delete(String userID) {
		Connection conn = null;
		PreparedStatement pStatement = null;
		ResultSet rs = null;
		
		try {
			conn = getConnection();
			String sql = "delete from user where userID=?";
			pStatement = conn.prepareStatement(sql);
			pStatement.setString(1, userID);
			
			int row = pStatement.executeUpdate();
			if(row > 0) {
				System.out.println("delete successfully");
			}
			else {
				System.out.println("delete failed");
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
	public void Test() {
		User user = new User("heyulin", "13602339963", "yolanehe@163.com", "yolane0401");
		//insert(user);
		search("heyulin");
		search("cwj");
		search("katherine");
		
		User newUser = new User("heyulin", "15113158871", "294813616@qq.com", "123456");
		// update("heyulin", newUser);
		search("heyulin");
		search("cwj");
		search("katherine");
		
		delete("heyulin");
	}
}
