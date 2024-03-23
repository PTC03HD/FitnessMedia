<%-- 
    Document   : BookingHistory
    Created on : Mar 12, 2024, 1:11:52 AM
    Author     : phamt
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="zxx">

    <head>
        <meta charset="UTF-8">
        <meta name="description" content="PONIGYM Template">
        <meta name="keywords" content="PONIGYM, unica, creative, html">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta http-equiv="X-UA-Compatible" content="ie=edge">
        <title>PONIGYM | Account Panels</title>

        <!-- Google Font -->
        <link href="https://fonts.googleapis.com/css?family=Roboto:400,500,700&display=swap" rel="stylesheet">
        <link href="https://fonts.googleapis.com/css?family=Oswald:300,400,500,600,700&display=swap" rel="stylesheet">
        <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto|Varela+Round">
        <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">

        <!-- Css Styles -->
        <link rel="stylesheet" href="https://cdn.usebootstrap.com/bootstrap/4.4.1/css/bootstrap.min.css">
        <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet">
        <link rel="stylesheet" href="css/style_Fitness.css" type="text/css">

        <style>
            div.latest-text{
                width:290px;
                height: 165px;
            }
            .latest-items img {
                min-width: 81%;
            }

            body {
                color: #566787;
                background: #f5f5f5;
                font-family: 'Varela Round', sans-serif;
                font-size: 13px;
            }
            .table-wrapper {
                background: #fff;
                padding: 20px 25px;
                margin: 30px 0;
                border-radius: 3px;
                box-shadow: 0 1px 1px rgba(0,0,0,.05);
            }
            .table-title {
                padding-bottom: 15px;
                background: #435d7d;
                color: #fff;
                padding: 16px 30px;
                margin: -20px -25px 10px;
                border-radius: 3px 3px 0 0;
            }
            .table-title h2 {
                margin: 5px 0 0;
                font-size: 24px;
            }
            .table-title .btn-group {
                float: right;
            }
            .table-title .btn {
                color: #fff;
                float: right;
                font-size: 13px;
                border: none;
                min-width: 50px;
                border-radius: 2px;
                border: none;
                outline: none !important;
                margin-left: 10px;
            }
            .table-title .btn i {
                float: left;
                font-size: 21px;
                margin-right: 5px;
            }
            .table-title .btn span {
                float: left;
                margin-top: 2px;
            }
            table.table tr th, table.table tr td {
                border-color: #e9e9e9;
                padding: 12px 15px;
                vertical-align: middle;
            }
            table.table tr th:first-child {
                width: 60px;
            }
            table.table tr th:last-child {
                width: 100px;
            }
            table.table-striped tbody tr:nth-of-type(odd) {
                background-color: #fcfcfc;
            }
            table.table-striped.table-hover tbody tr:hover {
                background: #f5f5f5;
            }
            table.table th i {
                font-size: 13px;
                margin: 0 5px;
                cursor: pointer;
            }
            table.table td:last-child i {
                opacity: 0.9;
                font-size: 22px;
                margin: 0 5px;
            }
            table.table td a {
                font-weight: bold;
                color: #566787;
                display: inline-block;
                text-decoration: none;
                outline: none !important;
            }
            table.table td a:hover {
                color: #2196F3;
            }
            table.table td a.edit {
                color: #FFC107;
            }
            table.table td a.delete {
                color: #F44336;
            }
            table.table td i {
                font-size: 19px;
            }
            table.table .avatar {
                border-radius: 50%;
                vertical-align: middle;
                margin-right: 10px;
            }
            .pagination {
                float: right;
                margin: 0 0 5px;
            }
            .pagination li a {
                border: none;
                font-size: 13px;
                min-width: 30px;
                min-height: 30px;
                color: #999;
                margin: 0 2px;
                line-height: 30px;
                border-radius: 2px !important;
                text-align: center;
                padding: 0 6px;
            }
            .pagination li a:hover {
                color: #666;
            }
            .pagination li.active a, .pagination li.active a.page-link {
                background: #03A9F4;
            }
            .pagination li.active a:hover {
                background: #0397d6;
            }
            .pagination li.disabled i {
                color: #ccc;
            }
            .pagination li i {
                font-size: 16px;
                padding-top: 6px
            }
            .hint-text {
                float: left;
                margin-top: 10px;
                font-size: 13px;
            }
            /* Custom checkbox */
            .custom-checkbox {
                position: relative;
            }
            .custom-checkbox input[type="checkbox"] {
                opacity: 0;
                position: absolute;
                margin: 5px 0 0 3px;
                z-index: 9;
            }
            .custom-checkbox label:before{
                width: 18px;
                height: 18px;
            }
            .custom-checkbox label:before {
                content: '';
                margin-right: 10px;
                display: inline-block;
                vertical-align: text-top;
                background: white;
                border: 1px solid #bbb;
                border-radius: 2px;
                box-sizing: border-box;
                z-index: 2;
            }
            .custom-checkbox input[type="checkbox"]:checked + label:after {
                content: '';
                position: absolute;
                left: 6px;
                top: 3px;
                width: 6px;
                height: 11px;
                border: solid #000;
                border-width: 0 3px 3px 0;

                z-index: 3;
                transform: rotateZ(45deg);
            }
            .custom-checkbox input[type="checkbox"]:checked + label:before {
                border-color: #03A9F4;
                background: #03A9F4;
            }
            .custom-checkbox input[type="checkbox"]:checked + label:after {
                border-color: #fff;
            }
            .custom-checkbox input[type="checkbox"]:disabled + label:before {
                color: #b8b8b8;
                cursor: auto;
                box-shadow: none;
                background: #ddd;
            }
            /* Modal styles */
            .modal .modal-dialog {
                max-width: 400px;
            }
            .modal .modal-header, .modal .modal-body, .modal .modal-footer {
                padding: 20px 30px;
            }
            .modal .modal-content {
                border-radius: 3px;
            }
            .modal .modal-footer {
                background: #ecf0f1;
                border-radius: 0 0 3px 3px;
            }
            .modal .modal-title {
                display: inline-block;
            }
            .modal .form-control {
                border-radius: 2px;
                box-shadow: none;
                border-color: #dddddd;
            }
            .modal textarea.form-control {
                resize: vertical;
            }
            .modal .btn {
                border-radius: 2px;
                min-width: 100px;
            }
            .modal form label {
                font-weight: normal;
            }

            .dropbtn {
                background-color: #04AA6D;
                color: white;
                padding: 16px;
                font-size: 16px;
                border: none;
                cursor: pointer;

            }

            .dropbtn:hover, .dropbtn:focus {
                background-color: #3e8e41;
            }

            #myInput {
                box-sizing: border-box;
                background-image: url('searchicon.png');
                background-position: 14px 12px;
                background-repeat: no-repeat;
                font-size: 16px;
                padding: 14px 20px 12px 45px;
                border: none;
                border-bottom: 1px solid #ddd;
            }

            #myInput:focus {
                outline: 3px solid #ddd;
            }



            .dropdown-content {
                display: none;
                position: absolute;
                background-color: #f6f6f6;
                min-width: 230px;
                overflow: auto;
                border: 1px solid #ddd;
                z-index: 1;
            }

            .dropdown-content a {
                color: black;
                padding: 12px 16px;
                text-decoration: none;
                display: block;
            }

            .dropdown a:hover {
                background-color: #ddd;
            }

            .show {
                display: block;
            }

        </style>
    </head>

    <body>
        <div id="payment-container"></div>
        <button onclick="open()">Click Me!</button>

        <!-- Page Preloder -->
        <div id="preloder">
            <div class="loader"></div>
        </div>

        <!-- Header Section Begin -->
        <header class="header-section header-normal">
            <div class="container">
                <div class="logo">
                    <a href="./home">
                        <img src="img/logo-normal.png" alt="">
                    </a>
                </div>
                <div class="nav-menu">
                    <nav class="mainmenu mobile-menu">
                        <ul>
                            <li class="active"><a href="./home">Home</a></li>
                            <li><a href="./about-us.html">About</a></li>
                                <c:if test='${sessionScope.userAuthorization!=null}'>
                                    <c:if test='${sessionScope.userAuthorization.hasRole("ROLE_CUSTOMER")}'>
                                    <li><a href="./scheduleDetail">My Schedule</a></li>
                                    </c:if>
                                    <c:if test='${sessionScope.userAuthorization.hasRole("ROLE_MANANGER")}'>
                                    <li><a href="#">Gym Schedule</a></li>
                                    </c:if>
                                    <c:if test='${sessionScope.userAuthorization.hasRole("ROLE_ADMIN")}'>
                                    <li><a href="./AdminList">Account Panel</a></li>
                                    </c:if>
                                    <c:if test='${sessionScope.userAuthorization.hasRole("ROLE_OWNER")}'>
                                    <li><a href="#">Dashboard</a></li>
                                    </c:if>
                                </c:if>
                            <li><a href="./gallery.html">Portfolio</a></li>
                            <li><a href="./blog.html">Blog</a>
                                <ul class="dropdown">
                                    <li><a href="blog-details.html">Blog Details</a></li>
                                </ul>
                            </li>
                        </ul>
                    </nav>
                    <div class="nav-right">
                        <c:if test='${sessionScope.userAuthorization==null}'>
                            <a class="nav-link alert-success" href="./Login">Login</a>
                            <a class="nav-link alert-danger" href="./Register">Register</a>
                        </c:if>
                        <c:if test='${sessionScope.userAuthorization!=null}'>
                            <a class="nav-link alert-success" href="./userprofile">Hi, ${sessionScope.userAuthorization.user.userName}</a>
                            <a class="nav-link alert-danger" href="./Logout">Log out</a>
                        </c:if>                      
                    </div>
                </div>
                <div id="mobile-menu-wrap"></div>
            </div>
        </header>
        <!-- Header End -->



        <!-- Site Breadcrumb Begin -->
        <section class="breadcrumb-section set-bg" data-setbg="img/about-breadcrumb-bg.jpg">
            <div class="container">
                <div class="row">
                    <div class="col-lg-12">
                        <div class="site-text">
                            <h2>Account Panel</h2>
                            <div class="site-breadcrumb">
                                <a href="./home" class="sb-item">Home</a>
                                <span class="sb-item">Account List</span>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </section>
        <!-- Site Breadcrumb End -->
        <br>
        <!-- Body Content Start-->
        <div class="container">
            <div class="table-wrapper">
                <div class="table-title">
                    <div class="row">
                        <div class="col-sm-4">
                            <h2 style="color:white">Booking History</h2>
                        </div>
                        <div class="col-sm-4">
                            <div class="dropdown">
                                <form method="post" action="AdminList">         
                                    <div id="myDropdown" class="dropdown-content">
                                        <input type="text" name="txtSearch" placeholder="Search.." id="myInput" onkeyup="filterFunction()">
                                        <input type="submit" class="btn-primary" value="Search" name="btnSearch">
                                        <a>
                                            <input type="radio" id="html" name="role" value="ROLE_CUSTOMER">
                                            <label for="Customer">Customer</label><br>
                                            <input type="radio" id="html" name="role" value="ROLE_MANANGER">
                                            <label for="Manager">Manager</label><br>
                                            <input type="radio" id="html" name="role" value="ROLE_OWNER">
                                            <label for="Owner">Owner</label><br>
                                            <input type="radio" id="css" name="status" value="False">
                                            <label for="Active">Active</label><br>
                                            <input type="radio" id="css" name="status" value="True">
                                            <label for="Inactive">Inactive</label><br>
                                            <button type="submit" name="btnFilter" class="btn-primary">Filter</button>
                                        </a>
                                    </div>
                                </form>

                            </div>
                        </div>
                        <!--                        <div class="col-sm-4">
                                                    <a href="#" class="btn btn-success"><i class="material-icons">&#xE147;</i> <span>Add New Account</span></a>			
                                                </div>-->
                    </div>
                </div>
                <table class="table table-striped table-hover">
                    <thead>
                        <tr>
                            <th>Gym</th>
                            <th>Total Money</th>
                            <th>Experience</th>
                            <th>Booked Date</th>
                            <th>Schedule Date</th>
                            <th>Start Time</th>
                            <th>End Time</th>
                            <th>Status</th>
                            <th>Actions</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${BookingMap.get(sessionScope.userAuthorization.user.userId).billMap()}" var="billM" varStatus="Bstatus">
                            <c:if test="${!billM.key.deleted}">
                                <tr>
                                    <td class="px-6 py-4 whitespace-nowrap">${GymMap.get(billM.value.get(0).sche.gymId).gymName}</td>
                                    <td class="px-6 py-4 whitespace-nowrap">${vndFormat.format(billM.key.totalMoney)}</td>
                                    <c:forEach items="${billM.value}" var="BookScheSlot" varStatus="status">
                                        <c:if test="${BookScheSlot.book.experienced && status.count == 1}">
                                            <td class="px-6 py-4 whitespace-nowrap">Experienced</td>
                                        </c:if>
                                        <c:if test="${!BookScheSlot.book.experienced && status.count == 1}">
                                            <td class="px-6 py-4 whitespace-nowrap">Inexperienced</td>
                                        </c:if>
                                    </c:forEach>
                                    <c:forEach items="${billM.value}" var="BookScheSlot" varStatus="status">
                                        <c:if test="${status.count==1}">
                                            <td class="px-6 py-4 whitespace-nowrap">${bookdateformat.format(BookScheSlot.book.createdDate)}</td>
                                        </c:if>
                                    </c:forEach>
                                    <c:forEach items="${billM.value}" var="BookScheSlot" varStatus="status">
                                        <c:if test="${status.count == 1}">
                                            <td class="px-6 py-4 whitespace-nowrap">${BookScheSlot.sche.scheduleDate}</td>
                                        </c:if>
                                    </c:forEach>
                                    <c:forEach items="${billM.value}" var="BookScheSlot" varStatus="status">
                                        <c:if test="${status.first}">
                                            <td class="px-6 py-4 whitespace-nowrap">${BookScheSlot.slot.startTime}:00h</td>
                                        </c:if>
                                    </c:forEach>
                                    <c:forEach items="${billM.value}" var="BookScheSlot" varStatus="status">
                                        <c:if test="${status.last}">
                                            <td class="px-6 py-4 whitespace-nowrap">${BookScheSlot.slot.endTime}:00h</td>
                                        </c:if>
                                    </c:forEach>
                                    <td class="px-6 py-4 whitespace-nowrap" style="color:${billM.key.paid?"yellowgreen":"red"}">${billM.key.paid?"Paid":"Unpaid"}</td>
                                    <td class="px-6 py-4 whitespace-nowrap">
                                        <a href="./AdminUpdate?aid=${a.user_id}" class="edit"><i class="material-icons" title="Edit">&#xE254;</i></a>
                                        <a href="#" id="delete" class="delete" onclick="submitForm()"><i class="material-icons" title="Delete">&#xE872;</i></a>
                                    </td>
                                    <td hidden="">
                                        <form action="BookingHistory" method="post" id="deleteBooking">
                                            <input type="text" name="billId" value="${billM.key.billId}">
                                        </form>
                                    </td>
                                </tr>

                            </c:if>
                        </c:forEach>

                    </tbody>
                </table>

                <div class="clearfix">
                    <div class="hint-text">Showing <b>${size}</b> out of <b>${toa.total}</b> bookings</div>
                    <ul class="pagination">
                        <li class="page-item disabled"><a href="./AdminList?index=${i+1}&size=${size}">Previous</a></li>
                            <c:forEach begin='1' end='${endP}' var="i">
                            <li class="page-item ${index==i?"active":""}"><a href="./AdminList?index=${i}&size=${size}" class="page-link">${i}</a></li>
                            </c:forEach>  
                        <li class="page-item"><a href="./AdminList?index=${i+2}&size=${size}" class="page-link">Next</a></li>
                    </ul>
                </div>
            </div>
        </div>
        <!-- Edit Modal HTML -->







        <!-- Body Content End-->
        <br><br>

        <!-- Footer Section Begin -->
        <footer class="footer-section">

            <div class="register">
                <div class="container">
                    <div class="row">
                        <div class="col-lg-12">
                            <div class="copyright">
                                <!-- Link back to Colorlib can't be removed. Template is licensed under CC BY 3.0. -->
                                Copyright &copy;<script>document.write(new Date().getFullYear());</script> All rights reserved | This template is made with <i class="fa fa-heart-o" aria-hidden="true"></i> by <a href="https://colorlib.com" target="_blank">Colorlib</a>
                                <!-- Link back to Colorlib can't be removed. Template is licensed under CC BY 3.0. -->
                            </div>
                            <div class="footer-widget">
                                <ul>
                                    <li>Privacy Policy</li>
                                    <li>Terms Of Service</li>
                                    <li>Careers</li>
                                    <li>${tag}</li>
                                </ul>
                            </div>
                            <div class="footer-links">
                                <a href="#"><i class="fa fa-facebook"></i></a>
                                <a href="#"><i class="fa fa-twitter"></i></a>
                                <a href="#"><i class="fa fa-instagram"></i></a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </footer>
        <!-- Footer Section End -->

        <!-- Js Plugins -->
        <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.1/umd/popper.min.js"></script>
        <script src="https://cdn.usebootstrap.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>


        <script src="js/main_Fitness.js"></script>
        <script src="js/show_more_less.js"></script>
        <script src="js/vietqr.js"></script>
        <script>
                                    // Hàm để submit form
                                    function submitForm() {
                                        // Lấy ra form cụ thể bạn muốn submit
                                        var form = document.getElementById("deleteBooking");

                                        // Submit form
                                        form.submit();
                                    }
        </script>
        <script>
// Định nghĩa cấu hình thanh toán payOSConfig
            const payOSConfig = {
                RETURN_URL: "http://localhost:9999/FitnessMedia/home", // bắt buộc
                ELEMENT_ID: "payment-container", // bắt buộc
                CHECKOUT_URL: "http://localhost:9999/FitnessMedia/BookingHistory", // bắt buộc
                onSuccess: function (event) {
                    // Hành động sau khi thanh toán thành công
                },
                onCancel: function (event) {
                    // Hành động sau khi người dùng hủy thanh toán
                },
                onExit: function (event) {
                    // Hành động sau khi người dùng đóng cửa sổ popup thanh toán
                }
            };

            // Sử dụng hàm usePayOS từ PayOSCheckout để tạo các hàm open và exit
            const {open, exit} = PayOSCheckout.usePayOS(payOSConfig);

//            // Gọi hàm open để mở thanh toán
//            open();

        </script>
        <script src="https://cdn.payos.vn/payos-checkout/v1/stable/payos-initialize.js"></script>
    </body>

</html>

