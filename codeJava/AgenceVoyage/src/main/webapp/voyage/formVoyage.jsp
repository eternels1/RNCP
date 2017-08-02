<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<s:url action="" var="baseurl" />
<link href="<:property value='baseurl' />vendors/bootstrap/css/bootstrap. ">
<title>Les voyages de rêves !</title>

</head>
<body>
<h2>Edition Voayge</h2>
<s:form action="save" method="post">
	<s:hidden name="Id" />
	<s:textfield name="libelle" label="Libillé du voyage" />
	<s:textfield name="destination" label="Destination du voyage" />
	<s:textfield name="prix" label="Prix du voyage" />
	<s:select list="agences" listKey="id" listValue="nom" name="agenceId"></s:select>
	<td style="text-align: center">
					<s:checkbox  name="passeport" label="Passeport Requis" /></td>
	<s:textfield name="dateDepart" label="Date de départ"/>
	<s:textfield name="dateArrivee" label="Date de départ"/>
	<s:submit value="Sauver"/>
</s:form>
</body>
</html>