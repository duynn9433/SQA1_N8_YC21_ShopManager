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
            <td><input class="form-control" type="text" name="note" value="${note}" required></td>
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
<%--tìm cách in danh sách hóa đơn cũ--%>
                <tbody>
            <tr></tr>
            <tr></tr>
            <c:forEach items="${buyiggoodslist}" var="element" varStatus="status">
            <tr>
                <td>${element.goods.name}</td>
                <td>${element.goods.unity}</td>
                <td>${element.goods.pricePerUnit}</td>
                <td>${element.amount}</td>
</form>
</td>
<td>
    <form action="<c:url value="/EditBillServlet"/>" method="post">
        <input type="hidden" name="action" value="remove_goods">
        <input type="hidden" name="index" value="${status.count}">
        <input type="submit" value="Xoá">
    </form>
</td>
</tr>
</c:forEach>

            </tbody>


            </table>

        </tr>
        <tr class="tbl-row">
            <td>Thêm món:</td>
            <td>
                <form action="<c:url value="/EditBillServlet"/>" method="post">
                    <input id="good_name" type="text" name="goodsname" placeholder="Nhập tên mặt hàng"/>
                    <input type="hidden" name="action" value="search_goods">
                    <button class="btn btn-primary" type="submit">Tìm món</button>
                </form>
                <form action="<c:url value="/EditBillServlet"/>" method="post">
                    <div class="table-responsive">
                        <table class="table">
                            <thead>
                            <tr>
                                <th>Tên</th>
                                <th>Đơn vị</th>
                                <th>Giá</th>
                                <th>Chọn</th>
                            </tr>
                            </thead>
                            <tbody>
                            <tr></tr>
                            <tr></tr>
                            <c:forEach items="${goodsList}" var="element" varStatus="status">
                                <tr>
                                    <td>${element.name}</td>
                                    <td>${element.unity}</td>
                                    <td>${element.pricePerUnit}</td>
                                    <td><input type="radio" name="chooseIndex" value="${status.count}" checked></td>
                                </tr>
                            </c:forEach>

                            </tbody>

                        </table>
                    </div>
                    <input type="text" pattern="[0-9]+" name="amount" placeholder="Số lượng mặt hàng" required/>
                    <input type="hidden" name="action" value="add_goods">
                    <button class="btn btn-primary" type="submit">Thêm</button>
                </form>
            </td>
        </tr>
        <td> <div>  </div> </td>
            <td>Khách hàng:</td>
        <div class="container">
            <div class="row">
                <div class="col-md-8" style="border:1px solid #cecece;">
                    <form action="<c:url value="/EditBillServlet"/>" method="post">
                        <input type="hidden" name="action" value="find_client">&nbsp;
                        <label for="phoneNumber" class="form-label">Số điện thoại</label>
                        <input id="phoneNumber" type="text" pattern="(84|0[3|5|7|8|9])+([0-9]{8})\b"
                               name="client_phone" value="" placeholder="Nhập số điện thoại"/>
                        &nbsp;
                        <button class="btn btn-primary" type="submit">Tìm khách hàng</button>
                    </form>
                    <form action="<c:url value="/EditBillServlet"/>" method="post">
                        <input type="hidden" name="action" value="choose_client">
                        <div class="table-responsive">
                            <table class="table">
                                <thead>
                                <tr>
                                    <th>Tên</th>
                                    <th>Số điện thoại</th>
                                    <th>Địa chỉ</th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach items="${clientlist}" var="element" varStatus="status">
                                    <tr>
                                        <td>${element.name}</td>
                                        <td>${element.phoneNumber}</td>
                                        <td>${element.address}</td>
                                        <td><input type="radio" name="chooseIndex" value="${status.count}" checked></td>
                                    </tr>
                                </c:forEach>

                                </tbody>
                            </table>
                        </div>
                        <button class="btn btn-primary" type="submit">Chọn khách hàng</button>
                    </form>


                </div>
                <div class="col-md-4" style="border:1px solid #cecece;">
                    <form action="<c:url value="/EditBillServlet"/>" method="get">


                        <label>Tên khách hàng: </label> <label>${client_name}</label> <br>
                        <label>Số điện thoại: </label> <label>${client_phone}</label> <br>
                        <label>Địa chỉ: </label> <label>${client_adress}</label> <br>


                    </form>
                </div>
            </div>
        </div>


    </table>
    <form action="<c:url value="/EditBillServlet"/>" method="post">
        <input type="hidden" name="action" value="update_bill">
        <button class="btn btn-primary" type="submit">Cập nhật hóa đơn</button>
    </form>

    </form>
<form action="<c:url value="/manager/ManagerHomeView.jsp"/>" method="post">
    <input class="btn btn-primary" type="submit" value="HOME">
</form>

</body>
</html>

