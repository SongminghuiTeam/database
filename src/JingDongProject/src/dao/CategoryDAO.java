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
	
	//ͨ通过categoryName查找categoryID
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
	
	//改
	@org.junit.Test
	public void update() {
		Connection conn = null;
		PreparedStatement pstmt = null;	
		
		String oldCategoryName = "家电";
		String newCategoryName = "电器";
		
		CategoryDAO cDAO = new CategoryDAO();
		Long categoryID = cDAO.queryCategoryID(oldCategoryName);
		
		if(categoryID == -1) {			
			System.out.println("Update failed");
			return;
		}
		
		try {
			conn = this.getConnection();
			String sql = "update Category set categoryName = ? where categoryID = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, newCategoryName);
			pstmt.setLong(2, categoryID);
			
			if(pstmt.executeUpdate() > 0) {
				System.out.println("Update successfully");
			}
			else {
				System.out.println("Update failed");
			}
			
			this.release(conn, pstmt, null);			
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	//查
	@org.junit.Test
	public void search() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String categoryName = "电器";
		CategoryDAO caDAO = new CategoryDAO();
		ClassificationDAO cl = new ClassificationDAO();
		Long categoryID = caDAO.queryCategoryID(categoryName);
		
		if(categoryID == -1) {			
			System.out.println("Search failed");
			return;
		}
		
		try {
			conn = this.getConnection();
			String sql = "select classificationName from Classification where categoryID=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setLong(1, categoryID);
			
			System.out.println(categoryName + "：");
			rs = pstmt.executeQuery();
			while(rs.next()) {
				String classificationName = rs.getString(1);
				cl.search(classificationName);
			}
			this.release(conn, pstmt, rs);
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	//删
	@org.junit.Test
	public void delete() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		String categoryName = "服装";
		
		CategoryDAO cDAO = new CategoryDAO();
		Long categoryID = cDAO.queryCategoryID(categoryName);
		
		if(categoryID == -1) {			
			System.out.println("no categoryName " + categoryName);
			return;
		}
		
		try {
			conn = this.getConnection();
			
			String sql = "delete from Category where categoryID = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setLong(1, categoryID);
			pstmt.executeUpdate();
			
			System.out.println("Delete successfully");
			this.release(conn, pstmt, null);
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
}
