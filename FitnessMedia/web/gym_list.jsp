<%-- 
    Document   : gym_list
    Created on : Feb 10, 2024, 11:18:29 PM
    Author     : phamt
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="utf-8">
        <title>Hotelier - Hotel HTML Template</title>
        <meta content="width=device-width, initial-scale=1.0" name="viewport">
        <meta content="" name="keywords">
        <meta content="" name="description">

        <meta http-equiv="cache-control" content="no-cache">
        <meta http-equiv="pragma" content="no-cache">
        <meta http-equiv="expires" content="0">

        <!-- Favicon -->
        <link href="img/favicon.ico" rel="icon">

        <!-- Google Web Fonts -->
        <link rel="preconnect" href="https://fonts.googleapis.com">
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
        <link href="https://fonts.googleapis.com/css2?family=Heebo:wght@400;500;600;700&family=Montserrat:wght@400;500;600;700&display=swap" rel="stylesheet">  

        <!-- Icon Font Stylesheet -->
        <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.10.0/css/all.min.css" rel="stylesheet">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.4.1/font/bootstrap-icons.css" rel="stylesheet">

        <!-- Libraries Stylesheet -->


        <!-- Customized Bootstrap Stylesheet -->
        <link href="css/bootstrap5.min.css" rel="stylesheet">

        <!-- Template Stylesheet -->
        <link href="css/style_Hotel.css" rel="stylesheet">
        <style>
            .center {
                text-align: center;
            }

            .pagination {
                display: inline-block;
            }

            .pagination a {
                color: black;
                float: left;
                padding: 8px 16px;
                text-decoration: none;
                transition: background-color .3s;
                border: 1px solid #ddd;
                margin: 0 4px;
            }

            .pagination a.active {
                background-color: #FEAF39;
                color: white;
                border: 1px solid #FEAF39;
            }

            .pagination a:hover:not(.active) {
                background-color: #ddd;
            }
        </style>
    </head>

    <body>
        <div class="container-xxl bg-white p-0">
            <!-- Spinner Start -->
            <div id="spinner" class="show bg-white position-fixed translate-middle w-100 vh-100 top-50 start-50 d-flex align-items-center justify-content-center">
                <div class="spinner-border text-primary" style="width: 3rem; height: 3rem;" role="status">
                    <span class="sr-only">Loading...</span>
                </div>
            </div>
            <!-- Spinner End -->

            <!-- Header Start -->
            <div class="container-fluid bg-dark px-0">
                <div class="row gx-0">
                    <div class="col-lg-3 bg-dark d-none d-lg-block">
                        <a href="index.html" class="navbar-brand w-100 h-100 m-0 p-0 d-flex align-items-center justify-content-center">
                            <img src="img/logo.png" alt="logo"/>
                        </a>
                    </div>
                    <div class="col-lg-9">
                        <div class="row gx-0 bg-white d-none d-lg-flex">
                            <div class="col-lg-7 px-5 text-start">
                                <div class="h-100 d-inline-flex align-items-center py-2 me-4">
                                    <i class="fa fa-envelope text-primary me-2"></i>
                                    <p class="mb-0">info@example.com</p>
                                </div>
                                <div class="h-100 d-inline-flex align-items-center py-2">
                                    <i class="fa fa-phone-alt text-primary me-2"></i>
                                    <p class="mb-0">+012 345 6789</p>
                                </div>
                            </div>
                            <div class="col-lg-5 px-5 text-end">
                                <div class="d-inline-flex align-items-center py-2">
                                    <a class="me-3" href=""><i class="fab fa-facebook-f"></i></a>
                                    <a class="me-3" href=""><i class="fab fa-twitter"></i></a>
                                    <a class="me-3" href=""><i class="fab fa-linkedin-in"></i></a>
                                    <a class="me-3" href=""><i class="fab fa-instagram"></i></a>
                                    <a class="" href=""><i class="fab fa-youtube"></i></a>
                                </div>
                            </div>
                        </div>
                        <nav class="navbar navbar-expand-lg bg-dark navbar-dark p-3 p-lg-0">
                            <a href="index.html" class="navbar-brand d-block d-lg-none">
                                <img th:src="img/logo.png" alt="logo"/>
                            </a>
                            <button type="button" class="navbar-toggler" data-bs-toggle="collapse" data-bs-target="#navbarCollapse">
                                <span class="navbar-toggler-icon"></span>
                            </button>
                            <div class="collapse navbar-collapse justify-content-between" id="navbarCollapse">
                                <div class="navbar-nav mr-auto py-0 w-100 justify-content-evenly">
                                    <a href="./home" class="nav-item nav-link">Home</a>
                                    <a href="./GymList" class="nav-item nav-link">Gyms</a>
                                    <c:if test='${sessionScope.userAuthorization!=null}'>
                                        <c:if test='${sessionScope.userAuthorization.hasRole("ROLE_CUSTOMER")}'>
                                            <a href="./scheduleDetail" class="nav-item nav-link">My Schedule</a>
                                        </c:if>
                                        <c:if test='${sessionScope.userAuthorization.hasRole("ROLE_MANANGER")}'>
                                            <a href="./GymManager" class="nav-item nav-link">My Gym</a>
                                        </c:if>
                                        <c:if test='${sessionScope.userAuthorization.hasRole("ROLE_ADMIN")}'>
                                            <a href="./AdminList" class="nav-item nav-link">Account Panel</a>
                                        </c:if>
                                        <c:if test='${sessionScope.userAuthorization.hasRole("ROLE_OWNER")}'>
                                            <a href="#" class="nav-item nav-link">Dashboard</a>
                                        </c:if>
                                    </c:if>
                                    <a href="./GymPostList?index=1" class="nav-item nav-link active">Blog</a>
                                </div>
                            </div>
                        </nav>
                    </div>
                </div>
            </div>
            <!-- Header End -->


            <!-- Page Header Start -->
            <div class="container-fluid page-header mb-5 p-0" style="background-image: url(img/carousel-1.jpg);">
                <div class="container-fluid page-header-inner py-5">
                    <div class="container text-center pb-5">
                        <h1 class="display-3 text-white mb-3 animated slideInDown">Gyms</h1>
                        <nav aria-label="breadcrumb">
                            <ol class="breadcrumb justify-content-center text-uppercase">
                                <li class="breadcrumb-item"><a href="#">Home</a></li>
                                <li class="breadcrumb-item"><a href="#">Pages</a></li>
                                <li class="breadcrumb-item text-white active" aria-current="page">GymList</li>
                            </ol>
                        </nav>
                    </div>
                </div>
            </div>
            <!-- Page Header End -->


            <!-- Room Start -->
            <div class="container-xxl py-5">
                <div class="container">
                    <div class="text-center wow fadeInUp" data-wow-delay="0.1s">
                        <h6 class="section-title text-center text-primary text-uppercase">Our Gyms</h6>
                        <h1 class="mb-5">Explore Our <span class="text-primary text-uppercase">Gyms</span></h1>
                    </div>
                    <div class="input-group rounded justify-content-center d-flex" style="margin-bottom: 30px">
                        <form class="d-flex" style="width: 35%" action="GymList">
                            <input type="search" name="keyword" id="keyword" class="form-control rounded"
                                   placeholder="Search" aria-label="Search" aria-describedby="search-addon" />
                            <button class="input-group-text border-0" type="submit" id="search-addon">
                                <i class="fas fa-search"></i>
                            </button>
                        </form>
                        <div class="dropdown" style="margin-left: 5px">
                            <button type="button" class="btn btn-primary dropdown-toggle" data-bs-toggle="dropdown">
                                Order By
                            </button>
                            <ul class="dropdown-menu">
                                <li><a class="dropdown-item" href="javascript:void(0)" onclick="addParametersToUrl([['order', '1']])">Price &#9651;</a></li>
                                <li><a class="dropdown-item" href="javascript:void(0)" onclick="addParametersToUrl([['order', '2']])">Price &#9660;</a></li>
                                <li><a class="dropdown-item" href="javascript:void(0)" onclick="addParametersToUrl([['order', '3']])">Vote &#9651;</a></li>
                                <li><a class="dropdown-item" href="javascript:void(0)" onclick="addParametersToUrl([['order', '4']])">Vote &#9660;</a></li>
                                <!--                            <li><a class="dropdown-item active" href="#">Active</a></li>
                                                                <li><a class="dropdown-item disabled" href="#">Disabled</a></li>-->
                            </ul>
                        </div>

                    </div>
                    <div class="row g-4">
                        <c:forEach items="${gyms}" var="gym">
                            <div class="col-lg-4 col-md-6 wow fadeInUp" data-wow-delay="0.1s">
                                <div class="room-item shadow rounded overflow-hidden">
                                    <div class="position-relative">
                                        <img class="img-fluid" src="${gym.gymAvatar}" alt="gym_avata">
                                        <small class="position-absolute start-0 top-100 translate-middle-y bg-primary text-white rounded py-1 px-3 ms-4">${vndFormat.format(gym.gymCost)}</small>
                                    </div>
                                    <div class="p-4 mt-2">
                                        <div class="d-flex justify-content-between mb-3">
                                            <h5 class="mb-0">${gym.gymName}</h5>
                                            <div class="ps-2">
                                                <c:forEach begin="1" end="5" step="1" var="i">
                                                    <c:if test="${i <= reviewPerGymList.get(gym.gymId).averageVote}">
                                                        <small class="fa fa-star text-primary"></small>                                                    </c:if>
                                                    <c:if test="${i > reviewPerGymList.get(gym.gymId).averageVote}">
                                                        <small class="far fa-star text-primary"></small>
                                                    </c:if>
                                                </c:forEach>
                                            </div>

                                        </div>
                                        <p class="text-body mb-3">
                                            ${gym.getGymWall().length()<=90?gym.getDescription():gym.getGymWall().subSequence(0, 90)} ...
                                        </p>
                                        <div class="d-flex justify-content-between">
                                            <form action="GymDetail" method="post">
                                                <input type="text" name="gymId" value="${gym.gymId}" hidden>
                                                <input type="submit"  value="View Detail" name="btnViewDetail" class="btn btn-sm btn-primary rounded py-2 px-4">
                                            </form>
                                            <!--<a class="btn btn-sm btn-primary rounded py-2 px-4" href="">View Detail</a>-->
                                            <form action="${sessionScope.userAuthorization!=null?'Booking':'Login'}" method="post">
                                                <input name="gymId" value="${gym.gymId}" hidden>
                                                <button class="btn btn-sm btn-dark rounded py-2 px-4" name="btnBooking" type="submit">
                                                    Booking
                                                </button>
                                            </form>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </c:forEach>
                    </div>
                </div>
            </div>
            <!-- Room End -->


            <!-- Pagination Start -->
            <div class="center">
                <div class="pagination">
                    <a id="first-page" href="javascript:void(0);" onclick="addParametersToUrl([['index', '1'], ['size', '${size}']])">&laquo;</a>
                    <a id="pre-page" href="javascript:void(0);" onclick="addParametersToUrl([['index', '${index==1?1:index-1}'], ['size', '${size}']])"><</a>
                    <c:forEach begin="1" end="${endP}" var="i">
                        <a href="javascript:void(0)" onclick="addParametersToUrl([['index', '${i}'], ['size', '${size}']])" class="${index==i?'active':''}">${i}</a>
                    </c:forEach>
                    <a id="next-page" href="javascript:void(0);" onclick="addParametersToUrl([['index', '${index==endP?endP:index+1}'], ['size', '${size}']])">></a>
                    <a id="last-page" href="javascript:void(0);" onclick="addParametersToUrl([['index', '${endP}'], ['size', '${size}']])">&raquo;</a>
                </div>
            </div>
            <!-- Pagination Start -->


            <!-- Footer Start -->
            <!--<div class="container-fluid bg-dark text-light footer wow fadeIn" data-wow-delay="0.1s">-->
            <div class="container pb-5 bg-dark text-light footer" style="padding-top: 80px; margin-top: 20px">
                <div class="row g-5">
                    <div class="col-md-6 col-lg-4">
                        <div class="bg-primary rounded p-4">
                            <a href="index.html"><h1 class="text-white text-uppercase mb-3">Hotelier</h1></a>
                            <p class="text-white mb-0">
                                Download <a class="text-dark fw-medium" href="https://htmlcodex.com/hotel-html-template-pro">Hotelier – Premium Version</a>, build a professional website for your hotel business and grab the attention of new visitors upon your site’s launch.
                            </p>
                        </div>
                    </div>
                    <div class="col-md-6 col-lg-3">
                        <h6 class="section-title text-start text-primary text-uppercase mb-4">Contact</h6>
                        <p class="mb-2"><i class="fa fa-map-marker-alt me-3"></i>123 Street, New York, USA</p>
                        <p class="mb-2"><i class="fa fa-phone-alt me-3"></i>+012 345 67890</p>
                        <p class="mb-2"><i class="fa fa-envelope me-3"></i>info@example.com</p>
                        <div class="d-flex pt-2">
                            <a class="btn btn-outline-light btn-social" href=""><i class="fab fa-twitter"></i></a>
                            <a class="btn btn-outline-light btn-social" href=""><i class="fab fa-facebook-f"></i></a>
                            <a class="btn btn-outline-light btn-social" href=""><i class="fab fa-youtube"></i></a>
                            <a class="btn btn-outline-light btn-social" href=""><i class="fab fa-linkedin-in"></i></a>
                        </div>
                    </div>
                    <div class="col-lg-5 col-md-12">
                        <div class="row gy-5 g-4">
                            <div class="col-md-6">
                                <h6 class="section-title text-start text-primary text-uppercase mb-4">Company</h6>
                                <a class="btn btn-link" href="">About Us</a>
                                <a class="btn btn-link" href="">Contact Us</a>
                                <a class="btn btn-link" href="">Privacy Policy</a>
                                <a class="btn btn-link" href="">Terms & Condition</a>
                                <a class="btn btn-link" href="">Support</a>
                            </div>
                            <div class="col-md-6">
                                <h6 class="section-title text-start text-primary text-uppercase mb-4">Services</h6>
                                <a class="btn btn-link" href="">Food & Restaurant</a>
                                <a class="btn btn-link" href="">Spa & Fitness</a>
                                <a class="btn btn-link" href="">Sports & Gaming</a>
                                <a class="btn btn-link" href="">Event & Party</a>
                                <a class="btn btn-link" href="">GYM & Yoga</a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <!--</div>-->
            <!-- Footer End -->

            <!-- Back to Top -->
            <a href="#" class="btn btn-lg btn-primary btn-lg-square back-to-top"><i class="bi bi-arrow-up"></i></a>
        </div>
        <!-- JavaScript Libraries -->
        <script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/js/bootstrap.bundle.min.js"></script>

        <!-- Template Javascript -->
        <script src="js/show_more_less.js"></script>
        <script src="js/main_Hotel.js"></script>

        <script>
                        function submitForm() {
                            document.getElementById("book").submit();
                        }
        </script>
    </body>

</html>
