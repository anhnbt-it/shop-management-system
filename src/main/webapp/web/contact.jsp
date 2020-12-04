<%--
  Created by IntelliJ IDEA.
  User: samsam
  Date: 12/4/20
  Time: 09:36
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:set var="pageTitle" scope="request" value="Cart"/>
<jsp:include page="/common/web/header.jsp"></jsp:include>
<div class="container" style="padding-top: 180px;">
    <section class="jumbotron text-center mb-4">
        <div class="container">
            <h1 class="jumbotron-heading">Liên hệ với chúng tôi</h1>
        </div>
    </section>
    <div class="row">
        <div class="col-12">
            <p class="mb-3 mt-3">Cửa hàng siêu thị thú cưng PetShop - PetShop Hà Nội </p>
            <p>Số ĐKKD: 123456789 - Ngày cấp: 11/11/2011, được sửa đổi lần thứ 6, ngày 05/05/2016 </p>
            <p><strong>Trụ sở chính:</strong> 113 Ngõ 110 Hoa La Canh - Cầu Giấy - Hà Nội</p>
            <p><strong>Cơ sở 2:</strong> 111 La Hoa, Mỗ Lao, Hà Đông, Hà Nội</p>
            <p><strong>Hotline: </strong> <a href="tel:0949111250">0949111250</a></p>
            <p><strong>Email: </strong><a href="mailto:Cutepets.cskh@gmail.com">Cutepets.cskh@gmail.com</a></p>
        </div>
    </div>
</div>
<!-- /.container -->
<%@include file="/common/web/footer.jsp" %>