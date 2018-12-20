package dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import domain.Category;

public class CategoryDAO extends DaoBase{
	
	//增
	public void insert(Category category) {
		Connection conn = null;
		PreparedStatement pstmt = null;		

		try {
			conn =  this.getConnection();
			String sql = null;
			if(category.getParentID() == null) {
				sql = "insert into category (categoryName) values (?)";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, category.getCategoryName());				
			}	
			else {
				sql = "insert into category (categoryName, parentID) values (?, ?)";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, category.getCategoryName());		
				pstmt.setLong(2, category.getParentID());
			}
			
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
	
	//删
	public void delete(Long categoryID) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = this.getConnection();
			
			String sql = "delete from category where categoryID = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setLong(1, categoryID);
			pstmt.executeUpdate();
			
			System.out.println("Delete successfully");
			
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			this.release(conn, pstmt, null);
		}
	}
	
	//改categoryName
	public void updateCategoryName(Long categoryID, String newCategoryName) {
		Connection conn = null;
		PreparedStatement pstmt = null;	
		
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
					
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			this.release(conn, pstmt, null);
		}
	}
	
	//改parentID
	public void updateParentID(Long categoryID, Long newParentID) {
		Connection conn = null;
		PreparedStatement pstmt = null;	
		
		try {
			conn = this.getConnection();
			String sql = "update Category set parentID = ? where categoryID = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setLong(1, newParentID);
			pstmt.setLong(2, categoryID);
			
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
	
		
	//ͨ通过categoryName查找categoryID
	public Long queryCategoryID(String categoryName) {
		Connection conn = null;
		PreparedStatement pstmt = null;	
		ResultSet rs = null;
		
		Long categoryID = (long)-1;
		
		try {
			conn =  this.getConnection();
			String sql = "select categoryID from category where categoryName=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, categoryName);
			rs = pstmt.executeQuery();
						
			while(rs.next()) {
				categoryID = rs.getLong(1);
			}

		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			this.release(conn, pstmt, rs);
		}
		return categoryID;
	}
	
	//ͨ通过categoryName查找parentID
	public Long queryParentID(String categoryName) {
		Connection conn = null;
		PreparedStatement pstmt = null;	
		ResultSet rs = null;
		
		Long parentID = (long)-1;
		
		try {
			conn =  this.getConnection();
			String sql = "select parentID from category where categoryName=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, categoryName);
			rs = pstmt.executeQuery();
						
			while(rs.next()) {
				parentID = rs.getLong(1);
			}

		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			this.release(conn, pstmt, rs);
		}
		return parentID;	
	}
	
	@org.junit.Test
	public void Test() {
		
		Category c1= new Category();
		c1.setCategoryName("家用电器");
		insert(c1);
		
		updateCategoryName(queryCategoryID("家用电器"), "家电");
		
		Category c2= new Category();
		c2.setCategoryName("数码");
		insert(c2);		

		Category c3 = new Category();
		c3.setCategoryName("电视机");
		c3.setParentID(queryCategoryID("数码"));
		insert(c3);
		
		Category c4 = new Category();
		c4.setCategoryName("冰箱");
		c4.setParentID(queryCategoryID("家电"));
		insert(c4);
		
		updateParentID(queryCategoryID("电视机"),queryCategoryID("家电"));
		
		delete(queryCategoryID("冰箱"));

	}
}
