package com.anand;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Project
 */
@WebServlet("/ProjectServlet")
public class Project extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Project() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		out.println("<html>");
	    out.println("<head>");
	    out.println("<title>My Projects</title>");
	    // CSS link - confirm path matches your WebContent folder
	    out.println("<link rel='stylesheet' type='text/css' href='style.css'>");
	    out.println("</head>");
	    out.println("<body>");

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/portfolio_db","root","anand@90");
			System.out.println("DB connected successfully");
			  Statement stmt = con.createStatement();
	            ResultSet rs = stmt.executeQuery("SELECT * FROM project");
	            if(!rs.isBeforeFirst()) { 
	                System.out.println("No projects found in database");
	            }


	            while (rs.next())
	            {
	                out.println("<div class='project-item'>");
	                out.println("<h3 class='project-title'>" + rs.getString("title") + "</h3>");
	                out.println("<p class='project-description'>" + rs.getString("description") + "</p>");
	                out.println("<a href='" + rs.getString("link") + "' target='_blank' class='project-link'>View Project</a>");
	                out.println("</div>");
	            }

			
			
		}
	            catch (Exception e) {
			// TODO: handle exception
	            	 e.printStackTrace();
	                 response.getWriter().println("Error fetching projects." +e);

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
