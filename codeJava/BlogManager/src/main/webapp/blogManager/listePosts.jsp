<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="vendors/bootstrap/css/bootstrap.css" />
<title>Blog Manager</title>
</head>
<body>

<div class="container">
<div class="title"><h2>Les meilleurs Posts du monde!!</h2></div>
<s:iterator value="lstPosts">
<div class="panel panel-primary">
 <div class="panel-heading"><s:property value="titre"/></div>
  <div class="panel-body">
   <s:property value="corps"/>
  </div>
  <div class="panel-footer">Auteur : <s:property value="auteur.nom"/><br>
  Date de création : <s:property value="dateEdition"/>
  Date d'édition : <s:property value="dateEdition"/>
  						<s:a action="edit/%{id}"
								cssClass="btn btn-warning">Edition
						</s:a>
						<s:form style="display: inline-block;" action="delete" method="post" theme="simple">
							<s:hidden name="id" />
							<s:submit value="Supprimer" class="btn btn-danger"/>
						</s:form>
  </div>
</div>
</s:iterator>
<div class="row">
<div class="col-md-9">
<s:form action="create" method="post" theme="simple">
		<s:submit value="creer un nouveau Post" class="btn btn-success"/>
	</s:form>
</div>
<div class="col-md-3 text-right" >
						<s:a action="auteur"
								cssClass="btn btn-primary">Voir Les Auteurs</s:a>
</div>
</div>
<div><br></div>
</div>
</body>
</html>