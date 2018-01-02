<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Mes Super Films </title>
<s:url value="/resources/css/bootstrap.css" var="bootstrapStyle" />
<link href="${bootstrapStyle}" rel="stylesheet" />
<s:url value="/resources/css/bonjour.css" var="coreStyle" />
<link href="${coreStyle}" rel="stylesheet" />
</head>



<body>
<div class="container">
<h2>Liste des trops bons Films !!!</h2>
<div class="well">
	<form method="post" action="addFilm">
		<div class="form-group">
			<label for="titre">Titre du Film à Ajouter</label>
			<input type="text" class="form-control" id="titre" name="titre">		
		</div>
	<div class="form-group">
			<label for="realisateur">Réalisateur du Film à Ajouter</label>
			<input type="text" class="form-control" id="realisateur" name="realisateur">		
		</div>
		<div class="form-group">
			<label for="annee">Année du Film à Ajouter</label>
			<input type="text" class="form-control" id="annee" name="annee">		
		</div>
		<div class="form-group">
			<label for="synopsis">Synopsis du Film à Ajouter</label>
			<input type="text" class="form-control" id="synopsis" name="synopsis">		
		</div>
		<input type="submit" class="btn btn-primary" value="ajouter">
	</form>
</div>

<table border="1" class="table table-stripped">
<thead>
	<tr>
		<th>Titre</th>
		<th>Synopsis</th>
		<th>Réalisateur</th>
		<th>Année</th>
		<th>Actions</th>
		</tr>
		</thead>
		<tbody>
			<c:forEach var="film" items="${films}">
			<tr>
			<td>${film.titre}</td>
			<td>${film.synopsis}</td>
			<td>${film.realisateur}</td>
			<td>${film.annee}</td>
			<td><form method="post" action="deleteFilm/${film.id}">
				<input type="submit" class="btn btn-danger" value="supprimer">
			</form></td>
			</tr>
			</c:forEach>
		
		</tbody>	

</table>








</div>
</body>
</html>