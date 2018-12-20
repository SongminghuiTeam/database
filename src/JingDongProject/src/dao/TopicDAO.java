package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.junit.Test;

import domain.Topic;

public class TopicDAO extends DaoBase {

	@Test
	public void Test() {
		Topic topic = new Topic();
		topic.setTopicName("冬季女装");
		String topicName = "冬季女装";
		//插入话题测试
		//insert(topic);
		//根据话题名查找话题
		topic=searchByTopicName(topicName);
		//根据话题id查找话题
		//searchByTopicID(topic.getTopicID());
		//模糊查找
		//searchBySomeTopicName("冬");
		//删除话题
		//delectByTopicID(topic.getTopicID());
		
	}

	public int insert(Topic topic) {

		Connection connection = getConnection();
		PreparedStatement pStatement = null;
		String sql = "insert topic(topicName) values(?)";
		int resultSet = 0;
		try {
			pStatement = connection.prepareStatement(sql);
			pStatement.setString(1, topic.getTopicName());
			resultSet = pStatement.executeUpdate();
			if (resultSet == 1) {
				System.out.println("insert topic success");
			}
		} catch (SQLException e) {

			e.printStackTrace();
		}
		release(connection, pStatement, null);
		return resultSet;
	}

	/**
	 * 测试 根据主题id获取主题 应该只有一条记录
	 */
	public Topic searchByTopicID(Long topicID) {
		Topic topic = new Topic();
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
		return topic;
	}

	/**
	 * 测试 根据话题名进行精确查询
	 */
	public Topic searchByTopicName(String topicName) {
		Topic topic = new Topic();
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
		return topic;
	}

	/**
	 * 测试根据话题名进行模糊查询
	 */
	public ArrayList<Topic> searchBySomeTopicName(String topicName) {
		ArrayList<Topic> topics=new ArrayList<Topic>();
		Topic topic = new Topic();
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
				topics.add(topic);
				System.out.println(topic.getTopicID() + "   " + topic.getTopicName());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		release(connection, pStatement, resultSet);
		return topics;
	}

	public int delectByTopicID(Long topicID) {
		Connection connection = getConnection();
		String sql = "delete from topic where topicID=?";
		PreparedStatement pStatement = null;
		int resultSet = 0;
		try {
			pStatement = connection.prepareStatement(sql);
			pStatement.setLong(1, topicID);
			resultSet = pStatement.executeUpdate();
			if (resultSet == 1) {
				System.out.println("delete topic success");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		release(connection, pStatement, null);
		return resultSet;
	}
}
