package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import org.junit.Test;

import domain.Product;

public class ProductDAO extends DaoBase {
	public int insert(Product product) {
		Connection conn = null;
		PreparedStatement pStatement = null;
		ResultSet rs = null;
		
		int row = 0;
		
		try {
			conn = getConnection();
			String sql = "";
			if(product.getJdBean() == 0) {
				if(product.getWeight() == 0.0) {
					sql = "insert into product(productName,visitVolume,model,description,categoryID,price,storeID) values(?,?,?,?,?,?,?)";
					pStatement = conn.prepareStatement(sql);
					pStatement.setString(1, product.getProductName());
					pStatement.setLong(2, product.getVisitVolume());
					pStatement.setString(3, product.getModel());
					pStatement.setString(4, product.getDescription());
					pStatement.setLong(5, product.getCategoryID());
					pStatement.setFloat(6, product.getPrice());
					pStatement.setLong(7, product.getStoreID());
				}
				else {
					sql = "insert into product(productName,weight,visitVolume,model,description,categoryID,price,storeID) values(?,?,?,?,?,?,?,?)";
					pStatement = conn.prepareStatement(sql);
					pStatement.setString(1, product.getProductName());
					pStatement.setFloat(2, product.getWeight());
					pStatement.setLong(3, product.getVisitVolume());
					pStatement.setString(4, product.getModel());
					pStatement.setString(5, product.getDescription());
					pStatement.setLong(6, product.getCategoryID());
					pStatement.setFloat(7, product.getPrice());
					pStatement.setLong(8, product.getStoreID());	
				}
			}
			else {
				if(product.getWeight() == 0.0) {
					sql = "insert into product(productName,visitVolume,model,description,jdBean,categoryID,price,storeID) values(?,?,?,?,?,?,?,?)";
					pStatement = conn.prepareStatement(sql);
					pStatement.setString(1, product.getProductName());
					pStatement.setLong(2, product.getVisitVolume());
					pStatement.setString(3, product.getModel());
					pStatement.setString(4, product.getDescription());
					pStatement.setLong(5, product.getJdBean());
					pStatement.setLong(6, product.getCategoryID());
					pStatement.setFloat(7, product.getPrice());
					pStatement.setLong(8, product.getStoreID());
				}
				else {
					sql = "insert into product(productName,weight,visitVolume,model,description,jdBean,categoryID,price,storeID) values(?,?,?,?,?,?,?,?,?)";
					pStatement = conn.prepareStatement(sql);
					pStatement.setString(1, product.getProductName());
					pStatement.setFloat(2, product.getWeight());
					pStatement.setLong(3, product.getVisitVolume());
					pStatement.setString(4, product.getModel());
					pStatement.setString(5, product.getDescription());
					pStatement.setLong(6, product.getJdBean());
					pStatement.setLong(7, product.getCategoryID());
					pStatement.setFloat(8, product.getPrice());
					pStatement.setLong(9, product.getStoreID());	
				}
			}
			
			row =  pStatement.executeUpdate();
			
			if(row > 0) {
				System.out.println("insert successfully");
			}
			else {
				System.out.println("insert failed");
			}
		}catch(Exception sqlException) {
			sqlException.printStackTrace();
		}finally {
			try {
				release(conn, pStatement, rs);
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		return row;
	}
	
	public ArrayList<Product> search(String productName) {
		Connection conn = null;
		PreparedStatement pStatement = null;
		ResultSet rs = null;
		
		ArrayList<Product> products = new ArrayList<Product>();
		
		try {
			conn = getConnection();
			String sql = "select * from product where productName like '%" + productName + "%'";
			pStatement = conn.prepareStatement(sql);
			
			rs = pStatement.executeQuery();
			if(rs.next()) {
				System.out.println("search successfully!" + "\n");
				Product product = new Product();
				while(true) {
					product.setProductName(rs.getString("productName"));
					product.setPrice(rs.getFloat("price"));
					product.setWeight(rs.getFloat("weight"));
					product.setModel(rs.getString("model"));
					product.setDescription(rs.getString("description"));
					product.setJdBean(rs.getLong("jdBean"));
					product.setVisitVolume(rs.getLong("visitVolume"));
					product.setStatus(rs.getBoolean("status"));
					product.setStoreID(rs.getLong("storeID"));
					
					products.add(product);
		
					if(!rs.next()) {
						break;
					}
				}
			}
			else {
				System.out.println("search failed");
			}
		}catch(Exception sqlException) {
			sqlException.printStackTrace();
		}finally {
			try {
				release(conn, pStatement, rs);
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		return products;
	}
	
	public int update(Long productID,Product product) {
		Connection conn = null;
		PreparedStatement pStatement = null;
		ResultSet rs = null;
		
		int row = 0;
		
		try {
			conn = getConnection();
			String sql = "select * from product where productID=?";
			pStatement = conn.prepareStatement(sql);
			pStatement.setLong(1, productID);
			
			rs = pStatement.executeQuery();
			if(rs.next()) {
				sql = "update product set productName=?, weight=?, visitVolume=?, model=?, description=?, jdBean=?, price=? , categoryID=?, storeID=? where productID=?";
				pStatement = conn.prepareStatement(sql);
				pStatement.setString(1, product.getProductName());
				pStatement.setFloat(2, product.getWeight());
				pStatement.setLong(3, product.getVisitVolume());
				pStatement.setString(4, product.getModel());
				pStatement.setString(5, product.getDescription());
				pStatement.setLong(6,product.getJdBean());
				pStatement.setFloat(7, product.getPrice());
				pStatement.setLong(8, product.getCategoryID());
				pStatement.setLong(9, product.getStoreID());
				pStatement.setLong(10, productID);
				
				row = pStatement.executeUpdate();
				
				if(row > 0) {
					System.out.println("update successfully!");
				}
				else {
					System.out.println("update failed!");
				}
			}
			else {
				System.out.println("no such product");
			}
		}catch(Exception sqlException) {
			sqlException.printStackTrace();
		}finally {
			try {
				release(conn, pStatement, rs);
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		return row;
	}
	
	@Test
	public void Test() {
		Product newProduct = new Product("iPhone X" , "13英寸 银色", 
				"Apple/苹果 macbookPro 13英寸 双核Inter core i5 处理器 8G 银色 2560*1600 原封国行", (long)39, (long)100, (float)10888,(long) 1);
		insert(newProduct);
		
		/*Product product = new Product("玩偶", (float)0.01, "环球影城神奇动物在哪里电影正版周边", "神奇动物在哪里", (long)28 , (float)50, (long)2);
		insert(product);*/
		
		/*ArrayList<Product> products1 = search("iPhone X");
		for(int i = 0;i < products1.size();i++) {
			System.out.println("productName:" + products1.get(i).getProductName() + "   price:" + products1.get(i).getPrice() + "   weight:" +
					products1.get(i).getWeight());
			System.out.println("model:" + products1.get(i).getWeight());
			System.out.println("description:" + products1.get(i).getDescription());
			System.out.print("jdBean:" + products1.get(i).getJdBean() + "   visitVolume:" + products1.get(i).getVisitVolume());
			System.out.println("storeID:" + products1.get(i).getStoreID());
			if(!products1.get(i).getStatus()) {
				System.out.println("   缺货");
			}
			System.out.print("\n");
		}*/
		
		/*Long productID = (long)12;
		Product product2 = new Product("嗅嗅玩偶", (float)0.01, 
				"环球影城神奇动物在哪里电影正版周边!!!!!!!!!", "神奇动物在哪里", (long)26 , (long)10, (float)50, (long)1);
		update(productID, product2);*/
	}
}
