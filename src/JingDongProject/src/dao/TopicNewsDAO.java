package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.Test;

import domain.News;
import domain.Topic;

public class TopicNewsDAO extends DaoBase {

	/**
	 * 测试 topicid与newsid做联合主键，所以不会出现相同的记录 如 topicid=1 newsid=1只能出现一次
	 * topicid与newsid又是外键，所以在topic表和news表中不存在的数值是不对的
	 */
	@Test
	public void insert() {
		Topic topic = new Topic();
		News news = new News();
		topic.setTopicID((long) 2);
		news.setNewsID((long) 2);
		Connection connection = getConnection();
		PreparedStatement pStatement = null;
		String sql = "insert topicnews(topicID,newsID) values(?,?)";
		try {
			pStatement = connection.prepareStatement(sql);
			pStatement.setLong(1, topic.getTopicID());
			pStatement.setLong(2, news.getNewsID());
			int resultSet = pStatement.executeUpdate();
			if (resultSet == 1)
				System.out.println("insert topicnews success");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		release(connection, pStatement, null);
	}

	/**
	 * 根据topicid查找newsid ————在搜寻topic时使用
	 */
	@Test
	public void searchNewsIDByTopicID() {
		Topic topic = new Topic();
		News news = new News();
		topic.setTopicID((long) 1);
		Connection connection = getConnection();
		PreparedStatement pStatement = null;
		ResultSet resultSet = null;
		String sql = "select newsid from topicnews where topicid=?";
		try {
			pStatement = connection.prepareStatement(sql);
			resultSet = pStatement.executeQuery();
			while (resultSet.next()) {
				news.setNewsID(resultSet.getLong("newsid"));
				System.out.println(news.getNewsID());
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		release(connection, pStatement, resultSet);
	}

	/**
	 * 根据newsid 查找topicid
	 */
	@Test
	public void searchTopicIDBYNewsID() {
		Topic topic = new Topic();
		News news = new News();
		news.setNewsID((long) 2);
		Connection connection = getConnection();
		PreparedStatement pStatement = null;
		ResultSet resultSet = null;
		String sql = "select topicID from topicnews where newsID=?";
		try {
			pStatement = connection.prepareStatement(sql);
			pStatement.setLong(1, news.getNewsID());
			resultSet = pStatement.executeQuery();
			while (resultSet.next()) {
				topic.setTopicID(resultSet.getLong("topicid"));
				System.out.println(topic.getTopicID());
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		release(connection, pStatement, resultSet);
	}

}
