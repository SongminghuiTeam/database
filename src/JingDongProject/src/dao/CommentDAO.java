package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Test;

import domain.Comment;

public class CommentDAO extends DaoBase{

	/**
	 * 添加评论
	 * @param comment
	 */
	public int insert(Comment comment){
		Connection connection=null;
		PreparedStatement pStatement=null;	
		
		int rows = 0;
		try {
			connection=getConnection();
			String sql="insert into comment(commentTime,content,score,orderID,productID) values(?,?,?,?,?)";
			pStatement=connection.prepareStatement(sql);
			pStatement.setString(1, comment.getCommentTime());
			pStatement.setString(2, comment.getContent());
			pStatement.setInt(3, comment.getScore());
			pStatement.setLong(4, comment.getOrderID());
			pStatement.setLong(5, comment.getProductID());		
			rows = pStatement.executeUpdate();
			if(rows>0) {
				System.out.println("insert successfully!");
			}
			else {
				System.out.println("insert defeat!");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			release(connection, pStatement, null);
		}
		
		return rows;
	}
	
	
	/**
	 * 按照产品ID查找某个产品的所有评论
	 * @param productID
	 */
	public List<Comment> searchByProductID(Long productID) {
		Connection connection=null;
		PreparedStatement pStatement=null;
		ResultSet resultset=null;	
		List<Comment> comments=new ArrayList<Comment>();
		Comment comment=new Comment();
		comment.setProductID(productID);
		try {
			connection=getConnection();
			String sql="select * from comment where productID=? and ispassed=?";
			pStatement=connection.prepareStatement(sql);
			pStatement.setLong(1, productID);
			pStatement.setString(2, "Y");
			resultset=pStatement.executeQuery();
			
			System.out.println("product("+productID+")'s all comments:");
			while(resultset.next()) {				
				comment.setCommentID(resultset.getLong(1));
				comment.setCommentTime(resultset.getString(2));
				comment.setContent(resultset.getString(3));
				comment.setScore(resultset.getInt(4));
				comment.setOrderID(resultset.getLong("orderID"));
				
				comments.add(comment);
				//根据该条记录中得orderID在order表中找到userID
				String sql2="select * from `order` where orderID=?";
				pStatement=connection.prepareStatement(sql2);				
				pStatement.setLong(1, resultset.getLong("orderID"));
				ResultSet resultset2=pStatement.executeQuery();
				String userID=null;
				while(resultset2.next()) {
					userID=resultset2.getString("userID");
					break;
				}				
				System.out.print(comment.getCommentID()+"\t");
				System.out.print(userID+"\t");
				System.out.print(comment.getCommentTime()+"\t");
				System.out.print(comment.getContent()+"\t");
				System.out.println(comment.getScore()+"星");				
			}		
			return comments;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			release(connection, pStatement, resultset);
		}	
	}
	
	
	@Test
	public void test() {
		//测试 insert
		/*Comment comment=new Comment();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date=new Date();
		String time = df.format(date);			
		comment.setCommentTime(time);
		comment.setContent("嗯，商品很不错，给个赞！");
		comment.setScore(5);
		comment.setOrderID((long)2);
		comment.setProductID((long)16);
		insert(comment);*/
		
		//测试searchByProductID
		Long productID=(long)15;
		searchByProductID(productID);
	}
}
