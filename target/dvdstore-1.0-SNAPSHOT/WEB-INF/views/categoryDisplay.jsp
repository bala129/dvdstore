<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<html>
<body>
<form action="/dvdstore/category/categoryMenu" method="get" align="right">
<button type="submit" style = "margin-right: 10px;" >Home</button><br>
</form>

<c:if test="${not empty message}">
<script>
    alert ("${message}");
</script>
</c:if>

 <table align="center" style="text-align:center;width:50%;">
  <tr>
    <th>Category Id</th>
    <th>Category</th>
  </tr>
<c:forEach var="category" items="${categories}" >
  <tr>
    <form method="post">
    <td>${category.id}</td>
    <td>${category.category}</td> 
    <td><input type="text" name="category" style="visibility:hidden" pattern="[A-Za-z]{1,10}" maxlength=10 />
    <button type="submit" id = "${category.id}" formaction="/dvdstore/category/update" onclick= "return confirm('do you want to update')" style="visibility:hidden" >Change</button>&nbsp
    
    <button type="submit" formaction="/dvdstore/category/view" onclick="setVisible(this.form)">View</button>&nbsp
    
    <button type="button" onclick= "update(this.form)">Update</button>&nbsp
    <button type="submit" formaction="/dvdstore/category/delete" onclick= "return confirm('do you want to delete the category with id- ${category.id}') && setVisible(this.form)" >Delete</button></td> 
    <input type="hidden" name="id" value="${category.id}">
    </form>
  </tr>
</c:forEach>
</table>
<input type="hidden" name="choice" value="display">
<script>

function update(form) {
    var result = document.getElementsByName("category");
    result.forEach(function(element){
        element.style.visibility="hidden";
        element.required = false;
    });
   
    result = document.getElementsByName("choice");
    result.forEach(function(element){
        if (element.value == "update") {
            element.style.visibility="hidden";
        }
    });
    
    form.category.required = true;
    form.category.style.visibility='visible';
    var id = form.id.value;
    document.getElementById(id).style.visibility='visible';
}

function setVisible(form) {
    form.category.required = false;
}
</script>
</body>
</html>
