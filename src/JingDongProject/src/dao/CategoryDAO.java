package dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import domain.Category;

public class CategoryDAO extends DaoBase{
	//Ôö
	@org.junit.Test
	public void Insert() {
		Connection conn = null;
		PreparedStatement pstmt = null;		
		
		Category category = new Category();
		category.setCategoryName("¼Òµç");
		try {
			conn =  this.getConnection();
			String sql = "insert into Category (categoryName) values (?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, category.getCategoryName());
		
			int flag = pstmt.executeUpdate();
			
			if(flag > 0) {
				System.out.println("Insert Successfully");
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
