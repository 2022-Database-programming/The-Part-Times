<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>The Part Times</title>
    <script src="http://code.jquery.com/jquery-3.6.1.min.js"></script>
    <script src="https://kit.fontawesome.com/0c8dd76eca.js" crossorigin="anonymous"></script>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">

    <script>
        // top navbar click시, 메뉴 css 처리
        $(document).ready(function () {
            const tabList = document.querySelectorAll('#menuList li');
            var para = document.location.href.split("/member/main?page=")[1];

            if(para == "main") {
                tabList[0].style.fontFamily = 'GmarketSansMedium';
            } else if(para == "record") {
                tabList[1].style.fontFamily = 'GmarketSansMedium';
            } else if(para == "salary") {
                tabList[2].style.fontFamily = 'GmarketSansMedium';
            } else if(para == "community") {
                tabList[3].style.fontFamily = 'GmarketSansMedium';
            } else {
                tabList[4].style.fontFamily = 'GmarketSansMedium';
            }
        });
    </script>

    <!-- salary-calculation -->
    <script type="text/javascript">

        function calculation(){

            if (inputForm.h_workTime.value == "" && inputForm.m_workTime.value == "") {
                alert('근무시간을 입력하십시오.');
                return false;
            }

            if (inputForm.salary.value == "") {
                alert('시급을 입력하십시오.');
                return false;
            }

            if (inputForm.day.value == "") {
                alert('근무 일수를 입력하십시오.');
                return false;
            }

            var h_time = document.getElementById('h_workTime').value;
            var m_time = document.getElementById('m_workTime').value;
            var sal = document.getElementById('salary').value;
            var date = document.getElementById('day').value;
            var time_sal = h_time * sal + Math.round(m_time * 152.666667);
            var result_salary = 0;
            var check = document.getElementById('week_salary').checked;

            /*주휴수당 x*/
            if(check == false){
                result_salary = time_sal * date;
                document.getElementById("result").innerText = result_salary;
            }


            else{
                result_salary = time_sal * date + (4 * time_sal);
                document.getElementById("result").innerText = result_salary;
            }

        }
    </script>

    <script type="text/javascript">
        let timer_sec;
        let timer_min;
        let timer_hour;
        function printTime() {

            var clock = document.getElementById("startT");            // 출력할 장소 선택
            var now = new Date();                                                  // 현재시간

            if(now.getMinutes() < 10)
                var nowTime = now.getHours() + " : 0" + now.getMinutes();

            else
                var nowTime = now.getHours() + " : " + now.getMinutes();
            clock.innerHTML = nowTime;           // 현재시간을 출력
        }

        function end_printTime() {

            var clock = document.getElementById("endT");            // 출력할 장소 선택
            var now = new Date();                                                  // 현재시간

            if(now.getMinutes() < 10)
                var nowTime = now.getHours() + " : 0" + now.getMinutes();

            else
                var nowTime = now.getHours() + " : " + now.getMinutes();
            clock.innerHTML = nowTime;           // 현재시간을 출력
        }

        function workTime() {


            let timer = 0;
            let click_check = 0;
            const btnElement = document.getElementById('work_start_btn');

            if(btnElement.value == " 시작 ")
                alert('총 근무 시간 저장 버튼을 클릭해주세요.');

            else if(btnElement.value == "시작" && click_check == 0){

                btnElement.value = "중지";
                printTime();

                if(timer > 0){
                    return;
                }

                var sec = parseInt(document.getElementById("sec").innerText);
                var min = parseInt(document.getElementById("min").innerText);
                var hour = parseInt(document.getElementById("hour").innerText);

                //start seconds
                timer_sec = setInterval(function(){

                    sec++;
                    if(sec == 60) {
                        sec = "00";
                    } else if(sec < 10){
                        sec = "0" + sec;
                    }
                    document.getElementById("sec").innerText = sec;
                }, 1000);

                //start minutes
                timer_min = setInterval(function(){
                    min++;

                    if(min == 60) {
                        min = 0;
                    } else if(min < 10){
                        min = "0" + min;
                    }

                    document.getElementById("min").innerText = min;
                }, 60000);

                //start hours
                timer_hour = setInterval(function(){
                    //console.log(hour);
                    hour++;

                    if(hour < 10){
                        hour = "0" + hour;
                    }

                    document.getElementById("hour").innerText = hour;

                }, 3600000);

                timer++;
            }


            else if(btnElement.value == "중지" && click_check == 0){
                btnElement.value = " 시작 ";
                end_printTime();
                stop();
                click_check = 1;
            }

        }

        function stop(){
            clearInterval(timer_sec);
            clearInterval(timer_min);
            clearInterval(timer_hour);

            timer--;
            if(timer < 0)
                timer = 0;
        }

        function breakTime(){
            const btnElement = document.getElementById('break_start_btn');

            if(btnElement.value == "시작"){
                btnElement.value = "중지";
            }

            else
                btnElement.value = "시작";
        }

        function time_store(){
            document.getElementById("sec").innerText = "00";
            document.getElementById("min").innerText = "00";
            document.getElementById("hour").innerText = "00";
            document.getElementById("startT").innerText = "00 : 00";
            document.getElementById("endT").innerText = "00 : 00";
        }

    </script>

    <link href="<c:url value='/css/reset.css' />" rel="stylesheet">
    <link href="<c:url value='/css/common.css' />" rel="stylesheet">

    <style>
        html, body {
            min-width: 1200px;
            margin: 0;
            width: 100%;
            height: 100%;
        }

        a, a:hover {
            color: white;
            text-decoration: none;
        }

        #container {
            width: 100%;
            height: 100%;
        }

        #menuLogo {
            width: 150px;
            height: 130px;
            margin-right: 30px;
        }

        #menuBox {
            width: 100%;
            height: 130px;
            background-color: #89a6d6;
            display: flex;
            justify-content : center;
            font-family: 'GmarketSansLight';
        }

        #menuList {
            align-self: flex-end;
            margin-bottom: 20px;
        }

        #menuList > li {
            display: inline;
            margin-right: 20px;
        }

        #profileBox {
            width: 270px;
            height: 60px;
        }

        #side_title_box {
            display: inline-block;
            width: 150px;
            flex-wrap: wrap;
        }

        #sideBox {
            background-color: #c1d5f6;
            float: left;
            width: 20%;
            height: 100%;
            min-width: 225px;
            font-family: 'GmarketSansLight';
        }

        #main_container {
            display: flex;
            width: 100%;
            height: calc(100% - 130px);
        }

        #side_title {
            font-family: 'GmarketSansMedium';
            margin-left: 20px;
            margin-top: 20px;
        }

        #side_add_btn {
            font-size: 12px;
            float: right;
            margin-right: 10px;
        }

        .contents {
            width:100%;
            height: 100%;
        }

        .disable {
            display: none;
        }

        /** content.html **/

        #content_container {
            width: 100%;
            height: 100%;
        }
    </style>

