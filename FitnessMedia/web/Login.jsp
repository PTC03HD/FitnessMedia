<%-- 
    Document   : Login
    Created on : Feb 7, 2024, 12:22:35 PM
    Author     : admin
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login</title>
        <link href="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
        <!--        <script src="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/js/bootstrap.min.js"></script>-->
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.6.1/css/font-awesome.min.css">

        <!-- Google Fonts -->
        <link href='https://fonts.googleapis.com/css?family=Passion+One' rel='stylesheet' type='text/css'>
        <link href='https://fonts.googleapis.com/css?family=Oxygen' rel='stylesheet' type='text/css'>
        <style>
            body, html{
                height: 100%;
                background-repeat: no-repeat;
                background-color: #d3d3d3;
                font-family: 'Oxygen', sans-serif;
            }
            
            body{
                background-image: url("img/contact-form-bg.jpg");
                background-position: center;
                background-size: cover;
            }

            .main{
                margin-top: 70px;
            }

            h1.title {
                font-size: 50px;
                font-family: 'Passion One', cursive;
                font-weight: 400;
            }

            hr{
                width: 10%;
                color: #fff;
            }

            .form-group{
                margin-bottom: 15px;
            }

            label{
                margin-bottom: 15px;
            }

            input,
            input::-webkit-input-placeholder {
                font-size: 11px;
                padding-top: 3px;
            }

            .main-login{
                background-color: #fff;
                /* shadows and rounded borders */
                -moz-border-radius: 2px;
                -webkit-border-radius: 2px;
                border-radius: 2px;
                -moz-box-shadow: 0px 2px 2px rgba(0, 0, 0, 0.3);
                -webkit-box-shadow: 0px 2px 2px rgba(0, 0, 0, 0.3);
                box-shadow: 0px 2px 2px rgba(0, 0, 0, 0.3);

            }

            .main-center{
                margin-top: 30px;
                margin: 0 auto;
                max-width: 330px;
                padding: 40px 40px;

            }

            .login-button{
                margin-top: 5px;
            }

            .login-register{
                font-size: 11px;
                text-align: center;
            }
            .error{
                color: red;
                font-size: 11px;
                font-weight: bold;
            }
        </style>
    </head>
    <body>
        <div class="container">
            <div class="row main">
                <div class="panel-heading">
                    <div class="panel-title text-center">
                        <h1 class="title" style='color:white'>Gym Gym Login</h1>
                        <hr />
                    </div>
                </div> 
                <div class="main-login main-center">
                    <form class="form-horizontal" action="Login" method="post" id="loginForm">
                        <div class="form-group">
                            <label for="user_name" class="cols-sm-2 control-label">Username</label>
                            <div class="cols-sm-10">
                                <div class="input-group">
                                    <span class="input-group-addon"><i class="fa fa-users fa" aria-hidden="true"></i></span>
                                    <input type="text" class="form-control" name="user_name" id="user_name"  placeholder="Enter your Username" value="${user_name}"/>
                                </div>
                            </div>
                        </div>

                        <div class="form-group">
                            <label for="user_password" class="cols-sm-2 control-label">Password</label>
                            <div class="cols-sm-10">
                                <div class="input-group">
                                    <span class="input-group-addon"><i class="fa fa-lock fa-lg" aria-hidden="true"></i></span>
                                    <input type="password" class="form-control" type="password" name="user_password" id="user_password" value="${user_password}" placeholder="Password"/>
                                </div>
                            </div>
                        </div>
                        <c:if test="${notice!=null}">
                            <div class="error">${notice}</div>
                        </c:if>
                        <div class="form-group">
                            <button type="submit" class="btn btn-primary btn-lg btn-block login-button" onclick="checkAll()">Login</button>
                        </div>
                        <div class="login-register">
                            <a href="/FitnessMedia/Register">Register</a>
                        </div>
                        <div id="error_system" class="error">${error_system}</div>
                    </form>
                </div>
            </div>
        </div>
        <c:if test="${sessionScope.userAuthorization !=null}">
            <form action="HomePage" method="get" id="returnHomePage" hidden></form>
        </c:if>

    </body>
    <c:if test="${sessionScope.userAuthorization !=null}">
        <script>
            document.getElementById("returnHomePage").submit();
        </script>
    </c:if>
</html>

