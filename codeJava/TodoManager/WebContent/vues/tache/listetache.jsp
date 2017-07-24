<%@page import="TodoManager.metier.Tache"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Todo Manager HomeList</title>
<link rel="stylesheet" type="text/css" href="vendors/bootstrap/css/bootstrap.css" />
</head>
<body>
<div class="container">
		<h2>Liste de Tache(s) à faire :</h2>
		<table class="table table-stripped">
			<thead>
				<tr><th>ID</th><th><a href="TodoManager?tri=description">DESCRIPTION</a></th><th><a href="TodoManager?tri=priorite">PRIORITE</a></th><th>CONTEXTE</th><th>FINISHED</th><th>ACTIONS</th></tr>
			</thead>
			
			<tbody>
				<% 
				
				List<Tache> lstTaches= (List<Tache>)request.getAttribute("lstTaches");
				for(Tache t : lstTaches){
				%>
					<tr>
						<td><%= t.getId() %></td>
						<td><%= t.getDescription() %></td>
						<td><%= t.getPriorite() %></td>
						<td><a href="TodoManager?filtre=<%= t.getContexte() %>"><%= t.getContexte() %></a></td>
						<td><%= t.isFinished() %></td>
						<td>
							<a href="TacheEdit?tacheId=<%= t.getId() %>" class="btn btn-primary">Edition</a>
						
						<form action="TodoManager" method="POST" style="display: inline-block;">
							<input type="hidden" name="tacheId" id="tacheId" value="<%= t.getId()%>" />
							<button type="submit" class="btn btn-danger">Supprimer</button>						
						</form>
						</td>		
					</tr>
			<%
			}			
			
			%>
		
		
			</tbody>
		</table>
		<a href="TacheEdit" class="btn btn-success">Céer une Tache</a>

</div>
</body>
</html>