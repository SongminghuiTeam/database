package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;

import org.junit.Test;

import domain.ProductCollect;

public class ProductCollectDAO extends DaoBase{
	
	@Test
	public void insert(){
		Connection connection=getConnection();
		PreparedStatement pStatement=null;
			
		ProductCollect productCollect=new ProductCollect();
		productCollect.setUserID("lxk");
		try {
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
}
