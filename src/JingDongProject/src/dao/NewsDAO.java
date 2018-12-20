package dao;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.junit.Test;

import domain.News;

public class NewsDAO extends DaoBase {

	@Test
	public void Test() {
		News news = new News();
		news.setContent("裤子上新了");
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = new Date();
		String time = df.format(date);
		news.setPublishTime(time);
		news.setStoreID((long) 2);
		news.setVisitVolume((long) 100);
		
		Long storeID=(long) 2;
		String content="新";
		Long newsID=(long) 3;
		
		//insert(news);
		//searchAll();
		//searchByStoreID(storeID);
		//searchBySomeNewsName(content);
		//deleteByNewsID(newsID);
	}

	public int insert(News news) {
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
		int rSet = 0;
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
	 * 测试 根据店铺的id进行查询
	 */
	public ArrayList<News> searchByStoreID(Long storeID) {
		ArrayList<News> newslist = new ArrayList<News>();
		News news = new News();
		Connection connection = getConnection();
		String sql = "select * from news where storeID=?";
		PreparedStatement pStatement = null;
		ResultSet resultSet = null;
		try {
			pStatement = connection.prepareStatement(sql);
			pStatement.setLong(1, storeID);
			resultSet = pStatement.executeQuery();
			while (resultSet.next()) {
				news.setContent(resultSet.getString("content"));
				news.setNewsID(resultSet.getLong("newsID"));
				news.setPublishTime(resultSet.getString("publishTime"));
				news.setStoreID(resultSet.getLong("storeID"));
				news.setVisitVolume(resultSet.getLong("visitVolume"));
				newslist.add(news);
				System.out.println(news.getNewsID() + " " + news.getStoreID() + " " + news.getContent() + " "
						+ news.getPublishTime() + " " + news.getVisitVolume());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		release(connection, pStatement, resultSet);
		return newslist;
	}

	/**
	 * 测试 根据时间倒叙输出
	 */
	public ArrayList<News> searchAll() {
		ArrayList<News> newslist = new ArrayList<News>();
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
				newslist.add(news);
				System.out.println(news.getNewsID() + " " + news.getStoreID() + " " + news.getContent() + " "
						+ news.getPublishTime() + " " + news.getVisitVolume());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		release(connection, pStatement, resultSet);
		return newslist;
	}

	public int deleteByNewsID(Long newsID) {

		Connection connection = getConnection();
		String sql = "delete from news where newsID=?";
		PreparedStatement pStatement = null;
		int resultSet = 0;
		try {
			pStatement = connection.prepareStatement(sql);
			pStatement.setLong(1, newsID);
			resultSet = pStatement.executeUpdate();
			if (resultSet == 1) {
				System.out.println("delete news success");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		release(connection, pStatement, null);
		return resultSet;
	}

	/**
	 * 测试 根据 newsName进行模糊查询
	 */
	public ArrayList<News> searchBySomeNewsName(String content) {
		ArrayList<News> newslist = new ArrayList<News>();
		News news = new News();
		Connection connection = getConnection();
		String sql = "select * from news where content like ?";
		PreparedStatement pStatement = null;
		ResultSet resultSet = null;
		try {
			pStatement = connection.prepareStatement(sql);
			pStatement.setString(1, "%" + content + "%");
			resultSet = pStatement.executeQuery();
			while (resultSet.next()) {
				news.setContent(resultSet.getString("content"));
				news.setNewsID(resultSet.getLong("newsID"));
				news.setPublishTime(resultSet.getString("publishTime"));
				news.setStoreID(resultSet.getLong("storeID"));
				news.setVisitVolume(resultSet.getLong("visitVolume"));
				newslist.add(news);
				System.out.println(news.getNewsID() + " " + news.getStoreID() + " " + news.getContent() + " "
						+ news.getPublishTime() + " " + news.getVisitVolume());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		release(connection, pStatement, resultSet);
		return newslist;
	}

}
