package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.junit.Test;

public class ProductCollectProductDAO extends DaoBase{
	
	/**
	 * 根据pcollectID查询指定收藏夹中的所有产品ID
	 */
	@Test
	public void queryByPcollectID() {
		Connection connection=null;
		PreparedStatement pStatement=null;
		ResultSet resultset=null;
	
		Long pcollectID=(long)5;
		try {
			connection=getConnection();
			String sql="select * from productcollectproduct where pcollectID=?";
			pStatement=connection.prepareStatement(sql);
			pStatement.setLong(1, pcollectID);
			resultset=pStatement.executeQuery();
			while(resultset.next()) {
				System.out.println(pcollectID+"---"+resultset.getLong("productID"));
			}	
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			release(connection, pStatement, resultset);
		}	
	}
	
	/**
	 * 根据pcollectID向某个收藏夹中添加产品ID
	 */
	@Test
	public void insert() {
		Connection connection=null;
		PreparedStatement pStatement=null;
		Long pcollectID=(long)6;
		Long productID=(long)1;
		
		try {
			connection=getConnection();
			String sql="insert into productcollectproduct values(?,?)";
			pStatement=connection.prepareStatement(sql);
			pStatement.setLong(1, pcollectID);
			pStatement.setLong(2, productID);
			int rows=pStatement.executeUpdate();
			if(rows>0) {
				System.out.println("insert successfully!");
			}
			else {
				System.out.println("insert defeat!");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			release(connection, pStatement, null);
		}
	}
	
	/**
	 * 根据PcollectID删除某个收藏夹中的产品ID
	 */
	@Test
	public void deleteByPcollectID() {
		Connection connection=null;
		PreparedStatement pStatement=null;
		Long pcollectID=(long)6;
		Long productID=(long)1;
		
		try {
			connection=getConnection();
			String sql="delete from productcollectproduct where pcollectID=? and productID=?";
			pStatement=connection.prepareStatement(sql);
			pStatement.setLong(1, pcollectID);
			pStatement.setLong(2, productID);
			int rows=pStatement.executeUpdate();
			if(rows>0) {
				System.out.println("delete successfully!");
			}
			else {
				System.out.println("delete defeat!");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			release(connection, pStatement, null);
		}
	}
	
}
