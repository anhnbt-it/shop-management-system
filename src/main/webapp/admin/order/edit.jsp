<%--
  Created by IntelliJ IDEA.
  User: anhnbt
  Date: 01/12/2020
  Time: 08:32
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:set var="pageTitle" scope="request" value="Edit Order: ${requestScope['order'].getOrderDate()}"/>
<jsp:include page="../inc/header.jsp"></jsp:include>
<jsp:include page="../inc/nav.jsp"></jsp:include>
<div class="container-fluid">
    <div class="card shadow mb-4">
        <div class="card-header py-3">
            <h6 class="m-0 font-weight-bold">${pageTitle} <a
                    href="${pageContext.request.contextPath}/administrator/orders"
                    class="btn btn-primary btn-sm" data-toggle="tooltip" data-placement="top" title="All Orders"><i
                    class="fas fa-reply"></i> All Users</a></h6>
        </div>
        <form action="${pageContext.request.contextPath}/administrator/orders" method="post">

            <div class="card-body">
                <% if (session.getAttribute("msg") != null) { %>
                <%=session.getAttribute("msg") %>
                <% session.removeAttribute("msg"); %>
                <% } %>
                <input type="hidden" name="act" value="update">
                <input type="hidden" name="id" value="${requestScope['order'].getId()}">

                <fieldset>
                    <legend>Order Details</legend>
                    <div class="form-group row">
                        <label for="orderDate" class="col-md-2 col-form-label text-md-right">Order Date <span
                                class="text-danger"></span>:</label>
                        <div class="col-md-10">
                            <input type="text" class="form-control" name="orderDate" id="orderDate"
                                   placeholder="Order date" value="${requestScope['order'].getOrderDate()}" readonly>
                        </div>
                    </div>
                    <div class="form-group row">
                        <label for="status" class="col-md-2 col-form-label text-md-right">Status <span
                                class="text-danger">*</span>:</label>
                        <div class="col-md-10">
                            <input type="text" class="form-control" name="status" id="status"
                                   placeholder="Enter Status" value="${requestScope['order'].getStatus()}" required>
                        </div>
                    </div>
                    <div class="form-group row">
                        <label for="comments" class="col-md-2 col-form-label text-md-right">Comments <span
                                class="text-danger">*</span>:</label>
                        <div class="col-md-10">
                            <input type="text" class="form-control" name="comments" id="comments"
                                   placeholder="Enter Comment" value="${requestScope['order'].getComment()}" required>
                        </div>
                    </div>
                    <div class="form-group row">
                        <label for="customer" class="col-md-2 col-form-label text-md-right">Customer <span
                                class="text-danger"></span>:</label>
                        <div class="col-md-10">
                            <input type="text" class="form-control" name="customer" id="customer"
                                   placeholder="Enter address" value="${requestScope['order'].getCustomer_id()}" readonly>
                        </div>
                    </div>
<%--                    <div class="form-group row">--%>
<%--                        <label class="col-md-2 col-form-label text-md-right">Gender</label>--%>
<%--                        <div class="col-md-10">--%>
<%--                            <div class="form-check form-check-inline">--%>
<%--                                <input class="form-check-input" type="radio" name="gender" id="male" value="1" <c:if test="${requestScope['customer'].getGender() == 1}">checked</c:if>>--%>
<%--                                <label class="form-check-label" for="male">Male</label>--%>
<%--                            </div>--%>
<%--                            <div class="form-check form-check-inline">--%>
<%--                                <input class="form-check-input" type="radio" name="gender" id="female" value="0" <c:if test="${requestScope['customer'].getGender() == 0}">checked</c:if>>--%>
<%--                                <label class="form-check-label" for="female">Female</label>--%>
<%--                            </div>--%>
<%--                        </div>--%>
<%--                    </div>--%>
                </fieldset>
<%--                <fieldset>--%>
<%--                    <legend>Other</legend>--%>
<%--                    <div class="form-group row">--%>
<%--                        <label for="password" class="col-md-2 col-form-label text-md-right">Password</label>--%>
<%--                        <div class="col-md-10">--%>
<%--                            <input type="password" class="form-control" name="password" id="password"--%>
<%--                                   placeholder="Password">--%>
<%--                        </div>--%>
<%--                    </div>--%>
<%--                    <div class="form-group row">--%>
<%--                        <label for="confirm" class="col-md-2 col-form-label text-md-right">Confirm</label>--%>
<%--                        <div class="col-md-10">--%>
<%--                            <input type="password" class="form-control" name="confirm" id="confirm"--%>
<%--                                   placeholder="Confirm">--%>
<%--                        </div>--%>
<%--                    </div>--%>
<%--                    <div class="form-group row">--%>
<%--                        <label for="status" class="col-md-2 col-form-label text-md-right">Status</label>--%>
<%--                        <div class="col-md-10">--%>
<%--                            <select name="status" id="status" class="form-control">--%>
<%--                                <option value="1" <c:if test="${requestScope['customer'].getStatus() == 1}">selected</c:if>>Enabled</option>--%>
<%--                                <option value="0" <c:if test="${requestScope['customer'].getStatus() == 0}">selected</c:if>>Disabled</option>--%>
<%--                            </select>--%>
<%--                        </div>--%>
<%--                    </div>--%>
<%--                </fieldset>--%>
            </div><!-- /.card-body-->
            <div class="card-footer">
                <div class="col-md-6 offset-md-4">
                    <button type="submit" class="btn btn-primary" data-toggle="tooltip" data-placement="top"
                            title="Save"><i class="fas fa-save"></i> Save
                    </button>
                    <a href="${pageContext.request.contextPath}/administrator/orders"
                       class="btn btn-secondary"
                       data-toggle="tooltip" data-placement="top" title="Cancel"><i class="fas fa-reply"></i>
                        Cancel</a>
                </div>
            </div>
        </form>
    </div><!-- /.card -->
</div><!-- /.container-fluid -->
<jsp:include page="../inc/copyright.jsp"></jsp:include>
<jsp:include page="../inc/scripts.jsp"></jsp:include>
<jsp:include page="../inc/footer.jsp"></jsp:include>