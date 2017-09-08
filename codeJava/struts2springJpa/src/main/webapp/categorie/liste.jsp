<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Liste des categories</title>
</head>
<body>
<h2>Liste des categories </h2>
<table border="1">
<thead>
	<tr>
	<th>ID</th>
	<th>LIBELLE</th>
	<th>ACTIONS</th>
	</tr>
</thead>
<tbody>
	<s:iterator value="categories">
		<tr>
			<td><s:property value="id"/></td>
			<td><s:property value="libelle"/></td>
			<td>
				<s:a action="categorie/edit/%{id}">Editer Categorie</s:a>
				<s:form theme="simple" action="categorie/delete" method="post">
				<s:hidden name="id"/>
				<s:submit value="Supprimer"/>
				</s:form>
			</td>
		</tr>
	</s:iterator>
</tbody>
</table>
<s:a action="categorie/create">Créer Categorie</s:a>
</body>
</html>