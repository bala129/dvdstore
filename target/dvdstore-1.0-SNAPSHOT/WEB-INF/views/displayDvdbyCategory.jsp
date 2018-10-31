<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<html>
<body>
<form action="/dvdstore/category/categoryMenu" method="get" align="right">
<button type="submit" name="choice" value="home"style = "margin-right: 10px;" >Home</button><br>
</form>

<c:if test="${not empty message}">
<script>
    alert ("${message}");
</script>
</c:if>

 <table align="center" style = "text-align: center; width:100%">
  <tr>
    <th>Dvd Id</th>
    <th>Dvd Name</th>
    <th>Dvd Type</th>
    <th>Language</th>
    <th>Price</th>
    <th>Rating</th>
    <th>Release Date</th>
    <th>Action</th>
  </tr>
<c:forEach var="dvd" items="${dvds}" >
  <tr>
    <td>${dvd.dvdId}</td>
    <td>${dvd.dvdName}</td>
    <td>${dvd.dvdType}</td>
    <td>${dvd.language}</td>
    <td>${dvd.price}</td>
    <td>${dvd.rating}</td>
    <td>${dvd.releaseDate}</td>
    <form method="post">
    <td><button type="submit" formaction="/dvdstore/category/remove" onclick= "return confirm('do you want to remove the this category from - ${dvd.dvdName} dvd')" >Remove</button></td> 
    <input type="hidden" name="id" value="${category.id}">
    <input type="hidden" name="dvdId" value="${dvd.dvdId}">
    </form>
  </tr>
</c:forEach>
</table>
<input type="hidden" name="choice" value="display">
</body>
</html>
