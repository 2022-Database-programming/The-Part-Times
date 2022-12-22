<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link rel=stylesheet href="<c:url value='/css/record-work-time.css' />" type="text/css">
<div id="work_time">
    <h4 class="workPlace_title">근무지를 선택하세요</h4>
    <select name="work" class="selectWorkPlace">
        <option value="알바1">동덕여자대학교 근로생</option>
        <option value="알바2">알바2</option>
        <option value="알바3">알바3</option>
        <option value="알바4">알바4</option>
    </select>
	
    <!-- 출근 시간 측정 박스 -->
    <div class="work_time_box">
        <span class="workT_title">출퇴근 시간</span>
        <div class="start">시작 시간</div>
        <span id="startT">00 : 00</span>
        <div class="working">일한 시간</div>
        <span id="hour">00</span>
        <span id="dot"> : </span>
        <span id="min">00</span>
        <span id="dot"> : </span>
        <span id="sec">00</span>
        <div class="end">종료 시간</div>
        <span id="endT">00 : 00</span>
        <input type='submit' id="work_start_btn" value="시작" onclick="workTime();">
    </div>

    <!-- 휴게 시간 측정 박스 -->
    <div class="break_time_box">
        <span class="breakT_title">휴게 시간</span>
        <div class="start">시작 시간</div>
        <span id="startB">00 : 00</span>
        <div class="breaking">휴게 시간</div>
        <span id="b_hour">00</span>
        <span id="dot"> : </span>
        <span id="b_min">00</span>
        <span id="dot"> : </span>
        <span id="b_sec">00</span>
        <div class="end">종료 시간</div>
        <span id="endB">00 : 00</span>
        <input type='submit' id="break_start_btn" value="시작" onclick="breakTime();">
    </div>
    
   	<button type='button' id="time_store_btn" onclick="time_store()">총 근무 시간 저장하기</button>
</div>

