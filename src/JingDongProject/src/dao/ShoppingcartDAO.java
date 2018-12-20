package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ShoppingcartDAO extends DaoBase {
	//增
	@org.junit.Test
	public void insert() {
		Connection conn = null;
		PreparedStatement pstmt = null;		
		
		String userID="chenwanjing";
		
		UserDAO uDAO = new UserDAO();
		if(!uDAO.isExist(userID)) {
			System.out.println("该用户不存在");
			return;
		}
			
		
		Long shoppingcartID = queryShoppingcartID(userID);
		
		if(shoppingcartID != -1) {
			System.out.println("该用户的购物车已存在");
			return;
		}
		
		try {
			conn = this.getConnection();
			String sql = "insert into shoppingcart (userID) values (?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userID);
			
			if(pstmt.executeUpdate() > 0) {
				System.out.println("Insert successfully");
			}
			else {
				System.out.println("Insert failed");
			}
			
			this.release(conn, pstmt, null);			
		}catch(SQLException e) {
			e.printStackTrace();
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
}
