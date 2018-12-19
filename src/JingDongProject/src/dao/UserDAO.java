package dao;

import java.sql.Connection;
import java.util.LinkedList;
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
		
		User user = new User("chenwanjing","13128662811","chenwanjing@163.com","222222");
		
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
	
	// public void 
	// 登录 用户名查询
	/*public void searchByUsername(String username,String password) {
		Connection conn = null;
		Statement statement = null;
		ResultSet rs = null;
		
		try {
			conn = getConnection();
			statement = conn.createStatement();
			rs = statement.executeQuery("select * from Register where username=" + "'" + username + "'" + " and password=" + "'" + password + "'");
			
			if(rs.next()) {
				System.out.println("login Success");
				System.out.println("login Info");
				System.out.println("username:" + rs.getString("username") + " phone:" + rs.getString("phone") + " mail:" + rs.getString("") + " password:" + rs.getString("password"));
			}
			else {
				System.out.println("login Error");
			}
		}catch(Exception sqlException) {
			sqlException.printStackTrace();
		}finally {
			try {
				release(conn, statement, rs);
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
	}*/
}
