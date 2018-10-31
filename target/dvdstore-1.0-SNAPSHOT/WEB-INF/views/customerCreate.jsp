<%@taglib uri = "http://www.springframework.org/tags/form" prefix = "form"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<html>
<body>

<c:if test="${not empty customer}">
<form action="/dvdstore/customer/customerMenu" method="get" align="left">
<button type="submit" style = "margin-right: 10px;">Back</button><br>
</form>
</c:if>

<c:if test="${empty customer}">
<form action="customer" method="get" align="left">
<button type="submit" name="choice" value="logout"style = "margin-right: 10px;" >Back</button><br>
</form>
</c:if>

<c:if test="${not empty message}">
<script>
    alert ("${message}");
</script>
</c:if>

<c:if test="${empty customer}">
    <h3 align="center"> Enter the Details </h3> 
</c:if>

<form method="post">  
    <table align="center">
    <tr>
        <td>Name:</td> 
        <td><input type="text" value="${customer.customerName}" name="customerName" pattern="[A-Za-z\s]+" maxlength=30 required/></td>  
    </tr>
    <tr>
        <td>MobileNo:</td>
        <td><input type="text" value="${customer.mobileNo}" name="mobileNo" pattern="[6789]{1}[0-9]{9}" maxlength=10 required/></td>
    </tr>
    <tr>  
        <td>Mail:</td>
        <td><input type="email" value="${customer.mailId}" name="mailId" required/></td>
    </tr>
    <c:if test="${empty customer}">
    <tr>
        <td>Address Line:</td>
        <td><input type="text" name="address[0].addressLine" required/></td>
    </tr>
    <tr>
        <td>City:</td>
        <td><input type="text"  name="address[0].city" pattern="[A-Za-z]+" maxlength=20 required/></td>
    </tr>
    <tr>
        <td>State:</td>
        <td><input type="text" name="address[0].state" pattern="[A-Za-z\s]+" maxlength=20 required/></td>
    </tr>
    <tr>
        <td>Country:</td>
        <td><input type="text" name="address[0].country" pattern="[A-Za-z]+" maxlength=20 required/></td>
    </tr>
    <tr>
        <td>Pincode:</td>
        <td><input type="text" name="address[0].pincode" pattern="[0-9]+" maxlength=6 required/></td>
    </tr>
    </c:if>
    <tr>
        <c:if test="${empty customer}">
        <td><button type="submit" formaction="/dvdstore/customer/createCustomer" value="create">Save</button></td>
        </c:if>
        <c:if test="${not empty customer}">
        <td><button type="submit" formaction="/dvdstore/customer/updateCustomer" value="updateCustomer">Update</button></td>
        </c:if>
    </tr>
    </table>
    <input type="hidden" name="user.userId" value="${customer.user.userId}">
    <input type="hidden" name="customerId" value="${customer.customerId}">
</form> 
</body>
</html>
