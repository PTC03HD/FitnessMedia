<%-- 
    Document   : Forgot
    Created on : Feb 13, 2024, 9:57:31 PM
    Author     : admin
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Forgot password?</title>
        <style>
            .error{
                color: red;
                font-size: 12px;
                font-weight: bold;
            }
        </style>
    </head>
    <body>
        <form action="Forgot" method="post" id="forgotForm">
            <input type="text" name="user_name" id="user_name" value="${user_name}" placeholder="User Name">
            <div id="error_user_name" class="error">${error_user_name}</div>
            <input type="text" name="user_email" id="user_email" value="${user_email}" placeholder="User Email">
            <div id="error_user_email" class="error">${error_user_email}</div>
            <input type="text" name="per_phone" id="per_phone" value="${per_phone}" placeholder="Personal Phone">
            <div id="error_per_phone" class="error">${error_per_phone}</div>
            <button type="button" onclick="checkAll()">Submit</button>            
        </form>


    </body>
    <script>
        var user_name = "";
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
            if (checkUserName() && checkUserEmail() && checkPerPhone()) {
                document.getElementById("forgotForm").submit();
            } else {

            }
        }
    </script>
</html>
