<%--
  Created by IntelliJ IDEA.
  User: sylee
  Date: 2022-12-08
  Time: 오후 5:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<style>
    /** reset.css - 추후 파일 처리**/
    html, body, div, span, applet, object, iframe,
    h1, h2, h3, h4, h5, h6, p, blockquote, pre,
    a, abbr, acronym, address, big, cite, code,
    del, dfn, em, img, ins, kbd, q, s, samp,
    small, strike, strong, sub, sup, tt, var,
    b, u, i, center,
    dl, dt, dd, ol, ul, li,
    fieldset, form, label, legend,
    table, caption, tbody, tfoot, thead, tr, th, td,
    article, aside, canvas, details, embed,
    figure, figcaption, footer, header, hgroup,
    menu, nav, output, ruby, section, summary,
    time, mark, audio, video {
        margin: 0;
        padding: 0;
        border: 0;
        font-size: 100%;
        vertical-align: baseline;
    }
    /* HTML5 display-role reset for older browsers */
    article, aside, details, figcaption, figure,
    footer, header, hgroup, menu, nav, section {
        display: block;
    }

    body {
        line-height: 1;
    }
    ol, ul {
        list-style: none;
    }
    blockquote, q {
        quotes: none;
    }
    blockquote:before, blockquote:after,
    q:before, q:after {
        content: '';
        content: none;
    }
    table {
        border-collapse: collapse;
        border-spacing: 0;
    }

    /** common.css **/
    @font-face {
        font-family: 'GmarketSansLight';
        src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_2001@1.1/GmarketSansLight.woff') format('woff');
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
</style>

<style>
    /** record-work-time.css **/

    /*메인화면*/
    .container {
        width: 100%;
        display: flex;
        justify-content: center;
    }

    .title_m {
        font-family: 'GmarketSansMedium';
    }

    #work_time_container {
        width: 100%;
        height: 100%;
    }
    #wt_title_box {

        font-size: 24px;
    }

    #wt_title {
        margin-top: 30px;
    }
    /*근무지 선택*/
    #select_workplace {
        width: 50%;
        height: 30px;
        max-width: 380px;
        border: 1.5px solid;
        border-color: #89a6d6;
        border-radius: 12px;
        font-family: 'GmarketSansLight';
    }

    #select_container {
        margin-top: 30px;
        margin-bottom: 30px;
    }

    #select_workplace {
        -webkit-appearance: none;
        -moz-appearance: none;
        appearance: none;
        padding-left: 15px;
        padding-right: 6px;
    }

    /*시간 측정*/
    .time_box {
        min-width: 600px;
        width: 65%;
        height: 70px;
        border-radius: 10px;
        background-color: #e9f1ff;
        top: 65%;
        margin-top: 20px;
    }

    .work_box{
        top: 47%;
    }

    .break_box{

    }

    /*
           span{
                   position: absolute;
                   font-family: 'GmarketSansMedium';
                   color: #536684;
                   margin-top: 38px;
                   margin-left: 18px;
           }
    */

    /*시간 흐름 박스*/
    .total_time_bar {
        display: inline;
        position: absolute;
        width: 65%;
        left: 15%;
        height: 27px;
        margin-top: 30px;
        border-radius: 10px;
        background-color: #c1d0ea;
    }

    /*출근 시간, 휴게 시간 버튼*/
    #work_start_btn, #break_start_btn{
        position: absolute;
        top: 27%;
        left: 85%;
        height: 45%;
        width: 10%;
        border-radius: 7px;
        border: solid 1px #fff;
        background-color: #c1d5f6;
        font-family: 'GmarketSansMedium';
    }

    /*시간 저장 버튼*/
    #time_store_btn{
        position: absolute;
        top: 87%;
        left: 52%;
        width: 17%;
        height: 6%;
        border-radius: 8px;
        border: none;
        background-color: #8ca9d8;
        font-family: 'GmarketSansLight';
        font-size: 17px;
    }

    /*모달창*/
    #my_modal {
        display: none;
        position: absolute;
        width: 60%;
        height: 60%;
        top: 25%;
        left: 25%;
        background-color: #fefefe;
        border: 1px solid #888;
        border-radius: 10px;
    }

    .black_bg{
        display: none;
        position: absolute;
        content: "";
        width: 100%;
        height: 100%;
        background-color:rgba(0, 0,0, 0.5);
        top:0;
        left: 0;
        z-index: 1;
    }

    #modal_title{
        position: absolute;
        left: 35%;
        font-family: 'GmarketSansMedium';
        font-size: 22px;
        color: black;
    }
    #modal_close_btn {
        position: absolute;
        top: 80%;
        left: 35%;
        width: 30%;
        height: 10%;
        background-color: #c1d0ea;
        border: none;
        border-radius: 8px;
        font-family: 'GmarketSansMedium';
        font-size: 18px;
    }
</style>

<div id="work_time_container">
    <div id="wt_title_box" class="container">
        <span id="wt_title" class="title_m">근무지를 선택하세요</span>
    </div>

    <div id="select_container" class="container">
        <select name="work" id="select_workplace">
            <option value="알바1">동덕여자대학교 근로생</option>
            <option value="알바2">알바2</option>
            <option value="알바3">알바3</option>
            <option value="알바4">알바4</option>
        </select>
    </div>

    <!-- 출근 시간 측정 박스 -->
    <div class="container">
        <div class="time_box">
            <span>출퇴근 시간</span>
            <div class="total_time_bar"></div>
            <button id="work_start_btn" type='submit'>시작</button>
        </div>
    </div>

    <!-- 휴게 시간 측정 박스 -->
    <div class="container">
        <div class="time_box">
            <span>휴게 시간</span>
            <div class="total_time_bar"></div>
            <button id="break_start_btn" type='submit'>시작</button>
        </div>

    </div>
    <div class="container">
        <button type='button' id="time_store_btn">총 근무 시간 저장하기</button>
    </div>

    <!-- 모달창 -->
    <div id="my_modal">
        <span id="modal_title">오늘의 총 근로시간</span>
        <button id="modal_close_btn">확인</button>
    </div>
</div>
