<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Probleme</title>
</head>
<body>
<h2>Un problème est survenu</h2>
<p>nous n'avons malheureusement pas pu enregistrer votre commentaire
	cela ne marchera probablement pas si vous réessayez, <%= request.getAttribute("nom") %>
	cliquez ici pour aller <a href="http://www.google.fr">ailleurs</a></p>
</body>
</html>