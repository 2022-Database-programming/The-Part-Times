<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link rel=stylesheet href="<c:url value='/css/salary-calculation.css' />" type="text/css">
<div class="calculation">
	<h2 class="time_title">근무 시간 계산기</h2>
    <div class="inputValue">
    	<form name="inputForm" method="POST">
			<div class="inputTime">근무 시간
				<input type="text" name="h_workTime" class="h_inputBox">&nbsp;시간
				<input type="text" name="m_workTime" class="m_inputBox">&nbsp;분
			</div>
			<div class="inputSalary">시급
				<input type="text" name="salary" value="9160"class="salary_inputBox">
			</div>
			<div class="check_week_salary">주휴수당 여부
				<input type="checkbox" name="week_salary" class="weekSalary_inputBox">
			</div>
		</form>
	</div>
	<div class="line">
		<hr></hr>
	</div>
	<div class="calResult">
		<div class="calResult_title">이번 달 예상 급여</div>
		<div class="salaryResult">365803</div>
	</div>
	<div>
		<input type="submit" value="계산" class="calButton" onClick="alert=('사용자');">
	</div>
</div>