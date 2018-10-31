<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<html>
<body>

<form action="/dvdstore/dvd/dvdMenu" method="get" align="right">
<button type="submit" style = "margin-right: 10px;" >Home</button><br>
</form>

<c:if test="${not empty message}">
<script>
    alert ("${message}");
</script>
</c:if>

<table style = "text-align: center;width:100%;">
  <tr>
    <th>Dvd Id</th>
    <th>Dvd Name</th>
    <th>Dvd Type</th>
    <th>Language</th>
    <th>Price</th>
    <th>Rating</th>
    <th>Release Date</th>
    <th>Category</th>
  </tr>
<c:forEach var= "dvd" items = "${dvds}" >
  <tr>
    <td>${dvd.dvdId}</td>
    <td>${dvd.dvdName}</td>
    <td>${dvd.dvdType}</td>
    <td>${dvd.language}</td>
    <td>${dvd.price}</td>
    <td>${dvd.rating}</td>
    <td>${dvd.releaseDate}</td>
    <td>
    <c:forEach var="category" items= "${dvd.categories}" >
    ${category.category}
    </c:forEach></td>
    <form action="/dvdstore/dvd/restore" method="post">
    <td><input type="hidden" name="id" value="${dvd.dvdId}"></td>
    <td><button type="submit" name="choice" value="restore">Restore</button></td> 
    </form>
  </tr>
</c:forEach>
</table>
</body>
<html>
