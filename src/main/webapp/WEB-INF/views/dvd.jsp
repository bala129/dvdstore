<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<html>
<head>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<link rel="stylesheet" type="text/css" href="<c:url value = '/resources/dvdStore.css' />">
</head>

<body class="background">

<form action="/dvdstore/dvd/admin" method="get" align="right">
<button type="submit" name="choice" value="admin" class="home" ><i class="fa fa-home"></i>Home</button><br>
</form>

<c:if test="${not empty message}">
<script>
    alert ("${message}");
</script>
</c:if>

<form action="/dvdstore/dvd/createForm" method="get" align="center">  
 <button type="submit" name="choice"  value="createForm" style= "margin: 10px;width: 235px;"> Create DVD</button><br>
</form>

<form action="/dvdstore/dvd/display" method="get" align="center">  
 <button type="submit" name="choice" value="display" style= "margin: 10px;width: 235px;"> Display all DVD</button><br>
</form>

<form action="/dvdstore/dvd/showInactiveDvd" method="get" align="center">  
 <button type="submit" name="choice" value="restoreForm" style= "margin: 10px;width: 235px;" > Restore DVD</button><br>
</form>
 
</body>
</html>
