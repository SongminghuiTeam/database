package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import domain.StoreCollectStore;

public class StoreCollectStoreDAO extends DaoBase{
	
	/**
	 * 根据scollectID向某个收藏夹中添加店铺ID
	 */
	public void insert(Long scollectID,Long storeID) {
		Connection connection=null;
		PreparedStatement pStatement=null;	
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
	 * 根据scollectID查询指定收藏夹中的所有店铺ID
	 */
	public List<Long> searchByScollectID(Long scollectID) {
		Connection connection=null;
		PreparedStatement pStatement=null;
		ResultSet resultset=null;
		List<Long> storeIDs=new ArrayList<Long>();
		try {
			connection=getConnection();
			String sql="select * from storecollectstore where scollectID=?";
			pStatement=connection.prepareStatement(sql);
			pStatement.setLong(1, scollectID);
			resultset=pStatement.executeQuery();
			System.out.println("收藏夹"+scollectID+"下的所有店铺ID：");
			while(resultset.next()) {
				storeIDs.add(resultset.getLong("storeID"));
				System.out.println(resultset.getLong("storeID"));
			}	
			return storeIDs;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			release(connection, pStatement, resultset);
		}	
	}
	

	
	/**
	 * 根据scollectID删除某个收藏夹中的店铺ID
	 */
	public void deleteByScollectID(Long scollectID,Long storeID) {
		Connection connection=null;
		PreparedStatement pStatement=null;		
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
	
	@Test
	public void test() {
		//测试insert
		StoreCollectStore storeCollectStore=new StoreCollectStore();
		storeCollectStore.setScollectID((long)1);
		storeCollectStore.setStoreID((long)2);
		insert(storeCollectStore.getScollectID(), storeCollectStore.getStoreID());
		
		//测试searchByPcollectID
		Long scollectID2=(long)1;
		searchByScollectID(scollectID2);
		
		//测试deleteByPcollectID
		storeCollectStore.setScollectID((long)1);
		storeCollectStore.setStoreID((long)2);
		deleteByScollectID(storeCollectStore.getScollectID(), storeCollectStore.getStoreID());
	}
}
