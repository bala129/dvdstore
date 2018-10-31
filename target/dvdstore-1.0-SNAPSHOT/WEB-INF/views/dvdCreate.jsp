<%@taglib uri = "http://www.springframework.org/tags/form" prefix = "form"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<html>
<body>
<form action="/dvdstore/dvd/dvdMenu" method="get" align="right">
<button type="submit" style = "margin-right: 10px;" >Home</button>
</form>

<c:if test="${not empty message}">
<script>
    alert ("${message}");
</script>
</c:if>

<form:form id="form" method="post">  
    <table align="center">
    <tr>
    <td>Dvd Name:</td><td><form:input path="dvdName" value="${dvd.dvdName}" pattern="[A-Za-z0-9\s]+" maxlength="30" required="required"/></td>  
    </tr>
    <tr>
        <td>Dvd Type:</td>
        <td><input type="radio" name="type" value="HD" <c:if test = "${dvd.dvdType.equals('HD')}"> checked </c:if> /> HD &nbsp
            <input type="radio" name="type" value="HQ" <c:if test = "${dvd.dvdType.equals('HQ')}"> checked </c:if>  /> HQ &nbsp 
            <input type="radio" name="type" value="LQ" <c:if test = "${dvd.dvdType.equals('LQ')}"> checked </c:if>  /> LQ &nbsp
        </td>
    </tr>
    <tr><td>Language:</td><td><form:input path="language" value="${dvd.language}" maxlength="10" required="required"/><br/></td></tr>
    <tr><td>Price:</td><td><form:input type="number" path="price" value="${dvd.price}" min="30" max="300" step="10" required="required"/></td></tr>
    <tr><td>Rating:</td><td><form:input type="number" path="rating" value="${dvd.rating}" min="1" max="10" step="0.5" required="required"/></td></tr>
    <tr><td>Release Date:</td><td><form:input type="date" value="${dvd.releaseDate}" path="releaseDate" max="${today}" required="required"/></td></tr>
    
    <tr><td>Category:<br/></td></tr>
    <tr><td/><td><c:forEach var="category" items= "${categories}" >
    <input type="checkbox" name= "category" value= "${category.category}" <c:if test = "${dvd.categories.contains(category)}"> checked </c:if> /> ${category.category} <br/>
    </c:forEach></td>
    </tr>
    <tr>
         <c:if test="${empty dvd}">
         <td><button type="submit" onclick= "return categoryIsEmpty() && setCreateAction()" name="choice" value="create">Save</button></td>
         <td><button type="reset" value="Reset">Reset</button></td>
         </c:if>
         
         <c:if test="${not empty dvd}">
         <td><button type="submit" onclick= "return categoryIsEmpty() && setUpdateAction()" name="choice" value="update">Update</button></td>
         </c:if>
    </tr>
    </table>  
    <input type="hidden" name="id" value="${dvd.dvdId}">
</form:form> 
<script>
function categoryIsEmpty() {
   var checkboxes = document.getElementsByName('category');
    for (var i=0; i<checkboxes.length; i++) {
        if (checkboxes[i].checked) {
            return true;
        }  
    }
    alert("please select the category");
    return false;
}

function setCreateAction() {
    document.getElementById("form").action="/dvdstore/dvd/create";
}

function setUpdateAction() {
    document.getElementById("form").action="/dvdstore/dvd/update";
}
</script>
</body>
</html>
