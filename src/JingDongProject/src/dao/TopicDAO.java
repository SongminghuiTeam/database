package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.junit.Test;

import domain.Topic;

public class TopicDAO extends DaoBase {

	@Test
	public void insert() {
		Topic topic = new Topic();
		topic.setTopicName("冬季新款");
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
}
