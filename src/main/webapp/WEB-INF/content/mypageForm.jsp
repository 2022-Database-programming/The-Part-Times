<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>마이페이지</title>
    <script>
        <!--유효성 검사-->
        function userCreate() {

            if (mypageForm.memberId.value == "") {
                alert('사용자 ID를 입력하십시오.');
                form.memberId.focus();
                return false;
            }

            if (mypageForm.newPassword.value == "") {
                alert("새 비밀번호를 입력하십시오.");
                form.password.focus();
                return false;
            }
            if (mypageForm.newPassword.value != mypageForm.newPasswordCheck.value) {
                alert("새 비밀번호가 일치하지 않습니다.");
                form.name.focus();
                return false;
            }

            if (mypageForm.phone1.value == "") {
                alert("전화번호 형식이 다 입력되지 않았습니다.");
                form.name.focus();
                return false;
            }

            if (mypageForm.phone2.value == "") {
                alert("전화번호 형식이 다 입력되지 않았습니다.");
                form.name.focus();
                return false;
            }

            if (mypageForm.phone3.value == "") {
                alert("전화번호 형식이 다 입력되지 않았습니다.");
                form.name.focus();
                return false;
            }

            form.submit();
        }
    </script>
</head>
<link rel=stylesheet href="<c:url value='/css/mypage.css' />" type="text/css">
<body>
<div class="inputBox">
    <form name="mypageForm" method="POST">
        <table class="table">
            <tr>
                <td>아이디</td>
                <td><input type="text" name="memberId" class="id_underline"></td>
            </tr>
            <tr>
                <td>현재 비밀번호</td>
                <td><input type="password" name="newPassword" class="underline"></td>
            </tr>
            <tr>
                <td>새 비밀번호</td>
                <td><input type="password" name="newPasswordCheck" class="underline"></td>
            </tr>
            <tr>
                <td>새 비밀번호 확인</td>
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
            </tr>
            <tr>
                <td><input type="submit" value="변경" class="updateButton" onclick="userCreate();"></td>
                <td><input type="reset" value="다시입력" class="resetButton"></td>
            </tr>
        </table>
    </form>
</div>
</body>
</html>