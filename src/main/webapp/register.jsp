<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>회원가입</title>
    <style>
        @font-face {
            font-family: 'GmarketSansMedium';
            src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_2001@1.1/GmarketSansMedium.woff') format('woff');
            font-weight: normal;
            font-style: normal;
        }

        @font-face {
            font-family: 'GmarketSansBold';
            src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_2001@1.1/GmarketSansBold.woff') format('woff');
            font-weight: normal;
            font-style: normal;
        }

        * {
            font-family: 'GmarketSansMedium';
        }

        .title {
            color: #89a6d6;
            text-align: center;
            font-family: 'GmarketSansBold';
            font-size: 45px;
        }

        .titleBox {
            background-color: white;
            width: 80%;
            height: 15%;
            margin: 3% auto 0;
            border-radius: 15px;
            font-size: 20px;
            padding-top: 0.5%;
        }

        .inputBox {
            background-color: white;
            width: 80%;
            height: 70%;
            margin: 3% auto 0;
            border-radius: 20px;
            font-size: 20px;
        }

        .submitButton {
            width: 60%;
            height: 70%;
            background-color: #89a6d6;
            border-width: 0px;
            color: white;
            margin-top: 2%;
        }

        .phone1 {
            width: 14%;
            padding: 1%;
        }

        .phone2 {
            width: 16%;
            padding: 1%;
        }

        .phone3 {
            width: 16%;
            padding: 1%;
        }

        .underline {
            border-left-width: 0px;
            border-right-width: 0px;
            border-top-width: 0px;
            border-bottom-width: 1px;
            width: 50%;
            height: 50%;
            padding: 1%;
        }

        .table {
            margin-left: 25%;
            width: 80%;
            height: 90%;
            padding: 0;
        }

        .dateBox {
            width: 50%;
            height: 50%;
            padding: 1%;
        }

        .radioText {
            margin-right: 2%;
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
