<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>회원가입</title>
</head>
<link rel=stylesheet href="<c:url value='../../css/community.css' />" type="text/css">
<body bgcolor="#89a6d6">
<div class="titleBox">
    <h2 class="title">회원가입</h2>
</div>
<div class="inputBox">
    <form name="registerForm" method="POST">
        <table class="table">
            <tr>
                <td>아이디</td>
                <td><input type="text" name="memberId" class="underline"></td>
            </tr>
            <tr>
                <td>비밀번호</td>
                <td><input type="password" name="password" class="underline"></td>
            </tr>
            <tr>
                <td>비밀번호 확인</td>
                <td><input type="password" name="passwordCheck" class="underline"></td>
            </tr>
            <tr>
                <td>이름</td>
                <td><input type="text" name="name" class="underline"></td>
            </tr>
            <tr>
                <td>생년월일</td>
                <td><input type="date" name="birth" class="dateBox"></td>
            </tr>
            <tr>
                <td>전화번호</td>
                <td><input type="text" name="phone1" class="phone1"> - <input type="text" name="phone2" class="phone2"> - <input type="text" name="phone3" class="phone3"></td>
            </tr>
            <tr>
                <td>사용자 유형</td>
                <td><span class="radioText"><input type="radio" name="type" value="parttimer">아르바이트생</span> <span><input type="radio" name="type" value="employer">사업주</span></td>
            </tr>
            <tr>
                <td colspan="2">
                    <input type="submit" value="회원가입" class="submitButton">
                </td>
            </tr>
        </table>
    </form>
</div>
</body>
</html>
