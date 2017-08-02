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
	<h2>Liste des Voyages Proposés : </h2>
	<table border="1" class="table table-striped">
			<thead>
		<tr><th>NOM</th><th>PRENOM</th><th>EMAIL</th><th>ACTIONS</th></tr>
			</thead>
			
			<tbody>
			<s:iterator value="lstAuteurs">
			<tr>
					<td><s:property value="nom"/></td>
					<td><s:property value="prenom"/></td>
					<td><s:property value="email"/></td>
					
					<td>
						<s:a action="editauteur/%{id}"
								cssClass="btn btn-warning btn-block">Edition
						</s:a>
						<s:form action="deleteauteur" method="post" theme="simple">
							<s:hidden name="id" />
							<s:submit value="Supprimer" class="btn btn-danger btn-block"/>
						</s:form>
					</td>				
				</tr>
			</s:iterator>
						
				
						
			</tbody>			
	</table>	
	<s:form action="create" method="post" theme="simple">
		<s:submit value="Enregistrer nouvel Auteur" class="btn btn-success"/>
	</s:form>
</div>
</body>
</html>