package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.junit.Test;

import domain.News;
import domain.Topic;

public class TopicNewsDAO extends DaoBase {

	/**
	 * 测试 topicid与newsid做联合主键，所以不会出现相同的记录 如 topicid=1 newsid=1只能出现一次
	 * topicid与newsid又是外键，所以在topic表和news表中不存在的数值是不对的
	 */
	@Test
	public void Test() {
		Topic topic = new Topic();
		News news = new News();
		topic.setTopicID((long) 2);
		news.setNewsID((long) 2);

		//insert(topic, news);
		//searchNewsIDByTopicID(topic);
		searchTopicIDBYNewsID(news);
	}

	public int insert(Topic topic, News news) {
		Connection connection = getConnection();
		PreparedStatement pStatement = null;
		String sql = "insert topicnews(topicID,newsID) values(?,?)";
		int resultSet = 0;
		try {
			pStatement = connection.prepareStatement(sql);
			pStatement.setLong(1, topic.getTopicID());
			pStatement.setLong(2, news.getNewsID());
			resultSet = pStatement.executeUpdate();
			if (resultSet == 1)
				System.out.println("insert topicnews success");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		release(connection, pStatement, null);
		return resultSet;
	}

	/**
	 * 根据topicid查找newsid ————在搜寻topic时使用
	 */
	public ArrayList<Long> searchNewsIDByTopicID(Topic topic) {
		ArrayList<Long> newsids = new ArrayList<Long>();
		News news = new News();
		topic.setTopicID((long) 1);
		Connection connection = getConnection();
		PreparedStatement pStatement = null;
		ResultSet resultSet = null;
		String sql = "select newsid from topicnews where topicid=?";
		try {
			pStatement = connection.prepareStatement(sql);
			pStatement.setLong(1, topic.getTopicID());
			resultSet = pStatement.executeQuery();
			while (resultSet.next()) {
				news.setNewsID(resultSet.getLong("newsid"));
				newsids.add(news.getNewsID());
				System.out.println(news.getNewsID());
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		release(connection, pStatement, resultSet);
		return newsids;
	}

	/**
	 * 根据newsid 查找topicid
	 */
	public ArrayList<Long> searchTopicIDBYNewsID(News news) {
		ArrayList<Long> topicids = new ArrayList<Long>();
		Topic topic = new Topic();
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
				topicids.add(topic.getTopicID());
				System.out.println(topic.getTopicID());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		release(connection, pStatement, resultSet);
		return topicids;
	}

}
