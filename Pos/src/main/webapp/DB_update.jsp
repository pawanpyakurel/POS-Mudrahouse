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
         String stockstatus = request.getParameter("stockstatus");
	    String saleprice = request.getParameter("price");
		
		TibetHouseDao dao = new TibetHouseDao();
		System.out.println(sku);		
		System.out.println(saleprice);		
		int rs = dao.updateSKU(sku, stockstatus, saleprice);
		int rsb = dao.updateSKU(sku, stockstatus, saleprice);
		double rsa = dao.updateSKU(sku, stockstatus, saleprice);
		
		response.sendRedirect("PosList.jsp");
	%>

</body>
</html>