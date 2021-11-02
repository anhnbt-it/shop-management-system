<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 11/29/2020
  Time: 4:58 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<footer class="pt-4">
    <div class="container text-center text-md-left">
        <div class="row text-center text-md-left">
            <div class="col-md-3 col-lg-3 col-xl-3 mx-auto mt-3">
                <h6 class="text-uppercase mb-4 font-weight-bold">Pet Shop</h6>
                <p>Là lựa chọn hàng đầu cho các bạn muốn dở hữu một chú pet cưng tuyệt vời nhất, quý hiếm nhất.</p>
            </div>
            <div class="col-md-2 col-lg-2 col-xl-2 mx-auto mt-3">
                <h6 class="text-uppercase mb-4 font-weight-bold">Products</h6>
                <ul class="list-unstyled">
                    <li><a href="#">Chó</a></li>
                    <li><a href="#">Mèo</a></li>
                </ul>
            </div>
            <div class="col-md-3 col-lg-2 col-xl-2 mx-auto mt-3">
                <h6 class="text-uppercase mb-4 font-weight-bold">Chính sách</h6>
                <ul class="list-unstyled">
                    <li><a href="#">Bảo vệ thông tin người dùng</a></li>
                    <li><a href="#">Giao hàng</a></li>
                </ul>
            </div>
            <div class="col-md-4 col-lg-3 col-xl-3 mx-auto mt-3">
                <h6 class="text-uppercase mb-4 font-weight-bold">Contact</h6>
                <ul class="list-unstyled">
                    <li><i class="fas fa-home mr-3"></i>123, Cầu Giấy, Hà Nội</li>
                    <li><i class="fas fa-envelope mr-3"></i> PetShop@gmail.com</li>
                    <li><i class="fas fa-phone mr-3"></i> 0123 456 789</li>
                    <li><i class="fas fa-envelope mr-3"></i> 0123 456 789</li>
                </ul>
            </div>
        </div>
        <hr class="w-100 clearfix">
        <div class="row">
            <div class="col-md-7 col-lg-8">
                <p class="text-center text-md-left">© 2020 Copyright <a href="#"><strong>Unknown.com</strong></a></p>
            </div>
            <div class="col-md-5 col-lg-4 ml-lg-0">
                <div class="text-center text-md-right">
                    <ul class="list-unstyled list-inline">
                        <li class="list-inline-item">
                            <a href="#" class="btn-floating btn-sm rgba-white-slight mx-1">
                                <i class="fab fa-facebook-f"></i>
                            </a>
                        </li>
                        <li class="list-inline-item">
                            <a href="#" class="btn-floating btn-sm rgba-white-slight mx-1">
                                <i class="fab fa-twitter"></i>
                            </a>
                        </li>
                        <li class="list-inline-item">
                            <a href="#" class="btn-floating btn-sm rgba-white-slight mx-1">
                                <i class="fab fa-google-plus-g"></i>
                            </a>
                        </li>
                        <li class="list-inline-item">
                            <a href="#" class="btn-floating btn-sm rgba-white-slight mx-1">
                                <i class="fab fa-linkedin-in"></i>
                            </a>
                        </li>
                    </ul>
                </div>
            </div>
        </div>
    </div>
</footer>
<!-- The actual snackbar -->
<div id="snackbar">Thêm vào giỏ hàng thành công..</div>
<!-- Bootstrap core JavaScript-->
<script src="${pageContext.request.contextPath}/admin/assets/js/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/admin/assets/js/bootstrap.bundle.min.js"></script>
<script>
    // Enable tooltips
    $(function () {
        $('[data-toggle="tooltip"]').tooltip()
    })

    function addToCart(id) {
        $(document).ready(function () {
            var xhttp = new XMLHttpRequest();
            xhttp.onreadystatechange = function () {
                if (this.readyState == 4 && this.status == 200) {
                    $("#amount").html(this.responseText);
                    var x = document.getElementById("snackbar");
                    // Add the "show" class to DIV
                    x.className = "show";
                    // After 3 seconds, remove the show class from DIV
                    setTimeout(function(){ x.className = x.className.replace("show", ""); }, 3000);
                }
            };
            xhttp.open("POST", "/cart", true);
            xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
            xhttp.send("action=add&id=" + id);
        });
    }
</script>
</body>
</html>