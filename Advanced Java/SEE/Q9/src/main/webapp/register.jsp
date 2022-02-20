<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<%
		String user = request.getParameter("user");
		String pass = request.getParameter("pass");
		
		if(user.equals("abhi") && pass.equals("1234")){
			System.out.println("Welcome abhi");
			session.setAttribute("user", user);
			request.getRequestDispatcher("welcome.jsp").forward(request, response);
		}
		else{
			System.out.println("Not valid");
			response.sendRedirect("index.html");
		}
	%>
</body>
</html>