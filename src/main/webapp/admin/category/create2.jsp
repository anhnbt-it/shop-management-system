<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:set var="pageTitle" scope="request" value="Add New Product"/>
<jsp:include page="../inc/header.jsp"></jsp:include>
<!-- Begin Page Content -->
<div class="container-fluid">
    <!-- DataTales Example -->
    <div class="card shadow mb-4">
        <div class="card-header py-3">
            <h6 class="m-0 font-weight-bold">${pageTitle} <a
                    href="${pageContext.request.contextPath}/administrator/category"
                    class="btn btn-primary btn-sm" data-toggle="tooltip" data-placement="top" title="All Products"><i
                    class="fas fa-reply"></i> All Category</a></h6>
        </div>
        <form action="${pageContext.request.contextPath}/administrator/category" method="post">

            <div class="card-body">
                <% if (request.getAttribute("msg") != null) { %>
                <%=request.getAttribute("msg") %>
                <% request.removeAttribute("msg"); %>
                <% } %>
                <input type="hidden" name="act" value="create2">
                <div class="form-group row">
                    <label for="name" class="col-md-4 col-form-label text-md-right">Name <span
                            class="text-danger">*</span>:</label>
                    <div class="col-md-6">
                        <input type="text" class="form-control" name="name" id="name"
                               placeholder="Enter name" required>
                    </div>
                </div>
                <div class="form-group row">
                    <label for="desc" class="col-md-4 col-form-label text-md-right">Description:</label>
                    <div class="col-md-6">
                        <textarea name="desc" id="desc" cols="30" rows="5" class="form-control"></textarea>
                    </div>
                </div>
                <div class="form-group row">
                    <label for="img" class="col-md-4 col-form-label text-md-right">Image</label>
                    <div class="col-md-6">
                        <input type="file" name="img" id="img" class="form-control">
                    </div>
                </div>
                <div class="form-group row">
                    <label for="type_id" class="col-md-4 col-form-label text-md-right">Type</label>
                    <div class="col-md-6">
                        <select name="type_id" id="type_id" class="form-control">
                            <option value="1">Chó</option>
                            <option value="2">Mèo</option>
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