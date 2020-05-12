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
.tab2{
border-left: 1px solid black;
border-right: 1px solid black;
border-top: 1px solid black;
}
.tab3{
border-left: 1px solid black;
border-right: 1px solid black;
border-bottom: 1px solid black;
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





		<div style="background: #F1F1F1; width: 100%; height: 30px"></div>



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
		
		</div>
<div>

<div>
<div style="float:left">

<table>
<tr><td>Udostępnione dla  grupy: </td></tr>
</table>
<table style="border: 1px solid black">


<c:forEach items="${mm}" var="g">
<tr><td>${fn:replace(g,'.','<br>')}</td></tr>


</c:forEach>



</table>


<table>
<f:form modelAttribute="fLoad" action="delUG">
<f:select path="Groups">
<c:forEach items="${allGrupa}" var="b">
<f:option value="${b.getGroups()}">${b.getGroups()}</f:option>
</c:forEach>


<input type="submit" value="Usuń grupę"  >
</f:select>

</f:form>
</table>

<table>
<f:form modelAttribute="fLoad" action="dodUG">
<f:select path="Groups">
<c:forEach items="${allGrupa}" var="b">
<f:option value="${b.getGroups()}">${b.getGroups()}</f:option>

</c:forEach>


<input type="submit" value="Dodaj grupę" >
</f:select>



</f:form>


</table>



</div>






</div>

<div style="float: left;margin-left:100px">
<table>
Udostępnione dla użytkowników :
</table>
<table style="border: 1px solid black">
<c:forEach items="${ko}" var="k">
<tr><td>${fn:replace(k,'.','<br>')}</td><tr>





</c:forEach>




</table>

<table>
<f:form modelAttribute="fLoad1" action="delUP">
<f:select path="Users">
<c:forEach items="${allFileUsers}" var="b">
<f:option value="${b.getLogin()}">${b.getLogin()}</f:option>
</c:forEach>


<input type="submit" value="Usuń użytkownika"  >
</f:select>

</f:form>
</table>

<table>
<f:form modelAttribute="fLoad1" action="dodUP">
<f:select path="Users">
<c:forEach items="${allFileUsers}" var="b">
<f:option value="${b.getLogin()}">${b.getLogin()}</f:option>

</c:forEach>


<input type="submit" value="Dodaj użytkownika" >
</f:select>



</f:form>


</table>





</div>








</div>
</body>
</html>