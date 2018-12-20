package dao;

import java.sql.Connection;
import java.util.ArrayList;
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
	
	public ArrayList<User> search(String userID) {
		Connection conn = null;
		PreparedStatement pStatement = null;
		ResultSet rs = null;
		
		ArrayList<User> users = new ArrayList<User>();
		
		try {
			conn = getConnection();
			
			String sql = "select * from user where userID=?";
			pStatement = conn.prepareStatement(sql);
			pStatement.setString(1, userID);
			rs = pStatement.executeQuery();
			
			if(rs.next()) {
				User user = new User();
				user.setUserID(rs.getString("userID"));
				user.setPassword(rs.getString("password"));
				user.setMail(rs.getString("mail"));
				user.setPhone(rs.getString("phone"));
				
				users.add(user);
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
		
		return users;
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
		ArrayList<User> users1 = search("heyulin");
		for(int i = 0;i < users1.size();i++) {
			System.out.println("userID:" + users1.get(i).getUserID() + "   password:" + users1.get(i).getPassword()
					 + "   mail:" + users1.get(i).getMail() + "   phone:" + users1.get(i).getPhone());
		}
		ArrayList<User> users2 = search("cwj");
		for(int i = 0;i < users2.size();i++) {
			System.out.println("userID:" + users2.get(i).getUserID() + "   password:" + users2.get(i).getPassword()
					 + "   mail:" + users2.get(i).getMail() + "   phone:" + users2.get(i).getPhone());
		}
		ArrayList<User> users3 = search("katherine");
		for(int i = 0;i < users3.size();i++) {
			System.out.println("userID:" + users3.get(i).getUserID() + "   password:" + users3.get(i).getPassword()
					 + "   mail:" + users3.get(i).getMail() + "   phone:" + users3.get(i).getPhone());
		}
		
		User newUser = new User("heyulin", "15113158871", "294813616@qq.com", "123456");
		// update("heyulin", newUser);
		ArrayList<User> users4 = search("heyulin");
		for(int i = 0;i < users4.size();i++) {
			System.out.println("userID:" + users4.get(i).getUserID() + "   password:" + users4.get(i).getPassword()
					 + "   mail:" + users4.get(i).getMail() + "   phone:" + users4.get(i).getPhone());
		}
		ArrayList<User> users5 = search("cwj");
		for(int i = 0;i < users5.size();i++) {
			System.out.println("userID:" + users5.get(i).getUserID() + "   password:" + users5.get(i).getPassword()
					 + "   mail:" + users5.get(i).getMail() + "   phone:" + users5.get(i).getPhone());
		}
		ArrayList<User> users6 = search("katherine");
		for(int i = 0;i < users6.size();i++) {
			System.out.println("userID:" + users6.get(i).getUserID() + "   password:" + users6.get(i).getPassword()
					 + "   mail:" + users6.get(i).getMail() + "   phone:" + users6.get(i).getPhone());
		}
		
		//delete("heyulin");
	}
}
