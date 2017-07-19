<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<h2>Fabriquons une figure!!!</h2>
<form action="fabricationFigure" method="POST">
		<table>
		<tr>
					<td><label for="taille">Choisisser la taille de la figure :</label></td>
					<td><input type="text" name="taille" id="taille" /></td>
				</tr>
				<tr>
					<td><label for="choixfigure">Choisir la figure : </label></td>					
					<td>
					<select name="choixfigure" id="choixfigure">
						<option value="0">Triangle</option>
						<option value="1">Carée</option>										
					</select>
					</td>
				</tr>
				
				<tr>
					<td colspan="2"><input type="submit" value="envoyer"></td>
				</tr>
		</table>

</form>
</body>
</html>