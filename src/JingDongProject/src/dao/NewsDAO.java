package dao;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Test;

import domain.News;

public class NewsDAO extends DaoBase {

	@Test
	public void insert() {
		News news = new News();
		news.setContent("衣服上新了");
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = new Date();
		String time = df.format(date);
		news.setPublishTime(time);
		news.setStoreID((long) 2);
		news.setVisitVolume((long) 100);
		Connection connection = getConnection();
		String sql = "insert news(storeID,visitVolume,publishTime,content) values(?,?,?,?)";
		PreparedStatement pStatement = null;
		try {
			pStatement = connection.prepareStatement(sql);
			pStatement.setLong(1, news.getStoreID());
			pStatement.setLong(2, news.getVisitVolume());
			pStatement.setString(3, news.getPublishTime());
			pStatement.setString(4, news.getContent());

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
	 * 测试 根据店铺的id进行查询
	 */
	@Test
	public void searchByStoreID() {
		News news = new News();
		Connection connection = getConnection();
		String sql = "select * from news where storeID=?";
		PreparedStatement pStatement = null;
		ResultSet resultSet = null;
		try {
			pStatement = connection.prepareStatement(sql);
			pStatement.setLong(1, 2);
			resultSet = pStatement.executeQuery();
			while (resultSet.next()) {
				news.setContent(resultSet.getString("content"));
				news.setNewsID(resultSet.getLong("newsID"));
				news.setPublishTime(resultSet.getString("publishTime"));
				news.setStoreID(resultSet.getLong("storeID"));
				news.setVisitVolume(resultSet.getLong("visitVolume"));
				System.out.println(news.getNewsID() + " " + news.getStoreID() + " " + news.getContent() + " "
						+ news.getPublishTime() + " " + news.getVisitVolume());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		release(connection, pStatement, resultSet);
	}

	/**
	 * 测试 根据时间倒叙输出
	 */
	@Test
	public void searchAll() {
		News news = new News();
		Connection connection = getConnection();
		String sql = "select * from news order by newsID desc";
		PreparedStatement pStatement = null;
		ResultSet resultSet = null;
		try {
			pStatement = connection.prepareStatement(sql);
			resultSet = pStatement.executeQuery();
			while (resultSet.next()) {
				news.setContent(resultSet.getString("content"));
				news.setNewsID(resultSet.getLong("newsID"));
				news.setPublishTime(resultSet.getString("publishTime"));
				news.setStoreID(resultSet.getLong("storeID"));
				news.setVisitVolume(resultSet.getLong("visitVolume"));
				System.out.println(news.getNewsID() + " " + news.getStoreID() + " " + news.getContent() + " "
						+ news.getPublishTime() + " " + news.getVisitVolume());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		release(connection, pStatement, resultSet);
	}
	
	@Test
	public void deleteByNewsID() {
		
		Long newsID =(long) 3;
		Connection connection = getConnection();
		String sql = "delete from news where newsID=?";
		PreparedStatement pStatement = null;
		
		try {
			pStatement = connection.prepareStatement(sql);
			pStatement.setLong(1, newsID);
			int resultSet = pStatement.executeUpdate();
			if(resultSet==1)
			{
				System.out.println("delete news success");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		release(connection, pStatement, null);
	}
	
}
