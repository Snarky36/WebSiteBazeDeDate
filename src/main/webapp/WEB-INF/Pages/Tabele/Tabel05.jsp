<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Tabel 13.04 b).</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/Resurse/styleUserPanel.css">
</head>
<body>
<div class="table">
<% 
   if(request.getAttribute("ConturiCeCorespund")!= null){

	   %>
	   <h3><center>13.04 subpunctul b).</center></h3>
	   <%=request.getAttribute("ConturiCeCorespund") %>
	<%
   }
   else{
	  
   }
%>
</body>
</html>