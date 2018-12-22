package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import domain.Order;

public class OrderDAO extends DaoBase {
	//增
	public int insert(Order order) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		int row = 0;
		
		try {
			conn =  this.getConnection();
			String sql = "insert into `order` (userID, orderTime, addressID) values (?, ?, ?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, order.getUserID());
			pstmt.setString(2, order.getOrderTime());
			pstmt.setLong(3, order.getAddressID());
			
			row = pstmt.executeUpdate();
			
			if(row > 0) {
				System.out.println("Insert successfully");
			}
			else {
				System.out.println("Insert failed");
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			this.release(conn, pstmt, null);
		}
		
		return row;
	}
	
	//删
	public int delete(Long orderID) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		int row = 0;
		
		try {
			conn = this.getConnection();
			
			String sql = "delete from `order` where orderID = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setLong(1, orderID);
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
	
	@org.junit.Test
	public void Test() {
		Order order = new Order();
		order.setUserID("chenwanjing");
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = new Date();
		String orderTime = df.format(date);
		order.setOrderTime(orderTime);
		order.setAddressID((long)9);
		
		//insert(order);
		
		delete((long)6);
		
	}
}
