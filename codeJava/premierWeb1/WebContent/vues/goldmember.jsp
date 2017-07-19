<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Goldmember!</title>
</head>
<body>
<h2>Merci infiniment de votre commentaire <%= request.getAttribute("nom") %> !</h2>
<p>Nous avons bien enregistrer votre magnifique commentaire<br>
<p style="border: 2px dashed blue;">
<%= request.getAttribute("commentaire") %> 
</p>
	Sachez qu'au bout de cinq commentaire saisis, <br>
	vous avez une chance de gagner un lot<br>
	<a href="Aiguillage">essayer!</a></p>
</body>
</html>