<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 12/2/2020
  Time: 12:17 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:set var="pageTitle" scope="request" value="Cart"/>
<jsp:include page="/common/web/header.jsp"></jsp:include>
<div class="container" style="padding-top: 180px;">
    <nav aria-label="breadcrumb">
        <ol class="breadcrumb">
            <li class="breadcrumb-item"><a href="${pageContext.request.contextPath}/home">Trang chủ</a></li>
            <li class="breadcrumb-item active"><a href="${pageContext.request.contextPath}/products">Sản phẩm</a></li>
        </ol>
    </nav>
    <section class="jumbotron text-center mb-4">
        <div class="container">
            <h1 class="jumbotron-heading">YOUR CART</h1>
        </div>
    </section>
    <div class="row">
        <div class="col-12">
            <div class="table-responsive">
                <table class="table table-striped">
                    <thead>
                    <tr>
                        <th scope="col"></th>
                        <th scope="col">Product</th>
                        <th scope="col">Available</th>
                        <th scope="col" class="text-center">Quantity</th>
                        <th scope="col" class="text-right">Price</th>
                        <th></th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items='${sessionScope.order.getList()}' var="orderDetail">
                        <tr>
                            <td class="w-25"><img class="" src="<c:url value="${orderDetail.getProduct().getImg()}"/>"
                                                  width="70%" height="100" style="border: 1px solid black"/></td>
                            <td>${orderDetail.getProduct().getName()}</td>
                            <td>In stock</td>
                            <td style="text-align: center">${orderDetail.getQuantity()}</td>
                            <td class="text-right">$ ${orderDetail.getTotal()}</td>
                            <td class="text-right"><a class="btn btn-sm btn-danger"
                                                      href="/cart?action=del&id=${orderDetail.getProduct().getId()}"><i
                                    class="fa fa-trash"></i> </a></td>
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
                        <button class="btn btn-block btn-secondary">Continue Shopping</button>
                    </a>
                </div>
                <div class="col-sm-12 col-md-6 text-right">
                    <a href="/cart?action=payment">
                        <button class="btn btn-block btn-success text-uppercase">Checkout</button>
                    </a>
                </div>
            </div>
        </div>
    </div><!-- /.row -->
</div>
<!-- /.container -->
<%@include file="/common/web/footer.jsp" %>
