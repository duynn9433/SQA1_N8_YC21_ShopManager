<%-- 
    Document   : EditProduct
    Created on : Mar 15, 2022, 7:47:45 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!--<!DOCTYPE html>-->
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Trang Chủ Sản Phẩm</title>
    <jsp:useBean id="usebean" class="com.duynn.sqa1_n8_yc21_shopmanager.servlet.manager.EditBillServlet" scope="request"></jsp:useBean>

    <link rel="stylesheet" href="bootstraplib/bootstrap.4.0.0.min.css"
          crossorigin="anonymous">
    <script src="bootstraplib/jquery-3.2.1.js" crossorigin="anonymous"></script>
    <script src="bootstraplib/popper.min.js" crossorigin="anonymous"></script>
    <script src="bootstraplib/bootstrap.min.js" crossorigin="anonymous"></script>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
</head>
<body>

<h1>Sửa thông tin đơn hàng</h1>
<form action="<c:url value="/EditBillServlet"/>" method="post">
    <table>
        <tr class="tbl-row">
            <td>ID:</td>
            <td>${id}</td>
        </tr>
        <tr class="tbl-row">
            <td>Ngày thanh toán:</td>
            <td> <div>  ${paymentDate} </div> </td>
        </tr>
        <tr class="tbl-row">
            <td>Giảm giá:</td>
            <td><input class="form-control" type="text" name="saleOff" value="${saleOff}" required></td>
        </tr>
        <tr class="tbl-row">
            <td>Chú thích:</td>
            <td><input class="form-control" type="text" name="note" value="${sdt}" required></td>
        </tr>

        <%--       danh sách hóa đơn--%>
        <tr class="tbl-row">
            <td>Danh sach hoa don:</td>
        </tr>
        <tr>
            <table cellspacing="10" cellpadding="10" border="3">
                <tr>
                    <th>id</th>
                    <th>Tên</th>
                    <th>Giá</th>
                    <th>Số lượng</th>
                </tr>

                <c:forEach  items="${listgoods}" var="b">
                    <tr valign="top">
                        <td>${b.ID}</td>
                        <td>${b.amount}</td>
                        <td>${b.pricePerUnit}</td>
                        <td>${usebean.totalPrice}</td>
                        <td>
                            <form action="<c:url value="/EditBillServlet"/>" method="POST">
                                <input type="hidden" name="eid" value="${b.id}">
                                <input type="hidden" name="epaymentDate" value="${b.paymentDate}">
                                <input type="hidden" name="epaymentTotal" value="${b.paymentTotal}">
                                <input type="hidden" name="esaleOff" value="${b.saleOff}">
                                <input type="hidden" name="enote" value="${b.note}">
                                <input type="submit" class="btn btn-primary" value="Sửa" name="edit">
                                <input type="hidden" name="action" value="edit">
                            </form>
                        </td>


                    </tr>
                </c:forEach>
            </table>

        </tr>
        <tr class="tbl-row">
            <td>Thêm món:</td>
            <td>
                <input class="form-control" type="text" name="note" required>
                <input class="btn btn-primary" type="submit" value="Thêm">
            </td>
        </tr>
        <td> <div>  </div> </td>
            <td>Khách hàng:</td>
            <td>
                <input class="form-control" type="text" name="note" required>
                 <tr class="tbl-row">
                     <td>Tên:</td>
                    <td> <div> 'Khách hàng a' </div> </td>
                </tr>
                <input class="btn btn-primary" type="submit" value="Cập nhật khách hàng">
            </td>

    </table>
    <input type="hidden" name="action" value="edit"><br>
    <input class="btn btn-primary" type="submit" value="Cập nhật hóa đơn">
</form>
<form action="<c:url value="/manager/ManagerHomeView.jsp"/>" method="post">
    <input class="btn btn-primary" type="submit" value="HOME">
</form>

</body>
</html>

