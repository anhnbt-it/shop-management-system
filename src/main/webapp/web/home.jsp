<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 11/30/2020
  Time: 9:34 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:set var="pageTitle" scope="request" value="Home"/>
<jsp:include page="/common/web/header.jsp"></jsp:include>
<div class="container" style="padding-top: 180px;">
    <c:if test="${requestScope['discount'] != null}">
        <div class="row">
            <div class="col-sm-12">
                <div id="carouselExampleIndicators" class="carousel slide my-4" data-ride="carousel">
                    <ol class="carousel-indicators">
                        <li data-target="#carouselExampleIndicators" data-slide-to="0" class="active"></li>
                        <li data-target="#carouselExampleIndicators" data-slide-to="1"></li>
                        <li data-target="#carouselExampleIndicators" data-slide-to="2"></li>
                    </ol>
                    <div class="carousel-inner" role="listbox">
                        <div class="carousel-item active">
                            <a href="${pageContext.request.contextPath}/products?action=view&id=${requestScope['discount'].get(0).getId()}">
                                <img class="img-fluid" src="${pageContext.request.contextPath}/img/slide3.jpg"
                                     width="1100" height="300" alt="First slide">
                            </a>
                        </div>
                        <div class="carousel-item">
                            <a href="${pageContext.request.contextPath}/products?action=view&id=${requestScope['discount'].get(1).getId()}">
                                <img class="img-fluid" src="${pageContext.request.contextPath}/img/slide1.jpg"
                                     width="1100" height="300" alt="Second slide">
                            </a>
                        </div>
                        <div class="carousel-item">
                            <a href="${pageContext.request.contextPath}/products?action=view&id=${requestScope['discount'].get(2).getId()}">
                                <img class="img-fluid" src="${pageContext.request.contextPath}/img/slide2.jpg"
                                     width="1100" height="300" alt="Third slide">
                            </a>
                        </div>
                    </div>
                    <a class="carousel-control-prev" href="#carouselExampleIndicators" role="button" data-slide="prev">
                        <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                        <span class="sr-only">Previous</span>
                    </a>
                    <a class="carousel-control-next" href="#carouselExampleIndicators" role="button" data-slide="next">
                        <span class="carousel-control-next-icon" aria-hidden="true"></span>
                        <span class="sr-only">Next</span>
                    </a>
                </div>
            </div><!-- /.col-sm-12 -->
        </div>
        <!-- /.row -->
    </c:if>
    <c:if test="${requestScope['date'] != null}">
        <div class="row">
            <div class="col-md-12">
                <h2>Sản phẩm mới</h2>
            </div>
        </div>
        <div class="row">
            <c:forEach items='${requestScope["date"]}' var="product">
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
                        <button class="btn btn-success btn-block text-white" onclick="addToCart(${product.getId()})"><i
                                class="fas fa-shopping-cart"></i> Thêm vào giỏ hàng
                        </button>
                    </div>
                </div>
                <!-- /.col-lg-4 -->
            </c:forEach>
        </div>
        <!-- /.row -->
    </c:if>
    <c:if test="${requestScope['new'] != null}">
        <div class="row">
            <div class="col-md-12">
                <h2>Sản phẩm bán chạy</h2>
            </div>
        </div>
        <div class="row">
            <c:forEach items='${requestScope["new"]}' var="product">
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
                            <button class="btn btn-success btn-block text-white" onclick="addToCart(${product.getId()})"><i
                                    class="fas fa-shopping-cart"></i> Thêm vào giỏ hàng
                            </button>
                    </div>
                </div>
                <!-- /.col-lg-4 -->
            </c:forEach>
        </div>
        <!-- row 2-->
    </c:if>
    <div class="row">
        <div class="row">
            <div class="col-md-12">
                <h2>Cửa hàng</h2>
            </div>
        </div>
        <div id="carouselInterval" class="carousel slide my-4" data-ride="carousel">
            <ol class="carousel-indicators">
                <li data-target="#carouselInterval" data-slide-to="0" class="active"></li>
                <li data-target="#carouselInterval" data-slide-to="1"></li>
                <li data-target="#carouselInterval" data-slide-to="2"></li>
                <li data-target="#carouselInterval" data-slide-to="3"></li>
                <li data-target="#carouselInterval" data-slide-to="4"></li>
            </ol>
            <div class="carousel-inner" role="listbox">
                <div class="carousel-item active" data-interval="1000">
                    <img class="img-fluid" src="<c:out value="/img/cuahang/cuahang-1.png"/>" width="1100" height="400"
                         alt="First slide">
                </div>
                <div class="carousel-item" data-interval="1000">
                    <img class="img-fluid" src="<c:out value="/img/cuahang/cuahang-2.png"/>" width="1100" height="400"
                         alt="Second slide">
                </div>
                <div class="carousel-item" data-interval="1000">
                    <img class="img-fluid" src="<c:out value="/img/cuahang/cuahang-3.png"/>" width="1100" height="400"
                         alt="Third slide">
                </div>
                <div class="carousel-item" data-interval="1000">
                    <img class="img-fluid" src="<c:out value="/img/cuahang/cuahang-4.png"/>" width="1100" height="400"
                         alt="Second slide">
                </div>
                <div class="carousel-item" data-interval="1000">
                    <img class="img-fluid" src="<c:out value="/img/cuahang/cuahang-5.png"/>" width="1100" height="400"
                         alt="Third slide">
                </div>
            </div>
        </div>
    </div><!-- /.row -->
</div><!-- /.container -->
<%@include file="/common/web/footer.jsp" %>