package dao;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.Test;

import domain.Store;

public class StoreDAO extends DaoBase {

	@Test
	public void Test() {
		Store store = new Store();
		store.setScore(Float.valueOf(0));
		store.setStoreName("宋明惠的店铺");
		store.setUserID("宋明惠");
		//insert(store);
		store=searchByUserID("宋明惠");
		//store=searchByStoreName("宋明惠的店铺");
		//updateStoreName("宋明惠店铺", store.getStoreID());
		//updateScore(store.getStoreID(), (float) 2);
		//delectByStoreID(store.getStoreID());
	}

	/**
	 * 测试 一个用户账号只能添加一个店铺 用户必须在user表中存在
	 * 并且一个用户只能有一个店铺
	 */
	public int insert(Store store) {
		Connection connection = getConnection();
		String sql = "insert store(userID,storeName,score) values(?,?,?)";
		PreparedStatement pStatement = null;
		int rSet = 0;
		try {
			pStatement = connection.prepareStatement(sql);
			pStatement.setString(1, store.getUserID());
			pStatement.setString(2, store.getStoreName());
			pStatement.setFloat(3, store.getScore());

		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			rSet = pStatement.executeUpdate();
			if (rSet == 1)
				System.out.println("insert success");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		release(connection, pStatement, null);
		return rSet;
	}

	/**
	 * 测试 如果存在这个店铺，那么只会有一个
	 */
	public Store searchByUserID(String userID) {
		Store store = new Store();
		Connection connection = getConnection();
		String sql = "select * from store where userID=?";
		PreparedStatement pStatement = null;
		ResultSet resultSet = null;
		try {
			pStatement = connection.prepareStatement(sql);
			pStatement.setString(1, userID);
			resultSet = pStatement.executeQuery();
			if (resultSet.next()) {
				store.setStoreID(resultSet.getLong(1));
				store.setUserID(resultSet.getString(2));
				store.setStoreName(resultSet.getString(3));
				store.setScore(resultSet.getFloat(4));
				System.out.println(store.getStoreID() + " " + store.getStoreName() + " " + store.getUserID() + " "
						+ store.getScore());
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		release(connection, pStatement, resultSet);
		return store;
	}

	/**
	 * 测试 如果存在这个店铺，那么只会有一个
	 */
	public Store searchByStoreName(String storeName) {
		Store store = new Store();
		Connection connection = getConnection();
		String sql = "select * from store where storeName=?";
		PreparedStatement pStatement = null;
		ResultSet resultSet = null;
		try {
			pStatement = connection.prepareStatement(sql);
			pStatement.setString(1, storeName);
			resultSet = pStatement.executeQuery();
			if (resultSet.next()) {
				store.setStoreID(resultSet.getLong(1));
				store.setUserID(resultSet.getString(2));
				store.setStoreName(resultSet.getString(3));
				store.setScore(resultSet.getFloat(4));
				System.out.println(store.getStoreID() + " " + store.getStoreName() + " " + store.getUserID() + " "
						+ store.getScore());
			}
		} catch (SQLException e) {

			e.printStackTrace();
		}
		release(connection, pStatement, resultSet);
		return store;
	}

	/**
	 * 根据店铺id更新店铺名
	 */
	public int updateStoreName(String storeName, Long storeID) {
		Connection connection = getConnection();
		String sql = "update store set storeName=? where storeID=?";
		PreparedStatement pStatement = null;
		int resultSet = 0;
		try {
			pStatement = connection.prepareStatement(sql);
			pStatement.setString(1, storeName);
			pStatement.setLong(2, storeID);
			resultSet = pStatement.executeUpdate();
			if (resultSet != 0) {
				System.out.println("update storeName success");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		release(connection, pStatement, null);
		return resultSet;
	}

	/**
	 * 测试 根据店铺id进行更新评分
	 */
	public int updateScore(Long storeID, Float score) {
		Connection connection = getConnection();
		String sql = "update store set score=? where storeID=?";
		PreparedStatement pStatement = null;
		int resultSet = 0;
		try {
			pStatement = connection.prepareStatement(sql);
			pStatement.setFloat(1, score);
			pStatement.setLong(2, storeID);
			resultSet = pStatement.executeUpdate();
			if (resultSet != 0) {
				System.out.println("update score success");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		release(connection, pStatement, null);
		return resultSet;
	}

	public int delectByStoreID(Long storeID) {
		Connection connection = getConnection();
		String sql = "delete from store where storeID=?";
		PreparedStatement pStatement = null;
		int resultSet = 0;
		try {
			pStatement = connection.prepareStatement(sql);
			pStatement.setLong(1, storeID);
			resultSet = pStatement.executeUpdate();
			if (resultSet == 1) {
				System.out.println("delete store success");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		release(connection, pStatement, null);
		return resultSet;
	}
}
