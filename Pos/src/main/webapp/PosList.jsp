<%@page import="com.model.TibetHouseModel"%>
<%@page import="com.dao.TibetHouseDao,java.util.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<title>SKU Detail Page</title>

<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.0/jquery.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
</head>

<body>
	<%
		List<TibetHouseModel> list;
		TibetHouseDao dao = new TibetHouseDao();
		String sku = request.getParameter("sku");
		list = dao.posList(sku);
		request.setAttribute("list", list);
		TibetHouseModel u = new TibetHouseModel();
	%>

	<div class="container">
		<h2>Items</h2>
		<p>Detail Of Particular SKU.</p>
		<table id="poslist" class="table table-dark table-striped"
			border="1px" width=60%>
			<thead>
				<tr id="somerow">
					<th><font color=#c1fcff> SKU </font></th>
					<th><font color=#c1fcff> Title </font></th>
					<th><font color=#c1fcff> Cost</font></th>
					<th id="readvalue"><font color=#c1fcff>STATUS</font></th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${list}" var="u">
					<tr>
						<td>${u.getSku()}</td>
						<td>${u.getTitle()}</td>
						<td>Rs ${u.getCostprice()}</td>
						<td>${u.getStatus()}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>

</body>
</html>
