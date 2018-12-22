package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import org.junit.Test;

import domain.Detailed;
import domain.User;

public class DetailedDAO extends DaoBase{
	public int insert(Detailed detailed) {
		Connection conn = null;
		PreparedStatement pStatement = null;
		ResultSet rs = null;

		int row = 0;
		
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
				
				row =  pStatement.executeUpdate();
				
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
		
		return row;
	}
	
	public ArrayList<Detailed> search(String username, String password) {
		Connection conn = null;
		PreparedStatement pStatement = null;
		ResultSet rs = null;
		
		ArrayList<Detailed> detaileds = new ArrayList<Detailed>();
		
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
						Detailed detailed = new Detailed();
						detailed.setUserID(rs.getString("userID"));
						detailed.setNickName(rs.getString("nickName"));
						detailed.setJdBean(rs.getLong("jdBean"));
						detailed.setGender(rs.getString("gender"));
						detailed.setBirthday(rs.getString("birthday"));
						detailed.setTrueName(rs.getString("trueName"));
						detailed.setIdNumber(rs.getString("idNumber"));

						detaileds.add(detailed);
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
		
		return detaileds;
	}
	
	public int update(String userID,Detailed detailed){
		Connection conn = null;
		PreparedStatement pStatement = null;
		ResultSet rs = null;
		
		int row = 0;
		
		try {
			conn = getConnection();
			String sql = "select * from user where userID=?";
			pStatement = conn.prepareStatement(sql);
			pStatement.setString(1, userID);
			
			rs = pStatement.executeQuery();
			if(rs.next()) {
				if(!detailed.getGender().equals("empty")) {
					sql = "update detailed set nickName=?, gender=?, birthday=?, trueName=?, idNumber=?, jdBean=? where userID=?";
					pStatement = conn.prepareStatement(sql);
					
					pStatement.setString(1, detailed.getNickName());
					pStatement.setString(2, detailed.getGender());
					pStatement.setString(3, detailed.getBirthday());
					pStatement.setString(4, detailed.getTrueName());
					pStatement.setString(5, detailed.getIdNumber());
					pStatement.setLong(6, detailed.getJdBean());
					pStatement.setString(7, userID);	
				}
				else {
					sql = "update detailed set nickName=?, gender=?, birthday=?, trueName=?, idNumber=?, jdBean=? where userID=?";
					pStatement = conn.prepareStatement(sql);
					
					pStatement.setString(1, detailed.getNickName());
					pStatement.setString(2, "保密");
					pStatement.setString(3, detailed.getBirthday());
					pStatement.setString(4, detailed.getTrueName());
					pStatement.setString(5, detailed.getIdNumber());
					pStatement.setLong(6, detailed.getJdBean());
					pStatement.setString(7, userID);
				}
				
				row = pStatement.executeUpdate();
				
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
		
		return row;
	}
	
	@Test
	public void Test() {
		//Detailed detailed = new Detailed("chenwanjing", "kezi", "女", "1998-05-10", "梁晓珂", "350702199807200219");
		//insert(detailed);
		
		/*ArrayList<Detailed> detaileds1 = search("cwj", "222222");
		for(int i = 0;i < detaileds1.size();i++) {
			System.out.println("userID:" + detaileds1.get(i).getUserID() + "   gender:" + detaileds1.get(i).getGender() + 
					"    jdBean:" + detaileds1.get(i).getJdBean());
			System.out.println("nickName:" + detaileds1.get(i).getGender() + "   birthday:" + detaileds1.get(i).getBirthday());
			System.out.println("trueName:" + detaileds1.get(i).getTrueName() + "   idNumber:" + detaileds1.get(i).getIdNumber());
			System.out.print("\n");
		}*/
		
		Detailed newDetailed = new Detailed("smh", "yolanehe", "1998-05-01", null, null);
		update("smh", newDetailed);
	
	}
}
