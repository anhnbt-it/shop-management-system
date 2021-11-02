<%--
  Created by IntelliJ IDEA.
  User: anhnbt
  Date: 01/12/2020
  Time: 15:48
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:set var="pageTitle" scope="request" value="Account Login"/>
<jsp:include page="../inc/header.jsp"></jsp:include>
<div class="container-fluid">
    <div class="row d-flex justify-content-center align-self-center">
        <div class="col-md-6">
            <div class="card shadow mb-4">
                <div class="card-header py-3">
                    <h6 class="m-0 font-weight-bold">${pageTitle}</h6>
                </div>
                <form action="${pageContext.request.contextPath}/account/login" method="post">
                    <div class="card-body px-4">
                        <c:if test="${sessionScope['msg'] != null}">
                            <%=session.getAttribute("msg")%>
                            <%session.removeAttribute("msg");%>
                        </c:if>
                        <div class="form-group">
                            <label for="email">E-Mail Address <span
                                    class="text-danger">*</span>:</label>
                            <input type="email" class="form-control" name="email" id="email"
                                   placeholder="E-Mail Address" required>
                        </div>
                        <div class="form-group">
                            <label for="password">Password <span
                                    class="text-danger">*</span>:</label>
                            <input type="password" class="form-control" name="password" id="password"
                                   placeholder="Password" required>
                        </div>
                        <div class="form-group">
                            <button type="submit" class="btn btn-primary btn-block" data-toggle="tooltip" data-placement="top"
                                    title="Save">Login
                            </button>
                        </div>
                    </div><!-- /.card-body-->
                    <div class="card-footer px-4">
                        <a href="${pageContext.request.contextPath}/account/register" class="btn btn-success btn-block btn-sm">Create New Account</a>
                    </div>
                </form>
            </div><!-- /.card -->
        </div><!--- /.col-md-6 -->
    </div><!-- /.row -->
</div><!-- /.container-fluid -->
<jsp:include page="../inc/footer.jsp"></jsp:include>

