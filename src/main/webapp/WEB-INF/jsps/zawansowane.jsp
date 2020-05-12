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
<table class="tab1">
<tr><td>Add or delete directory</td></tr>

				
					<c:choose>

							<c:when test="${fn:contains(fn:replace(i,'192.168.1.3',''),'.')}">
								

							
			
				<tr>
					<td><f:form modelAttribute="fLoad" action="delDir">
							<f:hidden path="path" value="${i}" />
							<input id="but" type="submit" value="Usuń plik" />
						</f:form></td>
					

				</tr>
				</c:when>
				<c:otherwise>

					
					
		
				
					
					<tr>
					

						<f:form modelAttribute="fLoad" action="mkdir"
							style="margin=left:20px">
							<td><f:hidden path="path" value="${i}" /> <f:input
									path="nazwaFolderu" type="text" style="width:100px" /> <input
								id="but" type="submit" value="Utwórz Folder" id="but" /></td>
						</f:form>
						<f:form modelAttribute="fLoad" action="delDir"
							style="margin=left:20px">
							<td><f:hidden path="path" value="${i}" /> <input id="but"
								type="submit" value="Usuń Folder" /></td>
						</f:form>
						

</tr>

</table>
<br><br><br>
<table class="tab1">


<f:form modelAttribute="fLoad" action="loadfile"
							enctype="multipart/form-data">
							<td><f:hidden path="path" value="${i}" />
	<tr>	
						
<th></th>
<th>DocumentCategory</th>
<th>NavigationTags</th>
<th>Language</th>
<th>Scope</th>
<th>Users</th>
<th>Groups</th>
<th></th>
</tr>
<tr>
<td><f:input path="Title" placeholder="Title"></f:input></td>
<td>
<f:select path="DocumentCategory">
<f:option selected="selected" value=""></f:option>
<c:forEach items="${getallDocumentCategory}" var="z">
<f:option value="${z.getDocumentCategory()}">${z.getDocumentCategory()} </f:option>
</c:forEach>
</f:select></td>

<td>
<f:select path="NavigationTags">
<f:option selected="selected" value=""></f:option>
<c:forEach items="${allNavigationTags}" var="i">
<f:option value="${i.getNavigationTags()}">${i.getNavigationTags()}</f:option>
</c:forEach>
</f:select></td>

<td>
<f:select path="Language">
<f:option selected="selected" value=""></f:option>
<c:forEach items="${allLanguages}" var="i">
<f:option value="${i.getLanguage()}">${i.getLanguage()}</f:option>
</c:forEach>
</f:select></td>

<td>
<f:select path="Scope">
<f:option selected="selected" value=""></f:option>
<c:forEach items="${getallScopes}" var="i">
<f:option value="${i.getScope()}">${i.getScope()}</f:option>
</c:forEach>
</f:select></td>
							
							

<td>							
<f:select path="Users">
<f:option selected="selected" value=""></f:option>
<c:forEach items="${allFileUsers}" var="i">
<f:option value="${i.getLogin()}">${i.getLogin()}</f:option>
</c:forEach>
</f:select>			</td>


<td>
<f:select path="Groups">
<f:option selected="selected" value=""></f:option>
<c:forEach items="${allGroups}" var="i">
<f:option value="${i.getGroups()}">${i.getGroups()}</f:option>
</c:forEach>
</f:select></td>


<td>

 <f:input
									path="mfile" type="file" style="width: 60px;margin-left:15px"
									multiple="multiple" /> <input id="but" type="submit"
								value="Zaladuj" /></td>				
	</tr>
	</table>
	
							

						</f:form>



					


				</c:otherwise>
				</c:choose>
				
</div>
<br><br><br>

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
<f:form modelAttribute="fLoad1" action="delG">
<f:select path="Groups">
<c:forEach items="${allGroups}" var="b">
<f:option value="${b.getGroups()}">${b.getGroups()}</f:option>
</c:forEach>


<input type="submit" value="Usuń grupę"  >
</f:select>

</f:form>
</table>

<table>
<f:form modelAttribute="fLoad1" action="dodG">
<f:select path="Groups">
<c:forEach items="${allGroups}" var="b">
<f:option value="${b.getGroups()}">${b.getGroups()}</f:option>

</c:forEach>


<input type="submit" value="Dodaj grupe" >
</f:select>



</f:form>


</table>










</div>

<div style="float: left;margin-left:200px">
<table>
Udostępnione dla użytkowników :
</table>

<table style="border: 1px solid black">
<c:forEach items="${ko}" var="k">
<tr><td>${fn:replace(fn:replace(k,'.','<br>'),' ','')}</td><tr>


</c:forEach>
</table>

<table>
<f:form modelAttribute="fLoad1" action="delU">
<f:select path="Users">
<c:forEach items="${allFileUsers}" var="b">
<f:option value="${b.getLogin()}">${b.getLogin()}</f:option>
</c:forEach>


<input type="submit" value="Usuń użytkownika"  >
</f:select>

</f:form>
</table>

<table>
<f:form modelAttribute="fLoad1" action="dodU">
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
	</div>
</body>
</html>