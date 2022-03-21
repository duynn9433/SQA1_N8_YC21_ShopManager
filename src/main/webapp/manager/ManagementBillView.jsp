<%-- 
    Document   : ManagementBiillView
    Created on : Mar 15, 2022, 9:06:13 PM
    Author     : Admin
--%>


<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>

    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>thông tin hóa đơn</title>
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
        <div>
            <div class="row" style="margin-top:20px;">
                <div class="col-12 text-center">
                    <h1>Bill Management</h1>
                </div>
            </div>
            <div class="row ">
                <div class="col-12 text-center">
                    <form class="form-inline" action="<c:url value="/ManagementBillServlet"/>" method="POST">
                        <div class="input-group">
                            <input type="text" class="form-control" size="80" placeholder="ID hóa đơn khách hàng"
                                   name="search_date">
                            <input class="btn btn-primary" type="submit" name="search" value="Tìm kiếm">
                            <input type="hidden" name="action" value="search">
                        </div>
                    </form>

                    <table cellspacing="6" cellpadding="6" border="1">
                        <tr>
                            <th>ID</th>
                            <th>Ngày thanh toán</th>
                            <th>tổng thanh toán</th>
<%--                            <th>loại thanh toán</th>--%>
                            <th>giảm giá</th>
                            <th>chú thích</th>

                        </tr>

                        <c:forEach var="i" items="${listBill}" varStatus="status">
                            <tr valign="top">
                                <td>${i.ID}</td>
                                <td>${i.paymentDate}</td>
                                <td>${i.paymentTotal}</td>
<%--                                <td>${i.paymentType}</td>--%>
                                <td>${i.saleOf}</td>
                                <td>${i.note}</td>
                                <td>
                                    <form action="<c:url value="/ManagementBillServlet"/>" method="POST">
                                        <input type="hidden" name="eid" value="${i.ID}">
                                        <input type="hidden" name="epaymentDate" value="${i.paymentDate}">
                                        <input type="hidden" name="epaymentTotal" value="${i.paymentTotal}">
<%--                                        <input type="hidden" name="epaymentType" value="${i.paymenType}">--%>
                                        <input type="hidden" name="esaleOf" value="${i.saleOf}">
                                        <input type="hidden" name="enote" value="${i.note}">

                                        <input type="submit" class="btn btn-primary" value="Sửa" name="edit">
                                        <input type="hidden" name="action" value="edit">
                                    </form>
                                </td>

                                <td>
                                    <form action="<c:url value="/ManagementBillServlet"/>" method="POST">
                                        <input type="hidden" name="did" value="${i.ID}">
                                        <input type="hidden" name="dpaymentDate" value="${i.paymentDate}">
                                        <input type="hidden" name="dpaymentTotal" value="${i.paymentTotal}">
<%--                                        <input type="hidden" name="dpaymentType" value="${i.paymentType}">--%>
                                        <input type="hidden" name="dsaleOf" value="${i.saleOf}">
                                        <input type="hidden" name="dnote" value="${i.note}">

                                        <input type="submit" class="btn btn-primary" value="Xóa" name="delete">
                                        <input type="hidden" name="action" value="delete">
                                    </form>
                                </td>
                            </tr>
                        </c:forEach>
                    </table>

                </div>
            </div>
        </div>


    </body>
</html>
