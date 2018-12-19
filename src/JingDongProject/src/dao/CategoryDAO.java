package dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import domain.Category;

public class CategoryDAO extends DaoBase{
	
	//增
	@org.junit.Test
	public void insert() {
		Connection conn = null;
		PreparedStatement pstmt = null;		
		
		Category category = new Category();
		category.setCategoryName("家电");
		try {
			conn =  this.getConnection();
			String sql = "insert into Category (categoryName) values (?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, category.getCategoryName());
		
			int flag = pstmt.executeUpdate();
			
			if(flag > 0) {
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
	
	//ͨ通过CategoryName查找CategoryID
	public Long queryCategoryID(String categoryName) {
		Connection conn = null;
		PreparedStatement pstmt = null;	
		ResultSet rs = null;
		
		Long categoryID = (long)-1;
		
		try {
			conn =  this.getConnection();
			String sql = "select Category.categoryID from Category where Category.categoryName=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, categoryName);
			rs = pstmt.executeQuery();
						
			while(rs.next()) {
				categoryID=rs.getLong(1);
			}

			this.release(conn, pstmt, rs);
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return categoryID;
	}
}
