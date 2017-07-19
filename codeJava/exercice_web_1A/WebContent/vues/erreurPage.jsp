<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Erreur Page</title>
</head>
<body>
<h2>Erreur sur le Calcul!</h2>
<p>Nous avons bien enregistrer votre calcul mais une erreur s'est produite:<br>

<% if (request.getAttribute("messageresultat")!=null) { %>
	Votre calcul était <%= request.getAttribute("messageresultat") %><br>
	Réaliser un nouveau calcul <a href="centreCalcul">ici</a>
<% } 


else {%> le format des champs doit uniquement être des nombres!<%} %>	
</p>
</body>
</html>