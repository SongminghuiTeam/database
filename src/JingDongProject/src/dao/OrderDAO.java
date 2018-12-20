package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import domain.Order;

public class OrderDAO extends DaoBase {
	//增
	@org.junit.Test
	public void insert() {
		Connection conn = null;
		PreparedStatement pstmt = null;		
		
		Order order = new Order();
		order.setUserID("chenwanjing");
		
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date=new Date();
		String orderTime = df.format(date);
		order.setOrderTime(orderTime);
		order.setAddressID((long)2);
		
		//判断userID是否合法
		UserDAO uDAO = new UserDAO();
		if(!uDAO.isExist(order.getUserID())) {
			System.out.println("该用户不存在");
			return;
		}
		
		//判断addressID是否合法
		AddressDAO aDAO = new AddressDAO();
		if(!aDAO.queryUserIDByAddressID(order.getAddressID()).equals(order.getUserID())) {
			System.out.println("该地址不属于该用户");
			return;
		}
		
		try {
			conn =  this.getConnection();
			String sql = "insert into `order` (userID, orderTime, addressID) values (?, ?, ?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, order.getUserID());
			pstmt.setString(2, order.getOrderTime());
			pstmt.setLong(3, order.getAddressID());
			
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
