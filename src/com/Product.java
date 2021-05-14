package com;

import java.sql.*;

public class Product {
	
	private Connection connect() {
		Connection con = null;
		
		try {

			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/gadgetbadget_db","root","toor");
			System.out.println("-------------Connection succeed----------");
		}catch(Exception e){
			System.out.println("-------------Error connectiong to database---------/n " + e.fillInStackTrace());
		}
		
		return con;
	}
	
	public String viewProducts() {
		String result;
		
		try {
			Connection con = connect();		
			
			result ="<<link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css\">\r\n"
					+ "  <script src=\"https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js\"></script>\r\n"
					+ "  <script src=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js\"></script>>"
					+ "<table class=\"table table-dark\">"
					+ "    <tr>"
					+ "      <th scope=\"col\">ID</th>"
					+ "      <th scope=\"col\">Name</th>"
					+ "      <th scope=\"col\">Innovator Name</th>"
					+ "      <th scope=\"col\">Initial Price</th>"
					+ "		<th scope=\"col\">Stock Amount</th>"
					+ "		<th scope=\"col\">Category</th>"
					+ "    </tr>"
					+ "  <tbody>";
			
			PreparedStatement ps = null;
			String sql = "SELECT * FROM products";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			
			while (rs.next()) {
				result += "    <tr><th scope=\"row\">" + Integer.toString(rs.getInt("p_id")) + "</th>";
				result += "      <td>" + rs.getString("p_name")  + "</td>";
				result += "      <td>" + rs.getString("innovator_name")  + "</td>";
				result += "      <td>" + rs.getString("initial_price")  + "</td>";
				result += "      <td>" + rs.getString("stock_amount")  + "</td>";
				result += "      <td>" + rs.getString("product_category")  + "</td></tr>";
				
			}
			
			result += "</tbody></table>";
			
			
		}catch(Exception e) {
			System.err.println(e);
			return "Error Displaying Data";
		}
		
		return result;
		
	}
	
	public String createProduct(String p_name,String innovator_name,int initial_price,int stock_amount,String product_category) {
		String result = null;
		
		try {
			
			Connection con = connect();
			PreparedStatement ps = null;
			
			if(con == null) {
				return "Null connection Error !!!";
			}
			
			
			String sql = "INSERT INTO products VALUES (NULL,?,?,?,?,?)";
            ps = con.prepareStatement(sql);
            ps.setString(1,p_name);
            ps.setString(2,innovator_name);
            ps.setInt(3,initial_price);
            ps.setInt(4,stock_amount);
            ps.setString(5,product_category);
          
            
            ps.execute();
            
            
		}catch(Exception e) {
			
			System.err.println("Error occurred while Inserting Data!\n" + e.getMessage());
			return "Error occurred while Inserting Data!";
			
		}
		
		return "	   Data added to the database Successfully! ";
		
	}
	
	
	public String updateProduct(int p_id,String p_name,String innovator_name,int initial_price,int stock_amount,String product_category) {
		
		String result = null;
		
		try {

			Connection con = connect();
			PreparedStatement ps = null;
			
			if(con == null) {
				return "	Null connection Error !!!";
			}
			
			String sql = "UPDATE products SET p_id=?, p_name=?, innovator_name=?, initial_price=?, stock_amount=?, product_category=? ";
			
			ps = con.prepareStatement(sql);
			ps.setInt(1,p_id);
			ps.setString(2, p_name);
			ps.setString(3, innovator_name);
            ps.setInt(3,initial_price);
            ps.setInt(4,stock_amount);
            ps.setString(5,product_category);
			ps.setInt(1,p_id);
			
			ps.execute();
			
			
			
		}catch(Exception e) {
			
			System.err.println("Error occurred while Updating Data!\n" + e.getMessage());
			return "Error occurred while Updating Data!";
		}
		
		return " Data updated Successfully!";
		
	}
	
	public String deleteProduct(int p_id) {
		
		try {
			
			Connection con = connect();
			PreparedStatement ps = null;
			
			if (con == null) {
				return "	Null connection Error !!!";
			}
			
			String sql = "DELETE FROM products WHERE p_id = ?";
			
			ps = con.prepareStatement(sql);
			ps.setInt(1, p_id);
			
			ps.execute();
			
			
		}catch(Exception e) {
			
			System.err.println("Error Deleting Product ! \n" + e.getMessage());
			return "Error Deleting Product ! ";
		}
		
		return "	Product Deleted Successfully !";
	}
	
	
	
	
	
	

}
