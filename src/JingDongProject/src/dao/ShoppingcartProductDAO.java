package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;


import org.junit.Test;

import domain.ShoppingcartProduct;

public class ShoppingcartProductDAO extends DaoBase{
	public int insert(ShoppingcartProduct shoppingcartProduct) {
		Connection conn = null;
		PreparedStatement pStatement = null;
		ResultSet rs = null;
		
		int row = 0;

		try {
			conn = getConnection();
			String sql = "insert into shoppingcartproduct(shoppingcartID,productID) values(?,?)";
			pStatement = conn.prepareStatement(sql);
			pStatement.setLong(1, shoppingcartProduct.getShoppingcartID());
			pStatement.setLong(2, shoppingcartProduct.getProductID());
			
			row =  pStatement.executeUpdate();
			
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
		
		return row;
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
	
	public void delete(Long shoppingcartID) {
		Connection conn = null;
		PreparedStatement pStatement = null;
		ResultSet rs = null;
		
		try {
			conn = getConnection();
			String sql = "delete from shoppingcartproduct where shoppingcartID=?";
			pStatement = conn.prepareStatement(sql);
			pStatement.setLong(1, shoppingcartID);
			
			int row = pStatement.executeUpdate();
			if(row > 0) {
				System.out.println("delete successfully");
			}
			else {
				System.out.println("delete failed");
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
	public void Test() {
		/*ShoppingcartProduct shoppingcartProduct1 = new ShoppingcartProduct((long)8, (long)16);
		insert(shoppingcartProduct1);*/
		
		/*ArrayList<Long> productIDs = search((long)1);
		if(productIDs.size() == 0) {
			System.out.println("no matched record!");
		}
		else {
			for(int i = 0;i < productIDs.size();i++) {
				System.out.println("productID:" + productIDs.get(i));
			}
		}*/
		
		delete((long)6);
	}
}
