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
					sql = "update detailed set nickName=?, birthday=?, trueName=?, idNumber=?, jdBean=? where userID=?";
					pStatement = conn.prepareStatement(sql);
					
					pStatement.setString(1, detailed.getNickName());
					pStatement.setString(2, detailed.getBirthday());
					pStatement.setString(3, detailed.getTrueName());
					pStatement.setString(4, detailed.getIdNumber());
					pStatement.setLong(5, detailed.getJdBean());
					pStatement.setString(6, userID);
				}
				
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
		insert(detailed);
		
		ArrayList<Detailed> detaileds1 = search("heyulin", "111111");
		for(int i = 0;i < detaileds1.size();i++) {
			System.out.println("userID:" + detaileds1.get(i).getUserID() + "   gender:" + detaileds1.get(i).getGender() + "    jdBean:" + detaileds1.get(i).getJdBean());
			System.out.println("nickName:" + detaileds1.get(i).getGender() + "   birthday:" + detaileds1.get(i).getBirthday());
			System.out.println("trueName:" + detaileds1.get(i).getTrueName() + "   idNumber:" + detaileds1.get(i).getIdNumber());
			System.out.print("\n");
		}
		ArrayList<Detailed> detaileds2 = search("heyulin", "123456");
		for(int i = 0;i < detaileds2.size();i++) {
			System.out.println("userID:" + detaileds2.get(i).getUserID() + "   gender:" + detaileds2.get(i).getGender() + "    jdBean:" + detaileds2.get(i).getJdBean());
			System.out.println("nickName:" + detaileds2.get(i).getGender() + "   birthday:" + detaileds2.get(i).getBirthday());
			System.out.println("trueName:" + detaileds2.get(i).getTrueName() + "   idNumber:" + detaileds2.get(i).getIdNumber());
			System.out.print("\n");
		}
		
		Detailed newDetailed = new Detailed("heyulin", "yolane", "1998-04-01", "何钰霖", "440402199804019047");
		Update("heyulin", newDetailed);
		
		ArrayList<Detailed> detaileds3 = search("heyulin", "111111");
		for(int i = 0;i < detaileds3.size();i++) {
			System.out.println("userID:" + detaileds3.get(i).getUserID() + "   gender:" + detaileds3.get(i).getGender() + "    jdBean:" + detaileds3.get(i).getJdBean());
			System.out.println("nickName:" + detaileds3.get(i).getGender() + "   birthday:" + detaileds3.get(i).getBirthday());
			System.out.println("trueName:" + detaileds3.get(i).getTrueName() + "   idNumber:" + detaileds3.get(i).getIdNumber());
			System.out.print("\n");
		}
		ArrayList<Detailed> detaileds4 = search("heyulin", "123456");
		for(int i = 0;i < detaileds4.size();i++) {
			System.out.println("userID:" + detaileds4.get(i).getUserID() + "   gender:" + detaileds4.get(i).getGender() + "    jdBean:" + detaileds4.get(i).getJdBean());
			System.out.println("nickName:" + detaileds4.get(i).getGender() + "   birthday:" + detaileds4.get(i).getBirthday());
			System.out.println("trueName:" + detaileds4.get(i).getTrueName() + "   idNumber:" + detaileds4.get(i).getIdNumber());
			System.out.print("\n");
		}
	}
}
