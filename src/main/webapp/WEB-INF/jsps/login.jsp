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


</head>

<style>
body {
	height: 100%;
	background-image: url("images/gory.jpg");
}

body * {
	font-family: monospace;
}
#log{
background: orange;
color:white;
 padding: 10px 20px;
 border: none; 
 allign: center;
 margin-top:20px;
text-decoration: none;
 font-size: 16px;
}
</style>
<body>
	<div style="width: 100%">
		<div style="margin-left: 500px; margin-top: 50px">
			<span
				style="font-size: 50px; color: white; text-align: center; margin-left: 20px">Login
				to<br>myWITRAZ
			</span> <br>
			<br>
			<br>
			<br>
			<br>
			<br>

			<div
				style="margin-top: 50px; background: white; border-radius: 5px; width: 275px; height: 280px;">
				<table>
					<tr>
						<td style="border-bottom: 1px solid #3366CC;align:center;width: 100%;margin-left:40px "><img src="images/witraz.jpg"
							style="width: 170px;height: 70px;margin-left:60px;">&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp
						</td></tr>
						
						<f:form modelAttribute="login" action="check">
							<tr>
							<tr>
								<td align="center"><span style="color:silver;font-size: 20px">USERNAME</span></td>
							</tr>
							<tr>
								<td align="center"><f:input path="login"  style="background:#EDE7F6"/></td>
							</tr>
					
					<tr>
						<td align="center"><span style="color:silver;font-size: 20px">PASSWORD</span></td>
					</tr>
					<tr><span style="color:red">${error}</span></tr><tr></tr>
					<tr>
						<td align="center" ><f:input path="password" style="background:#EDE7F6" type="password"/></td>
					</tr>
					<tr>
						<td align="center"><input type="submit" value="LOGON" size="30"
							 id="log"></td>
					</tr>
					</f:form>
				</table>
			</div>
		</div>
	</div>
</body>
</html>