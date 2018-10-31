<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<html>

<head>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<link rel="stylesheet" type="text/css" href="<c:url value = '/resources/dvdStore.css' />">
</head>

<body class="background">

<form action="/dvdstore/category/admin" method="get" align="right">
<button type="submit" name="choice" value="admin" class="home" ><i class="fa fa-home"></i>Home</button>
</form>

<form method="post">  
<table align= "center">
 <tr>
     <td> <button type="button" class="button" onclick= "setVisible(this.form)" style= "margin: 10px; width:250px;"> Create category</button></td>
 </tr><tr>
     <td><input type="text"  name="category" style="display:none" pattern="[A-Za-z]{1,10}" maxlength = 10 placeholder= "Enter the category" /></td><td>
     <button type="submit" formaction="/dvdstore/category/create" class="button" id="add" style="display:none" >Add</button></td>
 </tr>
 <tr>
     <td><button type="submit" class="button" formaction="/dvdstore/category/display" onclick= "setRequired(this.form)" style= "margin: 10px; width:250px;"> Display category</button></td>
 </tr>
 <tr>
     <td><button type="submit" class="button" formaction="/dvdstore/category/restoreForm" onclick= "setRequired(this.form)" style= "margin: 10px; width:250px;">Restore Category</button></td>
 </tr>
</table>
</form>
</body>

<c:if test="${not empty message}">
<script>
    alert ("${message}");
</script>
</c:if>

<script>
function setVisible(form) {
    document.getElementById("add").style.display='block';
    form.category.style.display='block';
    form.category.required = true;
}

function setRequired(form) {
    form.category.required = false;
}
</script>

</html>
