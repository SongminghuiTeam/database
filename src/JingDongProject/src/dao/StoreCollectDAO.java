package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.junit.Test;

import domain.StoreCollect;

public class StoreCollectDAO extends DaoBase{
	
	/**
	 * 根据userID为该用户创建店铺收藏夹
	 * @param userID
	 */
	public void insertByUserID(String userID){
		Connection connection=null;
		PreparedStatement pStatement=null;
		//先查出这个用户是否已经有店铺收藏夹
		if(searchByUserID(userID)!=null) {
			System.out.println("该用户已有店铺收藏夹，无需添加！");
			return ;
		}
		try {
			connection=getConnection();
			String sql="insert into storecollect(userID) values(?)";
			pStatement=connection.prepareStatement(sql);
			pStatement.setString(1, userID);
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

	/**
	 * 根据用户的userID查询该用户的店铺收藏夹的ID并返回scollectID
	 * @param userID
	 * @return
	 */
	public Long searchByUserID(String userID) {
		Connection connection=null;
		PreparedStatement pStatement=null;
		ResultSet resultset=null;		
		
		StoreCollect sCollect=new StoreCollect();
		sCollect.setUserID(userID);
		try {
			connection=getConnection();
			String sql="select * from storecollect where userID=?";
			pStatement=connection.prepareStatement(sql);
			pStatement.setString(1, userID);
			resultset=pStatement.executeQuery();
			while(resultset.next()) {
				sCollect.setScollectID(resultset.getLong(1));
				System.out.println(userID+"的店铺收藏夹ID："+sCollect.getScollectID());
				break;
			}			
			return sCollect.getScollectID();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			release(connection, pStatement, resultset);
		}
	}
	
	@Test
	public void test() {
		//测试insert
		String userID="lxk";
		insertByUserID(userID);
				
		//测试queryByUserID
		String userID2="hyl";
		searchByUserID(userID2);
	}
}
