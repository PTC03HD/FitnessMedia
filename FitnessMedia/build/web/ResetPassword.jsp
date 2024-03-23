<%-- 
    Document   : ResetPassword
    Created on : Feb 14, 2024, 12:53:32 AM
    Author     : admin
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Reset Password</title>
    </head>
    <body>
        <form action="ResetPassword" method="post" id="resetForm">
            <input type="password" name="user_password" id="user_password" placeholder="New Password">
            <div id="error_user_password"></div>
            <input type="password" name="re_user_password" id="re_user_password" placeholder="Re Enter">
            <c:if test="${user != null}">
                <input type="password" name="user_id" id="user_id" hidden="" value="${user.getUserId()}">
            </c:if>
            <button type="button" onclick="checkAll()">Reset</button>
        </form>
        <div id="error_system" class="error">${error_system}</div>
        <c:if test="${user == null}">
            <form action="ErrorPage" method="get" id="ErrorPage" hidden></form>
        </c:if>

    </body>
    <c:if test="${user!=null}">
        <script>
            function checkByRegex(inputString, inputRegex) {
                const regex = new RegExp(inputRegex);
                return regex.test(inputString);
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
            function checkAll() {
                if (checkUserPassword()) {
                    document.getElementById("resetForm").submit();
                } else {

                }
            }
        </script>
    </c:if>

    <c:if test="${user == null}">
        <script>
            document.getElementById("ErrorPage").submit();
        </script>
    </c:if>
</html>
