package com.loginvalidate;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model.TibetHouseModel;

@WebServlet("/userLogin")
public class loginvalidate extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String url = "jdbc:mysql://mudrahouse.com/thetibethouse";
		String user = "shopizer";
		String password = "SalesManager123!";

		String uname = request.getParameter("Uname");
		String pass = request.getParameter("password");
		String sql = "select user_login from wp_users where user_login=?";

		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection(url, user, password);
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, uname);
			ResultSet rs = st.executeQuery();
			while (rs.next()) {
				if (pass.equals("shrestha")) {
					response.sendRedirect("pos.jsp");
				   System.out.println("login");
				} else {
					response.sendRedirect("login.jsp");
					System.out.println("login unsucess");
				}
				return;
			}
			response.sendRedirect("login.jsp");
			System.out.println("login fail");
			return;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

//		if (uname != null && pass.equals("shrestha"))
//		{
//		response.sendRedirect("pos.jsp");
//		}
//		else {
//			response.sendRedirect("login.jsp");	
//		}
	}

}
