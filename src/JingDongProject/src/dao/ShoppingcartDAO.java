package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.cj.result.Row;

import domain.Shoppingcart;

public class ShoppingcartDAO extends DaoBase {
	//增
	public int insert(Shoppingcart shoppingcart) {
		Connection conn = null;
		PreparedStatement pstmt = null;		
		
		int row = 0;
		
		try {
			conn = this.getConnection();
			String sql = "insert into shoppingcart (userID) values (?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, shoppingcart.getUserID());
			
			row = pstmt.executeUpdate();
			if(row > 0) {
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
		
		return row;
	}
	
	//删
	public int delete(Long shoppingcartID) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		int row = 0;
		
		try {
			conn = this.getConnection();
			
			String sql = "delete from shoppingcart where shoppingcartID = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setLong(1, shoppingcartID);
			row = pstmt.executeUpdate();
			
			if(row > 0) {
				System.out.println("Delete successfully");
			}
			else {
				System.out.println("Delete error");
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			this.release(conn, pstmt, null);
		}
		
		return row;
	}
	
	//改userID
	public int updateUserID(Long shoppingcartID, String newUserID) {
		Connection conn = null;
		PreparedStatement pstmt = null;	
		
		int row = 0;
		
		try {
			conn = this.getConnection();
			String sql = "update shoppingcart set userID = ? where shoppingcartID = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, newUserID);
			pstmt.setLong(2, shoppingcartID);
			
			row = pstmt.executeUpdate();
			
			if(row > 0) {
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
		
		return row;
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
		
		/*Shoppingcart s1 = new Shoppingcart();
		s1.setUserID("cwj");
		insert(s1);*/
		
		// delete(queryShoppingcartID("liangxiaoke"));
		
		// updateUserID(queryShoppingcartID("chenwanjing"), "heyulin");
		
		System.out.println(queryShoppingcartID("heyulin"));
	}
}
