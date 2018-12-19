package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public interface Dao {
	public Connection getConnection();
	public void release(Connection connection, PreparedStatement statement, ResultSet resultSet);
	public void release(Connection connection, PreparedStatement statement);
}
