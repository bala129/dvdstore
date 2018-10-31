<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<html>
<head>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<link rel="stylesheet" type="text/css" href="<c:url value = '/resources/dvdStore.css' />">
</head>
<body class="background">

<form action="/dvdstore/user/logout" method="get" align="right">
<button type="submit" class="home" >LogOut</button><br>
</form>

<form action="/dvdstore/dvd/dvdMenu" method="get" align="center">
<button type="submit" name="choice" value="dvd" class="button"> DVD</button><br>
</form>

<form action="/dvdstore/category/categoryMenu" method="get" align="center">  
 <button type="submit" class="button"> Category</button><br>
</form> 

</form> 
<form action="/dvdstore/customer/viewCustomers-admin" method="get" align="center">
<button type="submit" onclick="setValue()" class="button"> View All Customer</button><br>
</form>
<form action="/dvdstore/order/viewOrders" method="get" align="center">
<button type="submit" class="button"> View All Orders</button><br>
</form>
</body>
<c:if test="${not empty message}">
<script>
    alert ("${message}");
</script>
<c:remove var="message" scope="session" />
</c:if>
</html>
