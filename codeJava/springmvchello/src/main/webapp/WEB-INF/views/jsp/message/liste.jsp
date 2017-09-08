<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Liste des Messages</title>
<s:url value="/resources/css/bonjour.css" var="coreStyle" />
<link href="${coreStyle}" rel="stylesheet" />
</head>
<body>
<h2>Liste des Messages</h2>
<s:url value="/message/titresearch" var="searchUrl"></s:url>
<form method="post" action="${searchUrl}">
	Recherche par Titre:
	<input type="text" name="searchterm" id="searchterm"/>
	<input type="submit" value="Rechercher">
</form>
<table border="1">
	<thead>
		<tr>
			<th>ID</th>
			<th>TITRE</th>
			<th>CORPS</th>
			<th>ACTIONS</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${messages}" var="m">
			<tr>
			<td><c:out value="${m.id}" /></td>
			<td><c:out value="${m.titre}" /></td>
			<td><c:out value="${m.corps}" /></td>
			<td>
				<s:url value="/message/details/${m.id}" var="detailsUrl"/>
				<a href="${detailsUrl}">details message</a>
			</td>
			</tr>
		</c:forEach>
	</tbody>
</table>
<s:url value="/message/liste" var="listeUrl"/>
<a href="${listeUrl}">retour à la liste des messages</a>
</body>
</html>