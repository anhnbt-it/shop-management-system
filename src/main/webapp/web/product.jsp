<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 11/30/2020
  Time: 10:50 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:set var="pageTitle" scope="request" value="Add New Customer"/>
<jsp:include page="/common/web/header.jsp"></jsp:include>
<div class="container" style="padding-top: 180px;">
    <nav aria-label="breadcrumb">
        <ol class="breadcrumb">
            <li class="breadcrumb-item"><a href="${pageContext.request.contextPath}/home">Trang chủ</a></li>
            <li class="breadcrumb-item active"><a href="${pageContext.request.contextPath}/products">Sản phẩm</a></li>
        </ol>
    </nav>
    <div class="row">
        <div class="col-lg-3">
            <div class="accordion" id="accordionExample">
                <div class="card">
                    <div class="card-header" id="headingOne">
                        <h2 class="mb-0">
                            <button class="btn btn-link btn-block text-left" type="button" data-toggle="collapse"
                                    data-target="#collapseOne" aria-expanded="true" aria-controls="collapseOne">
                                <i class="fas fa-dog mr-1"></i> Chó <i class="fas fa-arrow-down"></i>
                            </button>
                        </h2>
                    </div>

                    <div id="collapseOne" class="collapse show" aria-labelledby="headingOne"
                         data-parent="#accordionExample">
                        <div class="card-body">
                            <ul class="list-unstyled">
                                <c:forEach var="categoryDog" items="${categoriesDog}">
                                    <li><a href="/products?idCategory=${categoryDog.getId()}"><c:out
                                            value="${categoryDog.name}"/> </a></li>
                                </c:forEach>
                            </ul>
                        </div>
                    </div>
                </div>
                <div class="card">
                    <div class="card-header" id="headingTwo">
                        <h2 class="mb-0">
                            <button class="btn btn-link btn-block text-left collapsed" type="button"
                                    data-toggle="collapse" data-target="#collapseTwo" aria-expanded="false"
                                    aria-controls="collapseTwo">
                                <i class="fas fa-cat mr-1"></i> Mèo <i class="fas fa-arrow-down"></i>
                            </button>
                        </h2>
                    </div>
                    <div id="collapseTwo" class="collapse" aria-labelledby="headingTwo" data-parent="#accordionExample">
                        <div class="card-body">
                            <ul class="list-unstyled">
                                <c:forEach var="categoryCat" items="${categoriesCat}">
                                    <li><a href="/products?idCategory=${categoryCat.getId()}"><c:out
                                            value="${categoryCat.name}"/> </a></li>
                                </c:forEach>
                            </ul>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- /.col-lg-3 -->
        <div class="col-lg-9">
            <h2>Danh mục sản phẩm</h2>
            <form action="${pageContext.request.contextPath}/products" method="post">
                <input type="hidden" name="action" value="order">
                <input type="hidden" name="idCategory" value="${idCategory}">
                <div class="form-row align-items-center">
                    <div class="col-auto">
                        <label class="sr-only" for="sortByName">Name</label>
                        <div class="input-group mb-2">
                            <div class="input-group-prepend">
                                <div class="input-group-text">Name</div>
                            </div>
                            <select name="sortByName" class="form-control" id="sortByName">
                                <option selected>Choose...</option>
                                <option value="1">A-Z</option>
                                <option value="2">Z-A</option>
                            </select>
                        </div>
                    </div>
                    <div class="col-auto">
                        <label class="sr-only" for="sortByPrice">Name</label>
                        <div class="input-group mb-2">
                            <div class="input-group-prepend">
                                <div class="input-group-text">Name</div>
                            </div>
                            <select name="sortByPrice" class="form-control" id="sortByPrice">
                                <option selected>Choose...</option>
                                <option value="3">Giá cao nhất</option>
                                <option value="4">Giá thấp nhất</option>
                            </select>
                        </div>
                    </div>
                    <div class="col-auto">
                        <button type="submit" class="btn btn-primary mb-2">Sắp xếp</button>
                    </div>
                </div>
            </form>
            <div class="row">
                <c:forEach items='${requestScope["products"]}' var="product">
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
                            <button class="btn btn-success btn-block text-white" onclick="loadDoc(${product.getId()})">
                                <i
                                        class="fas fa-shopping-cart"></i> Thêm vào giỏ hàng
                            </button>
                        </div>
                    </div><!-- /.col-lg-4 -->
                </c:forEach>
            </div><!-- /.row -->
        </div><!-- /.col-lg-9 -->
    </div><!-- /.row -->
</div><!-- /.container -->
<%@include file="/common/web/footer.jsp" %>