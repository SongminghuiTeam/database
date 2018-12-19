package dao;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.junit.Test;

import domain.Store;

public class StoreDAO extends DaoBase {

	@Test
	public void insert() {
		Store store = new Store();
		store.setScore(Float.valueOf(0));
		store.setStoreName("宋明惠的店铺");
		store.setUserID("宋明惠");
		Connection connection = getConnection();
		String sql = "insert store(userID,storeName,score) values(?,?,?)";
		PreparedStatement pStatement = null;
		try {
			pStatement = connection.prepareStatement(sql);
			pStatement.setString(1, store.getUserID());
			pStatement.setString(2, store.getStoreName());
			pStatement.setFloat(3, store.getScore());

		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			int rSet = pStatement.executeUpdate();
			if (rSet == 1)
				System.out.println("insert success");
		} catch (SQLException e) {

			e.printStackTrace();
		}
		release(connection, pStatement, null);
	}
}
