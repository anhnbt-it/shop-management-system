<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 11/29/2020
  Time: 4:58 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>${pageTitle} - Pet Shop</title>
    <link href="${pageContext.request.contextPath}/template/bootstrap/css/bootstrap.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/template/css/header-footer/header.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/template/css/header-footer/search.css" rel="stylesheet">
</head>
<body>
<header class="bg-white fixed-top">
    <div class="container-fluid">
        <div class="row">
            <div class="col-md-5">
                <a href="${pageContext.request.contextPath}/home" target="_self">
                    <img src="${pageContext.request.contextPath}/img/logo.png" class="w-25">
                </a>
                <p class="lead">Niềm vui của bạn - Hạnh phúc của tôi</p>
            </div>
            <div class="col-md-2 d-flex justify-content-center d-none">
                <div class="d-md-inline infor">
                    <span style="font-size: 20px">0123456789 <br> <a href="#" class="phone_icon"><i class="fa fa-phone"
                                                                                                    style="font-size: 30px"></i></a>
                    </span>
                </div>
            </div>

            <div class="col-md-5 d-flex justify-content-center ml-auto">
                <ul class="nav navbar-nav ml-auto list-group list-group-horizontal">
                    <li class="nav-item active cart">
                        <a class="badge badge-danger" href="${pageContext.request.contextPath}/cart?action=view"><span
                                id="amount">
                        <c:choose>
                            <c:when test="${sessionScope['order'] == null}">
                                0
                            </c:when>
                            <c:otherwise>
                                ${sessionScope['order'].getList().size()}
                            </c:otherwise>
                        </c:choose></span> <i
                                class="fas fa-shopping-cart"></i></a>
                    </li>
                </ul>
            </div>
        </div>
    </div>
    <nav class="navbar navbar-expand-lg navbar-dark" style="background: blueviolet;">
        <div class="container-fluid">
            <a class="navbar-brand" href="${pageContext.request.contextPath}/home"><i class="fas fa-home"></i> Trang chủ</a>
            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent"
                    aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarSupportedContent">
                <ul class="navbar-nav mr-auto w-auto">
                    <li class="nav-item">
                        <a class="nav-link" href="${pageContext.request.contextPath}/products"><i
                                class="fas fa-list-alt mr-1"></i>Sản Phẩm<span
                                class="sr-only">(current)</span></a>
                    </li>
                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" href="#" id="dogDropdown" role="button"
                           data-toggle="dropdown"
                           aria-haspopup="true" aria-expanded="false">
                            <i class="fas fa-dog mr-1"></i> Chó
                        </a>
                        <div class="dropdown-menu" aria-labelledby="dogDropdown">
                            <c:forEach var="category" items="${categoriesDog}">
                                <a class="dropdown-item"
                                   href="${pageContext.request.contextPath}/products?idCategory=${category.getId()}">${category.getName()}</a>
                            </c:forEach>
                        </div>
                    </li>
                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" href="#" id="catDropdown" role="button"
                           data-toggle="dropdown"
                           aria-haspopup="true" aria-expanded="false">
                            <img src="https://camo.githubusercontent.com/63371d36886ee658f5a97401f393e1ab1684b2fd3de674b8f5efc7d410b2a3d0/68747470733a2f2f6d656469612e67697068792e636f6d2f6d656469612f57556c706c634d704f43456d5447427442572f67697068792e676966" data-canonical-src="https://media.giphy.com/media/WUlplcMpOCEmTGBtBW/giphy.gif" style="max-width:100%;" width="30"> Mèo
                        </a>
                        <div class="dropdown-menu" aria-labelledby="catDropdown">

                            <c:forEach var="category" items="${categoriesCat}">
                                <a class="dropdown-item"
                                   href="${pageContext.request.contextPath}/products?idCategory=${category.getId()}"><c:out
                                        value="${category.name}"/></a>
                            </c:forEach>
                        </div>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="${pageContext.request.contextPath}/lien-he"><span
                                class="fas fa-phone mr-1"></span> Liên hệ</a>
                    </li>
                    <li class="nav-item dropdown">
                        <c:choose>
                            <c:when test="${sessionScope['user'] == null}">
                                <a class="nav-link dropdown-toggle" href="#" id="accountDropdown" role="button"
                                   data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                    <span class="fas fa-user mr-1"></span> Tài khoản
                                </a>
                                <div class="dropdown-menu" aria-labelledby="accountDropdown">
                                    <a class="dropdown-item" href="${pageContext.request.contextPath}/account/login">Đăng
                                        nhập</a>
                                    <a class="dropdown-item" href="${pageContext.request.contextPath}/account/register">Đăng
                                        ký</a>
                                </div>
                            </c:when>
                            <c:otherwise>
                                <a class="nav-link dropdown-toggle" href="#" id="accountDropdown" role="button"
                                   data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                    <span class="fas fa-user mr-1"></span> Welcome ${sessionScope['user'].getName()}
                                </a>
                                <div class="dropdown-menu" aria-labelledby="accountDropdown">
                                    <a class="dropdown-item" href="#">Profile</a>
                                    <a class="dropdown-item" href="#">Change password</a>
                                    <div class="dropdown-divider"></div>
                                    <a class="dropdown-item" href="/account/sign-out">Sign out</a>
                                </div>
                            </c:otherwise>
                        </c:choose>
                    </li>
                </ul>
                <ul class="nav navbar-nav ml-auto">
                    <li class="nav-item active">
                        <div class="d-flex justify-content-center h-100">
                            <form class="searchbar" method="post" action="${pageContext.request.contextPath}/products">
                                <input type="hidden" name="action" value="search">
                                <input class="search_input" type="text" name="searchproduct" placeholder="Search...">
                                <button type="submit" class="btn btn-primary"><i class="fas fa-search"></i></button>
                            </form>
                        </div>
                    </li>
                </ul>
            </div>
        </div>
    </nav>
</header>
