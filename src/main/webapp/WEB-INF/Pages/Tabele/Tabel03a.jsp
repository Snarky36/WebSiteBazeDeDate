<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Table 13.03 subpunctul a</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/Resurse/styleUserPanel.css">
</head>
<body>
<div class="table">
    <%
if(request.getAttribute("htmlTable")!= null){
	
%>
<h3><center>13.03 a).</center></h3>
<%=request.getAttribute("htmlTable")%>

<%
}
else
{
	
	}
%>

  <%
if(request.getAttribute("htmlTablePersoane")!= null){
	
%>
<h3><center>13.04 a).</center></h3>
<%=request.getAttribute("htmlTablePersoane")%>

<%
}
else
{
	}
%>

 <%
if(request.getAttribute("PersSingurCard")!= null){
	
%>
<h3><center>13.05 a).</center></h3>
<%=request.getAttribute("PersSingurCard")%>

<%
}
else
{
	}
%>

<%
if(request.getAttribute("detinatoriMaster")!= null){
	
%>
<h3><center>13.06 a).</center></h3>
<%=request.getAttribute("detinatoriMaster")%>

<%
}
else
{
	}
%>

</div>
</body>
</html>