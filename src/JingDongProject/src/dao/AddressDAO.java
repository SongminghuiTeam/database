package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.junit.Test;

import domain.Address;


public class AddressDAO extends DaoBase{

	@Test
	public void insert(){
		Connection connection=null;
		PreparedStatement pStatement=null;
		
		Address address=new Address();
		address.setUserID("hyl");
		address.setProvince("北京");
		address.setCity("北京");
		address.setBlock("海淀");
		address.setStreet("学院路");
		address.setAddress("清华东路35号北京林业大学");
		address.setReceiver("cwj");
		address.setPhone("13121862811");
		
		try {
			connection=getConnection();
			String sql="insert into address(userID,province,city,block,street,address,receiver,phone) values(?,?,?,?,?,?,?,?)";
			pStatement=connection.prepareStatement(sql);
			pStatement.setString(1, address.getUserID());
			pStatement.setString(2, address.getProvince());
			pStatement.setString(3, address.getCity());
			pStatement.setString(4, address.getBlock());
			pStatement.setString(5, address.getStreet());
			pStatement.setString(6, address.getAddress());
			pStatement.setString(7, address.getReceiver());
			pStatement.setString(8, address.getPhone());
			
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
