<%@ page import="com.elendil.app.demo.Product"%>
<%@ page import="java.util.List"%>

<!DOCTYPE html>
<html>
<head>
<title>Title</title>
<link
	href="//netdna.bootstrapcdn.com/bootstrap/3.1.0/css/bootstrap.min.css"
	rel="stylesheet" id="bootstrap-css">
<script
	src="//netdna.bootstrapcdn.com/bootstrap/3.1.0/js/bootstrap.min.js"></script>
<!-- <script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
 -->
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
/* Fixme	font-family: 'Oswald';
	font-size: 20px; */
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
					<input  type="text" id="search" value="<%= lastSearchTerm %>"
						class="form-control col-lg-10" placeholder="Enter title" name="searchTerm">
					<input  id="submit" type="submit" value="<%= action %>"
						name="action">
				</form>
			</div>
		</div>
		<% List<Product> productResult = (List<Product>)request.getAttribute("productResult");%>
		<% for (int i=0 ; i < productResult.size() ; i++) { %>
		<%  Product product = productResult.get(i); %>


		<!-- Result element start -->
		<div class="g">

			<!--m-->
			<!--			<div data-ved="2ahUKEwjB3um9l-_kAhVQ6RoKHQC8A0wQFSgAMBx6BAgEEAA"	data-hveid="CAQQAA"> -->
<!-- 			<div class="rc"> -->
<!-- 				<div class="r"> -->
					<!-- 					<a
						onmousedown="return rwt(this,'','','','29','AOvVaw0EvBLT1xbVJNF17xS5AWnb','','2ahUKEwjB3um9l-_kAhVQ6RoKHQC8A0wQFjAcegQIBBAB','','',event)"
						href="https://bingads.microsoft.com/">
 -->
<!-- 					<h4 class="LC20lb"> -->

<!-- 						<div class="ellip"> -->
<%-- 							<%= product.getTitle()%> --%>
<!-- 						</div> -->
<!-- 					</h4> -->

					<h4 class="ellip">
							<%= product.getTitle()%>
					</h4>


					<div class="ellip">
						<cite class="iUh30"> <%= product.getId()%>
						</cite>
					</div>
					<br>
					<!-- 						</a> -->
					<!-- 						<span><div class="action-menu ab_ctl">
							<a class="GHDvEf ab_button" id="am-b28" role="button"
								aria-expanded="false" aria-haspopup="true"
								aria-label="Result options" href="#"
								jsaction="m.tdd;keydown:m.hbke;keypress:m.mskpe"
								data-ved="2ahUKEwjB3um9l-_kAhVQ6RoKHQC8A0wQ7B0wHHoECAQQAg"><span
								class="mn-dwn-arw"></span></a>
							<div tabindex="-1" class="action-menu-panel ab_dropdown"
								role="menu"
								jsaction="keydown:m.hdke;mouseover:m.hdhne;mouseout:m.hdhue"
								data-ved="2ahUKEwjB3um9l-_kAhVQ6RoKHQC8A0wQqR8wHHoECAQQAw">
								<ol>
									<li class="action-menu-item ab_dropdownitem" role="menuitem"><a
										class="fl"
										onmousedown="return rwt(this,'','','','29','AOvVaw3bOu8PX0wOEM8DhzrC8vqp','','2ahUKEwjB3um9l-_kAhVQ6RoKHQC8A0wQIDAcegQIBBAE','','',event)"
										href="https://webcache.googleusercontent.com/search?q=cache:05f7deM0i2gJ:https://bingads.microsoft.com/+&amp;cd=29&amp;hl=en&amp;ct=clnk&amp;gl=uk">Cached</a></li>
									<li class="action-menu-item ab_dropdownitem" role="menuitem"><a
										class="fl"
										href="/search?safe=strict&amp;q=related:https://bingads.microsoft.com/+bing&amp;tbo=1&amp;sa=X&amp;ved=2ahUKEwjB3um9l-_kAhVQ6RoKHQC8A0wQHzAcegQIBBAF">Similar</a></li>
								</ol>
							</div>
						</div></span>
 -->
<!-- 				</div> -->
<%-- 				<div class="s">
					<div>
						<span class="st">
							ID:<%= product.getDescription()%>
							</span>
					</div>
				</div>
 --%>
				<!-- 			<div id="ed_10"
					data-ved="2ahUKEwjB3um9l-_kAhVQ6RoKHQC8A0wQ2Z0BMBx6BAgEEAY"
					data-base-uri="/search?safe=strict">
					<div class="AUiS2" jsname="UTgHCf"
						data-ved="2ahUKEwiSvLW-l-_kAhWizoUKHXQuDkkQx40DegQIARAA">
						<div style="display: none" jsname="d3PE6e">
							<div data-ved="2ahUKEwiSvLW-l-_kAhWizoUKHXQuDkkQsKwBKAB6BAgBEAE">bing
								ads coupon</div>
							<div data-ved="2ahUKEwiSvLW-l-_kAhWizoUKHXQuDkkQsKwBKAF6BAgBEAI">bing
								ads login</div>
							<div data-ved="2ahUKEwiSvLW-l-_kAhWizoUKHXQuDkkQsKwBKAJ6BAgBEAM">bing
								ads support</div>
							<div data-ved="2ahUKEwiSvLW-l-_kAhWizoUKHXQuDkkQsKwBKAN6BAgBEAQ">bing
								ads vs google ads</div>
							<div data-ved="2ahUKEwiSvLW-l-_kAhWizoUKHXQuDkkQsKwBKAR6BAgBEAU">yahoo
								ads</div>
							<div data-ved="2ahUKEwiSvLW-l-_kAhWizoUKHXQuDkkQsKwBKAV6BAgBEAY">bing
								ads editor</div>
						</div>
						<span tabindex="0" class="XCKyNd" role="button"
							aria-label="Dismiss suggested follow ups" jsname="ZnuYW"></span>
						<div>
							<div class="d8lLoc" jsname="l1CLDf">
								<h4 class="eJ7tvc" jsname="IaVMje">People also search for</h4>
								<div class="hYkSRb" jsname="CeevUc"></div>
							</div>
						</div>
					</div>
				</div>
 -->
<!-- 			</div> -->
		</div>
		<!--n-->
		<!-- Results element end -->


	<% } %>

	</div>




</body>
</html>