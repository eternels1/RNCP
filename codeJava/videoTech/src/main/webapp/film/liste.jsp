<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Les vidéos du futur</title>
</head>
<body>
<h2>Vidéo Tech</h2>
<p><s:property value="titre"/></p>
<table border="1">
<thead>
<tr><th>TITRE</th><th>ANNEE</th><th>REALISATEUR</th><th>RATING</th><th>ACTION</th></tr>
</thead>
<tbody>
<s:iterator value="films">
<tr>
	<td><s:property value="titre"/></td>
	<td><s:property value="annee"/></td>
	<td><s:property value="realisateur"/></td>
	<td><s:property value="rating"/></td>
	<td>
	<s:form action="edit" method="post" theme="simple">
		<s:hidden name="id" />
		<s:submit value="Editer" />
	</s:form>
	<s:form action="delete" method="post" theme="simple">
		<s:hidden name="id" />
		<s:submit value="Supprimer" />
	</s:form>
	</td>
</tr>
</s:iterator>
</tbody>
</table>

<s:form action="create" method="post" theme="simple">
		<s:submit value="creer nouveau film" />
	</s:form>

</body>
</html>