<%-- 
    Document   : gym_detail
    Created on : Feb 12, 2024, 3:20:16 PM
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

        <meta http-equiv="cache-control" content="no-cache">
        <meta http-equiv="pragma" content="no-cache">
        <meta http-equiv="expires" content="0">

        <!-- Google Font -->
        <link href="https://fonts.googleapis.com/css?family=Roboto:400,500,700&display=swap" rel="stylesheet">
        <link href="https://fonts.googleapis.com/css?family=Oswald:300,400,500,600,700&display=swap" rel="stylesheet">

        <!-- Css Styles -->
        <link rel="stylesheet" href="https://cdn.usebootstrap.com/bootstrap/4.4.1/css/bootstrap.min.css">
        <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet">
        <link rel="stylesheet" href="css/style_Fitness.css" type="text/css">

        <style id="style_gyn_review">
            .blog-details .bd-comments .full-screen-overlay {
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
            .blog-details .bd-comments .full-screen-form {
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
            .blog-details .bd-comments .form-container {
                width: 100%;
                max-width: 500px;
                margin: 0 auto;
                padding: 20px;
            }

            /* Form title */
            .blog-details .bd-comments .form-container h2 {
                margin-top: 0;
                text-align: center;
                color: #333;
            }

            /* Form inputs */
            .blog-details .bd-comments .form-container label {
                display: block;
                margin-bottom: 5px;
                color: #555;
            }

            .blog-details .bd-comments .form-container input[type="number"],
            .blog-details .bd-comments .form-container textarea {
                width: 100%;
                padding: 10px;
                margin-bottom: 20px;
                border: 1px solid #ccc;
                border-radius: 5px;
                box-sizing: border-box;
            }

            /* Submit button */
            .blog-details .bd-comments .form-container .btn-container {
                display: flex;
                justify-content: space-between;
            }
            .blog-details .bd-comments .form-container .btn-container .btn {
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

            .blog-details .bd-comments .form-container .btn-container .btn:hover {
                background-color: #45a049;
            }

            /* Cancel button */
            .blog-details .bd-comments .form-container .btn-container .cancel {
                background-color: #f44336;
            }

            .blog-details .bd-comments .form-container .btn-container .cancel:hover {
                background-color: #da190b;
            }

            /* Close button (on top right corner) */
            .blog-details .bd-comments .form-container .btn-container .close {
                position: absolute;
                top: 5px;
                right: 5px;
                font-size: 20px;
                color: #aaa;
            }

            .blog-details .bd-comments .form-container .btn-container .close:hover {
                color: #000;
            }
        </style>
        <style>
            #rating{
                border:none;
                float:left;
                font-size: 30px;
                width: 100%;
                position: relative;
                right: 98px
            }
            #rating>input{
                display:none;
            }/*ẩn input radio - vì chúng ta đã có label là GUI*/
            #rating>label:before{
                margin:5px;
                font-size:1.25em;
                font-family:FontAwesome;
                display:inline-block;
                content:"\f005";
            }/*1 ngôi sao*/
            #rating>.half:before{
                content:"\f089";
                position:absolute;
            }/*0.5 ngôi sao*/
            #rating>label{
                color:#ddd;
                float:right;
            }/*float:right để lật ngược các ngôi sao lại đúng theo thứ tự trong thực tế*/
            /*thêm màu cho sao đã chọn và các ngôi sao phía trước*/
            #rating>input:checked~label,
            #rating:not(:checked)>label:hover,
            #rating:not(:checked)>label:hover~label{
                color:#FFD700;
            }
            /* Hover vào các sao phía trước ngôi sao đã chọn*/
            #rating>input:checked+label:hover,
            #rating>input:checked~label:hover,
            #rating>label:hover~input:checked~label,
            #rating>input:checked~label:hover~label{
                color:#FFED85;
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
                            <li class="active"><a href="./blog.html">Blog</a>
                                <ul class="dropdown">
                                    <li><a href="blog-details.html">Blog Details</a></li>
                                </ul>
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
                            <h2>Our Gym</h2>
                            <div class="site-breadcrumb">
                                <a href="./home.html" class="sb-item">Home</a>
                                <span class="sb-item">Gyms</span>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </section>
        <!-- Site Breadcrumb End -->

        <!-- Blog Section Begin -->
        <section class="blog-section spad">
            <div class="container">
                <div class="row">
                    <div class="col-lg-8">
                        <div class="blog-details">
                            <div class="bd-text">
                                <img src="${reviewPerGymList.get(gymId).gym.gymAvatar}" alt="">
                                <h3 style="margin-bottom: 38px; text-align: center">${reviewPerGymList.get(gymId).gym.gymName}</h3>
                                <h4 style="margin-bottom: 38px; text-align: center; color: #da190b">Owner: ${UserMap.get(reviewPerGymList.get(gymId).gym.ownerId).imgWall}</h4>
                                <p>${reviewPerGymList.get(gymId).gym.gymWall}</p>
                            </div>
                            <div class="bd-info">
                            </div>
                            <div class="bd-comments">
                                <div style="display: flex">
                                    <h3 style="margin-right: 10px">User Rating</h3>
                                    <div class="ps-2" style="font-size: 30px">
                                        <c:forEach begin="1" end="5" step="1" var="i">
                                            <c:if test="${i <= reviewPerGymList.get(gymId).averageVote}">
                                                <small class="fa fa-star text-primary"></small>
                                            </c:if>
                                            <c:if test="${i > reviewPerGymList.get(gymId).averageVote}">
                                                <small class="fa fa-star-o text-primary"></small>
                                            </c:if>
                                        </c:forEach>
                                    </div>
                                    <div class="dropdown" style="position: absolute; right: 10px">
                                        <button style="background-color: transparent; color: black" type="button" class="btn btn-primary dropdown-toggle" data-toggle="dropdown">
                                            Filter
                                        </button>
                                        <ul class="dropdown-menu">
                                            <li><a class="dropdown-item" href="javascript:void(0)" onclick="addParametersToUrl([['order', '1']])">Oldest</a></li>
                                            <li><a class="dropdown-item" href="javascript:void(0)" onclick="addParametersToUrl([['order', '2']])">Newest</a></li>
                                            <li><a class="dropdown-item" href="javascript:void(0)" onclick="addParametersToUrl([['order', '3']])">Vote &#9651;</a></li>
                                            <li><a class="dropdown-item" href="javascript:void(0)" onclick="addParametersToUrl([['order', '4']])">Vote &#9660;</a></li>
                                        </ul>
                                    </div>
                                </div>
                                <p style="margin-top: 10px">${reviewPerGymList.get(gymId).averageVote} average based on ${reviewPerGymList.get(gymId).gymReview.size()} reviews
                                    <c:forEach items="${reviewPerGymList.get(gymId).gymReview}" var="review" varStatus="loop">
                                    <div class="comments-item" style="display: ${loop.index < 2 ? 'block' : 'none'};">
                                        <c:if test="${!review.hide}">
                                            <c:if test="${review.makerId == sessionScope.userAuthorization.user.userId}">
                                                <input type="text" id="reviewId" value="${review.reviewId}" hidden>
                                                <div style="float: right" class="nav-menu">
                                                    <nav class="mainmenu mobile-menu">
                                                        <ul>
                                                            <li class="active"> <strong style="font-size: 30px">...</strong>
                                                                <ul class="dropdown">
                                                                    <li><a href="#">update</a></li>
                                                                    <li><a href="#" data-toggle="modal" data-target="#myDeleteForm" onclick="transferValue()">delete</a></li>
                                                                </ul>
                                                            </li>
                                                        </ul>
                                                    </nav>
                                                </div>
                                            </c:if>

                                            <div class="avatar-pic">
                                                <img src="${UserMap.get(review.makerId).imgAvatar}" alt="avatar_img">
                                            </div>
                                            <div class="avatar-text">
                                                <span>${dateFormat.format(review.createdDate)}</span>
                                                <h6>${UserMap.get(review.makerId).userName}</h6>
                                                <div class="ps-2">
                                                    <c:forEach begin="1" end="5" step="1" var="i">
                                                        <c:if test="${i <= review.vote}">
                                                            <small class="fa fa-star text-primary"></small>
                                                        </c:if>
                                                        <c:if test="${i > review.vote}">
                                                            <small class="fa fa-star-o text-primary"></small>
                                                        </c:if>
                                                    </c:forEach>
                                                </div>
                                                <p>${review.reviewContent}</p>
                                            </div>
                                        </c:if>
                                    </div>
                                </c:forEach>
                                <div class="show-btn-container" style="">
                                    <button id="show-more-btn" type="button">Show More</button>
                                    <button id="show-less-btn" type="button" style="display: none;">Show Less</button>
                                    <c:if test="${BookingMap.get(sessionScope.userAuthorization.user.userId).isExperienced(gymId) 
                                                  && !reviewPerGymList.get(gymId).isReviewed(sessionScope.userAuthorization.user.userId)}">
                                          <button id="write-review-btn" type="button" onclick="openFullScreenForm()">Leave Review</button>
                                    </c:if>
                                </div>
                                <div class="full-screen-overlay" id="overlay" >
                                    <div class="full-screen-form" id="reviewForm">
                                        <form action="GymDetail" class="form-container" method="post">
                                            <h2>Write a Review</h2>
                                            <input type="text" name="gymId" value="${gymId}" hidden="">
                                            <div id="rating">
                                                <input type="radio" id="star5" name="rating" value="5" />
                                                <label class = "full" for="star5" title="Awesome - 5 stars"></label>

                                                <input type="radio" id="star4" name="rating" value="4" />
                                                <label class = "full" for="star4" title="Pretty good - 4 stars"></label>

                                                <input type="radio" id="star3" name="rating" value="3" />
                                                <label class = "full" for="star3" title="Meh - 3 stars"></label>

                                                <input type="radio" id="star2" name="rating" value="2" />
                                                <label class = "full" for="star2" title="Kinda bad - 2 stars"></label>

                                                <input type="radio" id="star1" name="rating" value="1" />
                                                <label class = "full" for="star1" title="Sucks big time - 1 star"></label>
                                            </div>

                                            <textarea id="review" name="review" placeholder="Write something.." style="height:200px" required></textarea>
                                            <div class="btn-container">
                                                <button type="submit" class="btn">Submit</button>
                                                <button type="button" class="btn cancel" onclick="closeFullScreenForm()">Close</button>
                                            </div>
                                        </form>
                                    </div>
                                </div>
                                <!-- The Delete Form -->
                                <div class="modal" id="myDeleteForm">
                                    <div class="modal-dialog">
                                        <div class="modal-content">
                                            <form action="GymDetail" method="post">
                                                <input name="deleteId" id="deleteId" hidden>
                                                <input type="text" name="gymId" value="${gymId}" hidden>
                                                <div class="modal-header">
                                                    <h4 class="modal-title">Warning!</h4>
                                                </div>
                                                <div class="modal-body">
                                                    Are you sure to delete this review?
                                                </div>
                                                <div class="modal-footer">
                                                    <button type="button" class="btn btn-danger" data-dismiss="modal">Close</button>
                                                    <button type="submit" class="btn btn-danger" name="deleteBtn" value="${review.reviewId}">Ok</button>
                                                </div>
                                            </form>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="col-lg-3 offset-lg-1">
                        <div class="blog-sidebar">

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
                        <c:if test="${sessionScope.userAuthorization != null}">
                            <div style="display: flex; justify-content: center">
                                <form action="Booking" method="post">
                                    <input name="gymId" value="${gymId}" hidden>
                                    <button class="btn-primary btn" name="btnBooking" type="submit"
                                            style="border: none; background-color: #f34e3a;">
                                        Booking
                                    </button>
                                </form>
                            </div>
                        </c:if>
                    </div>
                </div>
            </div>
        </section>
        <!-- Blog Section End -->

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
                    <div class="row">
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

        <script>
            function openFullScreenForm() {
                document.getElementById("overlay").style.display = "block";
            }

            function closeFullScreenForm() {
                document.getElementById("overlay").style.display = "none";
            }

            function transferValue() {
                // Lấy giá trị từ input1
                var value1 = document.getElementById("reviewId").value;

                // Gán giá trị vào input2
                document.getElementById("deleteId").value = value1;
            }
        </script>

        <script id="vote_start_form">
            const stars = document.querySelectorAll(".stars i");
            // Loop through the "stars" NodeList    
            stars.forEach((star, index1) => {
                // Add an event listener that runs a function when the "click" event is triggered
                star.addEventListener("click", () => {
                    // Loop through the "stars" NodeList Again
                    stars.forEach((star, index2) => {
                        // Add the "active" class to the clicked star and any stars with a lower index
                        // and remove the "active" class from any stars with a higher index
                        index1 >= index2 ? star.classList.add("active") : star.classList.remove("active");
                    });
                });
            });
        </script>
        <script>
            window.onload = function showAlert() {
                var condition = '${error}';
                if (condition === 'true') {
                    alert("Please enter a valid value");
                }
            }
        </script>
        <!-- Js Plugins -->
        <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.1/umd/popper.min.js"></script>
        <script src="https://cdn.usebootstrap.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>


        <script src="js/main_Fitness.js"></script>
        <script src="js/show_more_less.js"></script>
    </body>
</html>

