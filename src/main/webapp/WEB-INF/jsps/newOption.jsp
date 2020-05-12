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

		
		<div style="background: #F1F1F1; width: 100%; height: 30px"><a href="${AddUser}">Dodaj użytkownika/Grupę</a><a href="${check1}" style="margin-left:50px;text-decoration: none">Zarządzanie dokumentami</a>
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
<f:form modelAttribute="DocumentCategory" action="saveNewSetting1">
<f:input path="DocumentCategory"  placeholder="DocumentCategory"/>
<input type="submit" value="Dodaj">
</f:form>

<f:form modelAttribute="NavigationTags" action="saveNewSetting2">
<f:input path="NavigationTags"  placeholder="NavigationTags"/>
<input type="submit" value="Dodaj">
</f:form>

<f:form modelAttribute="Language" action="saveNewSetting3">
<f:input path="Language"  placeholder="Language"/>
<input type="submit" value="Dodaj">
</f:form>

<f:form modelAttribute="Scope" action="saveNewSetting">
<f:input path="Scope"  placeholder="Scope"/>
<input type="submit" value="Dodaj">
</f:form>






</div>
			
		</div>



	</div>
</body>
</html>