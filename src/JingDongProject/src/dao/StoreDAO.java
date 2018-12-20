package dao;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.Test;

import domain.Store;

public class StoreDAO extends DaoBase {

	/**
	 * 测试 一个用户账号只能添加一个店铺 用户必须在user表中存在
	 */
	@Test
	public void insert() {
		Store store = new Store();
		store.setScore(Float.valueOf(0));
		store.setStoreName("梁晓珂的店铺");
		store.setUserID("梁晓珂");
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

	/**
	 * 测试 如果存在这个店铺，那么只会有一个
	 */
	@Test
	public void searchByUserID() {
		Store store = new Store();
		Connection connection = getConnection();
		String sql = "select * from store where userID='宋明惠'";
		PreparedStatement pStatement = null;
		ResultSet resultSet=null;
		try {
			pStatement = connection.prepareStatement(sql);
			// pStatement.setString(1, "宋明惠");
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
	}

	/**
	 * 测试 如果存在这个店铺，那么只会有一个
	 */
	@Test
	public void searchByStoreName() {
		Store store = new Store();
		Connection connection = getConnection();
		String sql = "select * from store where storeName=?";
		PreparedStatement pStatement = null;
		ResultSet resultSet = null;
		try {
			pStatement = connection.prepareStatement(sql);
			pStatement.setString(1, "宋明惠的店铺");
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
	}

	/**
	 * 根据店铺id更新店铺名
	 */

	@Test
	public void updateStoreName() {
		Connection connection = getConnection();
		String sql = "update store set storeName=? where storeID=?";
		PreparedStatement pStatement = null;
		try {
			pStatement = connection.prepareStatement(sql);
			pStatement.setString(1, "宋明惠店铺");
			pStatement.setInt(2, 2);
			int resultSet = pStatement.executeUpdate();
			if (resultSet != 0) {
				System.out.println("update storeName success");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		release(connection, pStatement, null);
	}

	/**
	 * 测试 根据店铺id进行更新评分
	 */
	@Test
	public void updateScore() {
		Connection connection = getConnection();
		String sql = "update store set score=? where storeID=?";
		PreparedStatement pStatement = null;
		try {
			pStatement = connection.prepareStatement(sql);
			pStatement.setFloat(1, Float.valueOf((float) 0.3));
			pStatement.setInt(2, 2);
			int resultSet = pStatement.executeUpdate();
			if (resultSet != 0) {
				System.out.println("update score success");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		release(connection, pStatement, null);
	}
	
	@Test
	public void delectByStoreID() {
		Long storeID=(long) 2;
		Connection connection = getConnection();
		String sql = "delete from store where storeID=?";
		PreparedStatement pStatement = null;
		
		try {
			pStatement = connection.prepareStatement(sql);
			pStatement.setLong(1, storeID);
			int resultSet = pStatement.executeUpdate();
			if(resultSet==1)
			{
				System.out.println("delete store success");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		release(connection, pStatement, null);
		
	}
}
