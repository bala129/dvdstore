<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<html>
<body>
<form action="/dvdstore/order/admin" method="get" align="right">
<button type="submit" style="margin-right: 10px;" >Home</button><br>
</form>

<table style="text-align:center;width:100%">
    <tr>
        <th>Order Id</th>
        <th>Customer Id</th>
        <th>Dvd Id</th>
        <th>Dvd Name</th>
        <th>Language</th>
        <th>Price</th>
        <th>Order Date</th>
        <th>Address</th>
    </tr>
 <c:forEach var= "order" items= "${orders}">
    <tr>
        <td>${order.orderId}</td>
        <td>${order.customerId}</td>
        <td>${order.dvd.dvdId}</td>
        <td>${order.dvd.dvdName}</td>
        <td>${order.dvd.language}</td>
        <td>${order.dvd.price}</td>
        <td>${order.orderDate}</td>
        <td>${order.address}</td>
    </tr>
 </c:forEach>
</table>
</body>
</html>
