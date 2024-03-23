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
                    <a href="./index.html">
                        <img src="img/logo-normal.png" alt="">
                    </a>
                </div>
                <div class="nav-menu">
                    <nav class="mainmenu mobile-menu">
                        <ul>
                            <li><a href="./index.html">Home</a></li>
                            <li><a href="./about-us.html">About</a></li>
                            <li><a href="./schedule.html">Schedule</a></li>
                            <li><a href="./gallery.html">Portfolio</a></li>
                            <li class="active"><a href="GymPostList?index=1">Blog</a>

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
                                <a href="./home.html" class="sb-item">Home</a>
                                <span class="sb-item">Blog-Details</span>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </section>
        <!-- Site Breadcrumb End -->
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
                    <button type="submit" name="update" value="update" class="btn btn-success" style="float: right;">Update Gym Post</button> 
                </form>
            </c:if>
        </c:if>
        <!-- Blog Section Begin -->
        <section class="blog-section spad">
            <div class="container">
                <div class="row">
                    <div class="col-lg-8">
                        <div class="blog-details">
                            <div class="bd-text">
                                <img src="${gympost.gymPostImg}" alt="">
                                <h3>${gympost.gymPostTile}</h3>
                                <div class="blog-tag">
                                    <div class="post-info">
                                        ${gympost.createdDate}
                                    </div>

                                </div>

                                <p>${gympost.gymPostStatus}</p>


                            </div>


                            <div class="bd-comments">
                                <h4>${size} Comments</h4>
                                <c:forEach items="${usercommentlist}" var="usercomment">
                                    <div class="comments-item">
                                        <div style="float: right" class="nav-menu">
                                            <nav class="mainmenu mobile-menu">
                                                <ul>
                                                    <c:if test="${user.userId==usercomment.user.userId}">
                                                        <li class="active">Chinh sua
                                                            <ul class="dropdown">
                                                                <li><a href="">update</a></li>
                                                                <li><a href="GymPostDetail?id=${gympostid}&commentid=${usercomment.comment.commentId}">delete</a>


                                                                </li>
                                                            </ul>
                                                        </li>
                                                    </c:if>
                                                </ul>
                                            </nav>
                                        </div>
                                        <h1 hidden>${usercomment.comment.commentId}</h1>
                                        <div class="avatar-pic">
                                            <img src="${usercomment.user.imgAvatar}" alt="">
                                        </div>
                                        <div class="avatar-text">
                                            <span>${usercomment.comment.createdDate}</span>
                                            <h6>${usercomment.user.userName}</h6>
                                            <p>${usercomment.comment.commentContent}</p>

                                        </div>
                                    </div>
                                    <hr>
                                </c:forEach>

                            </div>
                            <div class="comment-form">
                                <h4>Leave A Comment</h4>
                                <form action="GymPostDetail" method="post">
                                    <div class="row">
                                        <div class="col-lg-12">

                                            <textarea placeholder="Message" name="content" required></textarea>
                                            <input type="text" name="commentid" hidden readonly>
                                            <input type="text" name="id" value="${gympost.gymPostId}" hidden readonly>
                                            <button type="submit" value="insert" name="btnComment">Send Message</button>
                                        </div>
                                    </div>
                                </form>
                                <div style="color: red">${message}</div>
                            </div>
                            <script>
                                document.addEventListener('DOMContentLoaded', function () {
                                    // L?y ph?n t? textarea trong form
                                    var textarea = document.querySelector('.comment-form textarea');

                                    // L?y t?t c? các liên k?t "update" trong danh sách comment và thêm s? ki?n click cho m?i liên k?t
                                    var updateLinks = document.querySelectorAll('.comments-item .dropdown li:first-child a');
                                    updateLinks.forEach(function (updateLink) {
                                        updateLink.addEventListener('click', function (event) {
                                            event.preventDefault(); // Ng?n ch?n hành ??ng m?c ??nh c?a liên k?t (ch?ng h?n nh? chuy?n h??ng ??n m?t trang m?i)

                                            // L?y giá tr? commentid t? th? h1 t??ng ?ng
                                            var commentId = this.closest('.comments-item').querySelector('h1').textContent.trim();

                                            // L?y n?i dung c?a th? p t??ng ?ng v?i comment
                                            var commentContent = this.closest('.comments-item').querySelector('.avatar-text p').textContent.trim();

                                            // Gán giá tr? commentid vào input có tên là commentid
                                            document.querySelector('input[name="commentid"]').value = commentId;

                                            // Gán n?i dung c?a comment vào giá tr? c?a textarea trong form
                                            textarea.value = commentContent;

                                            // Cu?n ??n ph?n t? c?a form
                                            document.querySelector('.comment-form').scrollIntoView({behavior: 'smooth'});
                                        });
                                    });
                                });
                            </script>               
                        </div>
                    </div>
                    <div class="col-lg-3 offset-lg-1">
                        <div class="blog-sidebar">
                            <div class="sidebar-recent">
                                <h4>Recent News</h4>

                                <c:forEach items="${recentnewslist}" var="recentnews" >
                                    <div class="sr-item">
                                        <a href="GymPostDetail?id=${recentnews.gymPostId}">
                                            <h5>${recentnews.gymPostTile}</h5>
                                        </a>
                                        <div class="blog-date">${recentnews.createdDate}</div>
                                    </div>
                                </c:forEach>

                            </div>
                            <div class="sidebar-categories">
                                <h4>Categories</h4>
                                <form action="FilterGymPost" method="post">
                                    <select name="id">
                                        <c:forEach items="${gymlist}" var="gym" >
                                            <option value="${gym.gymId}">${gym.gymName}</option>
                                        </c:forEach>
                                    </select>
                                    <input type="text" name="index" value="1" hidden readonly>
                                    <input type="submit" name="btnFilter" value="Filter">
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
                                    <li class="active">
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