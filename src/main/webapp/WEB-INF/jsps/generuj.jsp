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
<title>Insert title here</title>
<script type="text/javascript">

function copy(inElement) {
  if (inElement.createTextRange) {
    var range = inElement.createTextRange();
    if (range)
      range.execCommand('Copy');
  } else {
    var flashcopier = 'flashcopier';
    if(!document.getElementById(flashcopier)) {
      var divholder = document.createElement('div');
      divholder.id = flashcopier;
      document.body.appendChild(divholder);
    }
    document.getElementById(flashcopier).innerHTML = '';
    var divinfo = '<embed src="_clipboard.swf" FlashVars="clipboard='+encodeURIComponent(inElement.value)+'" width="0" height="0" type="application/x-shockwave-flash"></embed>';
    document.getElementById(flashcopier).innerHTML = divinfo;
  }
}
 



</script>
</head>
<body>


<textarea rows="70" cols="500" style="font-size: 5px">
<!DOCTYPE html>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
  </head>
  <div style="font-family: Arial, sans-serif;font-size:14px;color:#000;width:694px;padding-left:40px;line-height: 20px;">
    <div style="margin-bottom:20px;">
      
 
      <div style="line-height: 20px;">
       

        <div style="color:#004970;">
 ${stopka.getPole5()}
 ${stopka.getImie()} ${stopka.getNazwisko()}<br>
 ${stopka.getStanowisko()}<br>

          <span style="color:#f15a22;">E-MAIL:</span> <a style="text-decoration: none;color:#004970;" href="mailto:${stopka.getMail()}">${stopka.getMail()}</a><br>
  ${stopka.getPole1()}
  ${stopka.getPole2()}
  ${stopka.getPole3()}
  ${stopka.getPole4()}
     
 </div>
      </div>
    </div>
    <div style="clear: both;"></div>
    <div style="padding-bottom:20px;width:694px;font-size:10px;line-height: 16px;text-transform: uppercase;">
      <div style="padding-top:10px;width:50%;">
        <a href="http://www.witraz.eu/">
          <img src="https://witraz.eu/pliki/witraz-okna.png">
        </a>
        <br>
        <br>
        <br>
        <div style="font-size:14px;color:#004970;line-height: 18px;">
          <span><strong>Witraż sp. z o.o. sp. k.</strong></span><br>
          <span style="color:#004970;">Łyski ul. Warszawska 68<br> 16-070 Choroszcz</span><br>
          <span style="color:#f15a22;">TEL.</span> +48 85 713 13 70<br>
          <span style="color:#f15a22;">E-MAIL:</span> <a style="text-decoration: none;color:#004970;" href="mailto:info@witraz.eu">info@witraz.eu</a><br>
          <a style="text-decoration: none;color:#004970;" href="http://www.witraz.eu">www.witraz.eu</a><br>

        </div>


      </div>
 
    </div> 
      </div>
 <span style="font-size:12px; color:#004970;">
    Klauzula informacyjna RODO znajduje się pod adresem <a href="https://www.witraz.eu/rodo/rodo.html">www.witraz.eu/rodo/rodo.html</a><br>
    The GDPR information clause can be found at <a href="https://www.witraz.eu/rodo/rodo.html">www.witraz.eu/rodo/rodo.html</a> </span>
       <div style="clear: both;"></div> 
</html>


</textarea>

<table>
<tr><td><span style="font-size: 30px">Instrukcja<span></td></tr>
<tr><td><img src="images/sc2.png"></td></tr>
<tr><td>Po wygenerowany kodzie kopiujemy jego całą zawartość i wklejamy do klienta poczty.</td></tr>
<tr><td><img src="images/sc3.png"></td></tr>
<tr><td><img src="images/sc4.png"></td></tr>
<tr><td><img src="images/sc5.png"></td></tr>


</table>

</body>
</html>