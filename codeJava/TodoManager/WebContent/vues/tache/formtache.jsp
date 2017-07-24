<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Tache</title>
<link rel="stylesheet" type="text/css" href="vendors/bootstrap/css/bootstrap.css" />
</head>
<body>
<div class="container">
	<h2><c:out value="${titre}" /></h2>
	<form action="TacheEdit" method="POST" novalidate>
	
	
	<div class="form-group">
	<label for="description">Nom de la Tache</label>
	<input type="text" class="form-control" id="description" name="description"  value="<c:out value='${tache.description}' />"/> 
	</div>
		<div class="form-group">
	<label for="priorite">Priorité de la Tache</label>
	<input type="number" class="form-control" id="priorite" name="priorite" value="<c:out value='${tache.priorite}' />" /> 
	</div>
		<div class="form-group">
	<label for="contexte">Contexte de la Tache</label>
	<input type="text" class="form-control" id="contexte" name="contexte" value="<c:out value='${tache.contexte}' />"/> 
	</div>
	
	<div class="form-group">
	<label for="checkboxfinished">Finished</label>
	<input type="checkbox" class="form-control" id="checkboxfinished" name="checkboxfinished" />
	</div>
	
	<input type="hidden" name="id" id="id" value="<c:out value='${tache.id}' />"/>
	<button type="submit" class="btn btn-primary">Sauver</button>
	
	
	
	</form>

</div>
</body>
</html>