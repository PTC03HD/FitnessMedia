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
                            <h2>Manage gym post</h2>
                            <div class="site-breadcrumb">
                                <a href="./home.html" class="sb-item">Home</a>
                                <span class="sb-item">manage gym post</span>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </section>
        <!-- Site Breadcrumb End -->


        <!--Add gym post-->
        <br>
        <div style="color: red;text-align: center" >${mess}</div>
        <div style="display: flex;justify-content: center;align-items: center;">
            <form action="CUGymPost" method="post" style="border: 5px solid #ccc;padding: 20px;border-radius: 5px;">
                <c:if test="${postid==null}">
                    <h3 style="text-align: center; color: #cc3300">Add Gympost</h3>
                </c:if>
                <c:if test="${postid!=null}">
                    <h3 style="text-align: center; color: #cc3300">Update Gympost</h3>
                </c:if>
                    <br>
                <input type="text" name="gymid" value="${gym.gymId}" readonly hidden="">
                <input type="text" name="gympostid" value="${postid}" readonly hidden="">

                <table>
                    <tr>
                        <td><b>Title:</b></td>
                        <!--<td><input type="text" name="title" value="${gympost_before.gymPostTile}" style="width: 500px;"></td>-->
                        <td><textarea name="title" style="height: 70px;width: 500px" required>${gympost_before.gymPostTile}</textarea></td>
                    </tr>
                    <tr>
                        <td><p> </p></td>
                    </tr>
                    <tr>
                        <td><b>Description:</b></td>
                        <!--<td><input type="text" name="description" value="${gympost_before.gymPostStatus}"></td>-->
                        <td><textarea name="description" style="height: 500px;width: 500px" required>${gympost_before.gymPostStatus}</textarea></td>
                    </tr>
                    <tr>
                        <td><p> </p></td>
                    </tr>
                    <tr>
                        <td><b>Image:</b></td>
                        <td><input type="text" name="image" value="${gympost_before.gymPostImg}" style="width: 500px" required></td>
                    </tr>

                </table>
                <br>
                <c:if test="${postid==null}">
                    <input type="submit" value="Add Gym Post" name="addgympost" class="btn btn-info" >
                </c:if>
                <c:if test="${postid!=null}">
                    <input type="submit" value="Update Gym Post" name="updategympost" class="btn btn-info">
                </c:if>
            </form>

        </div>
                    <br>
        <!--end gym post-->

        <!-- Footer Section Begin -->

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