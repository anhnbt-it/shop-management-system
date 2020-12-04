<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 12/2/2020
  Time: 12:17 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/common/libs.jsp"%>
<html>
<head>
    <title>Cart</title>
    <link href="<c:url value="/template/css/cart/cart.css"/>" rel="stylesheet" >
</head>
<body>
<%@include file="/common/web/header.jsp"%>
<div class="container">
    <section class="jumbotron text-center">
        <div class="container">
            <h1 class="jumbotron-heading">YOUR CART</h1>
        </div>
    </section>

    <div class="container mb-4">
        <div class="row">
            <div class="col-12">
                <div class="table-responsive">
                    <table class="table table-striped">
                        <thead>
                        <tr>
                            <th scope="col"> </th>
                            <th scope="col">Product</th>
                            <th scope="col">Available</th>
                            <th scope="col" class="text-center">Quantity</th>
                            <th scope="col" class="text-right">Price</th>
                            <th> </th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items='${sessionScope.order.getList()}' var="orderDetail">
                            <tr>
                                <td class="w-25"><img class="" src="<c:url value="${orderDetail.getProduct().getImg()}"/>" width="70%" height="100" style="border: 1px solid black" /> </td>
                                <td>${orderDetail.getProduct().getName()}</td>
                                <td>In stock</td>
                                <td style="text-align: center">${orderDetail.getQuantity()}</td>
<%--                                <td><input class="form-control" type="text" value="${orderDetail.getQuantity()}" /></td>--%>
                                <td class="text-right">$ ${orderDetail.getTotal()}</td>
                                <td class="text-right"><a class="btn btn-sm btn-danger" href="/cart?action=del&id=${orderDetail.getProduct().getId()}"><i class="fa fa-trash"></i> </a> </td>
                            </tr>
                        </c:forEach>
                        <tr>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td><strong>Total</strong></td>
                            <td class="text-right"><strong>$ ${sessionScope.order.getTotalOrder()}</strong></td>
                        </tr>
                        </tbody>
                    </table>
                </div>
            </div>
            <div class="col mb-2">
                <div class="row">
                    <div class="col-sm-12  col-md-6">
                        <a href="/products">
                            <button class="btn btn-block btn-light"> Continue Shopping</button>
                        </a>
<%--                        <button class="btn btn-block btn-light"> Continue Shopping</button>--%>
                    </div>
                    <div class="col-sm-12 col-md-6 text-right">
                        <a href="/cart?action=payment">
                            <button class="btn btn-lg btn-block btn-success text-uppercase">Checkout</button>
                        </a>
<%--                        <button class="btn btn-lg btn-block btn-success text-uppercase">Checkout</button>--%>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<%@include file="/common/web/footer.jsp"%>
</body>
</html>

