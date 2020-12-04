<%--
  Created by IntelliJ IDEA.
  User: macbook
  Date: 12/3/20
  Time: 11:05
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:set var="pageTitle" scope="request" value="All Category"/>
<jsp:include page="../inc/header.jsp"></jsp:include>
<jsp:include page="../inc/nav.jsp"></jsp:include>
<div class="container-fluid">
    <div class="row">
        <div class="col-md-12 col-sm-12">
            <div class="card shadow mb-4">
                <div class="card-header py-3">
                    <h6 class="m-0 font-weight-bold"><i class="fas fa-list"></i> ${pageTitle} <span
                            class="badge badge-secondary">${requestScope['countCustomers']}</span> <a
                            href="${pageContext.request.contextPath}/administrator/customers?act=create"
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
                        <c:when test="${requestScope['listcategory'] == null}">
                            <div class="alert alert-info">No data.</div>
                        </c:when>
                        <c:otherwise>
                            <table class="table table-striped">
                                <thead class="thead-dark">
                                <tr>
                                    <th>#Id</th>
                                    <th>Name</th>
                                    <th>Description</th>
                                    <th>Image</th>
                                    <th>Type</th>
                                    <th>Action</th>
                                </tr>
                                </thead>
                                <c:forEach items="${requestScope['listcategory']}" var="category">
                                    <tr>
                                        <td scope="row">${category.getId()}</td>
                                        <td>${category.getName()}</td>
                                        <td>${category.getDesc()}</td>
                                        <td>${category.getImg()}</td>
                                        <td>${category.getType_id()}</td>
                                        <td><a href="/administrator/categories?act=edit2&id=${category.getId()}"
                                               class="btn btn-info" data-toggle="tooltip" data-placement="bottom"
                                               title="Edit">Edit</a>
                                            <a href="${pageContext.request.contextPath}/administrator/category?act=delete2&id=${category.getId()}"
                                               class="btn btn-danger" data-toggle="tooltip" data-placement="top"
                                               title="Delete"
                                               onclick="return confirm('Are you sure you want to delete this item?');"><i
                                                    class="fas fa-trash"></i></a>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </table>
                        </c:otherwise>
                    </c:choose>
                </div>
            </div>
        </div>
    </div><!-- /.row -->
</div>
<!-- /.container-fluid -->
<jsp:include page="../inc/copyright.jsp"></jsp:include>
<jsp:include page="../inc/scripts.jsp"></jsp:include>
<jsp:include page="../inc/footer.jsp"></jsp:include>