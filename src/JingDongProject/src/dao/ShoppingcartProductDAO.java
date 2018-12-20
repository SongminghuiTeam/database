package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import org.junit.Test;

import org.junit.Test;

import domain.ShoppingcartProduct;

public class ShoppingcartProductDAO extends DaoBase{
	public void insert(ShoppingcartProduct shoppingcartProduct) {
		Connection conn = null;
		PreparedStatement pStatement = null;
		ResultSet rs = null;

		try {
			conn = getConnection();
			String sql = "insert into shoppingcartproduct(shoppingcartID,productID) values(?,?)";
			pStatement = conn.prepareStatement(sql);
			pStatement.setLong(1, shoppingcartProduct.getShoppingcartID());
			pStatement.setLong(2, shoppingcartProduct.getProductID());
			
			int row =  pStatement.executeUpdate();
			
			if(row > 0) {
				System.out.println("insert successfully");
				System.out.print("\n");
			}
			else {
				System.out.println("insert failed");
				System.out.print("\n");
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
	
	public ArrayList<Long> search(Long shoppingcartID){
		ArrayList<Long> productIDs = new ArrayList<Long>();
		
		Connection conn = null;
		PreparedStatement pStatement = null;
		ResultSet rs = null;
		
		try {
			conn = getConnection();
			String sql = "select * from shoppingcartproduct where shoppingcartID=?";
			pStatement = conn.prepareStatement(sql);
			pStatement.setLong(1, shoppingcartID);
			
			rs = pStatement.executeQuery();
			
			while(rs.next()) {
				productIDs.add(rs.getLong("productID"));
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
		return productIDs;
	}
	
	@Test
	public void Test() {
		//ShoppingcartProduct shoppingcartProduct1 = new ShoppingcartProduct((long)2, (long)7);
		ShoppingcartProduct shoppingcartProduct2 = new ShoppingcartProduct((long)4, (long)5);
		
		//insert(shoppingcartProduct1);
		//insert(shoppingcartProduct2);
		
		ArrayList<Long> productIDs = search((long)1);
		if(productIDs.size() == 0) {
			System.out.println("no matched record!");
		}
		else {
			for(int i = 0;i < productIDs.size();i++) {
				System.out.println("productID:" + productIDs.get(i));
			}
		}
	}
}
