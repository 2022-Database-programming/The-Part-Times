<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>회원가입</title>
	<script>
	<!--유효성 검사-->
		function userCreate() {
			
			if (registerForm.memberId.value == "") {
				alert('사용자 ID를 입력하십시오.');
				form.memberId.focus();
				return false;
			} 
			
			if(registerForm.idDuplication.value !="idCheck"){
				
				alert("아이디 중복체크를 해주세요.");
				return false;
			}
			
			if (registerForm.password.value == "") {
				alert("비밀번호를 입력하십시오.");
				form.password.focus();
				return false;
			}
			if (registerForm.password.value != registerForm.passwordCheck.value) {
				alert("비밀번호가 일치하지 않습니다.");
				form.name.focus();
				return false;
			}
			if (registerForm.name.value == "") {
				alert("이름을 입력하십시오.");
				form.name.focus();
				return false;
			}
			
			if (registerForm.phone1.value == "") {
				alert("전화번호 형식이 다 입력되지 않았습니다.");
				form.name.focus();
				return false;
			}
			
			if (registerForm.phone2.value == "") {
				alert("전화번호 형식이 다 입력되지 않았습니다.");
				form.name.focus();
				return false;
			}
			
			if (registerForm.phone3.value == "") {
				alert("전화번호 형식이 다 입력되지 않았습니다.");
				form.name.focus();
				return false;
			}
			
			if (registerForm.type.value == "") {
				alert("사용자 유형을 선택해주세요.");
				form.name.focus();
				return false;
			}
			
			form.submit();
		}
	</script>
</head>
<link rel=stylesheet href="<c:url value='/css/register.css' />" type="text/css">
<body bgcolor="#89a6d6">
<div class="titleBox">
    <h2 class="title">회원가입</h2>
</div>
<div class="inputBox">
    <form name="registerForm" method="POST">
        <table class="table">
            <tr>
                <td>아이디</td>
                <td><input type="text" name="memberId" class="id_underline">
                <input type="button" value="중복체크" class="idCheckButton"></td>
                <!-- 아이디 중복 체크 여부 -->
                <input type="hidden" name="isDuplication" value="idUncheck"/>
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
                    <input type="submit" value="회원가입" class="submitButton" onclick="userCreate();">
                </td>
            </tr>
        </table>
    </form>
</div>
</body>
</html>
