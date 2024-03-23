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
        <title>PONIGYM | My Schedule</title>

        <!-- Google Font -->
        <link href="https://fonts.googleapis.com/css?family=Roboto:400,500,700&display=swap" rel="stylesheet">
        <link href="https://fonts.googleapis.com/css?family=Oswald:300,400,500,600,700&display=swap" rel="stylesheet">

        <!-- Css Styles -->
        <link rel="stylesheet" href="https://cdn.usebootstrap.com/bootstrap/4.4.1/css/bootstrap.min.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
        <link rel="stylesheet" href="css/style_Fitness.css" type="text/css">

        <style>
            .abc:hover {
                color: hotpink;
            }
        </style>
    </head>
    <body>
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
        <!-- Site Breadcrumb Begin -->
        <section class="breadcrumb-section set-bg" data-setbg="img/about-breadcrumb-bg.jpg">
            <div class="container">
                <div class="row">
                    <div class="col-lg-12">
                        <div class="site-text">
                            <h2>My Schedule</h2>
                            <div class="site-breadcrumb">
                                <a href="./home" class="sb-item">Home</a>
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
                            <h2>My Timetable</h2>
                        </div>
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
                                            <form id="selectWeek" method="post" action="scheduleDetail">
                                                <input name="customerid" value="${sessionScope.userAuthorization.user.userId}" type="hidden">
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
                                                <c:if test="${timetable[row][cell.key]!=null}">
                                                    <td>
                                                        <a class="abc" href="#">${timetable[row][cell.key].gym_name}</a>
                                                        <c:if test="${timetable[row][cell.key].schedule_id!=0}">
                                                            <p>${timetable[row][cell.key].start_time}:00 - ${timetable[row][cell.key].end_time}:00</p> 
                                                        </c:if>

                                                    </td>
                                                </c:if>
                                                <c:if test="${timetable[row][cell.key] == null}">
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



        <script>
            document.getElementById("Weeks").onchange = function () {
                document.getElementById("selectWeek").submit();
            };
        </script>

        <!-- Js Plugins -->
        <script src="js/jquery-3.3.1.min.js"></script>
        <script src="js/bootstrap.min.js"></script>
        <script src="js/jquery.magnific-popup.min.js"></script>
        <script src="js/mixitup.min.js"></script>
        <script src="js/jquery.slicknav.js"></script>
        <script src="js/owl.carousel.min.js"></script>
        <script src="js/main.js"></script>
        <script src="js/main_Fitness.js" type="text/javascript"></script>

    </body>

</html>
