package com.coders;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * Servlet implementation class Passwordverification
 */
@WebServlet("/Passwordverification")
public class Passwordverification extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Passwordverification() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");

		String passwords = request.getParameter("password");

		HttpSession session = request.getSession();
		session.setAttribute("p", passwords);

		String url = "jdbc:mysql://localhost:3306/springjpa77";
		String user = "root";
		String password = "123456";
		String query = "select * from details where password=?";

		try {

			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection connection = DriverManager.getConnection(url, user, password);

			PreparedStatement ps = connection.prepareStatement(query);
			ps.setString(1, passwords);

			ResultSet rs = ps.executeQuery();

			if (rs.next()) {

				String em = (String) session.getAttribute("e");
				String pa = (String) session.getAttribute("p");
				System.out.println(em);
				response.getWriter().println("<h3> Welcome " + em + "&" + pa + "</h3> ");

				RequestDispatcher rd = request.getRequestDispatcher("home.html");
				rd.include(request, response);

			} else {
				response.getWriter().println("<h3>Not a Valid email .....</h3>");
				RequestDispatcher rd = request.getRequestDispatcher("second.html");
				rd.include(request, response);

			}

		} catch (Exception e) {

			e.printStackTrace();
		}

	}

}
