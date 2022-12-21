<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta charset="UTF-8">

    <title>로그인</title>

    <link href="<c:url value='/css/reset.css' />" rel="stylesheet">
    <link href="<c:url value='/css/common.css' />" rel="stylesheet">
    <link href="<c:url value='/css/loginForm.css' />" rel="stylesheet">

    <script src="https://kit.fontawesome.com/0c8dd76eca.js" crossorigin="anonymous"></script>

    <script>
        function login() {
            if (form.memberId.value == "") {
                alert("사용자 ID를 입력하십시오.");
                form.memberId.focus();
                return false;
            }
            if (form.password.value == "") {
                alert("비밀번호를 입력하십시오.");
                form.password.focus();
                return false;
            }
            form.submit();
        }
    </script>

</head>
<body>
<div id="container">
    <img id="loginLogo" src="<c:url value='/images/login_logo.png' />"/>
    <form name="form" method="post" method="POST" action="<c:url value='/member/signin' />">
        <div id="loginBox">
            <div id="loginInfoContainer">

                <div id="loginTitle">LOGIN</div>
                <div>
                    <c:if test="${loginFailed}">
                        <span style="color: red;"><c:out value="${exception.getMessage()}"/></span>
                    </c:if>
                </div>

                <div class="inputBox">
                    <i class="fas fa-user loginIcon fa-2x"></i>
                    <input type='text' name="memberId" class="loginInput" placeholder="ID" value="">
                </div>

                <div class="inputBox">
                    <i class="fas fa-lock loginIcon fa-2x"></i>
                    <input type='text' name="password" class="loginInput" placeholder="PW" value="">
                </div>

                <div id="btnBox">
                    <button class="btn" type='button' onClick="login()">로그인</button>
                    <button class="btn">회원가입</button>
                </div>
            </div>
        </div>
    </form>
</div>
</body>
</html>