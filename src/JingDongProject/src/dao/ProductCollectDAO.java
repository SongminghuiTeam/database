package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.junit.Test;

import domain.ProductCollect;

public class ProductCollectDAO extends DaoBase{
	
	@Test
	public void insert(){
		Connection connection=null;
		PreparedStatement pStatement=null;
			
		ProductCollect productCollect=new ProductCollect();
		productCollect.setUserID("lxk");
		try {
			connection=getConnection();
			String sql="insert into productcollect(userID) values(?)";
			pStatement=connection.prepareStatement(sql);
			pStatement.setString(1, productCollect.getUserID());
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
	public void delete() {
		Connection connection=null;
		PreparedStatement pStatement=null;
		try {
			connection=getConnection();
			String sql="delete from productcollect where pcollectID=?";
			pStatement=connection.prepareStatement(sql);
			pStatement.setLong(1, 7);
			int rows=pStatement.executeUpdate();
			if(rows>0) {
				System.out.println("delete successfully!");
			}
			else {
				System.out.println("delete defeat!");
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
		
		ProductCollect pCollect=new ProductCollect();
		try {
			connection=getConnection();
			String sql="select * from productcollect where userID=?";
			pStatement=connection.prepareStatement(sql);
			pStatement.setString(1, "smh");
			resultset=pStatement.executeQuery();
			while(resultset.next()) {
				pCollect.setPcollectID(resultset.getLong(1));
				pCollect.setUserID(resultset.getString(2));
				System.out.println(pCollect.getPcollectID()+"----"+pCollect.getUserID());
			}				
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			release(connection, pStatement, resultset);
		}
	}
}
