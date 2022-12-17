<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>The Part Times</title>
    <script src="http://code.jquery.com/jquery-3.6.1.min.js"></script>
    <script src="https://kit.fontawesome.com/0c8dd76eca.js" crossorigin="anonymous"></script>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">

    <script>
        // top navbar click시, css 처리+화면 전환
        $(document).ready(function () {
            const tabList = document.querySelectorAll('#menuList li');
            const contentPageList = document.querySelectorAll('.contents')

            for(var i = 0; i < tabList.length; i++){
                tabList[i].querySelector('a').addEventListener('click', function(e){
                    e.preventDefault();
                    for(var j = 0; j < tabList.length; j++){
                        tabList[j].classList.remove('active');
                        contentPageList[j].classList.add('disable');
                    }
                    this.parentNode.classList.add('active');
                    const idx = Array.from(document.querySelectorAll('a')).indexOf(e.target);
                    console.log(idx);
                    contentPageList[idx].classList.remove('disable');

                });
            }
        });
    </script>

    <script>  // html 불러오는 함수
    window.addEventListener('load', function() {
        var allElements = document.getElementsByTagName('*');
        Array.prototype.forEach.call(allElements, function(el) {
            var includePath = el.dataset.includePath;
            if (includePath) {
                var xhttp = new XMLHttpRequest();
                xhttp.onreadystatechange = function () {
                    if (this.readyState == 4 && this.status == 200) {
                        el.outerHTML = this.responseText;
                    }
                };
                xhttp.open('GET', includePath, true);
                xhttp.send();
            }
        });
    });
    </script>
    <link href="<c:url value='/css/reset.css' />" rel="stylesheet">
    <link href="<c:url value='/css/common.css' />" rel="stylesheet">

    <style>
        html, body {
            font-family: 'GmarketSansLight';
            width: 100%;
            height: 100%;
            min-width: 1012px;
            margin: 0;
        }

        a, a:hover {
            color: white;
            text-decoration: none;
        }

        li.active {
            /*            text-decoration: underline;*/
            font-family: 'GmarketSansMedium';
        }

        #container {
            width: 100%;
            height: 100%;
        }

        #menuList {
            position: absolute;
            bottom: 15px;

        }
        #menuList > li {
            display: inline;
            margin-right: 20px;
        }

        #menuBox {
            position: relative;
            width: 100%;
            height: 100px;
            background-color: #89a6d6;
            text-align: center;
            float: top;
            display: flex;
            justify-content : center;
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

        }

        #main_container {
            display: flex;
            width: 100%;
            height: calc(100% - 100px);
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
            position: relative;
            width: 100%;
            height: 100%;
        }


    </style>



</head>
<body>
<div id="container">
    <div id="menuBox">
        <ul id="menuList">
            <li id="menu_main" class="menu active"><a class="top_navbar_menu" href="#">Main</a></li>
            <li id="menu_record_work_time" class="menu"><a class="top_navbar_menu" href="#">Record Work Time</a></li>
            <li id="menu_salary_calculation" class="menu"><a class="top_navbar_menu" href="#">Salary Calculation</a></li>
            <li id="menu_community_board" class="menu"><a class="top_navbar_menu" href="#">Community Board</a></li>
        </ul>

        <div>
            <i class="fas fa-user"></i>
        </div>
    </div>
    <div id="main_container">
        <div id="sideBox">
            <div id="side_title_box">
                <div id="side_title">나의 주요 근무지</div>
                <div id="side_add_btn">+ 주요 근무지 추가하기</div>
            </div>
        </div>
        <div id="content_container">
            <div class="contents">
                <div data-include-path="<c:url value='/content/main.jsp' />"></div>
            </div>
            <div class="contents disable">
                <div data-include-path="<c:url value='/content/record-work-time.jsp' />"></div>
            </div>
            <div class="contents disable">
                <div data-include-path="<c:url value='/content/salary-calculation.jsp' />"></div>
            </div>
            <div class="contents disable">
                <div data-include-path="<c:url value='/content/community-board.jsp' />"></div>
            </div>

        </div>
    </div>
</div>
</body>
</html>

