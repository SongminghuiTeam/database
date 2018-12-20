package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.junit.Test;

import domain.ProductCollect;

public class ProductCollectDAO extends DaoBase{
	
	/**
	 * 根据userID，为该user添加一个收藏夹
	 * @param userID
	 */
	public void insertByUserID(String userID){
		Connection connection=null;
		PreparedStatement pStatement=null;
		//先查出这个用户是否已经有产品收藏夹
		if(queryByUserID(userID)!=null) {
			System.out.println("对不起，该用户已有产品收藏夹，不能添加！");
			return ;
		}	
		try {
			connection=getConnection();
			String sql="insert into productcollect(userID) values(?)";
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
			e.printStackTrace();
		} finally {
			release(connection, pStatement, null);
		}
	}
	
	/**
	 * 根据用户的userID查出她的产品收藏夹，并返回pcollectID
	 * @param userID
	 * @return
	 */
	public Long queryByUserID(String userID) {
		Connection connection=null;
		PreparedStatement pStatement=null;
		ResultSet resultset=null;			
		ProductCollect pCollect=new ProductCollect();
		pCollect.setUserID(userID);
		try {
			connection=getConnection();
			String sql="select * from productcollect where userID=?";
			pStatement=connection.prepareStatement(sql);
			pStatement.setString(1, userID);
			resultset=pStatement.executeQuery();
			while(resultset.next()) {
				pCollect.setPcollectID(resultset.getLong(1));			
				System.out.println(pCollect.getUserID()+"的收藏夹ID："+pCollect.getPcollectID());
				break;
			}	
			return pCollect.getPcollectID();
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
		String userID2="lxk";
		queryByUserID(userID2);

	}
}
