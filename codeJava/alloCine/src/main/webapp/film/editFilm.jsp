<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Edition Film</title>
</head>
<body>
<h2>Edition du Film </h2>


<s:form method="post" action="film/save">
	<s:hidden name="id"/>
	<s:textfield name="titre" label="Titre du film"/>
	<s:textarea name="synopsys" label="synopsys du film"/>
	<s:textfield name="annee" label="année du film"/>
	<s:select name="rid" list="allRealisateurs" listKey="id" listValue="nom" label="selctionner le réalisateur"/>
	<s:submit />
	</s:form>
	<s:set name="filmid" value="id" />
	<s:if test="%{id != 0}">
		<h3>liste des acteurs déjà associées au film</h3>
		<p>
			cliquez sur un acteur pour le retirer<br />
			<s:iterator value="acteurs" status="stat">
				<s:a action="film/actremove/%{filmid}/%{id}">
					<s:property value="nom"/>
				</s:a><s:if test="#stat.last==false">,</s:if>
			</s:iterator>
		</p>
		<h3>liste de tous les acteurs</h3>
		<p>
			cliquez sur un acteur pour l'ajouter<br />
			<s:iterator value="allActeurs" status="stat">
				<s:a action="film/actadd/%{filmid}/%{id}">
					<s:property value="nom"/>
				</s:a><s:if test="#stat.last==false">,</s:if>
			</s:iterator>
		</p>
	</s:if>
	

</body>
</html>