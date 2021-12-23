<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<form id ="form" class="form" method = "post" action = ${pageContext.request.contextPath}/index>
<button >Login</button>
<input type="text" id = "email1" name= "email" value ="${emailValue}"> 
</form>
</body>
</html>