<%@page import="com.dao.TibetHouseDao"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<%
		String sku = request.getParameter("sku");
		TibetHouseDao dao = new TibetHouseDao();
		int rs = dao.updateOutOfStock(sku);
		response.sendRedirect("PosList.jsp");
	%>
	
</body>
</html>