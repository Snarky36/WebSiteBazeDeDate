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
        try {
            if (request.getSession(false) != null) {
            	request.getSession().invalidate(); 
        		request.getRequestDispatcher("/WEB-INF/Pages/index.jsp").forward(request, response);
            } 
        }catch (Exception e) {

            }
    %>
</body>
</html>