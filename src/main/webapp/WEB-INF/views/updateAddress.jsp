<%@taglib uri = "http://www.springframework.org/tags/form" prefix = "form"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<html>
<body>
<form method="get" align="right">
<button type="submit" formaction="/dvdstore/user/logout" style = "margin-left: 90%;" >Log Out</button>
<button type="submit" formaction="/dvdstore/customer/customerMenu" style="float:left">Back</button>
</form>
<c:if test="${not empty message}">
<script>
    alert ("${message}");
</script>
</c:if>

<form:form method="get">  
<table align="center">
    <tr>
        <td>Address Line:</td>
        <td><form:input value="${address.addressLine}" path="addressLine" required="required"/></td>
    </tr>
    <tr>
        <td>City:</td>
        <td><form:input value="${address.city}" path="city" pattern="[A-Za-z]+" maxlength="20" required="required"/></td>
    </tr>
    <tr>
        <td>State:</td>
        <td><form:input value="${address.state}" path="state" pattern="[A-Za-z\s]+" maxlength="20" required="required"/></td>
    </tr>
    <tr>
        <td>Country:</td>
        <td><form:input value="${address.country}" path="country" pattern="[A-Za-z]+" maxlength="20" required="required"/></td>
    </tr>
    <tr>
        <td>Pincode:</td>
        <td><form:input value="${address.pincode}" path="pincode" pattern="[0-9]+" maxlength="6" required="required"/></td>
    </tr>
    <tr>
        <c:if test="${empty address}">
        <td><button type="submit" formaction="/dvdstore/customer/saveAddress" >Save</button></td>
        </c:if>
        
        <c:if test= "${not empty address}">
        <td><button type="submit" formaction="/dvdstore/customer/editAddress" >Update</button></td>
        </c:if>
    </tr>
    <input type="hidden" name="addressId" value="${address.addressId}">
    </table>
</form:form> 

</body>
</html>
