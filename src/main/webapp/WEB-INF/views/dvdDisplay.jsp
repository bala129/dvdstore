<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<html>
<body>
<form action="/dvdstore/dvd/dvdMenu" method="get" align="right">
<button type="submit" name="choice" value="home"style = "margin-right: 10px;" >Home</button><br>
</form>

<table align="center" cellspacing="20">
  <tr>
      <td>
          <form action="/dvdstore/dvd/search" method="get">
          <input type="text" name="detail" placeholder= "Enter movie name" required /> 
          <button type="submit" >Search</button>
          </form>
      </td>
      <td>
          <form action="/dvdstore/dvd/showDvdByCategory" method="post">
          <select name="category">
          <c:forEach var="category" items="${categories}">
          <option value="${category.id}" <c:if test = "${category.id==id}"> selected </c:if> >${category.category}</option>
          </c:forEach>
          </select>
          <button type="submit" >Display By Category</button>
          </form>
      </td>
      <td>
          <form action="/dvdstore/dvd/display" method="get">  
          <button type="submit" >Display All DVDs</button>
      </td></td>
  </tr>
</table>

 <table style = "text-align: center; width:100%" >
  <tr>
    <th>Dvd Id</th>
    <th>Dvd Name</th>
    <th>Dvd Type</th>
    <th>Language</th>
    <th>Price</th>
    <th>Rating</th>
    <th>Release Date</th>
    <th>Category</th>
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
    <td>
    <c:forEach var="category" items= "${dvd.categories}" >
    ${category.category}
    </c:forEach></td>
    <form id="form" method="post">
    <input type="hidden" name="id" value="${dvd.dvdId}">
    <td>
        <button type="submit" id="delete" formaction="/dvdstore/dvd/delete" onclick= "return confirm('do you want to delete the dvd ${dvd.dvdName}')">Delete</button>
        <button type="submit" formaction="/dvdstore/dvd/updateForm" onclick= "return confirm('do you want to update dvd ${dvd.dvdName}'">Update</button></td> 
    </form>
  </tr>
</c:forEach>
</table>
<input type="hidden" name="choice" value="display">
</body>
<c:if test="${not empty message}">
<script>
    alert ("${message}");
</script>
</c:if>
</html>
