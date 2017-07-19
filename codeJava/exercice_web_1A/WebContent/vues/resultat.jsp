<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Resultat!</title>
</head>
<body>
<h2>Merci de votre calcul !</h2>
<p>Nous avons bien enregistrer votre demande de calcul<br>
	Votre calcul était <%= request.getAttribute("messageresultat") %><br>
	Continuer la navigation sur notre site en réalisant un nouveau calcul <a href="centreCalcul">ici</a></p>
</body>
</html>