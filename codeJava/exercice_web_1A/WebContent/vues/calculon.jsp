<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Centre de Calcul ;-)</title>
</head>
<body>
<h2>Saisisser deux nombres : </h2>
<form action="centreCalcul" method="POST">
		<table>
				<tr>
					<td><label for="premiernombre">Votre premier nombre: </label></td>
					<td><input type="text" name="premiernombre" id="premiernombre" /></td>
				</tr>
				<tr>
					<td><label for="deuxiemenombre">Votre deuxieme nombre</label></td>
					<td><input type="text" name="deuxiemenombre" id="deuxiemenombre" /></td>
				</tr>
				<tr>
					<td>
					<label for="operateur">Votre opérateur :</label>
					</td>
					<td>
					<select name="operateur" id="operateur">
						<option value="+" selected>Adittion</option>
						<option value="-">Soustraction</option>
						<option value="*">Multiplication</option>
						<option value="/">Division</option>										
					</select>
					</td>
				</tr>
				
				<tr>
					<td colspan="2"><input type="submit" value="Calculer"></td>
				</tr>
		</table>

</form>
</body>
</html>