package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
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
			pStatement.setLong(1, 3);
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
}
