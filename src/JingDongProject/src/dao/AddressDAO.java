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
	
	/**
	 * 根据userID和指定的地址ID修改user的某个地址
	 */
	@Test
	public void updateByUserID() {
		Connection connection=null;
		PreparedStatement pStatement=null;	
		Address address=new Address();
		
		address.setUserID("hyl");//指定用户ID
		address.setAddressID((long)1);//指定该用户的其中一个地址ID
		
		address.setProvince("广东");
		address.setCity("珠海");
		address.setBlock("海淀");
		address.setStreet("XX");
		address.setAddress("XXXXXXX");
		address.setReceiver("壳子");
		address.setPhone("17801122933");
		
		try {
			connection=getConnection();
			String sql="update address set province=?,city=?,block=?,street=?,address=?,receiver=?,phone=? where userID=? and addressID=?";
			pStatement=connection.prepareStatement(sql);
			
			pStatement.setString(1, address.getProvince());
			pStatement.setString(2, address.getCity());
			pStatement.setString(3, address.getBlock());
			pStatement.setString(4, address.getStreet());
			pStatement.setString(5, address.getAddress());
			pStatement.setString(6, address.getReceiver());
			pStatement.setString(7, address.getPhone());
			pStatement.setString(8, address.getUserID());
			pStatement.setLong(9, address.getAddressID());
			
			int rows = pStatement.executeUpdate();
			if(rows>0) {
				System.out.println("update successfully!");
			}
			else {
				System.out.println("update defeat!");
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			release(connection, pStatement, null);
		}
	}
	
	/**
	 * 根据userID和指定的该user的addressID删除某条地址记录
	 */
	@Test
	public void deleteByUserID() {
		Connection connection=null;
		PreparedStatement pStatement=null;	
		
		String userID="hyl";
		Long addressID=(long)1;
		
		try {
			connection=getConnection();
			String sql="delete from address where userID=? and addressID=?";
			pStatement=connection.prepareStatement(sql);
			pStatement.setString(1, userID);
			pStatement.setLong(2, addressID);
			int rows=pStatement.executeUpdate();		
			if(rows>0) {
				System.out.println("delete successfully!");	
			}
			else {
				System.out.println("delete defeat!");
			}
		} catch (Exception e) {		
			e.printStackTrace();		
		} finally {
			release(connection, pStatement, null);
		}
	}
}
