<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Hipster Haven</title>
<link href="styles/demo.css" rel="stylesheet" type="text/css">
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

<h1>Hipster Haven</h1>
<br>
<h2>Lisää albumi</h2>
	<form action="/lisaa-albumi" method="post">
		<p>
			<label>Artistin nimi:</label> <input type="text" value="" name="artisti" size="50" />
		</p>
		<p>
			<label>Albumin nimi:</label> <input type="text" value="" name="nimi"size="50" />
		</p>
		<p>
			<label>Genre:</label> <input type="text" value="" name="genre" size="100" />
		</p>
		<p>
			<label>Kuvaus:</label> <input type="text" value="" name="kuvaus" size="100" />
		</p>
		<p>
			<label>Linkki kansikuvaan:</label> <input type="text" value="" name="kansikuva" size="100" />
		</p>
		<p>
		<span class="button"><a href="/koti" >Peruuta</a></span>
		<input type="submit" name="submit-button" class="btn btn-success" value="Tallenna" />
		</p>
	</form>
</body>
</html>