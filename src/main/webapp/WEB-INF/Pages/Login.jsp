<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Login Page!</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/Resurse/styleLogin.css"/>

</head>
<body>

<div class="container">
	<div class="header">
		<h2>Conecteaza-te</h2>
		<%
		   if(request.getAttribute("contInregistrat") != null){
			   %>
			   <p class="contInregistrat">
			   <%=request.getAttribute("contInregistrat")%>
			   </p>
		<%
		
		   }
		   else{
		   request.setAttribute("contInregistrat", "");
		   }
		%>
	</div>
	<form id="form" class="form" method="post" action= ${pageContext.request.contextPath}/Login>
		<div class="form-control">
			<label>Email</label>
			<input type="text" placeholder="Email" id="email" name="email" value="${emailValue}"/>
			<i style="visibility:${vis};color:red" class="fas fa-exclamation-circle"></i>
			<small style="visibility:${vis};">${text}</small>
		</div>
		<div class="form-control">
			<label for="username">Parola</label>
			<input type="password" placeholder="Parola" id="pass" name="pass" value = "${passValue}"/>
			<i style="visibility:${vis};color:red" class="fas fa-exclamation-circle"></i>
			<small style="visibility:${vis};">${text}</small>
		</div>
		<%
		   if(request.getAttribute("contInregistrat") == null || request.getAttribute("contInregistrat") == ""){
			   %>
		<p style="text-align:center;"><b><a style="text-decoration:none;color:black" href="${pageContext.request.contextPath}/Register">Creeaza un cont!</a></b></p>
		<%} %>
		<button class="connectButton">Conecteaza-te</button>
	</form>
	

<%
if(session.getAttribute("wrongPassword")==null || session.getAttribute("wrongPassword").equals("")){
	
}
else{
%>
<p class = "wrongPass">
<%=session.getAttribute("wrongPassword")%>
</p>
<%
}
%>

</div>

</body>
</html>