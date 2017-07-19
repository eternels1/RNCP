<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Merci !</title>
</head>
<body>
<h2>Merci de votre commentaire <%= request.getAttribute("nom") %> !</h2>
<p>Nous avons bien enregistrer votre commentaire<br>
vous etes inscrit sur notre mailling list à l'adresse <%= request.getAttribute("email") %>
	Continuer la navigation sur notre site ou saisissez un nouveau commentaire <a href="Aiguillage">ici</a></p>
</body>
</html>