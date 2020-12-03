<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 12/2/2020
  Time: 10:09 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/common/libs.jsp" %>
<html>
<head>
    <title>${requestScope.product.getName()}</title>
    <link href="<c:url value="/template/bootstrap/css/bootstrap.css"/>" rel="stylesheet" >
    <link href="<c:url value="/template/css/sidebar.css"/>" rel="stylesheet" >
    <link href="<c:url value="/template/css/product/product.css"/>" rel="stylesheet" >
</head>
<body>
<%@include file="/common/web/header.jsp"%>
<div class='container'>
    <h1> Chi Tiet San Pham</h1>
    <hr>
    <div class="row">
        <div class="col">
            <div style=" border: black 1px solid;width: 100%;height: 100%">
                <div class="discount">
                    <p>- ${product.getDiscount()}% </p>
                </div>
            <img class="card-img-top" src="<c:url value="${product.getImg()}"/>" height="100%" alt="loading">
            </div>
        </div>
        <div class="col">
            <h3><c:out value="${product.name}"/></h3>
            <br>
            <hr>
            <div class="price">
                Price: ${product.price}
            </div>
            <hr>
            <div style="color: red; font-weight: bolder">
                Realprice: ${requestScope.product.getRealPrice()}
            </div>
            <hr>
            <div>Mô tả : ${product.getDescription()}</div>
            <hr>
<%--            <form action="/cart?action=add&id=${product.getId()}&page=productDetail">--%>
<%--                <input type="hidden" name="action" value="add">--%>
<%--                <input type="hidden" name="action" value="add">--%>
               <a href="/cart?action=add&id=${product.getId()}&page=productDetail">
                   <button type="submit" class="btn btn-success">Them vao gio hang</button>
               </a>
<%--            </form>--%>

            <hr>
        </div>
    </div>
    <br>
    <div>Các sản phẩm tương tự :</div>
    <hr>
    <div class="row">
        <c:forEach var="product1" items="${products}">
            <div class="col-lg-3 col-md-6 mb-4">
                <div class="card h-100 cardBorder">
                    <div class="discount">
                        <p>- ${product1.getDiscount()}% </p>
                    </div>
                    <a href="/products?action=view&id=${product1.getId()}"><img class="card-img-top" src="<c:url value="${product.getImg()}"/>" height="200" alt="loading"></a>
                    <div class="card-body cardBody">
                        <h4 class="card-title">
                            <a href="/products?action=view&id=${product1.getId()}">${product1.getName()}</a>
                        </h4>
                        <h5 class="realPrice">$ ${product1.getRealPrice()}</h5>
                        <h5 class="price">$${product1.getPrice()}</h5>
                        <p class="card-text"> ${product1.getDescription()}</p>
                    </div>
                    <div class="card-footer ml-auto">
                        <a class="nav-link" href="/cart?action=add&id=${product1.getId()}&page=product" style="color: #c6c8ca; font-size: larger"><i class="fas fa-shopping-cart"></i></a>
                    </div>
                </div>
            </div>
        </c:forEach>
    </div>
</div>
<%@include file="/common/web/footer.jsp"%>
</body>
</html>
