<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <link rel="stylesheet" href="<c:url value='/css/main.css' />">
</head>
<body>
<!-- partial:index.partial.html -->
<!-- Git Repo: https://github.com/Russian60/flex-calendar -->
<div ng-app="app" class="calendarBox">
    <div ng-controller="MainController">
        <div class="wrapp">
            <flex-calendar options="options" events="events"></flex-calendar>
        </div>
    </div>
    <div class="workInfoBox">
        블라블라
    </div>
</div>
<!-- partial -->
<script src='https://ajax.googleapis.com/ajax/libs/angularjs/1.4.0/angular.min.js'></script>
<script src='https://cdnjs.cloudflare.com/ajax/libs/angular-translate/2.7.2/angular-translate.js'></script>
<script  src="<c:url value='/js/main.js' />"></script>
</body>
</html>
