<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Liste des produits</title>
</head>
<body>
<h2>Liste des produits </h2>
<table border="1">
<thead>
	<tr>
	<th>ID</th>
	<th>NOM</th>
	<th>PRIX</th>
	<th>POIDS</th>
	<th>CATEGORIES</th>
	<th>ACTIONS</th>
	</tr>
</thead>
<tbody>
	<s:iterator value="produits">
		<tr>
			<td><s:property value="id"/></td>
			<td><s:property value="nom"/></td>
			<td><s:property value="prix"/></td>
			<td><s:property value="poids"/></td>
			<td>
			<s:iterator value="categories">
				<s:property value="libelle"/>					
			</s:iterator>
			<td>
				<s:a action="produit/edit/%{id}">Editer Produit</s:a>
				<s:form theme="simple" action="produit/delete" method="post">
				<s:hidden name="id"/>
				<s:submit value="Supprimer"/>
				</s:form>
			</td>
		</tr>
	</s:iterator>
</tbody>
</table>
<s:a action="produit/create">Créer Produit</s:a>
</body>
</html>