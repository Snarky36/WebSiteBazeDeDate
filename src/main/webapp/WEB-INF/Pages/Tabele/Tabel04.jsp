<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Table 13.03 subpunctul b</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/Resurse/styleUserPanel.css">
</head>
<body>


<%
   if(request.getAttribute("invalidNumber")!=null){
	   
	   
%>
<h2><center><%=request.getAttribute("invalidNumber") %></center></h2>
<%} 
%>
<%
   if(request.getAttribute("invalidAn")!=null){
	   
	   
%>
<h2><center><%=request.getAttribute("invalidAn") %></center></h2>
<%} 
%>

<%
   if(request.getAttribute("invalidCard")!=null){
	   
	   
%>
<h2><center><%=request.getAttribute("invalidCard") %></center></h2>
<%} 
%>

<div class="table">
<% 
   if(request.getAttribute("intervalTranzactii")!= null){

	   %>
	   <h3><center>13.03 subpunctul b).</center></h3>
	   <%=request.getAttribute("intervalTranzactii") %>
	<%
   }
   else{
	  
   }
%>

<% 
   if(request.getAttribute("MiscariPetrecute")!= null){

	   %>
	   <h3><center>13.05 subpunctul b).</center></h3>
	   <%=request.getAttribute("MiscariPetrecute") %>
	<%
   }
   else{
	  
   }
%>

<% 
   if(request.getAttribute("ValoareMedie")!= null){

	   %>
	   <h3><center>13.06 subpunctul b).</center></h3>
	   <%=request.getAttribute("ValoareMedie") %>
	<%
   }
   else{
	  
   }
%>
</div>
</body>
</html>