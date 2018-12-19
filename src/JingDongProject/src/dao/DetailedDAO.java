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
}