</head>
<body>
<div id="container">
    <div id="menuBox">
        <img id="menuLogo" src="<c:url value='/images/menu_logo.png' />">
        <ul id="menuList">
            <li id="menu_main" class="menu" onclick="location.href='/member/main?page=main'"><a class="top_navbar_menu" href="/member/main?page=main">Main</a></li>
            <li id="menu_record_work_time" class="menu" onclick="location.href='/member/main?page=record'"><a class="top_navbar_menu" href="/member/main?page=record">Record Work Time</a></li>
            <li id="menu_salary_calculation" class="menu" onclick="location.href='/member/main?page=salary'"><a class="top_navbar_menu" href="/member/main?page=salary">Salary Calculation</a></li>
            <li id="menu_community_board" class="menu" onclick="location.href='/member/main?page=community'"><a class="top_navbar_menu" href="/member/main?page=community">Community Board</a></li>
            <li id="menu_my_page" class="menu" onclick="location.href='/member/main?page=mypage'"><a class="top_navbar_menu" href="/member/main?page=mypage">My Page</a></li>
        </ul>

        <%--        <div id="profileBox">--%>
        <%--            <i class="fas fa-user"></i>--%>
        <%--        </div>--%>
    </div>
    <div id="main_container">
        <div id="sideBox">
            <div id="side_title_box">
                <div id="side_title">나의 주요 근무지</div>
                <div id="side_add_btn">+ 주요 근무지 추가하기</div>
            </div>
        </div>
        <div id="content_container">
            <%--            <div class="contents">--%>
            <%--                <div data-include-path="<c:url value='/content/main.jsp' />"></div>--%>
            <%--            </div>--%>
            <%--            <div class="contents disable">--%>
            <%--                <div data-include-path="<c:url value='/content/record-work-time.jsp' />"></div>--%>
            <%--            </div>--%>
            <%--            <div class="contents disable">--%>
            <%--                <div data-include-path="<c:url value='/content/salary-calculation.jsp' />"></div>--%>
            <%--            </div>--%>
            <%--            <div class="contents disable">--%>
            <%--                <div data-include-path="<c:url value='/content/community-board.jsp' />"></div>--%>
            <%--            </div>--%>
            <%--            <div class="contents disable">--%>
            <%--                <div data-include-path="<c:url value='/content/mypageForm.jsp' />"></div>--%>
            <%--            </div>--%>

            <%if(request.getParameter("page").equals("main")) { %>
            <jsp:include page="./main.jsp" />
            <% System.out.println("main.jsp"); } else if(request.getParameter("page").equals("record")) {%>
            <jsp:include page="./record-work-time.jsp" />
            <% System.out.println("record.jsp"); } else if(request.getParameter("page").equals("salary")) { %>
            <jsp:include page="./salary-calculation.jsp" />
            <%} else if(request.getParameter("page").equals("community")){%>
            <jsp:include page="./community-board.jsp" />
            <% }else { %>
            <jsp:include page="./mypageForm.jsp" />
            <%} %>
        </div>
    </div>
</div>
</body>
</html>
