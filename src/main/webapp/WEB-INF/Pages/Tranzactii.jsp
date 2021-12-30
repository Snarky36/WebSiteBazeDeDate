<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<script>
function formdata() 
{
var dela= document.getElementById("delaValue").value;
var panala= document.getElementById("panalaValue").value;
document.getElementById("Tabel04").src = "${pageContext.request.contextPath}/Tabel04?"+"interval"+dela+"="+panala;
}
function nrcard() 
{
var nrcard= document.getElementById("nrcard").value;
document.getElementById("Tabel04").src = "${pageContext.request.contextPath}/Tabel04?"+"dataCard="+nrcard;
}
function valmedie() 
{
var dela= document.getElementById("delaAnValue").value;
var panala= document.getElementById("panalaAnValue").value;
var an= document.getElementById("anValue").value;
document.getElementById("Tabel04").src = "${pageContext.request.contextPath}/Tabel04?"+"delaAnValue="+dela+"&panalaAnValue="+panala+"&anValue="+an;
}
</script>

<meta charset="ISO-8859-1">
<title>Tranzactii</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Lato">
<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Montserrat">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/Resurse/styleUserPanel.css">
<style>
body,h1,h2,h3,h4,h5,h6 {font-family: "Lato", sans-serif}
.w3-bar,h1,button {font-family: "Montserrat", sans-serif}
.fa-anchor,.fa-coffee {font-size:200px}
</style>
</head>
<body>


<%
if(session.getAttribute("username") == null){
	request.getRequestDispatcher("/WEB-INF/Pages/Login.jsp").forward(request, response);
}
%>

 
<!-- Navbar -->
<div class="w3-top">
  <div class="w3-bar w3-blue-grey w3-card w3-left-align w3-large">
  
    
    <a class="w3-bar-item w3-button w3-hide-medium w3-hide-large w3-right w3-padding-large w3-hover-white w3-large w3-blue-grey" href="javascript:void(0);" onclick="myFunction()" title="Toggle Navigation Menu"><i class="fa fa-bars"></i></a>
    <a href="${pageContext.request.contextPath}/UserPanel" class="w3-bar-item w3-button w3-padding-large w3-white">Home</a>
   
    <%
        try {
            if (request.getSession(false) == null) {

            } else {
    %>
    
    <%
        if (session.getAttribute("username").equals("")) {
    %>
    <a href="${pageContext.request.contextPath}/Login" class="w3-button w3-hover-white" style ="float:right;">Login</a>
    <a href="${pageContext.request.contextPath}/Register" class="w3-button w3-hover-white"style ="float:right;">Register</a>
    <%
        } else {
    %>
    <a href="${pageContext.request.contextPath}/Logout" class="w3-button w3-hover-white" style ="float:right;">Logout</a>
    <p class = "usernamePanel">
    <%=session.getAttribute("username")%>
    <img src="${pageContext.request.contextPath}/Resurse/userIcon.png" width="35px" height= "35px" style="margin-left:4px; margin-top:-3px;">
    </p>
        
    <%
        }
            }
        } catch (Exception e) {

        }
    %>
    
    
  </div>

  <!-- Navbar on small screens -->
  <div id="navDemo" class="w3-bar-block w3-white w3-hide w3-hide-large w3-hide-medium">
    <a href="${pageContext.request.contextPath}/UserPanel" class="w3-bar-item w3-button w3-padding-large">Home</a>

  </div>
</div>

<!-- Header -->
<header class="w3-container w3-blue-grey w3-center" style="padding:128px 16px">
  <h1 class="w3-margin w3-jumbo">ZBank</h1>
  <p class="w3-xlarge">Welcome <%=session.getAttribute("username")%>!</p>
  <% if (session.getAttribute("permission").equals("0") ) {%>
  <a href="#" class="w3-button w3-black w3-padding-large w3-large w3-margin-top w3-round-large">Conturile mele</a>
  <a href="#" class="w3-button w3-black w3-padding-large w3-large w3-margin-top w3-round-large">Cardurile mele</a>
  <a href="#" class="w3-button w3-white w3-padding-large w3-large w3-margin-top w3-round-large">Istoric tranzactii</a>
  <%}
  else{
  %>
  <a href="${pageContext.request.contextPath}/Conturi" class="w3-button w3-black w3-padding-large w3-large w3-margin-top w3-round-large">Vizioneaza Conturi</a>
  <a href="${pageContext.request.contextPath}/Carduri" class="w3-button w3-black w3-padding-large w3-large w3-margin-top w3-round-large">Vizioneaza Carduri</a>
  <a href="${pageContext.request.contextPath}/Tranzactii" class="w3-button w3-white w3-padding-large w3-large w3-margin-top w3-round-large">Istoric tranzactii</a>
  <%} %>
</header>

<!-- First Grid -->
<div class="aranjare">
<div class ="textinput">
<p>De la: </p><input  type="text" id="delaValue"/>
<p>Pana la:</p><input  type="text" id="panalaValue"/>
<div class= "buttonCards">
<input type="submit" value="Vizioneaza" onclick="formdata()"/><br>
</div>
</div>

<!-- Secound Grid -->

<div class ="textinput">
<p>Numar Card </p><input  type="text" id="nrcard"/>

<div class= "buttonCards">
<input type="submit" value="Vizioneaza" onclick="nrcard()"/><br>
</div>
</div>


<div class ="textinput">
<p>Persoane nascute in anul: </p><input  type="text" id="anValue"/>
<p>Intre anul:</p><input  type="text" id="delaAnValue"/>
<p>si anul:</p><input  type="text" id="panalaAnValue"/>
<div class= "buttonCards">
<input type="submit" value="Vizioneaza" onclick="valmedie()"/><br>
</div>
</div>

</div>
<div class="w3-container w3-white w3-center w3-padding-64">
<iframe frameBorder="0" id="Tabel04" src="${pageContext.request.contextPath}/Tabel04" style="width:100%; height:400px" scrolling="auto">
</iframe>
</div>



<!-- Footer -->
<footer  class="w3-container w3-padding-64 w3-center w3-opacity">  
 <p>Powered by <a href="#" target="_blank">Zaharie Andrei</a></p>
</footer>

<script>
// Used to toggle the menu on small screens when clicking on the menu button
function myFunction() {
  var x = document.getElementById("navDemo");
  if (x.className.indexOf("w3-show") == -1) {
    x.className += " w3-show";
  } else { 
    x.className = x.className.replace(" w3-show", "");
  }
}
</script>
</body>
</html>
