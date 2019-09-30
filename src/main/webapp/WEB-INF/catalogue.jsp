<%@ page import="com.elendil.app.demo.Product"%>
<%@ page import="java.util.List"%>

<!DOCTYPE html>
<html>
<head>
<title>Find Product</title>
<link
	href="//netdna.bootstrapcdn.com/bootstrap/3.1.0/css/bootstrap.min.css"
	rel="stylesheet" id="bootstrap-css">
<script
	src="//netdna.bootstrapcdn.com/bootstrap/3.1.0/js/bootstrap.min.js"></script>
<script src="//code.jquery.com/jquery-1.11.1.min.js"></script>

</head>
<body>
	<style type="text/css">
#submit {
	background-color: #bbb;
	padding: .5em;
	-moz-border-radius: 5px;
	-webkit-border-radius: 5px;
	border-radius: 6px;
	color: #fff;
	text-decoration: none;
	border: none;
}

#submit:hover {
	border: none;
	background: orange;
	box-shadow: 0px 0px 1px #777;
}

#search {
	min-width: 400px;
}

</style>

	<div class="container">
		<div class="row">
			<div class="col-lg-12  col-lg-offset-4">
				<div class="page-header">
					<h1>Product Catalogue</h1>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="col-lg-12  col-lg-offset-4">
				<h3>Search by Product Title</h3>
			</div>
		</div>
		<% String action = "Search" ; %>
		<% String lastSearchTerm = (String)request.getAttribute("lastSearchTerm");%>
		<% String resultState = (String)request.getAttribute("resultState");%>
		<%  if (resultState != null && resultState == "filteredCatalogue") { action = "Clear" ;  lastSearchTerm =  (lastSearchTerm == null ? "" : lastSearchTerm); } else { lastSearchTerm = ""; } %>

		<%--		<p>			action:<%= action %></p>  --%>
		<%--		<p>		lastSearchTerm<%= lastSearchTerm %></p> --%>
		<%--		<p>		resultState<%= resultState %></p> --%>

		<div class="row">
			<div class="col-lg-12 col-lg-offset-4">
				<form action="" method="get" class="form-inline">
					<input type="text" id="search" value="<%= lastSearchTerm %>"
						class="form-control col-lg-10" placeholder="Enter title"
						name="searchTerm"
						<%= (action.equalsIgnoreCase("Search") ? "" : "readonly") %>
						>
					<input id="submit" type="submit"
						value="<%= action %>" name="action">
				</form>
			</div>
		</div>
		<% List<Product> productResult = (List<Product>)request.getAttribute("productResult");%>
		<% for (int i=0 ; i < productResult.size() ; i++) { %>
		<%  Product product = productResult.get(i); %>


		<!-- Result element start -->
		<div class="col-lg-8 col-lg-offset-4">
			<h4 class="ellip"><%= product.getTitle()%></h4>
			<div class="ellip">
				<cite class="col-lg-6"> <%= product.getId()%></cite>
				<cite class="col-lg-6"> <%= product.getProductForm()%></cite>
			</div>
			<br>
		</div>
		<!-- Results element end -->

		<% } %>
	</div>
</body>
</html>