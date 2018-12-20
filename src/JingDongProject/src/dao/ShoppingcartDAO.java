package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import domain.Shoppingcart;

public class ShoppingcartDAO extends DaoBase {
	//增
	public void insert(Shoppingcart shoppingcart) {
		Connection conn = null;
		PreparedStatement pstmt = null;		
		
		try {
			conn = this.getConnection();
			String sql = "insert into shoppingcart (userID) values (?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, shoppingcart.getUserID());
			
			if(pstmt.executeUpdate() > 0) {
				System.out.println("Insert successfully");
			}
			else {
				System.out.println("Insert failed");
			}
			
			this.release(conn, pstmt, null);			
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			this.release(conn, pstmt, null);
		}
	}
	
	//删
	public void delete(Long shoppingcartID) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = this.getConnection();
			
			String sql = "delete from shoppingcart where shoppingcartID = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setLong(1, shoppingcartID);
			pstmt.executeUpdate();
			
			System.out.println("Delete successfully");
			
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			this.release(conn, pstmt, null);
		}
	}
	
	//改userID
	public void updateUserID(Long shoppingcartID, String newUserID) {
		Connection conn = null;
		PreparedStatement pstmt = null;	
		
		try {
			conn = this.getConnection();
			String sql = "update shoppingcart set userID = ? where shoppingcartID = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, newUserID);
			pstmt.setLong(2, shoppingcartID);
			
			if(pstmt.executeUpdate() > 0) {
				System.out.println("Update successfully");
			}
			else {
				System.out.println("Update failed");
			}
					
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			this.release(conn, pstmt, null);
		}
	}
	
	//根据userID查询shoppingcartID
	public Long queryShoppingcartID(String userID) {
		Connection conn = null;
		PreparedStatement pstmt = null;	
		ResultSet rs = null;
		
		Long shoppingcartID = (long)-1;
		try {
			conn =  this.getConnection();
			String sql = "select shoppingcartID from shoppingcart where userID=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userID);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				shoppingcartID = rs.getLong(1);
			}

			this.release(conn, pstmt, rs);
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return shoppingcartID;
	}
	
	@org.junit.Test
	public void Test() {
		
		Shoppingcart s1 = new Shoppingcart();
		s1.setUserID("chenwanjing");
		insert(s1);
		
		Shoppingcart s2 = new Shoppingcart();
		s2.setUserID("heyulin");
		insert(s2);
		
		delete(queryShoppingcartID("chenwanjing"));
		
		updateUserID(queryShoppingcartID("heyulin"), "liangxiaoke");
	}
}
