<%--
  Created by IntelliJ IDEA.
  User: samsam
  Date: 12/4/20
  Time: 09:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/common/libs.jsp" %>
<html>
<head>
    <title>Product</title>
    <%--    <link rel="stylesheet" href="template/css/sidebar.css">--%>
    <%--    <link rel="stylesheet" href="template/css/product/product.css">--%>
    <%--    <link rel="stylesheet" href="template/bootstrap/css/bootstrap.css">--%>
    <link href="<c:url value="/template/bootstrap/css/bootstrap.css"/>" rel="stylesheet">
    <link href="<c:url value="/template/css/sidebar.css"/>" rel="stylesheet">
    <link href="<c:url value="/template/css/product/product.css"/>" rel="stylesheet">
</head>
<body>
<%@include file="/common/web/header.jsp" %>
<div class="mb-4 mt-4 container">

    <div class="page_cotact mb-3 mt-3">
        <h2 class="title-head-contact a-left"><span>Liên hệ với chúng tôi</span></h2>
    </div>
    <div class="content">
        <div class="intro">
            <p class="mb-3 mt-3">Cửa hàng siêu thị thú cưng PetShop - PetShop Hà Nội </p>
            <p>Số ĐKKD: 123456789 - Ngày cấp: 11/11/2011, được sửa đổi lần thứ 6, ngày 05/05/2016 </p>
        </div>
        <div class="item_contact">
            <div class="body_contact">
                <div class="contact_info">

                    <p><strong>Trụ sở chính:</strong> 113 Ngõ 110 Hoa La Canh - Cầu Giấy - Hà Nội</p>


                    <p><strong>Cơ sở 2:</strong> 111 La Hoa, Mỗ Lao, Hà Đông, Hà Nội
                    </p>

                </div>
            </div>
            <div class="body_contact item_2_contact">
						<span class="icon_widget">
							<svg aria-hidden="true" focusable="false" data-prefix="fas" data-icon="phone-square-alt" role="img" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 448 512" class="svg-inline--fa fa-phone-square-alt fa-w-14 fa-3x" style="width: 13px;"><path fill="currentColor" d="M400 32H48A48 48 0 0 0 0 80v352a48 48 0 0 0 48 48h352a48 48 0 0 0 48-48V80a48 48 0 0 0-48-48zm-16.39 307.37l-15 65A15 15 0 0 1 354 416C194 416 64 286.29 64 126a15.7 15.7 0 0 1 11.63-14.61l65-15A18.23 18.23 0 0 1 144 96a16.27 16.27 0 0 1 13.79 9.09l30 70A17.9 17.9 0 0 1 189 181a17 17 0 0 1-5.5 11.61l-37.89 31a231.91 231.91 0 0 0 110.78 110.78l31-37.89A17 17 0 0 1 299 291a17.85 17.85 0 0 1 5.91 1.21l70 30A16.25 16.25 0 0 1 384 336a17.41 17.41 0 0 1-.39 3.37z" class=""></path></svg>
						</span>

                <span class="contact_info">
							<strong>Hotline: </strong>
							<a href="tel:0949111250">0949111250</a>
						</span>
            </div>
            <div class="body_contact item_3_contact">
						<span class="icon_widget">
							<svg aria-hidden="true" focusable="false" data-prefix="fas" data-icon="envelope" role="img" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 512 512" class="svg-inline--fa fa-envelope fa-w-16 fa-3x" style="width: 15px;"><path fill="currentColor" d="M502.3 190.8c3.9-3.1 9.7-.2 9.7 4.7V400c0 26.5-21.5 48-48 48H48c-26.5 0-48-21.5-48-48V195.6c0-5 5.7-7.8 9.7-4.7 22.4 17.4 52.1 39.5 154.1 113.6 21.1 15.4 56.7 47.8 92.2 47.6 35.7.3 72-32.8 92.3-47.6 102-74.1 131.6-96.3 154-113.7zM256 320c23.2.4 56.6-29.2 73.4-41.4 132.7-96.3 142.8-104.7 173.4-128.7 5.8-4.5 9.2-11.5 9.2-18.9v-19c0-26.5-21.5-48-48-48H48C21.5 64 0 85.5 0 112v19c0 7.4 3.4 14.3 9.2 18.9 30.6 23.9 40.7 32.4 173.4 128.7 16.8 12.2 50.2 41.8 73.4 41.4z" class=""></path></svg>
						</span>

                <span class="contact_info">
							<strong>Email: </strong>
							<a href="mailto:Cutepets.cskh@gmail.com">Cutepets.cskh@gmail.com</a>
						</span>
            </div>
        </div>

    </div>
</div>
<%@include file="/common/web/footer.jsp" %>
</body>
</html>

