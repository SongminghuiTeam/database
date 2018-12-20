package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import domain.OrderProduct;

public class OrderProductDAO extends DaoBase {
	
	//增
	@org.junit.Test
	public void insert() {
		Connection conn = null;
		PreparedStatement pstmt = null;		
		
		OrderProduct op = new OrderProduct();
		op.setOrderID((long)1);
		op.setProductID((long)1);
		
		try {
			conn = this.getConnection();
			String sql = "insert into orderproduct (orderID, productID) values (?, ?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setLong(1, op.getOrderID());
			pstmt.setLong(2, op.getProductID());
			
			if(pstmt.executeUpdate() > 0) {
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
	}
}
