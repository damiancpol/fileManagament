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
<link rel="icon" href="images/siteIcon.png">
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
	display: none;
}

.trigger+box {
	display: block;
}

td {
	padding-right: 20px;
}
</style>
<script>
	
</script>


</head>
<body style="padding: 0; margin: 0; opacity: 1.2;">
	<div style="width: 100%; height: 100%; top: 0; padding: 0; z-index: 1">
		<img src="images/oknoba.png"
			style="opacity: 0.1; margin-left: 400px; margin-top: 100px">
	</div>
	<div
		style="width: 100%; height: 100%; top: 0; padding: 0; font-family: monospace; position: absolute;">
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


		<div style="background: #F1F1F1; width: 100%; height: 30px">
			<a href="${AddUser}" style="text-decoration: none">Dodaj
				użytkownika/Grupę</a><a href="${check1}"
				style="margin-left: 50px; text-decoration: none">Zarządzanie
				dokumentami</a> <a href="${newOp}"
				style="text-decoration: none; color: black; font-size: 12px; margin-left: 50px">Zawansowane
				opcje dokumentów</a>
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
				<img src="images/siteicon.png" style="margin-left: 20px:" /> <span
					style="color: black; font-size: 30px; font-style: monospace">WitrazDocs→</span><span
					style="color: black; font-size: 25px; font-style: monospace">${pathToFolder.replace("E:\\pliki\\src\\main\\resources\\static\\","")}</span>
				<br> <br>
				<div style="margin-left: 200px;">
					<c:forEach items="${list}" var="i" begin="0" end="0">

						<c:url value="Up" var="upp">
							<c:param name="up" value="${i}">
							</c:param>
						</c:url>

						<br>

						<a href="${upp}" style="text-decoration: none; font-size: 40px">↩</a>


						<br>
						<br>

					</c:forEach>
				</div>


			</div>
		</div>
		<br>
		<div
			style="float: left; width: 200px; height: 100%; border-right: 1px solid silver">
			<div style="">

				<table>
					<f:form modelAttribute="flo" action="searches">
						<tr>
							<td><span style="font-size: 12px; font-style: monospace">
									WYSZUKAJ PO OPISIE</span></td>
						</tr>
						<tr>
							<td><f:input path="Title"
									style="height: 20px; width: 135px; border: 1px solid silver;" /></td>
							<td><input type="image" class="lupa" src="images/lupa.png"
								style="height: 20px; width: 20px; border: 1px solid silver;"></td>
						</tr>

					</f:form>
					<f:form modelAttribute="flo" action="searches1">

						<tr>
							<td><span style="font-size: 12px; font-style: monospace">
									WYSZUKAJ PO KATEGORII</span></td>
						</tr>
						<tr>
							<td><f:input path="DocumentCategory"
									style="height: 20px; width: 135px; border: 1px solid silver;" /></td>
							<td><input type="image" class="lupa" src="images/lupa.png"
								style="height: 20px; width: 20px; border: 1px solid silver;"></td>
						</tr>
					</f:form>
					<f:form modelAttribute="flo" action="searches2">

						<tr>
							<td><span style="font-size: 12px; font-style: monospace">
									WYSZUKAJ PO TAGU</span></td>
						</tr>
						<tr>
							<td><f:input path="NavigationTags"
									style="height: 20px; width: 135px; border: 1px solid silver;" /></td>
							<td><input type="image" class="lupa" src="images/lupa.png"
								style="height: 20px; width: 20px; border: 1px solid silver;"></td>
						</tr>
					</f:form>

					<f:form modelAttribute="flo" action="searches3">
						<tr>
							<td><span style="font-size: 12px; font-style: monospace">
									WYSZUKAJ PO JĘZYKU</span></td>
						</tr>
						<tr>
							<td><f:input path="Language"
									style="height: 20px; width: 135px; border: 1px solid silver;" /></td>
							<td><input type="image" class="lupa" src="images/lupa.png"
								style="height: 20px; width: 20px; border: 1px solid silver;"></td>
						</tr>
					</f:form>


				</table>




			</div>










		</div>


		<div
			style="float: left; font-size: 10px; font-weight: bold; margin-left: 10px; margin-top: 10px;">

			<table>
				<tr></tr>
				<tr>
					<td style="padding-bottom: 20px"><span
						style="color: #0033FF; font-size: 12px; font-style: monospace">Wszystkie
							dokumenty</span></td>

					<f:form modelAttribute="fLoad" action="searchFile">
						<td><f:input path="searchFiles"
								style="margin-left:50px;height:15px;width:150px" type="text"
								placeholder="Znajdz plik" /></td>
						<td><input type="image" class="lupa" src="images/lupa.png"
							style="height: 20px; width: 20px; border: 1px solid silver;"></td>
					</f:form>
				</tr>
