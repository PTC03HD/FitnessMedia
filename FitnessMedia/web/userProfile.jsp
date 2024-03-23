<%-- 
    Document   : userProfile
    Created on : Feb 18, 2024, 3:44:38 PM
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
        <title>PONIGYM | User Profile</title>

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


        <style>
            body{
                background: -webkit-linear-gradient(left, #3931af, #00c6ff);
            }
            .emp-profile{
                padding: 3%;
                margin-top: 3%;
                margin-bottom: 3%;
                border-radius: 0.5rem;
                background: #fff;
            }
            .profile-img{
                text-align: center;
            }
            .profile-img img{
                width: 70%;
                height: 100%;
            }
            .profile-img .file {
                position: relative;
                overflow: hidden;
                margin-top: -20%;
                width: 70%;
                border: none;
                border-radius: 0;
                font-size: 15px;
                background: #212529b8;
            }
            .profile-img .file input {
                position: absolute;
                opacity: 0;
                right: 0;
                top: 0;
            }
            .profile-head h5{
                color: #333;
            }
            .profile-head h6{
                color: #0062cc;
            }
            .profile-edit-btn{
                border: none;
                border-radius: 1.5rem;
                width: 70%;
                padding: 2%;
                font-weight: 600;
                color: #6c757d;
                cursor: pointer;
            }
            .proile-rating{
                font-size: 12px;
                color: #818182;
                margin-top: 5%;
            }
            .proile-rating span{
                color: #495057;
                font-size: 15px;
                font-weight: 600;
            }
            .profile-head .nav-tabs{
                margin-bottom:5%;
            }
            .profile-head .nav-tabs .nav-link{
                font-weight:600;
                border: none;
            }
            .profile-head .nav-tabs .nav-link.active{
                border: none;
                border-bottom:2px solid #0062cc;
            }
            .profile-work{
                padding: 14%;
                margin-top: -15%;
            }
            .profile-work p{
                font-size: 12px;
                color: #818182;
                font-weight: 600;
                margin-top: 10%;
            }
            .profile-work a{
                text-decoration: none;
                color: #495057;
                font-weight: 600;
                font-size: 14px;
            }
            .profile-work ul{
                list-style: none;
            }
            .profile-tab label{
                font-weight: 600;
            }
            .profile-tab p{
                font-weight: 600;
                color: #0062cc;
            }

            .a1:link, .a1:visited {
                background-color: aqua;
                color: black;
                border: 2px solid blue;
                padding: 10px 20px;
                text-align: center;
                text-decoration: none;
                display: inline-block;
                font-size: 12px;
                width:131px
            }

            .a1:hover, .a1:active {
                background-color: blue;
                color: white;
            }
            .a2:link, .a2:visited {
                background-color: #f83351;
                color: black;
                border: 2px solid red;
                padding: 10px 20px;
                text-align: center;
                text-decoration: none;
                display: inline-block;
                font-size: 12px;
                width:131px
            }

            .a2:hover, .a2:active {
                background-color: red;
                color: white;
            }
            
           
        </style>
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
                                    <li><a href="./GymManager">My Gym</a></li>
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
                            <h2>My Profile</h2>
                            <div class="site-breadcrumb">
                                <a href="./home" class="sb-item">Home</a>
                                <span class="sb-item">Profile</span>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </section>
        <!-- Site Breadcrumb End -->
        <br><br>
        <!-- Body Content Begin-->
        <div class="container emp-profile">

            <div class="row">
                <div class="col-md-4">
                    <div class="profile-img">
                        <img src="${user.imgAvatar}" style="width:250px;height:250px;border-radius: 50%;border:5px solid red">
                    </div>
                </div>
                <div class="col-md-6">
                    <div class="profile-head">
                        <h3>
                            Welcome!
                        </h3>
                        <h4 style="color: red;">
                            ${user.imgWall}
                        </h4>
                        <p class="proile-rating">Date Created : <span>${user.createdDate}</span></p>
                        
                            <ul class="nav nav-tabs" id="myTab" role="tablist" style="margin-top:50px">
                                <li class="nav-item">
                                    <a class="nav-link active" id="home-tab" data-toggle="tab" href="#home" role="tab" aria-controls="home" aria-selected="true">About</a>
                                </li>
                                <c:if test='${!sessionScope.userAuthorization.hasRole("ROLE_ADMIN")}'>
                                
                                <li class="nav-item">
                                    <a class="nav-link" id="profile-tab"  href="./changePass?uid=${user.userId}">Change Password</a>
                                </li>
                                </c:if>
                            </ul>
                        

                    </div>     

                </div> 
                <div class="col-md-2">
                    <c:if test='${!sessionScope.userAuthorization.hasRole("ROLE_ADMIN")}'>
                        <a href="./updateProfile?uid=${user.userId}" class="a1">Edit Profile</a>
                        <a href="./deleteUser?uid=${user.userId}" class="a2">Delete Account</a>
                    </c:if>
                </div>
            </div>
           
            <div class="row">
                <div class="col-md-4">
                    <div class="profile-work">
                        <c:if test='${sessionScope.userAuthorization.hasRole("ROLE_CUSTOMER")}'>
                            <p>My Gym Rooms Use Recently</p>
                            <a href="">Phòng 1</a><br/>
                            <a href="">Phòng 2</a><br/>
                            <a href="">Phòng 3</a><br/>
                            <a href="">Phòng 4</a><br/>
                            <a href="">Phòng 5</a><br/>
                        </c:if>
                    </div>
                </div>
                <div class="col-md-8">
                    <div class="tab-content profile-tab" id="myTabContent">
                        <div class="tab-pane fade show active" id="home" role="tabpanel" aria-labelledby="home-tab">
                            <div class="row">
                                <div class="col-md-6">
                                    <label>Username</label>
                                </div>
                                <div class="col-md-6">
                                    <p> ${user.userName}</p>
                                </div>
                            </div>
                            <br>
                            <div class="row">
                                <div class="col-md-6">
                                    <label>Id Citizen</label>
                                </div>
                                <div class="col-md-6">
                                    <p> ${user.idCitizen}</p>
                                </div>
                            </div>
                            <br>
                            <div class="row">
                                <div class="col-md-6">
                                    <label>Full Name</label>
                                </div>
                                <div class="col-md-6">
                                    <p>${user.imgWall}</p>
                                </div>
                            </div>
                            <br>
                            <div class="row">
                                <div class="col-md-6">
                                    <label>Email</label>
                                </div>
                                <div class="col-md-6">
                                    <p>${user.userEmail}</p>
                                </div>
                            </div>
                            <br>
                            <div class="row">
                                <div class="col-md-6">
                                    <label>Phone</label>
                                </div>
                                <div class="col-md-6">
                                    <p>${user.perPhone}</p>
                                </div>
                            </div>
                            <br>
                        </div>
                        <div class="tab-pane fade" id="profile" role="tabpanel" aria-labelledby="profile-tab">


                        </div>
                    </div>
                </div>
            </div>

        </div>
        <!-- Body Content End -->
        <br><br>



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
