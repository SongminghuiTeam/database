package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
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
}
