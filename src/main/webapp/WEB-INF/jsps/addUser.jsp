<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>WitrażDocumentsSystem</title>
<link rel="icon" href="images/siteIcon1.png">
<style type="text/css">
input {
	margin-left: 5px;
	font-family: monospace;
	font-size: 10px;
}

but {
	font-family: monospace;
	font-size: 10px;
}

body * {
	font-family: monospace;
}

html, body {
	height: 100%;
}

.lupa:hover {
	background-color: #e9e9e9;
}
.box {

display:none;

}
.trigger + box{
 display:block;
}
.tab1{
border: 1px solid black;

}
</style>
<script>


</script>


</head>
<body style="padding: 0; margin: 0;">
	<div
		style="width: 100%; height: 100%; top: 0; padding: 0; font-family: monospace">
		<div style="background: #3366CC; width: 100%; height: 30px">
			<span
				style="color: white; font-family: monospace; font-size: 16px; float: left; margin-left: 20px">WITRAŻ
				SYSTEM</span>

			<c:url value="Logout" var="logout">



			</c:url>
			<div style="float: right; color: white">
				Witaj ${checkLogin.getImie()} ${checkLogin.getNazwisko() } | <a
					href="${logout}" style="text-decoration: none; color: white">
					WYLOGUJ</a>
			</div>
		</div>


		<c:url value="newOptions" var="newOp">
							
				</c:url>
				
				<c:url value="check1" var="check1">
							
				</c:url>
				
				<c:url value="AddUser" var="AddUser">
							
				</c:url>

		
		<div style="background: #F1F1F1; width: 100%; height: 30px"><a href="${AddUser}" style="text-decoration: none">Dodaj użytkownika/Grupę</a><a href="${check1}" style="margin-left:50px;text-decoration: none">Zarządzanie dokumentami</a>
		<a href="${newOp}"  style="text-decoration: none;color:black;font-size:12px;margin-left:50px">Zawansowane opcje dokumentów</a>
		</div>


		<div style="widht: 100%; height: 100px;; margin-top: 15px">
			<div style="float: right">
				<table>
					<tr>
						<td><f:form>
								<input style="margin-left: 50px; height: 15px; width: 200px"
									type="text" placeholder="Szukaj na tej stronie">
							</f:form></td>

						<td><img class="lupa" src="images/lupa.png"
							style="height: 20px; width: 20px; border: 1px solid silver;"></td>
					</tr>
				</table>
			</div>
			<div>
				<img src="images/siteicon.png" style="margin-left: 20px:" /> 
			


			</div>
		</div>
		<br>
		<div
			style="float: left; width: 200px; height: 100%; border-right: 1px solid silver">





		</div>

		<div
			style="float: left; font-size: 10px; font-weight: bold; margin-left: 10px; margin-top: 10px;">

		
			<div>

<table class="tab1">
<tr><td><span style="font-size: 20px"></span></td></tr>
<f:form modelAttribute="logins" action="saveUSer">
<f:hidden path="id"/>
<tr><td>Login</td><td>    <f:input path="login"/></td></tr>
<tr><td>Password</td><td> <f:password path="password"/></td></tr>

<tr><td>Group </td><td><f:select path="grupa">
<f:option selected="-"  value="-"></f:option>
<c:forEach items="${allGroups}" var="p">
<f:option value="${p.getGroups().trim()}">${p.getGroups().trim()}</f:option>
</c:forEach>
</f:select>
</td></tr>

<tr><td>Stanowisko </td><td><f:select path="stanowisko">
<f:option selected="-"  value="-"></f:option>
<c:forEach items="${allStanowisko}" var="p">
<f:option value="${p.getStanowisko().trim()}">${p.getStanowisko().trim()}</f:option>
</c:forEach>
</f:select>
</td></tr>



<tr><td>Imie</td><td><f:input path="imie" value="${p.getImie() }"/></td></tr>
<tr><td>Nazwisko</td><td><f:input path="nazwisko" value="${p.getNazwisko()}"/></td></tr>
<tr><td><input type="submit" value="Add"></td></tr>
</f:form>


</table>

<table class="tab1">
<tr><td><span style="font-size: 20px">Dodaj grupę</span></td></tr>
<f:form modelAttribute="groups" action="Addgroup">
<tr><td>Grupa </td><td><f:input path="Groups"/></td></tr>
<tr><td><input type="submit" value="add"></td></tr>
</f:form>
</table>


<table class="tab1">
<tr><td><span style="font-size: 20px">Dodaj stanowisko</span></td></tr>
<f:form modelAttribute="stanowisko" action="Addstanowisko">
<tr><td>Stanowisko</td><td><f:input path="Stanowisko"/></td></tr>
<tr><td><input type="submit" value="add"></td></tr>
</f:form>
</table>
<br><br>




<table style="border: 1px solid black">
<tr><td> Lista wszystkich użytkowników</td></tr>
<tr>
<th>Login</th>
<th>Hasło</th>
<th>Imię</th>
<th>Nazwisko</th>
<th>Stanowisko</th>
<th>Edytuj Użytkownika<</th></tr>

</tr>
<c:forEach items="${allFileUsers}" var="i">
<c:url value="editUSerr" var="z">
<c:param name="idUser" value="${i.id}"></c:param>
</c:url>



<tr><td>${i.login}</td>
<td>${i.password}</td>
<td>${i.imie}</td>
<td>${i.nazwisko }</td>
<td>${i.stanowisko}</td>
<td style="background:blue"><a href="${z}" style="color:white;text-decoration: none">Edytuj użytkownika</a></td></tr>
</c:forEach>
</table>

</div>
			
		</div>



	</div>
</body>
</html>