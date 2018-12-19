package dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Test;

import domain.News;

public class NewsDAO extends DaoBase{

	@Test
	public void insert() {
		News news =new News();
		news.setContent("衣服上新了");
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date=new Date();
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
}
