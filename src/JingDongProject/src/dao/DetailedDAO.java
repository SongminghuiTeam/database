package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;

import org.junit.Test;

import domain.Detailed;
import domain.User;

public class DetailedDAO extends DaoBase{
	public void insert(Detailed detailed) {
		Connection conn = null;
		PreparedStatement pStatement = null;
		ResultSet rs = null;

		try {
			conn = getConnection();
			String sql = "select * from user where userID=?";
			pStatement = conn.prepareStatement(sql);
			pStatement.setString(1, detailed.getUserID());
			rs = pStatement.executeQuery();
			
			if(rs.next()) {
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
			}
			else {
				System.out.println("no user named " + detailed.getUserID());
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
	
	public void search(String username, String password) {
		Connection conn = null;
		PreparedStatement pStatement = null;
		ResultSet rs = null;
		
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
						System.out.println("username:" + rs.getString("userID") + "   nickname:" + rs.getString("nickName") +  
								"   jdBeans:" + rs.getLong("jdBean"));
						System.out.println("gender:" + rs.getString("gender") + "   birthday:" + rs.getString("birthday"));
						System.out.println("trueName:" + rs.getString("trueName") + "   ID:" + rs.getString("idNumber"));
						System.out.print("\n");
					}
					else {
						System.out.println("search failed");
						System.out.print("\n");
					}
				}
				else {
					System.out.println("password Error!!!");
					System.out.print("\n");
				}
			}
			else {
				System.out.println("no such user");
				System.out.print("\n");
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
	
	public void Update(String userID,Detailed detailed){
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
				sql = "update detailed set nickName=?, gender=?, birthday=?, trueName=?, idNumber=?, jdBean=? where userID=?";
				pStatement = conn.prepareStatement(sql);
				
				pStatement.setString(1, detailed.getNickName());
				pStatement.setString(2, detailed.getGender());
				pStatement.setString(3, detailed.getBirthday());
				pStatement.setString(4, detailed.getTrueName());
				pStatement.setString(5, detailed.getIdNumber());
				pStatement.setLong(6, detailed.getJdBean());
				pStatement.setString(7, userID);
				
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
	
	@Test
	public void Test() {
		Detailed detailed = new Detailed("heyulin", "yolanehe", "1998-04-01", "", "");
		// insert(detailed);
		
		search("heyulin", "111111");
		search("heyulin", "123456");
		
		Detailed newDetailed = new Detailed("heyulin", "yolane", "1998-04-01", "何钰霖", "440402199804019047");
		Update("heyulin", newDetailed);
		
		search("heyulin", "111111");
		search("heyulin", "123456");
	}
}
