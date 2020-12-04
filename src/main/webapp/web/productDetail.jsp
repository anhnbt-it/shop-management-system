<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 12/2/2020
  Time: 10:09 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:set var="pageTitle" scope="request" value="${product.name}"/>
<jsp:include page="/common/web/header.jsp"></jsp:include>
<div class="container" style="padding-top: 180px;">
    <nav aria-label="breadcrumb">
        <ol class="breadcrumb">
            <li class="breadcrumb-item"><a href="${pageContext.request.contextPath}/home">Trang chủ</a></li>
            <li class="breadcrumb-item active"><a href="${pageContext.request.contextPath}/products">Sản phẩm</a></li>
        </ol>
    </nav>
    <div class="row mb-2">
        <div class="col-md-6 col-sm-12 position-relative">
            <span class="badge badge-danger position-absolute">-${product.getDiscount()}%</span>
            <img class="img-fluid" src="<c:url value="${product.getImg()}"/>" alt="Loading">
        </div>
        <div class="col-md-6 col-sm-12">
            <h1><c:out value="${product.name}"/></h1>
            <p>Price: <span class="font-weight-bold text-danger">${product.price}</span></p>
            <p>Realprice: <span style="text-decoration-line: line-through">${requestScope.product.getRealPrice()}</span>
            </p>
            <p>Mô tả: ${product.getDescription()}</p>
            <button class="btn btn-primary" onclick="addToCart(${product.getId()})"><i class="fas fa-shopping-cart"></i>
                Thêm vào giỏ hàng
            </button>
        </div>
    </div>
    <div class="row">
        <div class="col-md-12"><h2>Các sản phẩm tương tự</h2></div>
    </div>
    <div class="row">
        <c:choose>
            <c:when test="${requestScope['products'].size() == 0}">
                <div class="col-md-12">
                    <div class="alert alert-info">Không có sản phẩm liên quan</div>
                </div>
            </c:when>
            <c:otherwise>
                <c:forEach var="product" items="${products}">
                    <div class="col-lg-4 col-md-6 mb-3">
                        <div class="card shadow h-100">
                            <span class="display-4 position-absolute badge badge-danger">-${product.getDiscount()}%</span>
                            <a href="${pageContext.request.contextPath}/products?action=view&id=${product.getId()}">
                                <img
                                        class="card-img-top"
                                        src="<c:url value="${product.getImg()}"/>"
                                        width="700" height="200"
                                        alt="loading"></a>
                            <div class="card-body">
                                <h4 class="card-title">
                                    <a href="${pageContext.request.contextPath}/products?action=view&id=${product.getId()}">${product.getName()}</a>
                                </h4>
                                <span class="text-danger">$ ${product.getRealPrice()}</span> / <span class="text-dark"
                                                                                                     style="text-decoration-line: line-through;">$ ${product.getPrice()}</span>
                                <p class="card-text">${product.getDescription()}</p>
                            </div>
                            <button class="btn btn-success btn-block text-white"
                                    onclick="addToCart(${product.getId()})">
                                <i class="fas fa-shopping-cart"></i> Thêm vào giỏ hàng
                            </button>
                        </div>
                    </div>
                    <!-- /.col-lg-4 -->
                </c:forEach>
            </c:otherwise>
        </c:choose>
    </div><!-- /.row -->
</div>
<!-- /.container -->
<%@include file="/common/web/footer.jsp" %>