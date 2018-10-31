<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<html>
<body>
<head>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<link rel="stylesheet" type="text/css" href="<c:url value = '/resources/dvdStore.css' />">
</head>
<form action="customer" method="get">
<button type="submit" formaction="/dvdstore/user/logout" style = "margin-left: 90%;" >Log Out</button>
<button type="submit" formaction="/dvdstore/customer/customerMenu" style="float:left">Back</button>
</form>

<div id = "search">
<table align="center" cellspacing="20">
  <tr>
      <td>
          <form action="/dvdstore/customer/searchDvd" method="get">
          <input type="text" name="detail" placeholder= "Enter movie name" required /> 
          <button type="submit" >Search</button>
          </form>
      </td></td>
      <td>
          <form action="/dvdstore/customer/displayDvdByCategory" method="get">
          <select name="category">
          <c:forEach var="category" items="${categories}">
          <option value="${category.id}" <c:if test = "${category.id==id}"> selected </c:if> >${category.category}</option>
          </c:forEach>
          </select>
          <button type="submit" >Display By Category</button>
          </form>
      </td></td>
      <td>
          <form action="/dvdstore/customer/purchase" method="get">
          <button type="submit" >Display All DVDs</button>
      </td></td>
  </tr>
</table>
</div>

<c:if test="${not empty message}">
<script>
    alert ("${message}");
</script>
</c:if>
<form method="get">
 <div id="dvdlist" class="scroll">
 <table style ="text-align: center;width:100%">
  <tr>
    <th>Dvd Id</th>
    <th>Dvd Name</th>
    <th>Dvd Type</th>
    <th>Language</th>
    <th>Price</th>
    <th>Rating</th>
    <th>Release Date</th>
    <th>Category</th>
    <th>PurchaseDvd</th>
  </tr>
<c:forEach var="dvd" items="${dvds}">
  <tr>
    <td>${dvd.dvdId}</td>
    <td>${dvd.dvdName}</td>
    <td>${dvd.dvdType}</td>
    <td>${dvd.language}</td>
    <td>${dvd.price}</td>
    <td>${dvd.rating}</td>
    <td>${dvd.releaseDate}</td>
    <td>
    <c:forEach var="category" items= "${dvd.categories}">
    ${category.category}
    </c:forEach></td>
    <td><input type="checkbox" name="dvdIds" value="${dvd.dvdId}"></td>
  </tr>
  </c:forEach>
 </table>
 </div>
 <center><button id="buy" type="button" onclick="return dvdIsEmpty() && setVisbie()" style="width:357px;">Place Order</button></center>
 <div id="addressInfo" style="display:none">
 <table style= style="float:left" cellspacing="8">
    <tr>
        <th>Action</th>
        <th>Address Line</th>
        <th>City</th>
        <th>State</th>
        <th>Country</th>
        <th>Pincode</th>
    </tr>
    <c:forEach var= "address" items= "${customer.address}">
    <tr>
        <td><input type="radio" name="addressId" value="${address.addressId}" checked></button></td>
        <td>${address.addressLine}</td>
        <td>${address.city}</td>
        <td>${address.state}</td>
        <td>${address.country}</td>
        <td>${address.pincode}</td>
    </tr>
    </c:forEach>
    </table>
    <button type="submit" formaction="/dvdstore/customer/purchaseDvd">Purchase</button>
 </div>
</form>
</body>
<script>

function setVisbie() {
    document.getElementById("addressInfo").style.display='block';
    document.getElementById("dvdlist").style.display='none';
    document.getElementById("search").style.display='none';
    document.getElementById("buy").style.display='none';
}

function dvdIsEmpty() {
    var checkboxes = document.getElementsByName('dvdIds');
    for (var checkBox = 0; checkBox<checkboxes.length; checkBox++) {
        if (checkboxes[checkBox].checked) {
            return true;
        }
    }
    alert("please select the dvd");
    return false;
}

function checkSelect() {
    var buttons = document.getElementsByName("addressId");
    for (var button = 0; button<buttons.length; button++) {
        if (button.checked == true) {
            return true;
        }
    }
    alert("please select the address");
    return false;
}
</script>

</html>
