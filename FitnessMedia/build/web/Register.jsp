<%-- 
    Document   : Register
    Created on : Feb 7, 2024, 8:01:37 PM
    Author     : admin
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Đăng kí tài khoản</title>
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
                background-image: url("img/sotafit.jpg");
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
                        <h1 class="title">Gym Gym Register</h1>
                        <hr />
                    </div>
                </div> 
                <div class="main-login main-center">
                    <form class="form-horizontal" action="Register" method="post" id="registerForm">
                        <div class="form-group">
                            <label for="user_name" class="cols-sm-2 control-label">Username</label>
                            <div class="cols-sm-10">
                                <div class="input-group">
                                    <span class="input-group-addon"><i class="fa fa-users fa" aria-hidden="true"></i></span>
                                    <input type="text" class="form-control" name="user_name" id="user_name"  placeholder="Enter your Username" value="${user_name}"/>
                                </div>
                                <div id="error_user_name" class="error">${error_user_name}</div>
                            </div>
                        </div>

                        <div class="form-group">
                            <label for="user_password" class="cols-sm-2 control-label">Password</label>
                            <div class="cols-sm-10">
                                <div class="input-group">
                                    <span class="input-group-addon"><i class="fa fa-lock fa-lg" aria-hidden="true"></i></span>
                                    <input type="password" class="form-control" type="password" name="user_password" id="user_password" value="${user_password}" placeholder="Password"/>
                                </div>
                                <div id="error_user_password" class="error"></div>
                            </div>
                        </div>

                        <div class="form-group">
                            <label for="re_user_password" class="cols-sm-2 control-label">Confirm Password</label>
                            <div class="cols-sm-10">
                                <div class="input-group">
                                    <span class="input-group-addon"><i class="fa fa-lock fa-lg" aria-hidden="true"></i></span>
                                    <input type="password" class="form-control" name="re_user_password" id="re_user_password" value="${user_password}" placeholder="Re User Password"/>
                                </div>
                                <div id="error_re_user_password" class="error"></div>
                            </div>
                        </div>

                        <div class="form-group">
                            <label for="user_email" class="cols-sm-2 control-label">Your Email</label>
                            <div class="cols-sm-10">
                                <div class="input-group">
                                    <span class="input-group-addon"><i class="fa fa-envelope fa" aria-hidden="true"></i></span>
                                    <input type="text" class="form-control" name="user_email" id="user_email" value="${user_email}" placeholder="User Email"/>
                                </div>
                                <div id="error_user_email" class="error">${error_user_email}</div>
                            </div>
                        </div>

                        <div class="form-group">
                            <label for="per_phone" class="cols-sm-2 control-label">Your Phone</label>
                            <div class="cols-sm-10">
                                <div class="input-group">
                                    <span class="input-group-addon"><i class="fa fa-envelope fa" aria-hidden="true"></i></span>
                                    <input type="text" class="form-control" name="per_phone" id="per_phone" value="${per_phone}" placeholder="Personal Phone"/>
                                </div>
                                <div id="error_per_phone" class="error">${error_per_phone}</div>
                            </div>
                        </div>

                        <div class="form-group">
                            <button type="button" class="btn btn-primary btn-lg btn-block login-button" onclick="checkAll()">Register</button>
                        </div>
                        <div class="login-register">
                            <a href="/FitnessMedia/Login">Login</a>
                        </div>
                        <div id="error_system" class="error">${error_system}</div>
                    </form>
                </div>
            </div>
        </div>
    </body>
    <script>
        var user_name = "";
        var user_password = "";
        var re_user_password = "";
        var user_email = "";
        var per_phone = "";
        function checkByRegex(inputString, inputRegex) {
            const regex = new RegExp(inputRegex);
            return regex.test(inputString);
        }
        function checkUserName() {
            user_name = document.getElementById("user_name").value;
            if (checkByRegex(user_name, /^\S{5,16}$/)) {
                return true;
            } else {
                document.getElementById("error_user_name").innerHTML = "Tên tài khoản từ 5-16 kí tự không chứa dấu cách";
                return false;
            }
        }
        function checkUserPassword() {
            user_password = document.getElementById("user_password").value;
            re_user_password = document.getElementById("re_user_password").value;
            if (user_password !== re_user_password) {
                document.getElementById("error_re_user_password").innerHTML = "Mật khẩu nhập lại không trùng khớp";
                return false;
            }
            if (checkByRegex(user_password, /^\S{8,16}$/)) {
                return true;
            } else {
                document.getElementById("error_user_password").innerHTML = "Mật khẩu chứa từ 8-16 kí tự không chứa dấu cách";
                return false;
            }
        }
        function checkUserEmail() {
            user_email = document.getElementById("user_email").value;
            if (checkByRegex(user_email, /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)*[a-zA-Z]{2,}))$/)) {
                return true;
            } else {
                document.getElementById("error_user_email").innerHTML = "Email của bạn không hợp lệ";
                return false;
            }
        }
        function checkPerPhone() {
            per_phone = document.getElementById("per_phone").value;
            if (checkByRegex(per_phone, /^[(]?[0-9]{3}[)]?[-.\s]?[0-9]{3}[-.\s]?[0-9]{4}$/)) {
                return true;
            } else {
                document.getElementById("error_per_phone").innerHTML = "Số điện thoại của bạn không hợp lệ";
                return false;
            }
        }
        function checkAll() {
            if (checkUserName() && checkUserPassword() && checkUserEmail() && checkPerPhone()) {
                document.getElementById("registerForm").submit();
            } else {
            }
        }
    </script>
</html>
