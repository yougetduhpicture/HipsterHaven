<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
<meta charset="UTF-8">
<title>Hipster Haven</title>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" />
<style>
body {
  margin-top: 100px;
  margin-bottom: 100px;
  margin-right: 80px;
  margin-left: 90px;
}
a {
margin-right: 30px;
  margin-left: 30px;
}
table{
margin-left: 10px;
}
</style>
</head>
<body>

<h1>HIPSTER HAVEN</h1>

<p>Täält löytyy mun suosikki levyt.</p>

<a href="/lisaa-albumi">Lisää albumi</a>
<a href="/hae-albumia">Hae albumia</a>

<table class="table table_striped" >
		<tr>
			<th>Artisti</th>
			<th>Albumin nimi</th>
			<th>Genre</th>
			<th>Kuvaus</th>
			<th></th>
			<th></th>
		</tr>
	
		<%-- ${asiakkaat} viittaa keyword-arvolla request-olion Map-tietorakenteessa olevaan asiakaslistaan(ArrayList) --%>
		<c:forEach items="${albumit}" var="albumi">
			
			<tr>
				<td><c:out value="${albumi.artisti}" /></td> 
				<td><c:out value="${albumi.nimi}" /></td> 
				<td><c:out value="${albumi.genre}" /></td> 
				<td><c:out value="${albumi.kuvaus}" /></td> 
				<td><img src="${albumi.kansikuva}" alt="Levyn kansi" width="100" height="100"></td>
				<td><a href= "/poista-albumi?albumiId=${albumi.id}">poista</a></td>
				<td><a href= "/albuminmuokkaus?albumiId=${albumi.id}">muokkaa</a></td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>