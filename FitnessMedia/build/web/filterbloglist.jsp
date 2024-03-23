<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
        <link rel="stylesheet" href="css/bootstrap.min.css" type="text/css">
        <link rel="stylesheet" href="css/font-awesome.min.css" type="text/css">
        <link rel="stylesheet" href="css/themify-icons.css" type="text/css">
        <link rel="stylesheet" href="css/owl.carousel.min.css" type="text/css">
        <link rel="stylesheet" href="css/magnific-popup.css" type="text/css">
        <link rel="stylesheet" href="css/slicknav.min.css" type="text/css">
        <link rel="stylesheet" href="css/style.css" type="text/css">
    </head>

    <body>
        <!-- Page Preloder -->
        <div id="preloder">
            <div class="loader"></div>
        </div>

        <!-- Header Section Begin -->
        <header class="header-section header-normal">
            <div class="container">
                <div class="logo">
                    <a href="HomePage">
                        <img src="img/logo-normal.png" alt="">
                    </a>
                </div>
                <div class="nav-menu">
                    <nav class="mainmenu mobile-menu">
                        <ul>
                            <li><a href="HomePage">Home</a></li>
                            <li><a href="./about-us.html">About</a></li>
                            <li><a href="./schedule.html">Schedule</a></li>
                            <li><a href="./gallery.html">Portfolio</a></li>
                            <li><a href="GymPostList?index=1">Blog</a>

                            </li>
                            <li><a href="./contact.html">Contacts</a></li>
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
                            <h2>Our Blog</h2>
                            <div class="site-breadcrumb">
                                <a href="HomePage" class="sb-item">Home</a>
                                <span class="sb-item">Blogs</span>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </section>
        <!-- Site Breadcrumb End -->
        <c:if test='${sessionScope.userAuthorization.hasRole("ROLE_MANANGER")}'>
            <form action="CUGymPost" method="get">
                <br>
                <button type="submit" style="float: right" class="btn btn-success">Add Gym Post</button>
            </form>
        </c:if>
        <!-- Blog Section Begin -->
        <section class="blog-section spad">
            <div class="container">
                <div class="row">
                    <div class="col-lg-8">
                        <div class="blog-items">
                            <c:forEach items="${gympostlist}" var="gympost">
                                <div class="single-blog-item">
                                    <div class="blog-pic">
                                        <img src="${gympost.gymPostImg}" alt="">
                                    </div>
                                    <div class="blog-text">
                                        <div class="blog-tag">
                                            <div class="post-info">
                                                ${gympost.createdDate}
                                            </div>

                                        </div>
                                        <a href="GymPostDetail?id=${gympost.gymPostId}">
                                            <h3>${gympost.gymPostTile}</h3>
                                        </a>
                                        <c:if test='${sessionScope.userAuthorization.hasRole("ROLE_MANANGER")}'>
                                            <c:if test="${gympost.gymId==gymforbutton}">
                                                <form action="CUGymPost" method="get" style="float: right">
                                                <br>
                                                <input type="text" name="gympostid" value="${gympost.gymPostId}" hidden readonly>
                                                <button type="submit" name="delete" value="delete" style="float: right" class="btn btn-danger">Delete Gym Post</button> &nbsp; &nbsp; &nbsp;
                                            </form>
                                            <form action="CUGymPost" method="post" style="float: right">
                                                <br>
                                                <input type="text" name="gympostid" value="${gympost.gymPostId}" hidden readonly>
                                                <button type="submit" name="update" value="update" class="btn btn-success" style="float: right;">Update Gym Post</button> &nbsp; &nbsp; &nbsp;
                                            </form>
                                            </c:if>
                                        </c:if>    

                                    </div>

                                </div>

                            </c:forEach>

                            <!--phan trang-->
                            <div class="blog-pagination">

                                <!--prev-->
                                <c:if test="${endPage!=0}">
                                    <c:if test="${index_save<=1}">
                                        <a class="frist" href="FilterGymPost?index=${index_save}&id=${gymid}">Prev</a>
                                    </c:if>
                                    <c:if test="${index_save>1}">        
                                        <a class="frist" href="FilterGymPost?index=${index_save-1}&id=${gymid}">Prev</a>
                                    </c:if>
                                </c:if>
                                <!--number-->
                                <c:if test="${endPage<=5}">
                                    <c:forEach begin="1" end="${endPage}" var="i">

                                        <a href="FilterGymPost?index=${i}&id=${gymid}">${i}</a>

                                    </c:forEach>
                                </c:if>
                                <c:if test="${endPage>5}">
                                    <c:forEach begin="1" end="${endPage}" var="i">
                                        <c:if test="${i<=2}">
                                            <a href="FilterGymPost?index=${i}&id=${gymid}">${i}</a>

                                        </c:if>
                                        <c:if test="${i==endPage}">
                                            ...<a href="FilterGymPost?index=${i}&id=${gymid}">${i}</a>

                                        </c:if>
                                    </c:forEach>
                                </c:if>
                                <!--next-->
                                <c:if test="${endPage!=0}">
                                    <c:if test="${endPage<=index_save}">
                                        <a class="last" href="FilterGymPost?index=${endPage}&id=${gymid}">Next</a>
                                    </c:if>
                                    <c:if test="${endPage>index_save}">    
                                        <a class="last" href="FilterGymPost?index=${index_save+1}&id=${gymid}">Next</a>
                                    </c:if>
                                </c:if>

                            </div>
                        </div>
                    </div>
                    <div class="col-lg-3 offset-lg-1">
                        <div class="blog-sidebar">
                            <div class="sidebar-recent">
                                <h4>Recent News</h4>
                                <c:forEach items="${recentnewslist}" var="recentnews" >
                                    <div class="sr-item">
                                        <a href="#">
                                            <h5>${recentnews.gymPostTile}</h5>
                                        </a>
                                        <div class="blog-date">${recentnews.createdDate}</div>
                                    </div>
                                </c:forEach>
                            </div>
                            <div class="sidebar-categories">
                                <h4>Categories</h4>
                                <form action="FilterGymPost" method="post">
                                    <select name="id" class="form-control">
                                        <c:forEach items="${gymlist}" var="gym" >
                                            <option value="${gym.gymId}">${gym.gymName}</option>
                                        </c:forEach>
                                    </select>
                                    <input type="text" name="index" value="1" hidden readonly><br>
                                    <input type="submit" name="btnFilter" value="Filter" class="btn btn-info">
                                </form>
                            </div>

                            <div class="weekly-time">
                                <h4>Open Hours</h4>
                                <ul>
                                    <li>
                                        <p>Monday</p>
                                        <span>07:00-17:00</span>
                                    </li>
                                    <li>
                                        <p>Tuesday</p>
                                        <span>07:00-17:00</span>
                                    </li>
                                    <li>
                                        <p>Wednesday</p>
                                        <span>07:00-17:00</span>
                                    </li>
                                    <li>
                                        <p>Thursday</p>
                                        <span>07:00-17:00</span>
                                    </li>
                                    <li>
                                        <p>Friday</p>
                                        <span>07:00-17:00</span>
                                    </li>
                                    <li>
                                        <p>Saturay</p>
                                        <span>07:00-17:00</span>
                                    </li>
                                    <li>
                                        <p>Sunday</p>
                                        <span>07:00-17:00</span>
                                    </li>
                                </ul>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </section>
        <!-- Blog Section End -->

        <!-- Footer Section Begin -->
        <footer class="footer-section">
            <div class="container-fluid">
                <div class="row">
                    <div class="col-lg-6">
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
                    </div>
                    <div class="col-lg-6">
                        <div class="footer-form set-bg" data-setbg="img/contact-form-bg.jpg">
                            <div class="row">
                                <div class="col-lg-10">
                                    <div class="section-title">
                                        <h2>Request A Call Back</h2>
                                        <p>Shape your body and burn fat with strength training. With the right equipment
                                            such as free weights or resistance machines.</p>
                                    </div>
                                    <form action="#">
                                        <div class="row">
                                            <div class="col-lg-6">
                                                <input type="text" placeholder="Name">
                                            </div>
                                            <div class="col-lg-6">
                                                <input type="text" placeholder="Email">
                                            </div>
                                            <div class="col-lg-12">
                                                <input type="text" placeholder="Subject">
                                                <textarea placeholder="Message"></textarea>
                                                <button type="submit">Submit <i class="ti-angle-double-right"></i></button>
                                            </div>
                                        </div>
                                    </form>
                                </div>
                            </div>
                        </div>
                    </div>
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

        <!-- Js Plugins -->

        <script src="js/jquery-3.3.1.min.js"></script>
        <script src="js/bootstrap.min.js"></script>
        <script src="js/jquery.magnific-popup.min.js"></script>
        <script src="js/mixitup.min.js"></script>
        <script src="js/jquery.slicknav.js"></script>
        <script src="js/owl.carousel.min.js"></script>
        <script src="js/main.js"></script>
    </body>

</html>