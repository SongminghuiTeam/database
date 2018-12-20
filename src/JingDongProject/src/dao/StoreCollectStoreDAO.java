package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.junit.Test;

public class StoreCollectStoreDAO extends DaoBase{
	/**
	 * 根据scollectID查询指定收藏夹中的所有店铺ID
	 */
	@Test
	public void queryByScollectID() {
		Connection connection=null;
		PreparedStatement pStatement=null;
		ResultSet resultset=null;
	
		Long scollectID=(long)4;
		try {
			connection=getConnection();
			String sql="select * from storecollectstore where scollectID=?";
			pStatement=connection.prepareStatement(sql);
			pStatement.setLong(1, scollectID);
			resultset=pStatement.executeQuery();
			while(resultset.next()) {
				System.out.println(scollectID+"---"+resultset.getLong("storeID"));
			}	
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			release(connection, pStatement, resultset);
		}	
	}
	
	/**
	 * 根据scollectID向某个收藏夹中添加店铺ID
	 */
	@Test
	public void insert() {
		Connection connection=null;
		PreparedStatement pStatement=null;
		Long scollectID=(long)5;
		Long storeID=(long)2;
		
		try {
			connection=getConnection();
			String sql="insert into storecollectstore values(?,?)";
			pStatement=connection.prepareStatement(sql);
			pStatement.setLong(1, scollectID);
			pStatement.setLong(2, storeID);
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
	 * 根据scollectID删除某个收藏夹中的店铺ID
	 */
	@Test
	public void deleteByScollectID() {
		Connection connection=null;
		PreparedStatement pStatement=null;
		Long scollectID=(long)5;
		Long storeID=(long)2;
		
		try {
			connection=getConnection();
			String sql="delete from storecollectstore where scollectID=? and storeID=?";
			pStatement=connection.prepareStatement(sql);
			pStatement.setLong(1, scollectID);
			pStatement.setLong(2, storeID);
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
