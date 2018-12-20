package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.Test;

import domain.Topic;

public class TopicDAO extends DaoBase {

	@Test
	public void insert() {
		Topic topic = new Topic();
		topic.setTopicName("冬季女装");
		Connection connection = getConnection();
		PreparedStatement pStatement = null;
		String sql = "insert topic(topicName) values(?)";
		try {
			pStatement = connection.prepareStatement(sql);
			pStatement.setString(1, topic.getTopicName());
			int resultSet = pStatement.executeUpdate();
			if (resultSet == 1) {
				System.out.println("insert topic success");
			}
		} catch (SQLException e) {

			e.printStackTrace();
		}
		release(connection, pStatement, null);
	}

	/**
	 * 测试 根据主题id获取主题 应该只有一条记录
	 */
	@Test
	public void searchByTopicID() {
		Topic topic = new Topic();
		Long topicID = (long) 1;
		Connection connection = getConnection();
		String sql = "select * from topic where topicID=?";
		PreparedStatement pStatement = null;
		ResultSet resultSet = null;
		try {
			pStatement = connection.prepareStatement(sql);
			pStatement.setLong(1, topicID);
			resultSet = pStatement.executeQuery();
			while (resultSet.next()) {
				topic.setTopicID(resultSet.getLong("topicID"));
				topic.setTopicName(resultSet.getString("topicName"));
				System.out.println(topic.getTopicID() + "   " + topic.getTopicName());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		release(connection, pStatement, resultSet);
	}

	/**
	 * 测试 根据话题名进行精确查询
	 */
	@Test
	public void searchByTopicName() {
		Topic topic = new Topic();
		String topicName = "冬季新款";
		Connection connection = getConnection();
		String sql = "select * from topic where topicName=?";
		PreparedStatement pStatement = null;
		ResultSet resultSet = null;
		try {
			pStatement = connection.prepareStatement(sql);
			pStatement.setString(1, topicName);
			resultSet = pStatement.executeQuery();
			while (resultSet.next()) {
				topic.setTopicID(resultSet.getLong("topicID"));
				topic.setTopicName(resultSet.getString("topicName"));
				System.out.println(topic.getTopicID() + "   " + topic.getTopicName());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		release(connection, pStatement, resultSet);
	}

	/**
	 * 测试根据话题名进行模糊查询
	 */
	@Test
	public void searchBySomeTopicName() {
		Topic topic = new Topic();
		String topicName = "冬";
		Connection connection = getConnection();
		String sql = "select * from topic where topicName like ?";
		PreparedStatement pStatement = null;
		ResultSet resultSet = null;
		try {
			pStatement = connection.prepareStatement(sql);
			pStatement.setString(1, "%" + topicName + "%");
			resultSet = pStatement.executeQuery();
			while (resultSet.next()) {
				topic.setTopicID(resultSet.getLong("topicID"));
				topic.setTopicName(resultSet.getString("topicName"));
				System.out.println(topic.getTopicID() + "   " + topic.getTopicName());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		release(connection, pStatement, resultSet);
	}
	@Test
	public void delectByTopicID() {
		Long topicID=(long) 2;
		Connection connection = getConnection();
		String sql = "delete from topic where topicID=?";
		PreparedStatement pStatement = null;
		
		try {
			pStatement = connection.prepareStatement(sql);
			pStatement.setLong(1, topicID);
			int resultSet = pStatement.executeUpdate();
			if(resultSet==1)
			{
				System.out.println("delete topic success");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		release(connection, pStatement, null);
		
	}
}
