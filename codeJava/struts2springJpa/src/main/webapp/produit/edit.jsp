<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Edition Produit</title>
</head>
<body>
<h2>Edition Produit </h2>
<s:form method="post" action="produit/save">
	<s:hidden name="id"/>
	<s:textfield name="nom" label="nom produit"/>
	<s:textfield name="prix" label="prix produit"/>
	<s:textfield name="poids" label="poids produit"/>	
	<s:submit/>
</s:form>
<h3>Liste des categories déjà associés au produit</h3>
<p>
	<s:set name="pid" value="id"/>
	cliquer sur une catégorie pour la retirer 
		<s:iterator value="categories">
				<s:a action="produit/catremove/%{}%{id}">
					<s:property value="libelle"/>
				<s:property value="libelle"/>,
				</s:a>
		</s:iterator>
		</p>
<h3>liste de toutes les Catégories</h3>
</body>
</html> 