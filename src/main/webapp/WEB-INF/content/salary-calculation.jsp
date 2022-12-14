<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link rel=stylesheet href="<c:url value='/css/salary-calculation.css' />" type="text/css">
<div class="calculation">
    <div class="time_title">근무 시간 계산기</div>
    <div class="inputValue">
        <form name="inputForm" method="POST">
            <div class="inputTime">하루 근무 시간
                <input type="text" id="h_workTime" class="h_inputBox">&nbsp;시간
                <input type="text" id="m_workTime" class="m_inputBox">&nbsp;분
            </div>
            <div class="inputSalary">시급
                <input type="text" id="salary" value="9160" onFocus="value='';" class="salary_inputBox">
            </div>
            <div class="inputDay">한 달 근무 일수
                <input type="text" id="day" class="day_inputBox">
            </div>
            <div class="check_week_salary">주휴수당 여부
                <input type="checkbox" id="week_salary" class="weekSalary_inputBox">
            </div>
        </form>
    </div>
    <div class="line">
        <hr></hr>
    </div>
    <div class="calResult">    
        <div class="calResult_title">이번 달 예상 급여</div>
        <div id='result' class="salaryResult"></div>
    </div>
    <div>
        <input type="submit" value="계산" class="calButton" onClick="calculation();">
    </div>
</div>

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