package dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import domain.Category;

public class CategoryDAO extends DaoBase{
	
	//增
	public int insert(Category category) {
		Connection conn = null;
		PreparedStatement pstmt = null;	
		
		int row = 0;

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
	public int delete(Long categoryID) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		int row = 0;
		
		try {
			conn = this.getConnection();
			
			String sql = "delete from category where categoryID = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setLong(1, categoryID);
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
	
	//改categoryName
	public int updateCategoryName(Long categoryID, String newCategoryName) {
		Connection conn = null;
		PreparedStatement pstmt = null;	
		
		int row = 0;
		
		try {
			conn = this.getConnection();
			String sql = "update Category set categoryName = ? where categoryID = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, newCategoryName);
			pstmt.setLong(2, categoryID);
			
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
	
	//改parentID
	public int updateParentID(Long categoryID, Long newParentID) {
		Connection conn = null;
		PreparedStatement pstmt = null;	
		
		int row = 0;
		
		try {
			conn = this.getConnection();
			String sql = "update Category set parentID = ? where categoryID = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setLong(1, newParentID);
			pstmt.setLong(2, categoryID);
			
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
		
		/*Category c1= new Category();
		c1.setCategoryName("家庭电器");
		insert(c1);*/
		
		/*Category c4 = new Category();
		c4.setCategoryName("冰箱");
		c4.setParentID(queryCategoryID("家庭电器"));
		insert(c4);*/
		
		/*Category c4 = new Category();
		c4.setCategoryName("电视");
		c4.setParentID((long)1);
		insert(c4);*/
		
		// updateCategoryName(queryCategoryID("家电"), "服饰");
		
		/*Category c2= new Category();
		c2.setCategoryName("数码");
		insert(c2);		

		Category c3 = new Category();
		c3.setCategoryName("电视机");
		c3.setParentID(queryCategoryID("数码"));
		insert(c3);
		
		Category c4 = new Category();
		c4.setCategoryName("冰箱");
		c4.setParentID(queryCategoryID("家电"));
		insert(c4);*/
		
		// updateParentID(queryCategoryID("电子产品"),queryCategoryID("服饰"));
		
		// delete(queryCategoryID("电子产品"));
		
		System.out.println(queryParentID("电子产品"));

	}
}
