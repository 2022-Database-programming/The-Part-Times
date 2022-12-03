<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <style>
        .title {
            color: #89a6d6;
            text-align: center;
        }

        .titleBox {
            background-color: white;
            width: 80%;
            height: 15%;
            margin: 3% auto 0;
            border-radius: 15px;
            font-size: 20px;
            padding-top: 1%;
        }

        .inputBox {
            background-color: white;
            width: 80%;
            height: 60%;
            margin: 3% auto 0;
            border-radius: 20px;
            font-size: 20px;
        }

        .submitButton {
            width: 50%;
            height: 10%;
        }

        .phone1 {
            width: 15%;
        }

        .phone2 {
            width: 20%;
        }

        .phone3 {
            width: 20%;
        }

        .underline {
            border-left-width: 0px;
            border-right-width: 0px;
            border-top-width: 0px;
            border-bottom-width: 1px;
        }

        .table {
            margin-left: 30%;
            width: 80%;
            height: 90%;
        }
    </style>
</head>
<body bgcolor="#89a6d6">
    <div class="titleBox">
        <h2 class="title">회원가입</h2>
    </div>
    <div class="inputBox">
        <form>
        <table class="table">
            <tr>
                <td>아이디</td>
                <td><input type="text" name="userId" class="underline"></td>
            </tr>
            <tr>
                <td>비밀번호</td>
                <td><input type="password" name="userPassword" class="underline"></td>
            </tr>
            <tr>
                <td>비밀번호 확인</td>
                <td><input type="password" name="userPasswordCheck" class="underline"></td>
            </tr>
            <tr>
                <td>생년월일</td>
                <td><input type="date" name="birth"></td>
            </tr>
            <tr>
                <td>전화번호</td>
                <td><input type="text" name="phone1" class="phone1"> - <input type="text" name="phone2" class="phone2"> - <input type="text" name="phone3" class="phone3"></td>
            </tr>
            <tr>
                <td>사용자 유형</td>
                <td><input type="radio" name="type" value="parttimer">아르바이트생 <input type="radio" name="type" value="employer">사업주</td>
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
