<%@ page language="java" contentType="text/html; charset=EUC-KR"
         pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="EUC-KR">
    <title>index.jsp</title>
    <style>
        @font-face {
            font-family: 'GmarketSansLight';
            src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_2001@1.1/GmarketSansMedium.woff') format('woff');
            font-weight: normal;
            font-style: normal;
        }
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

        *{
            font-family: 'GmarketSansMedium';
        }

        body{
            background-color: #89a6d6;
            margin: 0;
        }

        .title{
            color: #ffffff;
        }

        .t1{
            position: relative;
            top: 140px;
            left: 300px;
            color: #ffffff;
            font-bold: 300px;
            font-size: 40px;
        }
        .t2{
            position: relative;
            color: #ffffff;
            top: 150px;
            left: 300px;
            font-size: 40px;
        }

        .t3{
            position: relative;
            color: #ffffff;
            top: 140px;
            left: 300px;
            font-size: 40px;
        }

        .t4{

        }

        .t5{

        }

        h3{
            position: relative;
            left: 100px;
            top: 50px;
            font-family:'GmarketSansBold';
            font-size: 55px;
            color: #89a6d6;
            text-shadow: 1px 1px 1px gray;
        }

        .login_box{
            position: relative;
            left: 750px;
            bottom: 200px;
            color: white;
            background-color: white;
            width: 400px;
            height: 450px;
            border-radius: 40px;
        }

        .id {
            position: relative;
            top: 10px;
            left: 150px;
            outline: 0px;
            border-left-width: 0px;
            border-right-width: 0px;
            border-top-width: 0px;
            border-bottom-width: 1px;
            width: 40%;
            height: 10%;
        }

        .pw{
            position: relative;
            top: 60px;
            right: 20px;
            outline: 0px;
            border-left-width: 0px;
            border-right-width: 0px;
            border-top-width: 0px;
            border-bottom-width: 1px;
            width: 40%;
            height: 10%;
        }

        .btn {
            position: relative;
            border: none;
            width: 250px;
            height: 38px;
            left: 80px;
            border-radius: 10px;
            box-shadow: 1px 1px 1px 1px gray;
            background-color: #89a6d6;
        }

        .login_btn {
            position: relative;
            top: 80px;
            color: white;
            font-size: 20px;
        }

        .signup_btn{
            position: relative;
            top: 90px;
            color: white;
            font-size: 20px;
        }

        .find_id{
            position: relative;
            top: 140px;
            width: 100px;
            height: 30px;
            right: 160px;
            bottom: 150px;
            color: black;
            background-color: white;
            border: none;
            font-size: 20px;
        }

        .find_pw{
            position: relative;
            top: 105px;
            width: 100px;
            height: 30px;
            left: 230px;
            color: black;
            background-color: white;
            border: none;
            font-size: 20px;
        }
    </style>
</head>
<body>
<form action="#" method="post">
    <h2 class='t1'>The</h2>
    <h2 class="t2">Part</h2>
    <h2 class="t3">Times</h2>

    <div class="login_box">
        <h3>LOGIN</h3>
        <input type='text' class="id" type='submit'>
        <input type='text' class="pw" type='submit'>
        <button class="btn login_btn" type='submit'>로그인</button>
        <button class="btn signup_btn" type='submit'>회원가입</button>
        <button class="find_id" type='submit'>ID찾기</button>
        <button class="find_pw" type='submit'>PW찾기</button>
    </div>
</form>
</body>
</html>