package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.junit.Test;

import domain.Product;

public class ProductDAO extends DaoBase {
	@Test
	public void insert() {
		Connection conn = null;
		PreparedStatement pStatement = null;
		ResultSet rs = null;
		
		//Product product = new Product("耳机", (float) 0.3, "X12 2 4.2", "双耳无线蓝牙迷你入耳式运动耳机", (long)1, (long)1, (long)10);
		Product product = new Product("鼠标", (float) 0.8, "USB 1600dpi 2.4GHz", "AOC无线笔记本鼠标", (long)2, (long)1);
	
		try {
			conn = getConnection();
			String sql = "";
			if(product.getJdBean() == 0) {
				sql = "insert into product(productName,weight,visitVolume,model,description,storeID,classificationID) values(?,?,?,?,?,?,?)";
				pStatement = conn.prepareStatement(sql);
				pStatement.setString(1, product.getProductName());
				pStatement.setFloat(2, product.getWeight());
				pStatement.setLong(3, product.getVisitVolume());
				pStatement.setString(4, product.getModel());
				pStatement.setString(5, product.getDescription());
				pStatement.setLong(6, product.getStoreID());
				pStatement.setLong(7, product.getClassificationID());
			}
			else {
				sql = "insert into product(productName,weight,visitVolume,model,description,storeID,classificationID,jdBean) values(?,?,?,?,?,?,?,?)";
				pStatement = conn.prepareStatement(sql);
				pStatement.setString(1, product.getProductName());
				pStatement.setFloat(2, product.getWeight());
				pStatement.setLong(3, product.getVisitVolume());
				pStatement.setString(4, product.getModel());
				pStatement.setString(5, product.getDescription());
				pStatement.setLong(6, product.getStoreID());
				pStatement.setLong(7, product.getClassificationID());
				pStatement.setLong(8, product.getJdBean());
			}
			
			int row =  pStatement.executeUpdate();
			
			if(row > 0) {
				System.out.println("insert successfully");
			}
			else {
				System.out.println("insert failed");
			}
		}catch(Exception sqlException) {
			sqlException.printStackTrace();
		}finally {
			try {
				release(conn, pStatement, rs);
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	@Test
	public void search() {
		Connection conn = null;
		PreparedStatement pStatement = null;
		ResultSet rs = null;
		
		String productName = "嗅嗅";
		
		try {
			conn = getConnection();
			String sql = "select * from product where productName like '%" + productName + "%'";
			pStatement = conn.prepareStatement(sql);
			
			rs = pStatement.executeQuery();
			if(rs.next()) {
				System.out.println("search successfully!" + "\n");
				while(true) {
					/*Long classificationID = rs.getLong("classificationID");
					Long storeID = rs.getLong("storeID");
					System.out.println("classificationID:" + classificationID);
					System.out.println("storeID:" + storeID);*/
					
					/*sql = "select store.storeName, classification.classificationName, category.categoryName from store, classification, category"
							+ " where classification.categoryID=category.categoryID and classification.classificationID=? and store.storeID=?";
					pStatement = conn.prepareStatement(sql);
					pStatement.setLong(1, classificationID);
					pStatement.setLong(2, storeID);
					
					ResultSet rs1 = pStatement.executeQuery();
					String storeName = rs1.getString("storeName");
					String classificationName = rs1.getString("classificationName");
					String categoryName = rs1.getString("categoryName");*/
					
					
					System.out.println("productName:" + rs.getString("productName") + " price:" + rs.getFloat("price") + " weight:" +
							rs.getFloat("weight") + " description:" + rs.getString("description") + " model:" + rs.getString("model"));
					System.out.println("jdBean:" + rs.getLong("jdBean") + " visitVolume:" + rs.getLong("visitVolume"));
					
					//String storeName = searchStoreName(storeID);
					//String classificationName =searchClassificationName(classificationID);
					//String categoryName = searchCategoryName(classificationID);
					//System.out.println("type:" + categoryName + "-" + classificationName);
					//System.out.println("storeName:" + storeName);
					Long classificationID = rs.getLong("classificationID");
					Long storeID = rs.getLong("storeID");
					System.out.println("classificationID:" + classificationID);
					System.out.println("storeID:" + storeID);
					
					if(!rs.getBoolean("status")) {
						System.out.println("缺货");
					}

					System.out.println("\n");
					if(!rs.next()) {
						break;
					}
				}
			}
			else {
				System.out.println("search failed");
			}
		}catch(Exception sqlException) {
			sqlException.printStackTrace();
		}finally {
			try {
				release(conn, pStatement, rs);
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	/*private String searchStoreName(Long storeID) {
		Connection conn = null;
		PreparedStatement pStatement = null;
		ResultSet rs = null;
		
		try {
			conn = getConnection();
			String sql = "select storeName from store where storeID=?";
			pStatement = conn.prepareStatement(sql);
			pStatement.setLong(1, storeID);
			
			rs = pStatement.executeQuery();
			
			String storeName = rs.getString("storeName");
			release(conn, pStatement, rs);
			System.out.println("storeName:" + storeName);
			return storeName;
		}catch(Exception sqlException) {
			sqlException.printStackTrace();
			return null;
		}
	}
	
	private String searchClassificationName(Long classificationID) {
		Connection conn = null;
		PreparedStatement pStatement = null;
		ResultSet rs = null;
		
		try {
			conn = getConnection();
			String sql = "select classificationName from store where classificationID=?";
			pStatement = conn.prepareStatement(sql);
			pStatement.setLong(1, classificationID);
			
			rs = pStatement.executeQuery();
			
			String classificationName = rs.getString("classificationName");
			release(conn, pStatement, rs);
			return classificationName;
		}catch(Exception sqlException) {
			sqlException.printStackTrace();
			return null;
		}
	}
	
	private String searchCategoryName(Long classificationID) {
		Connection conn = null;
		PreparedStatement pStatement = null;
		ResultSet rs = null;
		
		try {
			conn = getConnection();
			String sql = "select categoryName from classification, category where classification.categoryID=category.categoryID and classificationID=?";
			
			pStatement = conn.prepareStatement(sql);
			pStatement.setLong(1, classificationID);
			
			rs = pStatement.executeQuery();
			
			String categoryName = rs.getString("categoryName");
			release(conn, pStatement, rs);
			return categoryName;
		}catch(Exception sqlException) {
			sqlException.printStackTrace();
			return null;
		}
	}*/
	
	
}
