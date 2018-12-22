package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import domain.OrderProduct;

public class OrderProductDAO extends DaoBase {
	
	//增
	public int insert(OrderProduct op) {
		Connection conn = null;
		PreparedStatement pstmt = null;		
		
		int row = 0;
		
		try {
			conn = this.getConnection();
			String sql = "insert into orderproduct (orderID, productID) values (?, ?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setLong(1, op.getOrderID());
			pstmt.setLong(2, op.getProductID());
			
			row =  pstmt.executeUpdate();
			
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
	
	//根据orderID查询该订单的所有productID
	public ArrayList<Long> queryByOrderID(Long orderID) {
		Connection conn = null;
		PreparedStatement pstmt = null;	
		ResultSet rs = null;
		ArrayList<Long> productIDs = new ArrayList<Long>();
		
		try {
			conn = this.getConnection();
			String sql = "select productID from orderproduct where orderID=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setLong(1, orderID);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				productIDs.add(rs.getLong(1));
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			this.release(conn, pstmt, rs);
		}
		return productIDs;
	}
	
	
	@org.junit.Test
	public void Test() {
		
		/*OrderProduct op1 = new OrderProduct();
		op1.setOrderID((long)1);
		op1.setProductID((long)1);
		insert(op1);*/
		
		ArrayList<Long> productIDs =  queryByOrderID((long)2);
		if(productIDs.size() == 0) {
			System.out.println("search error");
		}
		else {
			for(Long productID : productIDs) {
				System.out.println(productID);
			}
		}
	}
}
