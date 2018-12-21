package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import org.junit.Test;

import domain.Product;

public class ProductDAO extends DaoBase {
	public void insert(Product product) {
		Connection conn = null;
		PreparedStatement pStatement = null;
		ResultSet rs = null;
		
		try {
			conn = getConnection();
			String sql = "";
			if(product.getJdBean() == 0) {
				sql = "insert into product(productName,weight,visitVolume,model,description,categoryID,price) values(?,?,?,?,?,?,?)";
				pStatement = conn.prepareStatement(sql);
				pStatement.setString(1, product.getProductName());
				pStatement.setFloat(2, product.getWeight());
				pStatement.setLong(3, product.getVisitVolume());
				pStatement.setString(4, product.getModel());
				pStatement.setString(5, product.getDescription());
				pStatement.setLong(6, product.getCategoryID());
				pStatement.setFloat(7, product.getPrice());
			}
			else {
				sql = "insert into product(productName,weight,visitVolume,model,description,jdBean,categoryID,price) values(?,?,?,?,?,?,?,?)";
				pStatement = conn.prepareStatement(sql);
				pStatement.setString(1, product.getProductName());
				pStatement.setFloat(2, product.getWeight());
				pStatement.setLong(3, product.getVisitVolume());
				pStatement.setString(4, product.getModel());
				pStatement.setString(5, product.getDescription());
				pStatement.setLong(6, product.getJdBean());
				pStatement.setLong(7, product.getCategoryID());
				pStatement.setFloat(8, product.getPrice());
			}
			
			int row =  pStatement.executeUpdate();
			
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
	
	public void update(Long productID,Product product) {
		Connection conn = null;
		PreparedStatement pStatement = null;
		ResultSet rs = null;
		
		try {
			conn = getConnection();
			String sql = "select * from product where productID=?";
			pStatement = conn.prepareStatement(sql);
			pStatement.setLong(1, productID);
			
			rs = pStatement.executeQuery();
			if(rs.next()) {
				sql = "update product set productName=?, weight=?, visitVolume=?, model=?, description=?, jdBean=?, price=? , categoryID=? where productID=?";
				pStatement = conn.prepareStatement(sql);
				pStatement.setString(1, product.getProductName());
				pStatement.setFloat(2, product.getWeight());
				pStatement.setLong(3, product.getVisitVolume());
				pStatement.setString(4, product.getModel());
				pStatement.setString(5, product.getDescription());
				pStatement.setLong(6,product.getJdBean());
				pStatement.setFloat(7, product.getPrice());
				pStatement.setLong(8, product.getCategoryID());
				pStatement.setLong(9, productID);
				
				int row = pStatement.executeUpdate();
				
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
	}
	
	@Test
	public void Test() {
		Product newProduct = new Product("macbookPro", (float)1.7 , "13英寸 银色", "Apple/苹果 macbookPro 13英寸 双核Inter core i5 处理器 8G 银色 2560*1600 原封国行", (long)23, (float)10888);
		Product product = new Product("", (float)0.01, "环球影城神奇动物在哪里电影正版周边", "神奇动物在哪里", (long)30 , (float)50);
		//insert(newProduct);
		
		ArrayList<Product> products1 = search("iPhone X");
		for(int i = 0;i < products1.size();i++) {
			System.out.println("productName:" + products1.get(i).getProductName() + "   price:" + products1.get(i).getPrice() + "   weight:" +
					products1.get(i).getWeight());
			System.out.println("model:" + products1.get(i).getWeight());
			System.out.println("description:" + products1.get(i).getDescription());
			System.out.print("jdBean:" + products1.get(i).getJdBean() + "   visitVolume:" + products1.get(i).getVisitVolume());
			if(!products1.get(i).getStatus()) {
				System.out.println("   缺货");
			}
			System.out.print("\n");
		}
		ArrayList<Product> products2 = search("macbook");
		for(int i = 0;i < products2.size();i++) {
			System.out.println("productName:" + products2.get(i).getProductName() + "   price:" + products2.get(i).getPrice() + "   weight:" +
					products2.get(i).getWeight());
			System.out.println("model:" + products2.get(i).getWeight());
			System.out.println("description:" + products2.get(i).getDescription());
			System.out.println("jdBean:" + products2.get(i).getJdBean() + "   visitVolume:" + products2.get(i).getVisitVolume());
			if(!products2.get(i).getStatus()) {
				System.out.println("   缺货");
			}
			System.out.print("\n");
		}
		ArrayList<Product> products3 = search("iPhone 8");
		for(int i = 0;i < products3.size();i++) {
			System.out.println("productName:" + products3.get(i).getProductName() + "   price:" + products3.get(i).getPrice() + "   weight:" +
					products1.get(i).getWeight());
			System.out.println("model:" + products3.get(i).getWeight());
			System.out.println("description:" + products3.get(i).getDescription());
			System.out.println("jdBean:" + products3.get(i).getJdBean() + "   visitVolume:" + products3.get(i).getVisitVolume());
			if(!products3.get(i).getStatus()) {
				System.out.println("   缺货");
			}
			System.out.print("\n");
		}
		
		Long productID = (long)5;
		Product product2 = new Product("iPhone 8", (float)0.7 , "银色", "Apple/苹果 iPhone 8 256G 原封国行", (long)22, (float)5000);
		//update(productID, product2);
	}
}
