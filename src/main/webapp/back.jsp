<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.DriverManager"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
	<%@ page import="java.sql.*" %>
	
	
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h3>this is response page</h3>


	<%
	
	String id=request.getParameter("userid");
	int ids=Integer.parseInt(id);
	
	response.setContentType("text/html");
	String url = "jdbc:mysql://localhost:3306/springjpa77";
	String user = "root";
	String password = "123456";
	String query = "select * from streg where id=?";

	try {

		Class.forName("com.mysql.cj.jdbc.Driver");

		Connection connection = DriverManager.getConnection(url, user, password);
		
	   PreparedStatement ps=connection.prepareStatement(query);
	   
	   ps.setInt(1, ids);
	   
	 ResultSet rs=  ps.executeQuery();
	 
	 if(rs.next()){
		%>
		
		 <h3>User Id : <%=rs.getInt(1) %></h3>
		 <h3>User Name : <%=rs.getString(2) %></h3>
		 <h3>User age : <%=rs.getInt(3) %></h3>
		 <h3>User Location : <%=rs.getString(4) %></h3>
	   <%	 
	 }
	 
	 
	connection.close();
	ps.close();
	rs.close();
		

	} catch (Exception e) {
   %>
    <h4><%= e.getMessage() %></h4>
   
	<%	
	}
	%>
	
	
	


</body>
</html>