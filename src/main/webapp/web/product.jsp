<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 11/30/2020
  Time: 10:50 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/common/libs.jsp" %>
<html>
<head>
    <title>Product</title>
    <%--    <link rel="stylesheet" href="template/css/sidebar.css">--%>
    <%--    <link rel="stylesheet" href="template/css/product/product.css">--%>
    <%--    <link rel="stylesheet" href="template/bootstrap/css/bootstrap.css">--%>
    <link href="<c:url value="/template/bootstrap/css/bootstrap.css"/>" rel="stylesheet">
    <link href="<c:url value="/template/css/sidebar.css"/>" rel="stylesheet">
    <link href="<c:url value="/template/css/product/product.css"/>" rel="stylesheet">
</head>
<body>
<%@include file="/common/web/header.jsp" %>

<div class="container">

    <div class="row link">
        <a href="/home"> Trang chủ</a> <span> &emsp; > &emsp; </span> <span> chó &emsp;</span>
    </div>
    <hr>
    <!-- row 1 -->

    <div class="row">

        <div class="col-lg-3">

            <div class="nav-side-menu">
                <div class="brand">Danh mục sản phẩm</div>
                <i class="fa fa-bars fa-2x toggle-btn" data-toggle="collapse" data-target="#menu-content"></i>

                <div class="menu-list">

                    <ul id="menu-content" class="menu-content collapse out">

                        <li data-toggle="collapse" data-target="#dog" class="collapsed">
                            <a href="#"><i class="fas fa-dog mr-1"></i> Chó <span class="arrow"></span></a>
                        </li>
                        <ul class="sub-menu collapse" id="dog">
                            <c:forEach var="categoryDog" items="${categoriesDog}">
                                <li><a href="/products?idCategory=${categoryDog.getId()}"><c:out
                                        value="${categoryDog.name}"/> </a></li>
                            </c:forEach>

                        </ul>

                        <li data-toggle="collapse" data-target="#cat" class="collapsed">
                            <a href="#"><i class="fas fa-cat mr-1"></i> Mèo <span class="arrow"></span></a>
                        </li>
                        <ul class="sub-menu collapse" id="cat">
                            <c:forEach var="categoryCat" items="${categoriesCat}">
                                <li><a href="/products?idCategory=${categoryCat.getId()}"><c:out
                                        value="${categoryCat.name}"/> </a></li>
                            </c:forEach>

                        </ul>

                    </ul>
                </div>

            </div>
        </div>
        <!-- /.col-lg-3 -->

        <div class="col-lg-9">
            <div>
                <h2>Danh mục sản phẩm</h2>

                <form action="/products" method="post">
                    <input type="hidden" name="action" value="order">
                    <input type="hidden" name="idCategory" value="${idCategory}">
                    <div class="input-group mb-3 d-flex">
                        <div class="input-group-prepend">
                            <label class="input-group-text" for="inputGroupSelect01" style="width: auto">Name</label>
                        </div>
                        <select name="sortByName" class="custom-select" id="inputGroupSelect01"
                                style="height: calc(1.5em + 0.75rem + 10px)">
                            <option selected>Choose...</option>
                            <option value="1">A-Z</option>
                            <option value="2">Z-A</option>
                        </select>
                        <div class="input-group-prepend">
                            <label class="input-group-text" for="inputGroupSelect02">Giá</label>
                        </div>
                        <select name="sortByPrice" class="custom-select" id="inputGroupSelect02"
                                style="height: calc(1.5em + 0.75rem + 10px)">
                            <option selected>Choose...</option>
                            <option value="3">Giá cao nhất</option>
                            <option value="4">Giá thấp nhất</option>
                        </select>
                        <div class="ml-auto">
                            <input type="submit" value="Sắp xếp"
                                   style="border-radius: 30px; color: blue; background: orange; font-weight: bold">
                        </div>
                    </div>
                </form>

            </div>

            <div class="row">

                <c:forEach items='${requestScope["products"]}' var="product">
                    <div class="col-lg-4 col-md-6 mb-4">

                        <div class="card h-100 cardBorder">

                            <div class="discount">
                                <p>- ${product.getDiscount()}% </p>
                            </div>
                            <a href="/products?action=view&id=${product.getId()}"><img class="card-img-top" src="<c:url value="${product.getImg()}"/>" width="700"
                                             height="200" alt="loading"></a>
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
                            </div>


                                <%--                        <div class="discount ">--%>
                                <%--                            <p>-10%</p>--%>
                                <%--                        </div>--%>
                                <%--                        <a href="#"><img class="card-img-top" src="http://placehold.it/700x400" alt=""></a>--%>
                                <%--                        <div class="card-body cardBody">--%>
                                <%--                            <h4 class="card-title">--%>
                                <%--                                <a href="#">Item One</a>--%>
                                <%--                            </h4>--%>
                                <%--                            <h5 class="realPrice">$24.99</h5>--%>
                                <%--                            <h5 class="price">$24.99</h5>--%>
                                <%--                            <p class="card-text">Lorem ipsum dolor sit amet, consectetur adipisicing elit. Amet numquam aspernatur!</p>--%>
                                <%--                        </div>--%>
                                <%--                        <div class="card-footer">--%>
                                <%--                            <small class="text-muted">&#9733; &#9733; &#9733; &#9733; &#9734;</small>--%>
                                <%--                        </div>--%>
                        </div>
                    </div>
                </c:forEach>

            </div>
            <!-- /.row -->

        </div>
        <!-- /.col-lg-9 -->

    </div>
    <!-- /.row -->

</div>

<%@include file="/common/web/footer.jsp" %>
</body>
</html>

