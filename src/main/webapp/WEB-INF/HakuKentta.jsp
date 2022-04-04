<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>HIPSTER HAVEN</title><link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css">
<style type="text/css">
label {
	display: block;
	width: 8em;
	float: left;
}

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
<h1>Hae albumia</h1>
<form action="/hae-albumia" method="post">
			<p>
				<label>Hae albumia nimellä:</label> <input type="text" value="" placeholder="Kirjoita albumin nimi..." name="nimi" size="50" />
			</p>
			<p>
				<span class="button"><a href="/koti" >Peruuta</a></span>
				<input type="submit" name="submit-button" class="btn btn-success" value="Hae!" />
			</p>
	</form>
</body>
</html>