<tr><td></td></tr>
				<c:url value="newOptions" var="newOp">

				</c:url>





			</table>
			<table>


				<tr style="margin-top: 30px">
					<th><span style="color: silver">Nazwa pliku</span></th>
					<th><span style="color: silver">Opis</span></th>
					<th><span style="color: silver">Kategoria</span></th>
					<th><span style="color: silver">Tagi</span></th>
					<th><span style="color: silver">Języka</span></th>
					<th><span style="color: silver">Zakres</span></th>
					<th><span style="color: silver">Zmodyfikowany</span></th>
					<th><span style="color: silver">Zmodyfikowany przez</span></th>
				</tr>

				<c:forEach items="${list}" var="i">

					<c:url value="NazwaFolderu" var="nazwa">
						<c:param name="nazwafolderu" value="${i}"></c:param>
					</c:url>

					<c:url value="NazwaFolderuS" var="nazwas">
						<c:param name="nazwafolderu" value="${i}"></c:param>
					</c:url>

					<c:choose>

						<c:when test="${fn:contains(i,'.')}">
							<tr>


								<td><img style="width: 15px; height: 20px"
										src="images/text.png" /> <a href="${i.getPath().replace("E:\\pliki\\src\\main\\resources\\static\\","")}"
										style="text-decoration: none; color: black;" target="_blank">
											${i.getName()}</a></td>



								<c:forEach items="${allFileLoad}" var="po" >

									<c:if test="${fn:replace(po.getPath(),' ','') eq fn:replace(i.getPath(),' ','') }">
                                 
										<td>${po.getTitle()} </td>
										<td>${po.getDocumentCategory() }</td>
										<td>${po.getNavigationTags() }</td>
										<td>${po.getLanguage() }</td>
										<td>${po.getScope() }</td>
										<td>${po.getReleaseDate() }</td>
										<td>${po.getModifiedBy()}</td>
									
									</c:if>
								</c:forEach>
							</tr>





							<c:url value="zawI1" var="zawa1">
								<c:param name="zawansowane1" value="${i}"></c:param>



							</c:url>


							<tr>
								<td><a href="${zawa1}"
									style="text-decoration: none; color: black" target="blank">
										Ustawienia pliku◄</a></td>
								<td><f:form modelAttribute="fLoad" action="delDir">
										<f:hidden path="path" value="${i}" />
										<input id="but" type="submit" value="Usuń" />
									</f:form></td>


							</tr>
						</c:when>
						<c:otherwise>
<c:url value="skaner" var="z">
<c:param name=""></c:param>

</c:url>
							<tr>


								<f:form modelAttribute="fLoad" action="loadfile"
									enctype="multipart/form-data">
									<td><a href="${nazwa}"
										style="text-decoration: none; color: black;"> <img
											style="width: 20px; height: 15px" src="images/folder2.png" />
											${i.getName()} 
									</a></td>

									<td></td>
									<td></td>
									<td></td>
									<td></td>
									<td></td>
									<td></td>
									<td style="padding-left: 40px"><jsp:useBean
											id="dateValue1" class="java.util.Date" /> <jsp:setProperty
											name="dateValue1" property="time" value="${i.lastModified()}" />
										<fmt:formatDate type="date" timeStyle="long" dateStyle="long"
											value="${dateValue1}" /></td>
									<td></td>



								</f:form>

							</tr>
							<tr>

							</tr>
							<c:url value="zawI" var="zawa">
								<c:param name="zawansowane" value="${i}"></c:param>



							</c:url>


							<tr>
								<td><a href="${zawa}"
									style="text-decoration: none; color: black" target="blank">Ustawienia
										folderu◄</a></td>

							</tr>




						</c:otherwise>
					</c:choose>
					<tr>
				</c:forEach>
			</table>
		</div>



	</div>

</body>
</html>