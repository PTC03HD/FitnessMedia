<%-- 
    Document   : booking
    Created on : Mar 3, 2024, 3:40:42 PM
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
        <title>PONIGYM | Template</title>

        <!-- Google Font -->
        <link href="https://fonts.googleapis.com/css?family=Roboto:400,500,700&display=swap" rel="stylesheet">
        <link href="https://fonts.googleapis.com/css?family=Oswald:300,400,500,600,700&display=swap" rel="stylesheet">

        <!-- Css Styles -->
        <link rel="stylesheet" href="https://cdn.usebootstrap.com/bootstrap/4.4.1/css/bootstrap.min.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
        <link rel="stylesheet" href="css/style_Fitness.css" type="text/css">

        <style id="style_gyn_review">
            .full-screen-overlay {
                display: none;
                position: fixed;
                top: 0;
                left: 0;
                width: 100%;
                height: 100%;
                background-color: rgba(0, 0, 0, 0.5);
                z-index: 999;
            }

            /* Full-screen form container */
            .full-screen-form {
                position: absolute;
                top: 50%;
                left: 50%;
                transform: translate(-50%, -50%);
                width: 80%;
                max-width: 500px;
                background-color: white;
                padding: 20px;
                border-radius: 5px;
            }

            /* Form container */
            .form-container {
                width: 100%;
                max-width: 500px;
                margin: 0 auto;
                padding: 20px;
            }

            /* Form title */
            .form-container h2 {
                margin-top: 0;
                text-align: center;
                color: #333;
            }

            /* Form inputs */
            .form-container label {
                display: block;
                margin-bottom: 5px;
                color: #555;
            }

            .form-container input[type="number"],
            .form-container textarea {
                width: 100%;
                padding: 10px;
                margin-bottom: 20px;
                border: 1px solid #ccc;
                border-radius: 5px;
                box-sizing: border-box;
            }

            /* Submit button */
            .form-container .btn-container {
                display: flex;
                justify-content: space-between;
            }
            .form-container .btn-container .btn {
                display: block;
                width: 47%;
                padding: 10px;
                background-color: #4CAF50;
                color: #fff;
                border: none;
                border-radius: 5px;
                cursor: pointer;
                margin-bottom: 20px
            }

            .form-container .btn-container .btn:hover {
                background-color: #45a049;
            }

            /* Cancel button */
            .form-container .btn-container .cancel {
                background-color: #f44336;
            }

            .form-container .btn-container .cancel:hover {
                background-color: #da190b;
            }

            /* Close button (on top right corner) */
            .form-container .btn-container .close {
                position: absolute;
                top: 5px;
                right: 5px;
                font-size: 20px;
                color: #aaa;
            }

            .form-container .btn-container .close:hover {
                color: #000;
            }
        </style>
    </head>
    <body onload="checkCondition()">
        <!-- Page Preloder -->
        <div id="preloder">
            <div class="loader"></div>
        </div>

        <!-- Header Section Begin -->
        <header class="header-section header-normal">
            <div class="container">
                <div class="logo">
                    <a href="./index.html">
                        <img src="img/logo-normal.png" alt="">
                    </a>
                </div>
                <div class="nav-menu">
                    <nav class="mainmenu mobile-menu">
                        <ul>
                            <li class="active"><a href="./home">Home</a></li>
                            <li><a href="./GymList">Gyms</a></li>
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
                            <li><a href="./GymPostList?index=1">Blog</a></li>
                        </ul>
                    </nav>
                    <div class="nav-right search-switch">
                        <i class="ti-search"></i>
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
                            <h2>Schedule</h2>
                            <div class="site-breadcrumb">
                                <a href="./home.html" class="sb-item">Home</a>
                                <span class="sb-item">Schedule</span>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </section>
        <!-- Site Breadcrumb End -->

        <!-- Trainer Table Schedule Section Begin -->
        <section class="trainer-schedule class-timetable spad">
            <div class="container">
                <div class="row">
                    <div class="col-lg-12">
                        <div class="section-title">
                            <h2>${schedulePerGyms.gym.gymName} Timetable</h2>
                        </div>
                    </div>
                </div>
                <div class="booking" style="margin-bottom: 20px; display: flex; justify-content: center">

                    <div class="block-32" data-aos="fade-up" data-aos-offset="-200">
                        <form id="BookingForm" action="Booking" method="post">
                            <input type="text" name="gymId" value="${gymId}" hidden="">
                            <div class="row" style="min-width: 700px">
                                <div class="col-md-11 col-lg-3">
                                    <label for="adults" class="font-weight-bold text-black">Book Date</label>
                                    <div class="field-icon-wrap">
                                        <div class="icon"><span class="ion-ios-arrow-down"></span></div>
                                        <select id="bookDate" name="bookDate" class="form-control">
                                            <c:forEach items="${dayInWeek}" var="day">
                                                <c:if test="${schedulePerGyms.isContainDate(bookdateformat.format(day.value))}">
                                                    <c:set var="show" value="${schedulePerGyms.isContainDate(bookdateformat.format(day.value))}"></c:set>
                                                    <option value="${bookdateformat.format(day.value)}">${bookdateformat.format(day.value)}</option>
                                                </c:if>
                                            </c:forEach>
                                        </select>
                                    </div>
                                </div>
                                <div class="col-md-6 col-lg-3">
                                    <label for="adults" class="font-weight-bold text-black">From Slot</label>
                                    <div class="field-icon-wrap">
                                        <div class="icon"><span class="ion-ios-arrow-down"></span></div>
                                        <select name="bookSlot_Start" id="bookSlot_Start" class="form-control">
                                            <c:forEach items="${schedulePerGyms.slot}" var="slot">
                                                <c:if test="${show}">
                                                    <option value="${slot.slotOrder}">Slot ${slot.slotOrder}</option>
                                                </c:if>
                                            </c:forEach>
                                        </select>
                                    </div>
                                </div>
                                <div class="col-md-6 col-lg-3">
                                    <label for="children" class="font-weight-bold text-black">To Slot</label>
                                    <div class="field-icon-wrap">
                                        <div class="icon"><span class="ion-ios-arrow-down"></span></div>
                                        <select name="bookSlot_End" class="form-control">
                                            <c:forEach items="${schedulePerGyms.slot}" var="slot">
                                                <c:if test="${show}">
                                                    <option value="${slot.slotOrder == 1?-1:slot.slotOrder}">Slot ${slot.slotOrder == 1?"None":slot.slotOrder}</option>
                                                </c:if>
                                            </c:forEach>
                                        </select>
                                    </div>
                                </div>
                                <div class="col-md-6 col-lg-3 align-self-end">
                                    <c:if test="${show}">
                                        <button name="bookBtn" type="submit" class="btn btn-primary btn-block text-white">Booking</button>
                                    </c:if>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
                <div class="row">
                    <div class="col-lg-12">
                        <div class="schedule-table">
                            <table>
                                <thead>
                                    <tr>
                                        <th rowspan="2">
                                            <span>Week</span>
                                            <form id="selectWeek" method="post" action="Booking">
                                                <input name="gymId" value="${gymId}" hidden="">
                                                <select id="Weeks" name="Weeks">
                                                    <c:forEach begin="1" end="${totalWeek - 1}" step="1" var="weekIndex">
                                                        <option value="${weekIndex}" ${curWeekNum == weekIndex?"selected":""}>${WeekMap.get(weekIndex).startDate} to ${WeekMap.get(weekIndex).endDate}</option>
                                                    </c:forEach>
                                                </select>
                                            </form>
                                        </th>
                                        <th>Monday</th>
                                        <th>Tuesday</th>
                                        <th>Wednesday</th>
                                        <th>Thursday</th>
                                        <th>Friday</th>
                                        <th>Saturday</th>
                                        <th>Sunday</th>
                                    </tr>
                                    <tr>
                                        <c:forEach items="${dayInWeek}" var="day">
                                            <th>${formatter.format(day.value)}</th>
                                            </c:forEach>
                                    </tr>
                                </thead>
                                <tbody>
                                    <c:forEach begin="0" end="23" step="1" var="row">
                                        <tr>
                                            <td class="slot_num">Slot ${row + 1}</td>
                                            <c:forEach items="${dayInWeek}" var="cell">
                                                <c:if test="${timetable[row][cell.key] > 0}">
                                                    <td>
                                                        <strong>Open Gym</strong>
                                                        <p style="font-size: 13px; margin: 0">${timeMap.get(row)}</p>
                                                        <p style="font-size: 13px">${curBookNum[row][cell.key]}</p>
                                                    </td>
                                                </c:if>
                                                <c:if test="${timetable[row][cell.key] < 0}">
                                                    <td></td>
                                                </c:if>
                                            </c:forEach>
                                        </tr>
                                    </c:forEach>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
        </section>
        <!-- Trainer Table Schedule Section End -->

        <!-- Footer Section Begin -->
        <footer class="footer-section">
            <div class="container-fluid">
                <div class="row">
                    <!--                    <div class="col-lg-6">
                                            <div class="map-location">
                                                <iframe src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d33245.297803635964!2d-73.76987401620775!3d40.704774398815005!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x89c24fa5d33f083b%3A0xc80b8f06e177fe62!2sNew%20York%2C%20NY%2C%20USA!5e0!3m2!1sen!2sbd!4v1575866843291!5m2!1sen!2sbd" style="border:0;" allowfullscreen=""></iframe>
                                                <div class="map-widget">
                                                    <i class="fa fa-map-marker"></i>
                                                    <div class="map-address">
                                                        <img src="img/map-location.jpg" alt="">
                                                        <ul class="map-text">
                                                            <li><span>Address:</span> Iris Watson, Box 283, NY</li>
                                                            <li><span>Phone:</span> 12-456-791</li>
                                                        </ul>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>-->
                    <!--<div class="col-lg-6">-->
                    
                </div>
            </div>
            <div class="register normal-register">
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
                                    <li class="active">Privacy Policy</li>
                                    <li>Terms Of Service</li>
                                    <li>Careers</li>
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

        <!-- Search model Begin -->
        <div class="search-model">
            <div class="h-100 d-flex align-items-center justify-content-center">
                <div class="search-close-switch">+</div>
                <form class="search-model-form">
                    <input type="text" id="search-input" placeholder="Search here.....">
                </form>
            </div>
        </div>
        <!-- Search model end -->

        <script>
            document.getElementById("Weeks").onchange = function () {
                document.getElementById("selectWeek").submit();
            };

            document.getElementById("BookingForm").addEventListener("submit", function (event) {
                // Hiển thị thông báo xác nhận
                var confirmation = confirm("Are you sure you want to book this gym?");

                // Nếu người dùng nhấn OK, tiếp tục submit form
                if (!confirmation) {
                    event.preventDefault(); // Ngăn chặn hành động mặc định của sự kiện submit
                }
            });
        </script>

        <script>
            function openBookingScreenForm(value1, value2) {
                document.getElementById("overlay").style.display = "block";
                document.getElementById("bookDate").value = value1;
                dFocument.getElementById("bookSlot_Start").value = value2;
            }
            function checkCondition() {
                // Thực hiện kiểm tra điều kiện của bạn ở đây
                var condition = ${validBookDate}; // Đây là một điều kiện giả định, bạn cần thay đổi nó bằng điều kiện thực tế của bạn
                if (condition) {
                    alert("Booked Successful");
                } else
                    alert("Invalid booking date or slot")
            }
        </script>


        <!-- Js Plugins -->
        <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
        <script src="https://cdn.usebootstrap.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>
        <script src="js/jquery.magnific-popup.min.js"></script>
        <script src="js/jquery.slicknav.js"></script>  
        <script src="js/owl.carousel.min.js"></script>
        <script src="js/main_Fitness.js"></script>
        <script src="js/show_more_less.js"></script>
    </body>

</html>
