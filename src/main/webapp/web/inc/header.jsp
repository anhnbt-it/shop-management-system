<%--
  Created by IntelliJ IDEA.
  User: anhnbt
  Date: 30/11/2020
  Time: 14:57
  To change this template use File | Settings | File Templates.
--%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>${pageTitle} - Pet Shop</title>
    <!-- Custom fonts for this template-->
    <link href="../../admin/assets/fontawesome/css/all.min.css" rel="stylesheet" type="text/css">
    <!-- Custom styles for this template-->
    <link href="../../admin/assets/css/bootstrap.min.css" rel="stylesheet">
    <link href="../../admin/assets/images/cart.png" rel="icon">
</head>
<body>
    <nav class="navbar navbar-expand-lg navbar-light bg-light">
        <a class="navbar-brand" href="#"><i class="fas fa-laugh-wink"></i></a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>

        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav mr-auto">
                <li class="nav-item active">
                    <a class="nav-link" href="#">Home <span class="sr-only">(current)</span></a>
                </li>
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" href="#" id="navbarDropdownOne" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                        Categories
                    </a>
                    <div class="dropdown-menu" aria-labelledby="navbarDropdownOne">
                        <a class="dropdown-item" href="${pageContext.request.contextPath}/administrator/categories?act=index">All Categories</a>
                        <a class="dropdown-item" href="${pageContext.request.contextPath}/administrator/categories?act=create">Add New Category</a>
                    </div>
                </li>
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" href="#" id="navbarDropdownTwo" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                        Products
                    </a>
                    <div class="dropdown-menu" aria-labelledby="navbarDropdownTwo">
                        <a class="dropdown-item" href="${pageContext.request.contextPath}/administrator/products?act=index">All Products</a>
                        <a class="dropdown-item" href="${pageContext.request.contextPath}/administrator/products?act=create">Add
                            New model.Customer</a>
                    </div>
                </li>
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" href="#" id="navbarDropdownThree" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                        <i class="fas fa-customer"></i> Users
                    </a>
                    <div class="dropdown-menu" aria-labelledby="navbarDropdownThree">
                        <a class="dropdown-item" href="${pageContext.request.contextPath}/administrator/customers?act=index">All Users</a>
                        <a class="dropdown-item" href="${pageContext.request.contextPath}/administrator/customers?act=create">Add New User</a>
                    </div>
                </li>
            </ul>
            <a class="nav-link" href="${pageContext.request.contextPath}/account/sign-out"><i class="fas fa-sign-out-alt fa-sm fa-fw mr-2 text-gray-400"></i> Sign out</a>
        </div>
    </nav>
