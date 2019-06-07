package com.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model.TibetHouseModel;


public class TibetHouseDao {

	String url ="jdbc:mysql://mudrahouse.com/thetibethouse";
//	String url ="jdbc:mysql://192.168.1.253/thetibethouse";
//    String url ="jdbc:mysql://192.168.1.253/mudrahouse";

    //String url ="jdbc:mysql://mudrahouse.com/mudrahouse";
    
	
	String user ="shopizer";
	String password ="SalesManager123!";
	
	public List<TibetHouseModel> getData() throws SQLException{
	String sql = "SELECT p.post_title as title,s.meta_value as SKU,cp.meta_value*100 as costprice FROM wp_posts as p LEFT JOIN wp_postmeta as s on s.post_id=p.ID and s.meta_key='_sku' left join wp_postmeta as cp on cp.post_id=p.ID and cp.meta_key='_price'  WHERE p.post_status='publish' and p.post_author='3' and p.post_type='product' limit 10";
//		
		//  String sql = "SELECT p.post_title as title,s.meta_value as SKU,cp.meta_value*100 as costprice FROM wp_posts as p LEFT JOIN wp_postmeta as s on s.post_id=p.ID and s.meta_key='_sku' left join wp_postmeta as cp on cp.post_id=p.ID and cp.meta_key='_price'  WHERE p.post_status='publish' and p.post_author='1' and p.post_type='product' limit 10";
		
		
		List<TibetHouseModel> list = new ArrayList<TibetHouseModel>();
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection(url, user, password);
			PreparedStatement st = con.prepareStatement(sql);
			ResultSet rs = st.executeQuery();
			while(rs.next()) {
				TibetHouseModel m = new TibetHouseModel();
				m.setTitle(rs.getString("title"));
				m.setSku(rs.getString("SKU"));
				m.setCostprice(rs.getFloat("costprice"));
				list.add(m);
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	public int getInventoryCount() throws SQLException {
	String sql = "SELECT count(post_author) as count FROM `wp_posts` WHERE `post_author`=3 and post_status='publish'";
//		
		//String sql = "SELECT count(post_author) as count FROM `wp_posts` WHERE `post_author`=1 and post_status='publish'";
		int totalcount = 0;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection(url, user, password);
			PreparedStatement st = con.prepareStatement(sql);
			ResultSet rs = st.executeQuery();
			while(rs.next()) {
				totalcount = rs.getInt("count");
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return totalcount;
	}
	public long getTotalCostPrice() throws SQLException {
		String sql = "SELECT sum(cp.meta_value)*50 as costprice FROM wp_posts as p LEFT JOIN wp_postmeta as s on s.post_id=p.ID and s.meta_key='_sku' left join wp_postmeta as cp on cp.post_id=p.ID and cp.meta_key='_price' WHERE p.post_status='publish' and p.post_author='3' and p.post_type='product'";
//		
		//  String sql = "SELECT sum(cp.meta_value)*50 as costprice FROM wp_posts as p LEFT JOIN wp_postmeta as s on s.post_id=p.ID and s.meta_key='_sku' left join wp_postmeta as cp on cp.post_id=p.ID and cp.meta_key='_price' WHERE p.post_status='publish' and p.post_author='1' and p.post_type='product'";
		long totalCostPrice = 0;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection(url, user, password);
			PreparedStatement st = con.prepareStatement(sql);
			ResultSet rs = st.executeQuery();
			while(rs.next()) {
				totalCostPrice = rs.getLong("costprice");
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return totalCostPrice;	
	}
	public long getTotalSellingPrice() throws SQLException {
	String sql = "SELECT sum(cp.meta_value) as sellingprice FROM wp_posts as p LEFT JOIN wp_postmeta as s on s.post_id=p.ID and s.meta_key='_sku' left join wp_postmeta as cp on cp.post_id=p.ID and cp.meta_key='_price' WHERE p.post_status='publish' and p.post_author='3' and p.post_type='product'";
//		
	//	String sql = "SELECT sum(cp.meta_value) as sellingprice FROM wp_posts as p LEFT JOIN wp_postmeta as s on s.post_id=p.ID and s.meta_key='_sku' left join wp_postmeta as cp on cp.post_id=p.ID and cp.meta_key='_price' WHERE p.post_status='publish' and p.post_author='1' and p.post_type='product'";
		long totalSellingPrice = 0;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection(url, user, password);
			PreparedStatement st = con.prepareStatement(sql);
			ResultSet rs = st.executeQuery();
			while(rs.next()) {
				totalSellingPrice = rs.getLong("sellingprice");
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return totalSellingPrice;	
	}
	public int getSoldQuantity() throws SQLException {
	String sql = "SELECT count(cp.meta_value) as soldquantity FROM wp_posts as p LEFT JOIN wp_postmeta as s on s.post_id=p.ID and s.meta_key='_sku' left join wp_postmeta as cp on cp.post_id=p.ID and cp.meta_key='_stock_status' WHERE cp.meta_value='outofstock' and p.post_status='publish' and p.post_author='3' and p.post_type='product'";
//		
		//String sql = "SELECT count(cp.meta_value) as soldquantity FROM wp_posts as p LEFT JOIN wp_postmeta as s on s.post_id=p.ID and s.meta_key='_sku' left join wp_postmeta as cp on cp.post_id=p.ID and cp.meta_key='_stock_status' WHERE cp.meta_value='outofstock' and p.post_status='publish' and p.post_author='1' and p.post_type='product'";
		
		int totalSoldQuantity = 0;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection(url, user, password);
			PreparedStatement st = con.prepareStatement(sql);
			ResultSet rs = st.executeQuery();
			while(rs.next()) {
				totalSoldQuantity = rs.getInt("soldquantity");
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return totalSoldQuantity;	
	}
	public List<TibetHouseModel> getDataByMonth(int i) throws SQLException{
		List<TibetHouseModel> list = new ArrayList<TibetHouseModel>();
	String sql ="SELECT p.post_title as title,s.meta_value as SKU,cp.meta_value*50 as costprice FROM wp_posts as p LEFT JOIN wp_postmeta as s on s.post_id=p.ID and s.meta_key='_sku' left join wp_postmeta as cp on cp.post_id=p.ID and cp.meta_key='_price' left join wp_postmeta as q on q.post_id=p.ID and q.meta_key='_stock_status' WHERE q.meta_value='outofstock' and p.post_status='publish' and p.post_author='3' and p.post_type='product' and month(post_modified)='"+i+"' limit 10";
//		
		//String sql ="SELECT p.post_title as title,s.meta_value as SKU,cp.meta_value*50 as costprice FROM wp_posts as p LEFT JOIN wp_postmeta as s on s.post_id=p.ID and s.meta_key='_sku' left join wp_postmeta as cp on cp.post_id=p.ID and cp.meta_key='_price' left join wp_postmeta as q on q.post_id=p.ID and q.meta_key='_stock_status' WHERE q.meta_value='outofstock' and p.post_status='publish' and p.post_author='1' and p.post_type='product' and month(post_modified)='"+i+"' limit 10";
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection(url, user, password);
			PreparedStatement st = con.prepareStatement(sql);
			ResultSet rs = st.executeQuery();
			while(rs.next()) {
				TibetHouseModel m = new TibetHouseModel();
				m.setTitle(rs.getString("title"));
				m.setSku(rs.getString("SKU"));
				m.setCostprice(rs.getFloat("costprice"));
				list.add(m);
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	public List<TibetHouseModel> getDataByYear(int j) throws SQLException{
		List<TibetHouseModel> list = new ArrayList<TibetHouseModel>();
		String sql ="SELECT p.post_title as title,s.meta_value as SKU,cp.meta_value*50 as costprice FROM wp_posts as p LEFT JOIN wp_postmeta as s on s.post_id=p.ID and s.meta_key='_sku' left join wp_postmeta as cp on cp.post_id=p.ID and cp.meta_key='_price' left join wp_postmeta as q on q.post_id=p.ID and q.meta_key='_stock_status' WHERE q.meta_value='outofstock' and p.post_status='publish' and p.post_author='3' and p.post_type='product' and year(post_modified)='"+j+"' limit 10";
//		
	//	String sql ="SELECT p.post_title as title,s.meta_value as SKU,cp.meta_value*50 as costprice FROM wp_posts as p LEFT JOIN wp_postmeta as s on s.post_id=p.ID and s.meta_key='_sku' left join wp_postmeta as cp on cp.post_id=p.ID and cp.meta_key='_price' left join wp_postmeta as q on q.post_id=p.ID and q.meta_key='_stock_status' WHERE q.meta_value='outofstock' and p.post_status='publish' and p.post_author='3' and p.post_type='product' and year(post_modified)='"+j+"' limit 10";
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection(url, user, password);
			PreparedStatement st = con.prepareStatement(sql);
			ResultSet rs = st.executeQuery();
			while(rs.next()) {
				TibetHouseModel m = new TibetHouseModel();
				m.setTitle(rs.getString("title"));
				m.setSku(rs.getString("SKU"));
				m.setCostprice(rs.getFloat("costprice"));
				list.add(m);
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	public List<TibetHouseModel> getDataByDay(String k) throws SQLException{
		List<TibetHouseModel> list = new ArrayList<TibetHouseModel>();
	String sql ="SELECT p.post_title as title,s.meta_value as SKU,cp.meta_value*50 as costprice FROM wp_posts as p LEFT JOIN wp_postmeta as s on s.post_id=p.ID and s.meta_key='_sku' left join wp_postmeta as cp on cp.post_id=p.ID and cp.meta_key='_price' left join wp_postmeta as q on q.post_id=p.ID and q.meta_key='_stock_status' WHERE q.meta_value='outofstock' and p.post_status='publish' and p.post_author='3' and p.post_type='product' and date(post_modified)='"+k+"' limit 10";
//		
	//	String sql ="SELECT p.post_title as title,s.meta_value as SKU,cp.meta_value*50 as costprice FROM wp_posts as p LEFT JOIN wp_postmeta as s on s.post_id=p.ID and s.meta_key='_sku' left join wp_postmeta as cp on cp.post_id=p.ID and cp.meta_key='_price' left join wp_postmeta as q on q.post_id=p.ID and q.meta_key='_stock_status' WHERE q.meta_value='outofstock' and p.post_status='publish' and p.post_author='1' and p.post_type='product' and date(post_modified)='"+k+"' limit 10";
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection(url, user, password);
			PreparedStatement st = con.prepareStatement(sql);
			ResultSet rs = st.executeQuery();
			while(rs.next()) {
				TibetHouseModel m = new TibetHouseModel();
				m.setTitle(rs.getString("title"));
				m.setSku(rs.getString("SKU"));
				m.setCostprice(rs.getFloat("costprice"));
				list.add(m);
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	public int updateDB_update(String sku) throws SQLException {
		String sql ="UPDATE wp_postmeta p inner join wp_postmeta q on p.meta_key = '_sku' and q.meta_key = '_stock_status' SET q.meta_value='outofstock' where p.post_id = q.post_id and  p.meta_value=?";
		int d=0;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection(url, user, password);
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, sku);
			d = st.executeUpdate();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return d;	
	}
	public int updateOutOfStock(String sku) throws SQLException {
		String sql ="UPDATE wp_postmeta p inner join wp_postmeta q on p.meta_key = '_sku' and q.meta_key = '_stock_status' SET q.meta_value='outofstock' where p.post_id = q.post_id and  p.meta_value=?";
		int d=0;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection(url, user, password);
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, sku);
			d = st.executeUpdate();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return d;	
	}
	
	// update new price of sku
	
    	public int updateSKU(String sku, String stockstatus, String saleprice) throws SQLException {
    		String a="500";
//		PriceFromUser pfu = new PriceFromUser();
//		String a =pfu.price;
		String sql ="UPDATE wp_postmeta p inner join wp_postmeta q on p.meta_key = '_sku' and q.meta_key = '_stock_status' SET q.meta_value='outofstock' where p.post_id = q.post_id and  p.meta_value=?";
		String sqla ="UPDATE wp_postmeta p inner join wp_postmeta q on p.meta_key = '_sku' and q.meta_key = '_stock' SET q.meta_value='0' where p.post_id = q.post_id and  p.meta_value=?";
		String sqlb ="UPDATE wp_postmeta p inner join wp_postmeta q on p.meta_key = '_sku' and q.meta_key = '_sale_price' SET q.meta_value=? where p.post_id = q.post_id and p.meta_value=?";
		int d=0;
	    
		
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection(url, user, password);
			PreparedStatement st = con.prepareStatement(sql);
			PreparedStatement sta = con.prepareStatement(sqla);
			PreparedStatement stb = con.prepareStatement(sqlb);
			st.setString(1, sku);
			sta.setString(1, sku);
			stb.setString(1, saleprice);
			stb.setString(2, sku);
			d = st.executeUpdate();
			d = sta.executeUpdate();
			d = stb.executeUpdate();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return d;	
	}
	
	public List<TibetHouseModel> posList(String sku) throws SQLException{
		//String sql = "SELECT p.post_title as title,s.meta_value as SKU,cp.meta_value*50 as costprice FROM wp_posts as p LEFT JOIN wp_postmeta as s on s.post_id=p.ID and s.meta_key='_sku' left join wp_postmeta as cp on cp.post_id=p.ID and cp.meta_key='_price'  WHERE p.post_status='publish' and p.post_author='3' and p.post_type='product' and s.meta_value=?";
		String sql = "SELECT p.post_title as title,s.meta_value as SKU,cp.meta_value*50 as costprice, stat.meta_value as stockstatus FROM wp_posts as p LEFT JOIN wp_postmeta as s on s.post_id=p.ID and s.meta_key='_sku' left join wp_postmeta as cp on cp.post_id=p.ID and cp.meta_key='_price' left join wp_postmeta as stat on stat.post_id=p.ID and stat.meta_key='_stock_status' WHERE p.post_status='publish' and p.post_author='3' and p.post_type='product' and s.meta_value=?";
		
		// String sql = "SELECT p.post_title as title,s.meta_value as SKU,cp.meta_value*50 as costprice, stat.meta_value as stockstatus FROM wp_posts as p LEFT JOIN wp_postmeta as s on s.post_id=p.ID and s.meta_key='_sku' left join wp_postmeta as cp on cp.post_id=p.ID and cp.meta_key='_price' left join wp_postmeta as stat on stat.post_id=p.ID and stat.meta_key='_stock_status' WHERE p.post_status='publish' and p.post_author='1' and p.post_type='product' and s.meta_value=?";
		List<TibetHouseModel> list = new ArrayList<TibetHouseModel>();
		
			try {
				Class.forName("com.mysql.jdbc.Driver");
				Connection con = DriverManager.getConnection(url, user, password);
				PreparedStatement st = con.prepareStatement(sql);
				st.setString(1, sku);
				ResultSet rs = st.executeQuery();
				while(rs.next()) {
					TibetHouseModel m = new TibetHouseModel();
					m.setTitle(rs.getString("title"));
					m.setSku(rs.getString("SKU"));
					m.setCostprice(rs.getFloat("costprice"));
					m.setStatus(rs.getString("stockstatus") );
					String vv;
					String Get_SKU=(rs.getString("SKU"));
					String a ="instock";
					if (rs.getString("stockstatus").equalsIgnoreCase(a)){
						vv="stock";
					
						vv = vv + "<tr> <form action='DB_update.jsp' method='post'> <input type='hidden' name='sku' id='sku' value='" + Get_SKU + "'> <br> <input type='text' name='price' id='price' placeholder='Enter Udated Price' > <br><input type='submit' name='update' value='Update'> </form></td>";
						}
					else { 
						vv="out";
						}
				
					m.setStatus(vv);
					list.add(m);
				}
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return list;
			
	}
}
