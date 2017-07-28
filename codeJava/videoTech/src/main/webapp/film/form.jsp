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
<h2>Edition Film</h2>
<s:form action="save" method="post">
	<s:hidden name="editId" />
	<s:textfield name="editTitre" label="titre du film" />
	<s:textfield name="editAnnee" label="année du film" />
	<s:textfield name="editRealisateur" label="réalisateur du film" />
	<s:textarea name="editDescription" label="description du film" rows="5" cols="50" />
	<s:textfield name="editRating" label="note du film"/>
	<s:submit value="Sauver"/>
</s:form>
</body>
</html>