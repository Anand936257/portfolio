package com.anand;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Contact
 */
@WebServlet("/ContactServlet")
public class Contact extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Contact() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		 String name = request.getParameter("name");
	        String email = request.getParameter("email");
	        String message = request.getParameter("message");


		try 
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/portfolio_db","root","anand@90");
			System.out.println("DB connected successfully"); 
			PreparedStatement ps = con.prepareStatement(
		                "INSERT INTO contacts(name,email,message,created_at) VALUES(?,?,?, NOW())"
		            );
		            ps.setString(1, name);
		            ps.setString(2, email);
		            ps.setString(3, message);
		            ps.executeUpdate();

		            response.getWriter().println("Message sent successfully!");

			
		} catch (Exception e) {
			// TODO: handle exception
			  e.printStackTrace();
	            response.getWriter().println("Error while sending message." +e);

		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
