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
		Product product = new Product("耳机", (float) 0.5, "X10 2 3.5", "双耳迷你入耳式运动耳机", (long)1, (long)1);
	
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
}
