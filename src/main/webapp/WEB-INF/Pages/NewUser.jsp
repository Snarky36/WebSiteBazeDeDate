<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Adauga date</title>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/Resurse/styleRegister.css">
</head>
<body>
<div class="container">
	<div class="header">
		<h2>Date necesare!</h2>
	</div>
	</form>
	<form id="form" class="form" name="form" method="post" action="${pageContext.request.contextPath}/NewUser">
	
	<div class="form-control">
			<label>Email</label>
			<input type="text" placeholder="Email" id="email" name="email" value="${emailValue}"/>
			<i style="visibility:${emailvisbun};color:green"class="fas fa-check-circle"></i>
			<i style="visibility:${emailvis};color:red" class="fas fa-exclamation-circle"></i>
			<small style="visibility:${emailvis};">${emailtext}</small>
		</div>
	
		<div class="form-control">
			<label>Nume</label>
			<input type="text" placeholder="Nume" id="nume" name="nume" value="${numeValue}"/>
			<i style="visibility:${numevisbun};color:green" class="fas fa-check-circle"></i>
			<i style="visibility:${numevis};color:red" class="fas fa-exclamation-circle"></i>
			<small style="visibility:${numevis};">${numetext}</small>
		</div>
		<div class="form-control">
			<label>Prenume</label>
			<input type="text" placeholder="Prenume" id="prenume" name="prenume" value="${prenumeValue}"/>
			<i style="visibility:${prenumevisbun};color:green" class="fas fa-check-circle"></i>
			<i style="visibility:${prenumevis};color:red" class="fas fa-exclamation-circle"></i>
			<small style="visibility:${prenumevis};">${prenumetext}</small>
		</div>
		
		<div class="form-control">
			<label>Adresa</label>
			<input type="text" placeholder="Adresa" id="adresa" name="adresa" value="${adresaValue}"/>
			<i style="visibility:${nrtelvisbun};color:green" class="fas fa-check-circle"></i>
			<i style="visibility:${nrtelvis};color:red;" class="fas fa-exclamation-circle"></i>
			<small style="visibility:${nrtelvis};">${nrteltext}</small>
		</div>
		<div class="form-control">
			<label>Gen</label>
			<input type="text" placeholder="Gen(F/M)" id="gen" name="gen" value="${genValue}"/>
			<i style="visibility:${parolavisbun};color:green" class="fas fa-check-circle"></i>
			<i style="visibility:${parolavis};color:red;" class="fas fa-exclamation-circle"></i>
			<small style="visibility:${parolavis};">${parolatext}</small>
		</div>
		<div class="form-control">
			<label>Data Nasterii</label>
			<input type="text" placeholder="mm/dd/yyyy" id="data" name="data" value="${dataValue}"/>
			<i style="visibility:${parola2visbun};color:green" class="fas fa-check-circle"></i>
			<i style="visibility:${parola2vis};color:red;" class="fas fa-exclamation-circle"></i>
			<small style="visibility:${parola2vis};">${parola2text}</small>
		</div>
		<button type="submit" form="form" value="Submit">Finalizeaza</button>
	</form>
	
</div>
</body>
</html>