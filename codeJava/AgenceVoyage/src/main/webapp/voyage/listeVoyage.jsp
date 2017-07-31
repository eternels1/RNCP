<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Liste des Voyages Proposés ! </title>
<link rel="stylesheet" type="text/css" href="vendors/bootstrap/css/bootstrap.css" />
</head>
<body>
<div class="container">
	<h2>Liste des Voyages Proposés : </h2>
	<table border="1" class="table table-striped">
			<thead>
		<tr><th>LIBELLE</th><th>DESTINATION</th><th>PRIX</th><th>AGENCE</th><th>PASSEPORT</th><th>DATE DEPART</th><th>DATE RETOUR</th><th>ACTIONS </th></tr>
			</thead>
			
			<tbody>
			<s:iterator value="voyages">
			<tr>
					<td><s:property value="libelle"/></td>
					<td><s:property value="destination"/></td>
					<td><s:property value="prix"/>€</td>
					<td><s:property value="agence"/></td>
					<td style="text-align: center">
					<s:form theme="simple"><s:checkbox  name="passeport" /></s:form></td>
					<td><s:property value="dateDepart"/></td>
					<td><s:property value="dateArrivee"/></td>
					<td>
						<s:form action="edit" method="post" theme="simple">
							<s:hidden name="id" />
							<s:submit value="Editer" class="btn btn-primary btn-block"/>
						</s:form>
						<s:form action="delete" method="post" theme="simple">
							<s:hidden name="id" />
							<s:submit value="Supprimer" class="btn btn-danger btn-block"/>
						</s:form>
					</td>				
				</tr>
			</s:iterator>
						
				
						
			</tbody>			
	</table>	
	<s:form action="create" method="post" theme="simple">
		<s:submit value="creer nouveau Voyage" class="btn btn-success"/>
	</s:form>
</div>
</body>
</html>