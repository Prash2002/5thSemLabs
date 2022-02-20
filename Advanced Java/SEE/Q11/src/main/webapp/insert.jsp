<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<!-- 
	Write a JSP program to create a form with Book_No, Title, Author, Publication, Price and a 
	Submit button. Using JSP-Database connectivity, get the data from the form and insert the records 
	into the database. Retrieve the book details for the particular title and display the details.
 -->
 
 	<form action="insert.jsp" method="post">
 		<input name="no">
 		<input name="title">
 		<input name="author">
 		<input name="pub">
 		<input name="price">
 		<input type="submit" name="insert">
 	
 	</form>
 
	<%@ page import = "java.sql.*" %>
	<%
		if(request.getParameter("insert")!=null){
			int no = 	Integer.parseInt(request.getParameter("no"));
			String title = request.getParameter("title");
			String author = request.getParameter("author");
			String pub = request.getParameter("pub");
			int price = 	Integer.parseInt(request.getParameter("price"));
		
	
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/see", "root", "");
			String q = "INSERT INTO books VALUES(?,?,?,?,?)";
			PreparedStatement ps = con.prepareStatement(q);
			ps.setInt(1,no);
			ps.setString(2, title);
			ps.setString(3, author);
			ps.setString(4, pub);
			ps.setInt(5, price);
			ps.execute();
			ps.close();
		}
	%>
</body>
</html>