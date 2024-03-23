<%-- 
    Document   : CustomUserPostPage
    Created on : Feb 23, 2024, 11:32:15 AM
    Author     : admin
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>User Post Page</title>
        <style>
            body {
                background-color: #eeeeee;
            }

            .h7 {
                font-size: 0.8rem;
            }

            .gedf-wrapper {
                margin-top: 0.97rem;
            }

            @media (min-width: 992px) {
                .gedf-main {
                    padding-left: 4rem;
                    padding-right: 4rem;
                }

                .gedf-card {
                    margin-bottom: 2.77rem;
                }
            }

            /**Reset Bootstrap*/
            .dropdown-toggle::after {
                content: none;
                display: none;
            }
            .hover-icon:hover {
                color: #007bff;
                cursor: pointer;
            }
            .hover-icon{
                color: black;
            }
            textarea {
                resize: none !important;
            }
        </style>
    </head>
    <body>
        <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
        <script src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
        <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
        <!------ Include the above in your HEAD tag ---------->

        <link href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet"
              integrity="sha384-wvfXpqpZZVQGK6TAh5PVlGOfQNHSoD2xbE+QkPxCAFlNEevoEH3Sl0sibVcOQVnN" crossorigin="anonymous">

        <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
                integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
        crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"
                integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49"
        crossorigin="anonymous"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"
                integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy"
        crossorigin="anonymous"></script>
        <nav class="navbar navbar-light bg-white">
            <a href="/FitnessMedia/ForumPage" class="navbar-brand">Forum</a>
            <form class="form-inline" action="ForumPage" method="get">
                <div class="input-group">
                    <input type="text" class="form-control" aria-label="Recipient's username"
                           aria-describedby="button-addon2" placeholder="Tên người dùng" name="partOfUserName">
                    <select name="isNewestFirst" class="form-control" aria-describedby="button-addon2">
                        <option value="true">Mới nhất trước tiên</option>
                        <option value="false">Cũ nhất trước tiên</option>
                    </select>
                    <div class="input-group-append">
                        <button class="btn btn-outline-primary" type="submit" id="button-addon2">
                            <i class="fa fa-search"></i>
                        </button>
                    </div>
                </div>
            </form>
        </nav>
        <div class="container-fluid gedf-wrapper">
            <div class="row">
                <div class="col-md-3">
                    <div class="card">
                        <div class="card-body">
                            <div class="h5">Trò chuyện đó đây</div>
                            <div class="h7 text-muted">From: Anh ADMIN</div>
                            <div class="h7">
                                Mọi người dùng đăng bài lưu ý tiêu chuẩn của cộng đồng nhé!
                            </div>
                        </div>
                        <ul class="list-group list-group-flush">
                            <li class="list-group-item">
                                <div class="h6 text-muted">Số bài viết</div>
                                <div class="h5">${totalNumberOfUserPost}</div>
                            </li>
                            <li class="list-group-item">
                                <div class="h6 text-muted">Số bài viết tháng này</div>
                                <div class="h5">${userPostOfThisMonth}</div>
                            </li>
                            <li class="list-group-item">Vestibulum at eros</li>
                        </ul>
                    </div>
                </div>
                <div class="col-md-6 gedf-main">
                    <c:set var="userPostId" value="${customUserPost.userPost.userPostId}"/>
                    <!-- Post /////-->
                    <!--- \\\\\\\Post-->
                    <div class="card gedf-card">
                        <div class="card-header">
                            <div class="d-flex justify-content-between align-items-center">
                                <div class="d-flex justify-content-between align-items-center">
                                    <div class="mr-2">
                                        <img class="rounded-circle" width="45" src="${customUserPost.getUser().getImgAvatar()}" alt=" ">
                                    </div>
                                    <div class="ml-2">
                                        <a class="h5 m-0" href="#">${customUserPost.getUser().getUserName()}</a>
                                        <div class="h7 text-muted">${customUserPost.getUser().getUserName()}</div>
                                    </div>
                                </div>
                                <div>
                                    <div class="dropdown">
                                        <button class="btn btn-link dropdown-toggle" type="button" id="gedf-drop1"
                                                data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                            <i class="fa fa-ellipsis-h"></i>
                                        </button>
                                        <div class="dropdown-menu dropdown-menu-right" aria-labelledby="gedf-drop1">
                                            <div class="h6 dropdown-header">Tùy chọn</div>
                                            <c:if test="${customUserPost.user.userId == sessionScope.userAuthorization.user.userId}">
                                                <a class="dropdown-item" href="/FitnessMedia/DeleteUserPost?userId=${customUserPost.user.userId}&userPostId=${customUserPost.userPost.userPostId}">Xóa</a>
                                            </c:if>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="card-body">
                            <div class="text-muted h7 mb-2"> <i class="fa fa-clock-o"></i>${customUserPost.getUserPost().getCreatedDate()}</div>

                            <c:if test="${customUserPost.countLines(customUserPost.userPost.userPostStatus)<=30}">
                                <textarea class="form-control bg-white" readonly rows="${customUserPost.countLines(customUserPost.userPost.userPostStatus)}">${customUserPost.getUserPost().getUserPostStatus()}</textarea>
                            </c:if>

                            <c:if test="${customUserPost.countLines(customUserPost.userPost.userPostStatus)>30}">
                                <textarea class="form-control bg-white" readonly rows="30">${customUserPost.getUserPost().getUserPostStatus()}</textarea>
                            </c:if>

                            <c:if test="${customUserPost.userPost.userPostImg!=null}">
                                <img src='userPostImgsFolder/${customUserPost.userPost.userPostImg}' class="img-fluid" alt="Responsive image">
                            </c:if>
                        </div>
                        <div class="card-footer">
                            <c:if test="${sessionScope.userAuthorization!=null}">
                                <a id="like" href="/FitnessMedia/LikeUserPost?userPostId=${userPostId}&userMakerId=${sessionScope.userAuthorization.user.userId}&makeLike=true" class="card-link" style="color: ${customUserPost.checkUserLike(sessionScope.userAuthorization.user.userId)?"#007bff":"black"}"><i class="fa fa-thumbs-up"></i> ${customUserPost.numberOfLikes()} Likes</a>
                                <a id="dislike" href="/FitnessMedia/LikeUserPost?userPostId=${userPostId}&userMakerId=${sessionScope.userAuthorization.user.userId}&&makeLike=false" class="card-link" style="color: ${customUserPost.checkUserDisLike(sessionScope.userAuthorization.user.userId)?"#007bff":"black"}"><i class="fa fa-thumbs-down" aria-hidden="true"></i> ${customUserPost.numberOfDislikes()} DisLikes</a>
                                <span class="card-link hover-icon" onclick="scrollToCommentBox()"><i class="fa fa-comment"></i> ${customUserPost.listCustomComments.size()} Bình luận</span>
                            </c:if>
                            <c:if test="${sessionScope.userAuthorization==null}">
                                <span onclick="messLogin()" class="card-link" style="color: black"><i class="fa fa-thumbs-up"></i> ${customUserPost.numberOfLikes()} Likes</span>
                                <span onclick="messLogin()" class="card-link" style="color: black"><i class="fa fa-thumbs-down" aria-hidden="true"></i> ${customUserPost.numberOfDislikes()} DisLikes</span>
                                <span onclick="messLogin()" class="card-link" style="color: black"><i class="fa fa-comment"></i> ${customUserPost.listCustomComments.size()} Comments</span>
                                <span class="btn btn-primary" onclick="messLogin()">Bình luận</span>
                            </c:if>
                        </div>
                        <!--- \\\\\\\Post-->
                        <form class="card gedf-card" action="CustomUserPost" method="post" id="formPostComment">
                            <input type="text" value="${customUserPost.userPost.userPostId}" hidden="">
                            <div class="card-header">
                                <ul class="nav nav-tabs card-header-tabs" id="myTab" role="tablist">
                                    <li class="nav-item">
                                        <a class="nav-link active" id="posts-tab" data-toggle="tab" href="#posts" role="tab"
                                           aria-controls="posts" aria-selected="true">Make a publication</a>
                                    </li>
                                    <li class="nav-item">
                                        <a class="nav-link" id="images-tab" data-toggle="tab" role="tab" aria-controls="images"
                                           aria-selected="false" href="#images">Images</a>
                                    </li>
                                </ul>
                            </div>
                            <div class="card-body">
                                <div class="tab-content" id="myTabContent">
                                    <div class="tab-pane fade show active" id="posts" role="tabpanel"
                                         aria-labelledby="posts-tab">
                                        <div class="form-group">
                                            <label class="sr-only" for="message">post</label>
                                            <textarea class="form-control" id="message" rows="3"
                                                      placeholder="What are you thinking?"></textarea>
                                        </div>

                                    </div>
                                    <div class="tab-pane fade" id="images" role="tabpanel" aria-labelledby="images-tab">
                                        <div class="form-group">
                                            <div class="custom-file">
                                                <input type="file" class="custom-file-input" id="customFile" name=>
                                                <label class="custom-file-label" for="customFile">Upload image</label>
                                            </div>
                                        </div>
                                        <div class="py-4"></div>
                                    </div>
                                </div>
                                <div class="btn-toolbar justify-content-between">
                                    <div class="btn-group">
                                        <button type="button" class="btn btn-primary">Submit</button>
                                    </div>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
    </body>
    <script>
        window.scroll({
            top: parseInt('${scrollY}')
//            behavior: 'smooth'
        });
        var x = 0;
        var y = 0;
        <c:if test="${sessionScope.userAuthorization!=null}">
        window.addEventListener('scroll', function () {
            x = window.scrollX;
            y = window.scrollY;
            document.getElementById("like").href = "/FitnessMedia/LikeUserPost?userPostId=${userPostId}&userMakerId=${sessionScope.userAuthorization.user.userId}&makeLike=true" + "&scrollX=" + x + "&scrollY=" + y;
            document.getElementById("dislike").href = "/FitnessMedia/LikeUserPost?userPostId=${userPostId}&userMakerId=${sessionScope.userAuthorization.user.userId}&makeLike=false" + "&scrollX=" + x + "&scrollY=" + y;
        });
        </c:if>
        function messLogin() {
            alert("Hãy đăng nhập để tiếp tục");
        }
        function scrollToCommentBox() {
            document.getElementById("formPostComment").scrollIntoView({behavior: 'smooth'});
        }
    </script>
</html>
