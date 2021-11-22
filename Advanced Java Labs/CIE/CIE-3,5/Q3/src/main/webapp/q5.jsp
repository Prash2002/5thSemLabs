<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<form action="q5.jsp" method= "post">
	<input type="text" placeholder="name: " name="name">
	<input type="number" placeholder="enter age" name="age">
	<input type="submit" name="btn">
</form>
<%! int price = 80; %>
<%
	if(request.getParameter("btn") != null){

		int age = Integer.parseInt(request.getParameter("age"));
		if(age>62){
			price = 50;
		}
		if(age<10){
			price = 30;
		}
		//out.print(price);
		 %>

<h1> <%= "User: " + request.getParameter("name") +" " + price %> </h1>
<%
}
%>
</body>
</html>