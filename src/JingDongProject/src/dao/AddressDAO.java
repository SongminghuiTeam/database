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
	
	/**
	 * 根据userID查出该用户的所有地址信息
	 */
	@Test
	public void queryByUserID() {
		Connection connection=null;
		PreparedStatement pStatement=null;
		ResultSet resultset=null;	
		
		Address address=new Address();
		String userID="hyl";
		try {
			connection=getConnection();
			String sql="select * from address where userID=?";
			pStatement=connection.prepareStatement(sql);
			pStatement.setString(1, userID);
			resultset=pStatement.executeQuery();
			
			System.out.println(userID+"'s all address:");
			int i=0;
			while(resultset.next()) {		
				i++;
				address.setProvince(resultset.getString("province"));
				address.setCity(resultset.getString("city"));
				address.setBlock(resultset.getString("block"));
				address.setStreet(resultset.getString("street"));
				address.setAddress(resultset.getString("address"));
				address.setReceiver(resultset.getString("receiver"));
				address.setPhone(resultset.getString("phone"));
						
				System.out.print(i+"\t");
				System.out.print(userID+"\t");
				
				System.out.print(address.getProvince()+"省\t");
				System.out.print(address.getCity()+"市\t");
				System.out.print(address.getBlock()+"区\t");				
				System.out.print(address.getStreet()+"街道\t");				
				System.out.print(address.getAddress()+"\t");				
				System.out.print(address.getReceiver()+"收\t");	
				System.out.println(address.getPhone());
			}				
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			release(connection, pStatement, resultset);
		}	
	}
	
	
}
