<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<html>
<body>

<form action="/dvdstore/user/logout" method="get">
<button type="submit" name="choice" value="logout" style = "margin-left:90%;">Log Out</button><br>
</form>

    <h3 align="center">Welcome ${customer.customerName}</h3>
    <table align="center">
    <tr>
        <td>Name:</td> 
        <td>${customer.customerName}</td>  
    </tr>
    <tr>
        <td>MobileNo:</td>
        <td>${customer.mobileNo}</td>
    </tr>
    <tr>  
        <td>Mail:</td>
        <td>${customer.mailId}</td>
    </table>
    
    <form method="get" align="center">  
    <button type="submit" id="profile" formaction="/dvdstore/customer/editProfile" style= "width:200px">Edit Profile</button>
    <button type="button" Onclick="visibleAddress()" style= "width:200px">View Address</button>
    <button type="button" Onclick="visibleOrders()" style= "width:200px">View Orders</button>
    <button type="submit" formaction="/dvdstore/customer/purchase" style= "width:200px">Purchase Dvd</button>
    <button type="submit" id="address" formaction="/dvdstore/customer/addAddress" style= "width:200px">Add Address</button>
    </form>
    
    <div id="addressInfo" style="display:none">
    <table style="text-align:center;width:100%">
    <tr>
        <th>Address Line:</th>
        <th>City</th>
        <th>State</th>
        <th>Country</th>
        <th>Pincode</th>
        <th>Action</th>
    </tr>
    <c:forEach var= "address" items= "${customer.address}">
    <tr>
        <td>${address.addressLine}</td>
        <td>${address.city}</td>
        <td>${address.state}</td>
        <td>${address.country}</td>
        <td>${address.pincode}</td>
        
        <form method="post" align="right">
        <td><button type="submit" formaction="/dvdstore/customer/editAddressForm" >Update</button>
        <button type="submit" formaction="/dvdstore/customer/removeAddress" >Remove</button></td>
        <input type="hidden" name="addressId" value=${address.addressId}>
        </form>
        
    </tr>
    </c:forEach>
    </table>
    </div>
    
    <div id="orderInfo" style="display:none">
    <table style="text-align:center;width:100%">
    <tr>
        <th>Order Id</th>
        <th>Dvd Name</th>
        <th>Language</th>
        <th>Price</th>
        <th>Order Date</th>
        <th>Address</th>
        <th>Action</th>
    </tr>
    <c:forEach var= "order" items= "${customer.orders}">
    <tr>
        <td>${order.orderId}</td>
        <td>${order.dvd.dvdName}</td>
        <td>${order.dvd.language}</td>
        <td>${order.dvd.price}</td>
        <td>${order.orderDate}</td>
        <td>${order.address}</td>
        <form method="post" align="right">
        <c:if test="${order.orderDate eq today}"> 
        <td><button type="submit" formaction="/dvdstore/customer/removeOrder" >Cancel</button></td>
        </c:if>
        <c:if test="${order.orderDate ne today}"> 
        <td><button type="submit" formaction="/dvdstore/customer/removeOrder" disabled>Cancel</button></td>
        </c:if>
        <input type="hidden" name="orderId" value="${order.orderId}">
        <input type="hidden" name="orderDate" value="${order.orderDate}">
        </form>
    </tr>
    </c:forEach>
    </table>
    </div>
    

</body>

<c:if test="${not empty message}">
<script>
    alert ("${message}");
</script>
<c:remove var="message" scope="session" />
</c:if>

<script>
function visibleAddress() {
    document.getElementById("addressInfo").style.display='block';
    document.getElementById("orderInfo").style.display='none';
}

function visibleOrders() {
    document.getElementById("orderInfo").style.display='block';
    document.getElementById("addressInfo").style.display='none';
}

</script>

</html>
