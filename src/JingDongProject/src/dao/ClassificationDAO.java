package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import domain.Classification;

public class ClassificationDAO extends DaoBase{
	
	//增
	@org.junit.Test
	public void insert() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		String categoryName = "家电";
		String classificationName = "冰箱";
		CategoryDAO cDAO = new CategoryDAO();
		Long categoryID = cDAO.queryCategoryID(categoryName);		
		Classification classification = new Classification();
		classification.setClassificationName(classificationName);
		classification.setCategoryID(categoryID);

		if(categoryID == -1) {			
			System.out.println("Insert failed");
			return;
		}
		try {
			conn =  this.getConnection();
			String sql = "insert into Classification (classificationName, categoryID) values (?, ?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, classification.getClassificationName());
			pstmt.setLong(2, classification.getCategoryID());
		
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

	//ͨ通过classificationName查找classificationID
	public Long queryClassificationID(String classificationName) {
		Connection conn = null;
		PreparedStatement pstmt = null;	
		ResultSet rs = null;
		
		Long classificationID = (long)-1;
		
		try {
			conn =  this.getConnection();
			String sql = "select Classification.classificationID from Classification where Classification.ClassificationName=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, classificationName);
			rs = pstmt.executeQuery();
						
			while(rs.next()) {
				classificationID=rs.getLong(1);
			}

			this.release(conn, pstmt, rs);
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return classificationID;
	}	
	
	//修改小分类名
	@org.junit.Test
	public void updateClassificationName() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		String oldClassificationName = "冰箱";
		String newClassificationName = "电视机";
		
		ClassificationDAO cDAO = new ClassificationDAO();
		long classificationID = cDAO.queryClassificationID(oldClassificationName);
		
		if(classificationID == -1) {
			System.out.println("Update failed");
			return;
		}
		
		try {
			conn = this.getConnection();
			String sql = "update Classification set classificationName = ? where classificationID = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, newClassificationName);
			pstmt.setLong(2, classificationID);
			
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
	
	//修改小分类所属的大分类
	@org.junit.Test
	public void updateCategoryID() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		String classificationName = "电视机";
		String newCategoryName = "电器";

		ClassificationDAO clDAO = new ClassificationDAO();
		Long classificationID = clDAO.queryClassificationID(classificationName);
		if(classificationID == -1) {
			System.out.println("Update failed");
			return;			
		}		
		
		CategoryDAO caDAO = new CategoryDAO();
		Long newCategoryID = caDAO.queryCategoryID(newCategoryName);
		if(newCategoryID == -1) {
			System.out.println("Update failed");
			return;
		}
		
		
		try {
			conn = this.getConnection();
			String sql = "Update Classification set categoryID = ? where classificationID = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setLong(1, newCategoryID);
			pstmt.setLong(2, classificationID);
			
			if(pstmt.executeUpdate() > 0) {
				System.out.println("Update successfully");
			}
			else {
				System.out.println("Update failed");
			}			
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
}
