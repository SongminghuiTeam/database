package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Test;

import domain.Comment;

public class CommentDAO extends DaoBase{

	@Test
	public void insert(){
		Connection connection=null;
		PreparedStatement pStatement=null;
		//获取当前时间
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date=new Date();
		String time = df.format(date);
		
		Comment comment=new Comment();
		comment.setCommentTime(time);
		comment.setContent("666666666");
		comment.setScore(4);
		comment.setOrderID((long)1);
		comment.setProductID((long)1);
		
		try {
			connection=getConnection();
			String sql="insert into comment(commentTime,content,score,orderID,productID) values(?,?,?,?,?)";
			pStatement=connection.prepareStatement(sql);
			pStatement.setString(1, comment.getCommentTime());
			pStatement.setString(2, comment.getContent());
			pStatement.setInt(3, comment.getScore());
			pStatement.setLong(4, comment.getOrderID());
			pStatement.setLong(5, comment.getProductID());
			
			int rows = pStatement.executeUpdate();
			if(rows>0) {
				System.out.println("insert successfully!");
			}
			else {
				System.out.println("insert defeat!");
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			release(connection, pStatement, null);
		}
	}
}
