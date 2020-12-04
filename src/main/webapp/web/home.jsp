<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 11/30/2020
  Time: 9:34 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/common/libs.jsp"%>
<html>
<head>
    <title>Title</title>
    <link href="<c:out value="/template/css/product/product.css"/>" rel="stylesheet">
</head>
<body>
    <%@include file="/common/web/header.jsp"%>
    <div class="container">

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
                            <a href="/products?action=view&id=${requestScope.discount.get(0).getId()}">
                                <img class="d-block " src="<c:out value='${requestScope["discount"].get(0).getImg()}'/>" width="1100" height="300" alt="First slide">
                            </a>
                        </div>
                        <div class="carousel-item">
                            <a href="/products?action=view&id=${requestScope.discount.get(1).getId()}">
                                <img class="d-block" src="<c:out value='${requestScope["discount"].get(1).getImg()}'/>" width="1100" height="300" alt="Second slide">
                            </a>
                        </div>
                        <div class="carousel-item">
                            <a href="/products?action=view&id=${requestScope.discount.get(2).getId()}">
                                <img class="d-block" src="<c:out value='${requestScope["discount"].get(2).getImg()}'/>" width="1100" height="300" alt="Third slide">
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

                <a href="#"><h1>Sản phẩm mới</h1></a>
                <hr>

                <div class="row">

                    <c:forEach items='${requestScope["date"]}' var="product">
                        <div class="col-lg-4 col-md-6 mb-4">

                            <div class="card h-100 cardBorder">

                                <div class="discount">
                                    <p>- ${product.getDiscount()}% </p>
                                </div>
                                <a href="/products?action=view&id=${product.getId()}"><img class="card-img-top" src="<c:url value="${product.getImg()}"/>" width="700" height="200" alt="loading"></a>
                                <div class="card-body cardBody">
                                    <h4 class="card-title">
                                        <a href="/products?action=view&id=${product.getId()}">${product.getName()}</a>
                                    </h4>
                                    <h5 class="realPrice">$ ${product.getRealPrice()}</h5>
                                    <h5 class="price">$${product.getPrice()}</h5>
                                    <p class="card-text"> ${product.getDescription()}</p>
                                </div>
                                <div class="card-footer ml-auto">
                                    <button class="btn btn-warning" onclick="loadDoc(${product.getId()})"
                                            style="color: #c6c8ca; font-size: larger"><i class="fas fa-shopping-cart"></i>
                                    </button>
<%--                                    <a class="nav-link" href="/cart?action=add&id=${product1.getId()}&page=home" style="color: #c6c8ca; font-size: larger"><i class="fas fa-shopping-cart"></i></a>--%>
                                </div>
                            </div>
                        </div>
                    </c:forEach>
                </div>
                <!-- /.row -->
                <a href="#"><h1>Sản phẩm bán chạy</h1></a>
                <hr>
                <div class="row">
                    <c:forEach items='${requestScope["new"]}' var="product">
                        <div class="col-lg-4 col-md-6 mb-4">

                            <div class="card h-100 cardBorder">

                                <div class="discount">
                                    <p>- ${product.getDiscount()}% </p>
                                </div>
                                <a href="/products?action=view&id=${product.getId()}"><img class="card-img-top" src="<c:url value="${product.getImg()}"/>" width="700" height="200" alt="loading"></a>
                                <div class="card-body cardBody">
                                    <h4 class="card-title">
                                        <a href="/products?action=view&id=${product.getId()}">${product.getName()}</a>
                                    </h4>
                                    <h5 class="realPrice">$ ${product.getRealPrice()}</h5>
                                    <h5 class="price">$${product.getPrice()}</h5>
                                    <p class="card-text"> ${product.getDescription()}</p>
                                </div>
                                <div class="card-footer ml-auto">
                                    <button class="btn btn-warning" onclick="loadDoc(${product.getId()})"
                                            style="color: #c6c8ca; font-size: larger"><i class="fas fa-shopping-cart"></i>
                                    </button>
<%--                                    <a class="nav-link" href="/cart?action=add&id=${product2.getId()}&page=home" style="color: #c6c8ca; font-size: larger"><i class="fas fa-shopping-cart"></i></a>--%>
                                </div>
                            </div>
                        </div>
                    </c:forEach>

                </div>
                <!-- row 2-->

                <h1 style="color: blue">Của hàng</h1>
                <hr>
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
                                <img class="d-block" src="<c:out value="/img/cuahang/cuahang-1.png"/>" width="1100" height="400" alt="First slide">
                            </div>
                            <div class="carousel-item" data-interval="1000">
                                <img class="d-block" src="<c:out value="/img/cuahang/cuahang-2.png"/>" width="1100" height="400" alt="Second slide">
                            </div>
                            <div class="carousel-item" data-interval="1000">
                                <img class="d-block" src="<c:out value="/img/cuahang/cuahang-3.png"/>" width="1100" height="400" alt="Third slide">
                            </div>
                            <div class="carousel-item" data-interval="1000">
                                <img class="d-block" src="<c:out value="/img/cuahang/cuahang-4.png"/>" width="1100" height="400" alt="Second slide">
                            </div>
                            <div class="carousel-item" data-interval="1000">
                                <img class="d-block" src="<c:out value="/img/cuahang/cuahang-5.png"/>" width="1100" height="400" alt="Third slide">
                            </div>
                        </div>

                    </div>


                </div>
                <!-- row 3-->

            </div>
            <!-- /.col-lg-12 -->

        </div>
        <!-- /.row -->

    </div>
    </div>
    <%@include file="/common/web/footer.jsp"%>
</body>
</html>
