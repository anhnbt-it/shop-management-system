<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 11/30/2020
  Time: 9:34 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/common/libs.jsp" %>
<html>
<head>
    <title>Home - Pet Shop</title>
    <link href="<c:out value="/template/css/product/product.css"/>" rel="stylesheet">
</head>
<body>
<%@include file="/common/web/header.jsp" %>
<div class="container">
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
                                <img class="d-block " src="<c:out value='${requestScope["discount"].get(0).getImg()}'/>"
                                     width="1100" height="300" alt="First slide">
                            </a>
                        </div>
                        <div class="carousel-item">
                            <a href="${pageContext.request.contextPath}/products?action=view&id=${requestScope['discount'].get(1).getId()}">
                                <img class="d-block" src="<c:out value='${requestScope["discount"].get(1).getImg()}'/>"
                                     width="1100" height="300" alt="Second slide">
                            </a>
                        </div>
                        <div class="carousel-item">
                            <a href="${pageContext.request.contextPath}/products?action=view&id=${requestScope['discount'].get(2).getId()}">
                                <img class="d-block" src="<c:out value='${requestScope["discount"].get(2).getImg()}'/>"
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
    <c:if test="${requestScope['discount'] != null}">
        <span class="display-4">San pham moi</span>
        <div class="row">
            <c:forEach items='${requestScope["date"]}' var="product1">
                <div class="col-lg-4 col-md-6 mb-4">
                    <div class="card h-100 cardBorder">
                        <div class="discount">
                            <p>- ${requestScope['product1'].getDiscount()}% </p>
                        </div>
                        <a href="${pageContext.request.contextPath}/products?action=view&id=${requestScope['product1'].getId()}"><img class="card-img-top"
                                                                                    src="<c:url value="${requestScope['product1'].getImg()}"/>"
                                                                                    width="700" height="200"
                                                                                    alt="loading"></a>
                        <div class="card-body cardBody">
                            <h4 class="card-title">
                                <a href="${pageContext.request.contextPath}/products?action=view&id=${requestScope['product1'].getId()}">${requestScope['product1'].getName()}</a>
                            </h4>
                            <h5 class="realPrice">$ ${requestScope['product1'].getRealPrice()}</h5>
                            <h5 class="price">$${requestScope['product1'].getPrice()}</h5>
                            <p class="card-text"> ${requestScope['product1'].getDescription()}</p>
                        </div>
                        <div class="card-footer ml-auto">
                            <a class="nav-link" href="${pageContext.request.contextPath}/cart?action=add&id=${requestScope['product1'].getId()}&page=home"
                               style="color: #c6c8ca; font-size: larger"><i class="fas fa-shopping-cart"></i></a>
                        </div>
                    </div>
                </div>
            </c:forEach>
        </div>
        <!-- /.row -->
    </c:if>
    <c:if test="${requestScope['new'] != null}">
        <div class="row">
            <div class="col-md-12"><span class="display-4">San pham ban chay</span></div>
        </div>
        <div class="row">
            <c:forEach items='${requestScope["new"]}' var="product2">
                <div class="col-lg-4 col-md-6 mb-4">
                    <div class="card h-100 cardBorder">
                        <div class="discount">
                            <p>- ${requestScope['product2'].getDiscount()}% </p>
                        </div>
                        <a href="${pageContext.request.contextPath}/products?action=view&id=${requestScope['product2'].getId()}"><img class="card-img-top" src="<c:url value="${requestScope['product2'].getImg()}"/>" width="700" height="200" alt="loading"></a>
                            <div class="card-body cardBody">
                                <h4 class="card-title">
                                    <a href="${pageContext.request.contextPath}/products?action=view&id=${requestScope['product2'].getId()}">${requestScope['product2'].getName()}</a>
                                </h4>
                                <h5 class="realPrice">$ ${requestScope['product2'].getRealPrice()}</h5>
                                <h5 class="price">$${requestScope['product2'].getPrice()}</h5>
                                <p class="card-text"> ${requestScope['product2'].getDescription()}</p>
                            </div>
                            <div class="card-footer ml-auto">
                                <a class="nav-link" href="${pageContext.request.contextPath}/cart?action=add&id=${requestScope['product2'].getId()}&page=home"
                                   style="color: #c6c8ca; font-size: larger"><i class="fas fa-shopping-cart"></i></a>
                            </div>
                    </div>
                </div>
            </c:forEach>
        </div>
        <!-- /.row -->
    </c:if>
    <span class="display-4">SHOP</span>
    <div class="row">
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
                    <img class="d-block" src="<c:out value="/img/cuahang/cuahang-1.png"/>" width="1100" height="400"
                         alt="First slide">
                </div>
                <div class="carousel-item" data-interval="1000">
                    <img class="d-block" src="<c:out value="/img/cuahang/cuahang-2.png"/>" width="1100" height="400"
                         alt="Second slide">
                </div>
                <div class="carousel-item" data-interval="1000">
                    <img class="d-block" src="<c:out value="/img/cuahang/cuahang-3.png"/>" width="1100" height="400"
                         alt="Third slide">
                </div>
                <div class="carousel-item" data-interval="1000">
                    <img class="d-block" src="<c:out value="/img/cuahang/cuahang-4.png"/>" width="1100" height="400"
                         alt="Second slide">
                </div>
                <div class="carousel-item" data-interval="1000">
                    <img class="d-block" src="<c:out value="/img/cuahang/cuahang-5.png"/>" width="1100" height="400"
                         alt="Third slide">
                </div>
            </div>
        </div>
    </div><!-- /.row -->
</div>
<%@include file="/common/web/footer.jsp" %>
</body>
</html>
