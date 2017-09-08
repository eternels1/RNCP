<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Edition Categorie</title>
</head>
<body>
<h2>Edition Categorie </h2>
<s:form method="post" action="categorie/save">
	<s:hidden name="id"/>
	<s:textfield name="libelle" label="Libelle categorie"/>
	<s:submit/>
</s:form>
</body>
</html>