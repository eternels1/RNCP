<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Voici la figure Carrée !</title>
</head>
<body>
<%
int taille=(int)request.getAttribute("taillefigure");

for (int i=0; i<taille; i++ ) { %>
*
<%} %><br>

</body>
</html>