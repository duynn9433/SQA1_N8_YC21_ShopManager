<%--
  Created by IntelliJ IDEA.
  User: nndng
  Date: 3/13/2022
  Time: 12:04 PM
  To change this template use File | Settings | File Templates.
--%>
<!DOCTYPE html>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<html>
<head>
    <title>Thêm thành công</title>
</head>
<body>

<%
    String msg = (String) request.getSession().getAttribute("addClientMsg");
    System.out.println("View" + msg);

    if (msg!=null){
%>
<script type="text/javascript">
    var msg = "${addClientMsg}";
    alert(msg);
    window.location.href='seller/SellerHome.jsp';
</script>
<%
        request.getSession().removeAttribute("addClientMsg");
    }
%>


<h3>Thông tin khách hàng</h3>
<table cellpadding="2" cellspacing="2" border="1">
    <tr>
        <td>Tên </td>
        <td>${client.name }</td>
    </tr>
    <tr>
        <td>Số điện thoại</td>
        <td>${client.phoneNumber }</td>
    </tr>
    <tr>
        <td>Địa chỉ</td>
        <td>${client.address }</td>
    </tr>
    <tr>

    </tr>
</table>
<form method="post" action="<c:url value="/AddClientServlet"/>">
    <input type="hidden" name="action" value="accept">
    <input type="hidden" name="name" value="${client.name }">
    <input type="hidden" name="phoneNumber" value="${client.phoneNumber }">
    <input type="hidden" name="address" value="${client.address }">
    <input type="submit" value="Xác nhận">
</form>
</body>
</html>