<%-- 
    Document   : homePage
    Created on : Feb 17, 2024, 9:39:30 AM
    Author     : ba Tung
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
        <title>PONIGYM | Home</title>

        <!-- Google Font -->
        <link href="https://fonts.googleapis.com/css?family=Roboto:400,500,700&display=swap" rel="stylesheet">
        <link href="https://fonts.googleapis.com/css?family=Oswald:300,400,500,600,700&display=swap" rel="stylesheet">

        <!-- Css Styles -->
        <link href="css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
        <link href="css/font-awesome.min.css" rel="stylesheet" type="text/css"/>
        <link href="css/themify-icons.css" rel="stylesheet" type="text/css"/>
        <link href="css/owl.carousel.min.css" rel="stylesheet" type="text/css"/>
        <link href="css/magnific-popup.css" rel="stylesheet" type="text/css"/>
        <link href="css/slicknav.min.css" rel="stylesheet" type="text/css"/>
        <link href="css/style.css" rel="stylesheet" type="text/css"/>

        <style>
            div.latest-text{
                width:290px;
                height: 165px;
            }
            .latest-items img {
                min-width: 81%;
            }

        </style>
    </head>

    <body>
        <!-- Page Preloder -->
        <div id="preloder">
            <div class="loader"></div>
        </div>

        <!-- Header Section Begin -->
        <header class="header-section">
            <div class="container">
                <div class="logo">
                    <a href="./home">
                        <img src="img/logo.png" alt="">
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
                                    <li><a href="./GymManager">My Gym</a></li>
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
        <!-- Header End -->

        <!-- Hero Section Begin -->
        <section class="hero-section">
            <div class="hero-items owl-carousel">
                <div class="single-hero-item set-bg" data-setbg="img/slider-bg-1.jpg">
                    <div class="container">
                        <div class="hero-text">
                            <h4>Elite Personal Training Services</h4>
                            <h1>Make it <span>Shape</span></h1>
                            <a href="#" class="primary-btn">Join Us Now</a>
                        </div>
                    </div>
                </div>
                <div class="single-hero-item set-bg" data-setbg="img/slider-bg-2.jpg">
                    <div class="container">
                        <div class="hero-text">
                            <h4>Be proud of what you've accomplished</h4>
                            <h1>Do it <span>More</span></h1>
                            <a href="#" class="primary-btn">Join Us Now</a>
                        </div>
                    </div>
                </div>
                <div class="single-hero-item set-bg" data-setbg="img/slider-bg-3.jpg">
                    <div class="container">
                        <div class="hero-text">
                            <h4>Stronger and Better</h4>
                            <h1>Don't be <span>Lazy</span></h1>
                            <a href="#" class="primary-btn">Join Us Now</a>
                        </div>
                    </div>
                </div>
            </div>
        </section>
        <!-- Hero Section End -->

        <!-- Services Section Begin -->
        <section class="services-section spad">
            <div class="container">
                <div class="row">
                    <div class="col-lg-6">
                        <div class="section-title">
                            <h2>Our Program</h2>
                            <p>Our fitness experts can help you discover new training techniques and exercises that offer a
                                dynamic and efficient full-body workout.</p>
                        </div>
                        <div class="services-items">
                            <div class="single-service-item">
                                <img src="img/icon-1.png" alt="">
                                <h5>Weight Lifting</h5>
                                <p>We have a wide choice of classes that are a great complement to any training programme.
                                </p>
                            </div>
                            <div class="single-service-item color-1">
                                <img src="img/icon-2.png" alt="">
                                <h5>Body Building</h5>
                                <p>We have a wide choice of classes that are a great complement to any training programme.
                                </p>
                            </div>
                            <div class="single-service-item color-2">
                                <img src="img/icon-3.png" alt="">
                                <h5>Healthy</h5>
                                <p>We have a wide choice of classes that are a great complement to any training programme.
                                </p>
                            </div>
                            <div class="single-service-item color-3">
                                <img src="img/icon-4.png" alt="">
                                <h5>Yoga</h5>
                                <p>We have a wide choice of classes that are a great complement to any training programme.
                                </p>
                            </div>
                        </div>
                    </div>
                    <div class="col-lg-5 offset-lg-1">
                        <div class="service-video set-bg" data-setbg="img/video-bg.jpg">
                            <div class="play-btn">
                                <a href="https://www.youtube.com/watch?v=GcVKLNSNWJY" class="service-video-popup"><i
                                        class="fa fa-play"></i></a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </section>
        <!-- Services Section End -->

        <!-- Cta Section Begin -->
        <section class="cta-section set-bg spad" data-setbg="img/cta-bg.jpg">
            <div class="container">
                <div class="row">
                    <div class="col-lg-12">
                        <h2>Start your Journey with our exciting offers</h2>
                        <p>You don't have to be great to start but you have to start to be great.</p>
                        <a href="#" class="primary-btn">Join With Us</a>
                    </div>
                </div>
            </div>
        </section>
        <!-- Cta Section End -->

        <!-- Class Section Begin-->
        <section class="class-section spad">
            <div class="container">
                <div class="row">
                    <div class="col-lg-12">
                        <div class="row">
                            <div class="col-lg-6">
                                <div class="section-title">
                                    <h2>Some Gyms</h2>
                                    <p>Our fitness experts can help you discover new training techniques and exercises that
                                        offer a dynamic and efficient full-body workout.</p>
                                </div>
                            </div>
                            <div class="col-lg-6">
                                <a href="#" class="primary-btn">View All Gyms</a>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-lg-12">
                                <div class="class-item">
                                    <div class="tab-content">
                                        <c:forEach items="${homeList}" var="o">
                                            <div class="tab-pane fade-in active" id="${o.gym_id}" role="tabpanel">
                                                <div class="single-class-item">
                                                    <div class="row">
                                                        <div class="col-lg-6">
                                                            <img src="${o.gym_avatar}" alt="${o.gym_name}" width="450px" height="350px">
                                                        </div>
                                                        <div class="col-lg-5 offset-lg-1">
                                                            <div class="class-text">
                                                                <h3>${o.gym_name}</h3>
                                                                <p>${o.gym_wall}</p>
                                                                <a href="#" class="schedule-btn">View Gym Room <i class="fa fa-long-arrow-right"></i></a>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                            <br> <br>                  
                                        </c:forEach>
                                    </div>

                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </section>
        <!-- Class Section End -->



        <!-- Team Section Begin -->
        <section class="team-section spad">
            <div class="container">
                <div class="row">
                    <div class="col-lg-12">
                        <div class="section-title">
                            <h2>Popular Owner</h2>
                            <p>The person who owns the most gyms and is the most prominent</p>
                        </div>
                        <a href="#" class="primary-btn">View All Gym <i class="ti-angle-double-right"></i></a>
                    </div>
                </div>
                <div class="team-members">
                    <div class="row m-0">
                        <div class="col-lg-4 order-lg-1 p-0">
                            <div class="member-pic first">
                                <img src="${pop.img_avatar}" alt="" width="310px" height="350px">
                            </div>
                        </div>
                        <div class="col-lg-4 order-lg-2 p-0">
                            <div class="member-text">
                                <span>Owner</span>
                                <h5>${pop.img_wall}</h5>
                                <p><span>Phone Contact</span>: ${pop.per_phone}<p>
                                    <span>Gym Room's Owned</span>: ${pop.totalRoom}
                                <div class="member-social">
                                    <a href="#"><i class="ti-facebook"></i></a>
                                    <a href="#"><i class="ti-twitter-alt"></i></a>
                                    <a href="#"><i class="ti-pinterest"></i></a>
                                    <a href="#"><i class="ti-vimeo-alt"></i></a>
                                </div>
                            </div>
                        </div> 
                    </div>
                </div>
            </div>
        </section>
        <!-- Team Section End -->

        <!-- Latest News Section Begin -->
        <section class="latest-news-section spad">
            <div class="container">
                <div class="row">
                    <div class="col-lg-12">
                        <div class="section-title">
                            <h2>Latest News</h2>
                            <p>The best news in this week.</p>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <c:forEach items="${post}" var="p">
                        <div class="col-lg-4">
                            <div class="latest-items">
                                <div class="latest-pic">
                                    <img src="${p.gymPostImg}" alt="" width='290px' height='250px'>
                                </div>
                                <div class="latest-text">
                                    <div class="latest-tag">
                                        <div class="tag-clock">
                                            <i class="fa fa-clock-o"></i>
                                            ${p.createdDate}
                                        </div>
                                    </div>
                                    <h5><a href="#">${p.gymPostTile}</a></h5>
                                </div>
                            </div>
                        </div>
                    </c:forEach>
                </div>
            </div>
        </section>
        <!-- Latest News Section End -->

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
        <script src="js/jquery-3.3.1.min.js"></script>
        <script src="js/bootstrap.min.js"></script>
        <script src="js/jquery.magnific-popup.min.js"></script>
        <script src="js/mixitup.min.js"></script>
        <script src="js/jquery.slicknav.js"></script>
        <script src="js/owl.carousel.min.js"></script>
        <script src="js/main.js"></script>
        <script src="js/vietqr.js"></script>
        <script src="https://cdn.payos.vn/payos-checkout/v1/stable/payos-initialize.js"></script>
    </body>

</html>


