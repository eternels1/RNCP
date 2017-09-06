<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Liste des Films!</title>
</head>
<body>
<h2>Liste des Films!</h2>

<table border="1" style="border-collapse: collapse ">
<thead>
	<tr>
		<th>ID</th>
		<th>TITRE</th>
		<th>SYNOPSYS</th>
		<th>ANNEE</th>
		<th>REALISATEUR</th>
		<th>ACTEUR</th>
		<th>ACTIONS</th>
	</tr>
</thead> 
<tbody>
	<s:iterator value="films">
	<tr>
		<td><s:property value="id"/></td>
		<td><s:property value="titre"/></td>
		<td><s:property value="synopsys"/></td>
		<td><s:property value="annee"/></td>
		<td><s:property value="realisateur.nom"/></td>
		<td>
			<s:iterator value="acteurs" status="stat">
			 	<s:property value="nom"/><s:if test="#stat.last==false">,</s:if>
			</s:iterator>
		</td>
		<td>
			<s:a action="film/edit/%{id}">editer film</s:a>
			<s:form theme="simple" action="film/delete" metod="post">
				<s:hidden name="id" />
				<s:submit value="supprimer" />
			</s:form>
		</td>
	</tr>
	</s:iterator>
</tbody>
</table>
<s:a action="film/create">creer un film</s:a>



</body>
</html>