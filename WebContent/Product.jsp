<%@page import="com.Product"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Product Management</title>
<link rel="stylesheet" href="Views/bootstrap.min.css">
<script src="Components/jquery-3.2.1.min.js"></script>
<script src="Components/Product.js"></script>
</head>
<body> 
<div class="container"><div class="row"><div class="col-6"> 
<h1>Product Management</h1>
<form id="productForm" name="formItem">
 p_id: 
 <input id="p_id" name="p_id" type="text" 
 class="form-control form-control-sm">
 <br> p_name: 
 <input id="p_name" name="p_name" type="text" 
 class="form-control form-control-sm">
 <br> innovator_name: 
 <input id="innovator_name" name="innovator_name" type="text" 
 class="form-control form-control-sm">
 <br> initial_price: 
 <input id="initial_price" name="initial_price" type="text" 
 class="form-control form-control-sm">
 <br>
stock_amount:
 <input id="stock_amount" name="stock_amount" type="text" 
 class="form-control form-control-sm">
 <br>
 product_category:
   <input id="product_category" name="product_category" type="text" 
 class="form-control form-control-sm">
 <br>
 <input id="btnSave" name="btnSave" type="button" value="Save" 
 class="btn btn-primary">

 <input type="hidden" id="hidItemIDSave" 
 name="hidItemIDSave" value="">
</form>
<div id="alertSuccess" class="alert alert-success"></div>
<div id="alertError" class="alert alert-danger"></div>
<br>
<div id="divItemsGrid">
 <%
 Product Obj = new Product(); 
  out.print(Obj.viewProducts());
 %>
</div>
</div> </div> </div> 
</body>
</html>