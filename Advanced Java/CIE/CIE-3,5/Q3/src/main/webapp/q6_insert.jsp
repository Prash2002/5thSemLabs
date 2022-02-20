<%@page import="q11.DB"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.sql.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<form action="q6_insert.jsp" method="POST">
		<input type="number" placeholder="Book no"  name="id">
		<input type="text" placeholder="Title"  name="name">
		<input type="text" placeholder="Author"  name="author">
		<input type="text" placeholder="Publication"  name="public">
		<input type="number" placeholder="Price"  name="price">
		<input type="submit" name="insert">
	</form>
	
	<%
		if(request.getParameter("insert") != null){
			try{
				Connection con = DB.initialize();
				out.println("works");
				
			}
			catch(Exception e){
				e.printStackTrace();
			}
			
		}
	%>
</body>
</html>