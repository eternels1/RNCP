<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Produit</title>
<link rel="stylesheet" type="text/css" href="vendors/bootstrap/css/bootstrap.css" />

</head>
<body>
<div class="container">
	<h2><c:out value="${titre}" /></h2>
	<form action="ProduitEdit" method="POST" novalidate>
	
	
	<div class="form-group">
	<label for="nom">Nom Produit</label>
	<input type="text" class="form-control" id="nom" name="nom"  value="<c:out value='${produit.nom}' />"/> 
	</div>
		<div class="form-group">
	<label for="prix">Prix Produit</label>
	<input type="number" class="form-control" id="prix" name="prix" value="<c:out value='${produit.prix}' />" /> 
	</div>
		<div class="form-group">
	<label for="poids">Poids Produit</label>
	<input type="number" class="form-control" id="poids" name="poids" value="<c:out value='${produit.poids}' />"/> 
	</div>
	
	<input type="hidden" name="id" id="id" value="<c:out value='${produit.id}' />"/>
	<button type="submit" class="btn btn-primary">Sauver</button>
	
	
	
	</form>

</div>
</body>
</html>