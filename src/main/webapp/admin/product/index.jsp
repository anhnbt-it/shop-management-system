<%--
  Created by IntelliJ IDEA.
  User: anhnbt
  Date: 30/11/2020
  Time: 14:41
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:set var="pageTitle" scope="request" value="All Products"/>
<jsp:include page="../inc/header.jsp"></jsp:include>
<!-- Begin Page Content -->
<div class="container-fluid">
    <div class="row">
        <div class="col-md-9 col-sm-12">
            <div class="card shadow mb-4">
                <div class="card-header py-3">
                    <h6 class="m-0 font-weight-bold"><i class="fas fa-list"></i> ${pageTitle} <a
                            href="${pageContext.request.contextPath}/administrator/products?act=create"
                            class="btn btn-primary btn-sm"
                            data-toggle="tooltip" data-placement="top" title="Add New"><i class="fas fa-plus"></i> Add
                        New</a>
                    </h6>
                </div>
                <div class="card-body">
                    <% if (session.getAttribute("msg") != null) { %>
                    <%=session.getAttribute("msg") %>
                    <% session.removeAttribute("msg"); %>
                    <% } %>
                    <c:choose>
                        <c:when test="${requestScope['products'] == null}">
                            <div class="alert alert-info">No data.</div>
                        </c:when>
                        <c:otherwise>

                            <div class="table-responsive">
                                <table class="table table-striped table-bordered" id="dataTable">
                                    <thead>
                                    <tr>
                                        <th scope="col">#</th>
                                        <th scope="col">Name</th>
                                        <th scope="col">Category</th>
                                        <th scope="col">Quantity</th>
                                        <th scope="col">Price</th>
                                        <th scope="col">Discount</th>
                                        <th scope="col">Img</th>
                                        <th scope="col">Date Added</th>
                                        <th scope="col">Actions</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <c:forEach items="${requestScope['products']}" var="product">
                                        <tr>
                                            <th scope="countProducts">${product.getId()}</th>
                                            <td>${product.getName()}</td>
                                            <td>${product.getCategory()}</td>
                                            <td>${product.getQty()}</td>
                                            <td>${product.getPrice()}</td>
                                            <td>${product.getDiscount()}</td>
                                            <td>${product.getImg()}</td>
                                            <td>${product.getCreated()}</td>
                                            <td>
                                                <a href="${pageContext.request.contextPath}/administrator/products?act=show&id=${product.getId()}"
                                                   class="btn btn-secondary mr-1" data-toggle="tooltip"
                                                   data-placement="top"
                                                   title="View"><i class="fas fa-eye"></i></a>
                                                <a href="${pageContext.request.contextPath}/administrator/products?act=edit&id=${product.getId()}"
                                                   class="btn btn-info mr-1" data-toggle="tooltip" data-placement="top"
                                                   title="Edit"><i class="fas fa-pen"></i></a>
                                                <a href="${pageContext.request.contextPath}/administrator/products?act=delete&id=${product.getId()}"
                                                   class="btn btn-danger" data-toggle="tooltip" data-placement="top"
                                                   title="Delete"
                                                   onclick="return confirm('Are you sure you want to delete this item?');"><i
                                                        class="fas fa-trash"></i></a>
                                            </td>
                                        </tr>
                                    </c:forEach>
                                    </tbody>
                                </table>
                            </div>
                            <!-- /.table-responsive -->
                        </c:otherwise>
                    </c:choose>
                    <div class="row">
                        <div class="col-md-6 text-left">
                            <nav aria-label="Navigation for users">
                                <ul class="pagination">
                                    <c:if test="${currentPage != 1}">
                                        <li class="page-item">
                                            <a class="page-link"
                                               href="${pageContext.request.contextPath}/administrator/products?recordsPerPage=${recordsPerPage}&currentPage=${currentPage-1}">Previous</a>
                                        </li>
                                    </c:if>
                                    <c:forEach begin="1" end="${countProducts}" var="i">
                                        <c:choose>
                                            <c:when test="${currentPage eq i}">
                                                <li class="page-item active">
                                                    <a class="page-link">${i} <span class="sr-only">(current)</span></a>
                                                </li>
                                            </c:when>
                                            <c:otherwise>
                                                <li class="page-item">
                                                    <a class="page-link"
                                                       href="${pageContext.request.contextPath}/administrator/products?recordsPerPage=${recordsPerPage}&currentPage=${i}">${i}</a>
                                                </li>
                                            </c:otherwise>
                                        </c:choose>
                                    </c:forEach>
                                    <c:if test="${currentPage lt countProducts}">
                                        <li class="page-item">
                                            <a class="page-link"
                                               href="${pageContext.request.contextPath}/administrator/products?recordsPerPage=${recordsPerPage}&currentPage=${currentPage+1}">Next</a>
                                        </li>
                                    </c:if>
                                </ul>
                            </nav>
                        </div>
                        <div class="col-md-6 text-right">
                            <span>Showing 1 to 4 of ${requestScope['countProducts']} (${countProducts} Pages)</span>
                        </div>
                    </div><!-- /.row -->
                </div><!-- /.card-body-->
            </div><!-- /.card -->
        </div><!-- /.col-md-9 -->
        <div class="col-md-3 col-sm-12 hidden-sm hidden-xs">
            <div class="card">
                <form action="${pageContext.request.contextPath}/administrator/products" method="post">
                    <div class="card-header"><i class="fas fa-filter"></i> Filter</div>
                    <div class="card-body">
                        <input type="hidden" name="act" value="search">
                        <div class="form-group">
                            <label for="query">Name:</label>
                            <input type="text" class="form-control" name="query" id="query"
                                   placeholder="Enter username" required>
                        </div>
                    </div>
                    <div class="card-footer">
                        <button type="submit" class="btn btn-primary" data-toggle="tooltip" data-placement="top"
                                title="Filter"><i class="fas fa-filter"></i> Filter
                        </button>
                    </div>
                </form>
            </div>
        </div>
    </div>
    <!-- /.row -->
</div>
<!-- /.container-fluid -->
<jsp:include page="../inc/footer.jsp"></jsp:include>