<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<html>
<body>
<form action="/dvdstore/category/categoryMenu" method="get" align="right">
<button type="submit" style = "margin-right: 10px;" >Home</button><br>
</form>

 <table align = "center" style = "text-align:center; width:30%">
  <tr>
    <th>Category Id</th>
    <th>Category</th>
    <th>Action</th>
  </tr>
<c:forEach var="category" items="${categories}" >
  <tr>
    <td>${category.id}</td>
    <td>${category.category}</td>
    <form action="category" method="post">
    <td><button type="submit" formaction="/dvdstore/category/restore" onclick= "return confirm('do you want to restore the category with id- ${category.id}')">Restore</button></td>
    <input type="hidden" name="id" value="${category.id}"> 
    </form>
  </tr>
</c:forEach>
</table>
<input type="hidden" name="choice" value="display">
</body>
</html>
