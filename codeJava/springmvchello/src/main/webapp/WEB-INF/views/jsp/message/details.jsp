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
<h2>Details du message</h2>
<h3><c:out value="${message.titre}"/></h3>
<p>
<c:out value="${message.corps}"/>
</p>
<s:url value="/message/liste" var="listeUrl"/>
<a href="${listeUrl}">retour à la liste des messages</a>
</body>
</html>