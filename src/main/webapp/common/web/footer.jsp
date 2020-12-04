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
<!-- Wrapping element -->
<div style="position: relative; min-height: 300px;">
    <!-- Position toasts -->
    <div style="position: absolute; top: 0; right: 0;">
        <div class="toast" role="alert" aria-live="polite" aria-atomic="true" data-animation="true" data-delay="10000"
             data-autohide="true">
            <div role="alert" aria-live="assertive" aria-atomic="true">...</div>
        </div>
    </div>
</div>
<script src="https://kit.fontawesome.com/06afc5370d.js"></script>
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"
        integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN"
        crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"
        integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q"
        crossorigin="anonymous"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"
        integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl"
        crossorigin="anonymous"></script>
<script>
    function loadDoc(id) {
        var xhttp = new XMLHttpRequest();
        xhttp.onreadystatechange = function () {
            if (this.readyState == 4 && this.status == 200) {
                document.getElementById("amount").innerHTML = this.responseText;
            }
        };
        xhttp.open("POST", "/cart", true);
        xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
        xhttp.send("action=add&id=" + id);
    }
</script>
</body>
</html>