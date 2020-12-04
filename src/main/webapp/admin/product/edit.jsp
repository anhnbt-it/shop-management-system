<%--
  Created by IntelliJ IDEA.
  User: anhnbt
  Date: 01/12/2020
  Time: 08:32
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:set var="pageTitle" scope="request" value="Edit User: ${product.getName()}"/>
<jsp:include page="../inc/header.jsp"></jsp:include>
<!-- Begin Page Content -->
<div class="container-fluid">
    <!-- DataTales Example -->
    <div class="card shadow mb-4">
        <div class="card-header py-3">
            <h6 class="m-0 font-weight-bold">${pageTitle} <a
                    href="${pageContext.request.contextPath}/administrator/products"
                    class="btn btn-primary btn-sm" data-toggle="tooltip" data-placement="top" title="All Products"><i
                    class="fas fa-reply"></i> All Products</a></h6>
        </div>
        <form action="${pageContext.request.contextPath}/administrator/products" method="post">

            <div class="card-body">
                <% if (session.getAttribute("msg") != null) { %>
                <%=session.getAttribute("msg") %>
                <% session.removeAttribute("msg"); %>
                <% } %>
                <input type="hidden" name="act" value="update">
                <input type="hidden" name="id" value="${product.getId()}">
                <div class="form-group row">
                    <label for="name" class="col-md-4 col-form-label text-md-right">Name <span
                            class="text-danger">*</span>:</label>
                    <div class="col-md-6">
                        <input type="text" class="form-control" name="name" id="name"
                               placeholder="Enter name" value="${product.getName()}" required>
                    </div>
                </div>
                <div class="form-group row">
                    <label class="col-md-4 col-form-label text-md-right" for="category">Categories</label>
                    <div class="col-md-6">
                        <select name="category" id="category" class="form-control">
                            <option value="1">Chó</option>
                        </select>
                    </div>
                </div>
                <div class="form-group row">
                    <label for="desc" class="col-md-4 col-form-label text-md-right">Description:</label>
                    <div class="col-md-6">
                        <textarea name="desc" id="desc" cols="30" rows="5" class="form-control">${product.getDesc()}</textarea>
                    </div>
                </div>
                <div class="form-group row">
                    <label for="qty" class="col-md-4 col-form-label text-md-right">Quantity</label>
                    <div class="col-md-6">
                        <input type="number" name="qty" id="qty" class="form-control" value="${product.getQty()}">
                    </div>
                </div>
                <div class="form-group row">
                    <label for="price" class="col-md-4 col-form-label text-md-right">Price</label>
                    <div class="col-md-6">
                        <input type="number" name="price" id="price" class="form-control" value="${product.getPrice()}">

                    </div>
                </div>
                <div class="form-group row">
                    <label for="discount" class="col-md-4 col-form-label text-md-right">Discount</label>
                    <div class="col-md-6">
                        <input type="number" name="discount" id="discount" class="form-control" value="${product.getDiscount()}">
                    </div>
                </div>
                <div class="form-group row">
                    <label for="img" class="col-md-4 col-form-label text-md-right">Image</label>
                    <div class="col-md-6">
                        <input type="file" name="img" id="img" class="form-control" value="${product.getImg()}">
                    </div>
                </div>
                <div class="form-group row">
                    <label for="status" class="col-md-4 col-form-label text-md-right">Status</label>
                    <div class="col-md-6">
                        <select name="status" id="status" class="form-control">
                            <option value="1">Enabled</option>
                            <option value="0">Disabled</option>
                        </select>
                    </div>
                </div>
            </div><!-- /.card-body-->
            <div class="card-footer">
                <div class="col-md-6 offset-md-4">
                    <button type="submit" class="btn btn-primary" data-toggle="tooltip" data-placement="top"
                            title="Save"><i class="fas fa-save"></i> Save
                    </button>
                    <a href="${pageContext.request.contextPath}/administrator/products" class="btn btn-secondary"
                       data-toggle="tooltip" data-placement="top" title="Cancel"><i class="fas fa-reply"></i> Cancel</a>
                </div>
            </div>
        </form>
    </div><!-- /.card -->

</div>
<!-- /.container-fluid -->
<jsp:include page="../inc/footer.jsp"></jsp:include>