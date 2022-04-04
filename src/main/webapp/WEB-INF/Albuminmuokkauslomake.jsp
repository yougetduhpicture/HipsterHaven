<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css">
<style type="text/css">
label {
	display: block;
	width: 8em;
	float: left;
}
body {
  margin-top: 100px;
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

<h1>Hipster Haven</h1>
<br>
<h2>Muokkaa albumin tietoja</h2>

	<form action="/albuminmuokkaus" method="post">
		<p>
			<label>Artistin nimi:</label> <input type="text" value="${albumi.artisti}" name="artisti" size="50" />
		</p>
		<p>
			<label>Albumin nimi:</label> <input type="text" value="${albumi.nimi}" name="nimi"size="50" />
		</p>
		<p>
			<label>Genre:</label> <input type="text" value="${albumi.genre}" name="genre" size="100" />
		</p>
		<p>
			<label>Kuvaus:</label> <input type="text" value="${albumi.kuvaus}" name="kuvaus" size="100" />
		</p>
		<p>
			<label>Linkki kansikuvaan:</label> <input type="text" value="${albumi.kansikuva}" name="kansikuva" size="100" />
		</p>
		<p>
			<label>Albumin Id:</label> <input type="text" value="${albumi.id}" name="id" size="50" />
		</p>
		<p>
		<span class="button"><a href="/koti" >Peruuta</a></span>
		<input type="submit" name="submit-button" class="btn btn-success" value="Tallenna" />
		</p>
	</form>
	
</body>
</html>