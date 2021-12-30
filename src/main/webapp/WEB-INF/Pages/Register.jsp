<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Register your account!</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/Resurse/styleRegister.css">
</head>
<body>
<div class="container">
	<div class="header">
		<h2>Creeaza cont</h2>
	</div>
	
	<form id="form" class="form" name="form" method="post" action="${pageContext.request.contextPath}/Register">
		<div class="form-control">
			<label>Email</label>
			<input type="text" placeholder="Email" id="email" name="email" value="${emailValue}"/>
			<i style="visibility:${emailvisbun};color:green"class="fas fa-check-circle"></i>
			<i style="visibility:${emailvis};color:red" class="fas fa-exclamation-circle"></i>
			<small style="visibility:${emailvis};">${emailtext}</small>
		</div>
		<div class="form-control">
			<label>Parola</label>
			<input type="password" placeholder="Parola" id="pass" name="pass" value ="${passValue}"/>
			<i style="visibility:${parolavisbun};color:green" class="fas fa-check-circle"></i>
			<i style="visibility:${parolavis};color:red;" class="fas fa-exclamation-circle"></i>
			<small style="visibility:${parolavis};">${parolatext}</small>
		</div>
		<div class="form-control">
			<label>Confirmare parola</label>
			<input type="password" placeholder="Confirmare parola" id="pass2" name="pass2" value ="${passValue2}"/>
			<i style="visibility:${parola2visbun};color:green" class="fas fa-check-circle"></i>
			<i style="visibility:${parola2vis};color:red;" class="fas fa-exclamation-circle"></i>
			<small style="visibility:${parola2vis};">${parola2text}</small>
		</div>
		<p class="form-control" style="text-align:center;">Ai un cont?<br><a style="text-decoration:none;color:black" href="${pageContext.request.contextPath}/Login"><b>Intra in cont!<b></a></p>
	</form>
	<button type="submit" form="form" value="Submit">Inregistrare cont</button>
</div>

</body>
</html>