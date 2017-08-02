<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="<:property value='baseurl' />vendors/bootstrap/css/bootstrap. ">
<title>L'Auteur</title>
</head>
<body>

<s:form action="save" method="post">
	<s:hidden name="id" />
	<s:textfield name="nom" label="Nom de l'Auteur : " />
	<s:textfield name="prenom" label="Prénom de l'Auteur : " />
	<s:textfield name="email" label="Email de l'Auteur : " />
	<s:submit value="Sauver"/>
</s:form>
</body>
</html>