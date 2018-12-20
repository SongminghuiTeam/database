package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import domain.Address;


public class AddressDAO extends DaoBase{

	/**
	 * 插入一条地址记录
	 * @param address
	 */
	public void insert(Address address){
		Connection connection=null;
		PreparedStatement pStatement=null;	
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
	 * 根据userID查出该用户的所有地址信息,并返回list
	 * @param userID
	 */
	public List<Address> searchAddressesByUserID(String userID) {
		Connection connection=null;
		PreparedStatement pStatement=null;
		ResultSet resultset=null;	
		List<Address> addresses=new ArrayList<Address>();
		Address address=new Address();
		address.setUserID(userID);
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
				address.setAddressID(resultset.getLong("addressID"));
				address.setProvince(resultset.getString("province"));
				address.setCity(resultset.getString("city"));
				address.setBlock(resultset.getString("block"));
				address.setStreet(resultset.getString("street"));
				address.setAddress(resultset.getString("address"));
				address.setReceiver(resultset.getString("receiver"));
				address.setPhone(resultset.getString("phone"));
				addresses.add(address);		
				
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
			return addresses;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			release(connection, pStatement, resultset);
		}	
	}
	
	/**
	 * 根据userID和addressID查找某个user的某条地址记录
	 * @param userID
	 * @param addressID
	 * @return
	 */
	public Address searchOneAddress(String userID,Long addressID) {
		Connection connection=null;
		PreparedStatement pStatement=null;
		ResultSet resultset=null;			
		Address address=new Address();
		address.setAddressID(addressID);
		address.setUserID(userID);
		
		try {
			connection=getConnection();
			String sql="select * from address where userID=? and addressID=?";
			pStatement=connection.prepareStatement(sql);
			pStatement.setString(1, userID);
			pStatement.setLong(2, addressID);
			resultset=pStatement.executeQuery();
			while(resultset.next()) {		
				address.setProvince(resultset.getString("province"));
				address.setCity(resultset.getString("city"));
				address.setBlock(resultset.getString("block"));
				address.setStreet(resultset.getString("street"));
				address.setAddress(resultset.getString("address"));
				address.setReceiver(resultset.getString("receiver"));
				address.setPhone(resultset.getString("phone"));
				
				System.out.print(userID+"\t");			
				System.out.print(address.getProvince()+"省\t");
				System.out.print(address.getCity()+"市\t");
				System.out.print(address.getBlock()+"区\t");				
				System.out.print(address.getStreet()+"街道\t");				
				System.out.print(address.getAddress()+"\t");				
				System.out.print(address.getReceiver()+"收\t");	
				System.out.println(address.getPhone());
				break;
			}		
			return address;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			release(connection, pStatement, resultset);
		}	
	}

	/**
	 * 根据传过来的address修改该条地址记录
	 * @param addressOld
	 */
	public void updateAddress(Address address) {
		Connection connection=null;
		PreparedStatement pStatement=null;	
		
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
			e.printStackTrace();
		} finally {
			release(connection, pStatement, null);
		}
	}
	
	/**
	 * 根据userID和指定的该user的addressID删除某条地址记录
	 * @param userID
	 * @param addressID
	 */
	public void deleteAddress(String userID,Long addressID) {
		Connection connection=null;
		PreparedStatement pStatement=null;		
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
	
	
	/**
	 * 根据addressID查询userID
	 * @param addressID
	 * @return
	 */
	public String searchUserIDByAddressID(Long addressID) {
		Connection connection=null;
		PreparedStatement pStatement=null;	
		ResultSet resultset=null;	
		String userID = null;
		
		try {
			connection=getConnection();
			String sql="select userID from address where addressID=?";
			pStatement=connection.prepareStatement(sql);
			pStatement.setLong(1, addressID);
			resultset=pStatement.executeQuery();
			while(resultset.next()) {
				userID = resultset.getString(1);
			}
			
		} catch (Exception e) {		
			e.printStackTrace();		
		} finally {
			release(connection, pStatement, resultset);
		}
		return userID;
	}
	
	
	@Test
	public void test() {
			
		//测试insert
		/*Address address=new Address();
		address.setUserID("hyl");
		address.setProvince("北京");
		address.setCity("北京");
		address.setBlock("海淀");
		address.setStreet("学院路");
		address.setAddress("清华东路35号北京林业大学");
		address.setReceiver("cwj");
		address.setPhone("13121862811");
		insert(address);*/
		
		//测试searchAddressesByUserID
		String userID="hyl";
		searchAddressesByUserID(userID);
		
		//测试searchOneAddress
		/*Long addressID=(long)4;
		String userID="lxk";
		searchOneAddress(userID, addressID);*/
		
		//测试updateAddress
		/*Address address2=searchOneAddress("hyl", (long)8);
		address2.setProvince("北京");
		address2.setCity("海淀");
		updateAddress(address2);
		System.out.println("after update:");
		searchOneAddress("hyl", (long)8);*/
		
		//测试deleteByUserID
		/*String userID="hyl";
		Long addressID=(long)8;
		deleteAddress(userID, addressID);*/
		
		//测试searchUserIDByAddressID
		/*System.out.println(searchUserIDByAddressID((long)4));*/
	}
}
