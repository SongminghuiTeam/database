package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.junit.Test;

import domain.StoreCollect;

public class StoreCollectDAO extends DaoBase{
	
	@Test
	public void insert(){
		Connection connection=null;
		PreparedStatement pStatement=null;
		try {
			connection=getConnection();
			String sql="insert into storecollect(userID) values(?)";
			pStatement=connection.prepareStatement(sql);
			pStatement.setString(1, "lxk");
			int rows = pStatement.executeUpdate();
			if(rows>0) {
				System.out.println("insert successfully!");
			}
			else {
				System.out.println("insert defeat!");
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			release(connection, pStatement, null);
		}
	}

	
	@Test
	public void queryByUserID() {
		Connection connection=null;
		PreparedStatement pStatement=null;
		ResultSet resultset=null;		
		
		StoreCollect sCollect=new StoreCollect();
		try {
			connection=getConnection();
			String sql="select * from storecollect where userID=?";
			pStatement=connection.prepareStatement(sql);
			pStatement.setString(1, "cwj");
			resultset=pStatement.executeQuery();
			while(resultset.next()) {
				sCollect.setScollectID(resultset.getLong(1));
				sCollect.setUserID(resultset.getString(2));
				System.out.println(sCollect.getScollectID()+"----"+sCollect.getUserID());
			}				
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			release(connection, pStatement, resultset);
		}
	}
}
