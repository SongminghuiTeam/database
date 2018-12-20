package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import domain.ProductCollectProduct;

public class ProductCollectProductDAO extends DaoBase{
	
	/**
	 * 根据pcollectID向某个收藏夹中添加产品ID
	 */
	public void insert(Long pcollectID,Long productID) {
		Connection connection=null;
		PreparedStatement pStatement=null;
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
	 * 根据收藏夹ID查询指定收藏夹中的所有产品ID，并返回productID的list
	 */
	public List<Long> searchByPcollectID(Long pcollectID) {
		Connection connection=null;
		PreparedStatement pStatement=null;
		ResultSet resultset=null;
		List<Long> productIDs=new ArrayList<Long>();
		try {
			connection=getConnection();
			String sql="select * from productcollectproduct where pcollectID=?";
			pStatement=connection.prepareStatement(sql);
			pStatement.setLong(1, pcollectID);
			resultset=pStatement.executeQuery();
			System.out.println("收藏夹"+pcollectID+"下的所有产品ID：");
			while(resultset.next()) {
				productIDs.add(resultset.getLong("productID"));
				System.out.println(resultset.getLong("productID"));
			}	
			return productIDs;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			release(connection, pStatement, resultset);
		}	
	}
	
	/**
	 * 根据PcollectID删除收藏夹中的某个产品ID，即取消收藏
	 */
	public void deleteByPcollectID(Long pcollectID,Long productID) {
		Connection connection=null;
		PreparedStatement pStatement=null;	
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
	
	@Test
	public void test() {
		//测试insert
		ProductCollectProduct pcProduct=new ProductCollectProduct();
		pcProduct.setPcollectID((long)10);
		pcProduct.setProductID((long)3);
		insert(pcProduct.getPcollectID(), pcProduct.getProductID());
		
		//测试searchByPcollectID
		Long pcollectID2=(long)2;
		searchByPcollectID(pcollectID2);
		
		//测试deleteByPcollectID
		pcProduct.setPcollectID((long)10);
		pcProduct.setProductID((long)3);
		deleteByPcollectID(pcProduct.getPcollectID(), pcProduct.getProductID());
	}
	
}
