<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<html>
<body>
<form action="/dvdstore/order/admin" method="get" align="right">
<button type="submit" style = "margin-right: 10px;" >Home</button><br>
</form>

 <table style = "text-align:center;width:100%">
  <tr>
    <th>Customer Id</th>
    <th>Name</th>
    <th>Mobile No</th>
    <th>Mail Id</th>
    <th>Action</th>
  </tr>
  <c:forEach var="customer" items="${customers}">
  <tr>
    <td>${customer.customerId}</td>
    <td>${customer.customerName}</td>
    <td>${customer.mobileNo}</td>
    <td>${customer.mailId}</td>
    <td>
        <form action="/dvdstore/customer/viewDetail-admin" method="post" >
        <button type="submit" >View</button>
        <input type="hidden" name="customerId" value=${customer.customerId}>
        </form>
    </td>
  </tr>
  </c:forEach>
 </table>
</form> 
</body>
</html>
