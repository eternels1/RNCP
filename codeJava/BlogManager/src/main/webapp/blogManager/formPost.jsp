<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="<:property value='baseurl' />vendors/bootstrap/css/bootstrap. ">
<title>Le Post</title>
</head>
<body>
<h2>Edition du Post</h2>
<h3> post crée le : <s:property value="dateCreation"/></h3>
<s:if test="id!=0">
<h3> Dernière édition :  <s:property value="dateEdition"/></h3>
</s:if>
<s:form action="save" method="post">
	<s:hidden name="id" />
	<s:textfield name="titre" label="Titre du Post" />
	<s:textarea name="corps" label="Post" />
	
	<s:select list="lstAuteurs" listKey="id" listValue="nom" name="auteurId"></s:select>
	<s:submit value="Sauver"/>
</s:form>
</body>
</html>