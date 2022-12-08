package controller.worktime;

import controller.Controller;
import controller.member.MemberController;
import controller.member.MemberSessionUtils;
import model.service.WorkTimeManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import test.MyTodayWorkTimeTest;
import model.dao.MyTotalWorkTimeDao;
import model.dao.MyTodayWorkTimeDao;
import model.dto.MyTotalWorkTimeDto;
import model.dto.TimeSettingDto;
import model.dto.MyTodayWorkTimeDto;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDate;

public class WorkTimeController implements Controller {
    private final Logger log = LoggerFactory.getLogger(MemberController.class);
    private final MemberSessionUtils memberSessionUtils = new MemberSessionUtils();
    private final WorkTimeManager workTimeManager = WorkTimeManager.getInstance();
    private MyTotalWorkTimeDao myTotalWorkTimeDao = new MyTotalWorkTimeDao();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        /* 사용자가 로그아웃하지 않았으면 세션에서 아이디를 꺼내온다. */
        if (request.getSession() == null) {
            return "/error/logoutError.jsp";
        }

        String memberId = memberSessionUtils.getLoginUserId(request.getSession());
        System.out.println(memberId);

        if (request.getServletPath().equals("/worktime/today")) {    // 오늘 일한 것 저장
            if (request.getMethod().equals("POST")) {
                try {
                    // 올바른 값을 입력했는지에 대한 예외처리 필요, 프론트앤드에서 해당 항목을 선택하면 해당 근무지의 id 값을 넘겨준다.
                    int partTimerWorkplaceId = Integer.parseInt(request.getParameter("workplaceId"));
                    // 최저 시급 -> 올바른 값을 입력했는지에 대한 예외처리 필요
                    int minimumWage = Integer.parseInt(request.getParameter("minimumWage"));
                    // 오늘 시간 저장
                    int workStartHour = Integer.parseInt(request.getParameter("workStartHour"));
                    int workStartMinute = Integer.parseInt(request.getParameter("workStartMinute"));
                    int workFinishHour = Integer.parseInt(request.getParameter("workFinishHour"));
                    int workFinishMinute = Integer.parseInt(request.getParameter("workFinishMinute"));
                    int breakStartHour = Integer.parseInt(request.getParameter("breakStartHour"));
                    int breakStartMinute = Integer.parseInt(request.getParameter("breakStartMinute"));
                    int breakFinishHour = Integer.parseInt(request.getParameter("breakFinishHour"));
                    int breakFinishMinute = Integer.parseInt(request.getParameter("breakFinishMinute"));
                    TimeSettingDto timeSettingDto = new TimeSettingDto(workStartHour, workStartMinute, workFinishHour, workFinishMinute, breakStartHour, breakStartMinute, breakFinishHour, breakFinishMinute);

                    // 오늘 일한 날
                    Date today = Date.valueOf(LocalDate.now());
                    // 데이터 트래킹 필드
                    Timestamp createdAt = new Timestamp(System.currentTimeMillis());
                    Timestamp updatedAt = new Timestamp(System.currentTimeMillis());

                    // 오늘 일한 날짜와 근무지를 가지고 월별 총 근무 기록 조회
                    MyTotalWorkTimeDto myTotalWorkTimeDto = new MyTotalWorkTimeDao().findMyTotalWorkTImeByDateAndWorkplace(today, partTimerWorkplaceId);
                    // 없으면 하나 만들어준다. 0시간 일했으며 급여는 0원
                    if (myTotalWorkTimeDto == null) {
                        myTotalWorkTimeDto = new MyTotalWorkTimeDto(partTimerWorkplaceId, new Time(0, 0, 0),
                                today, 0, createdAt, updatedAt);
                        myTotalWorkTimeDao.insert(myTotalWorkTimeDto);
                    }

                    // insert한 값으로 다시 한 번 찾아준다. id 값을 받아오기 위함
                    // TODO: 향후 메소드 분리 예정
                    myTotalWorkTimeDto = myTotalWorkTimeDao.findMyTotalWorkTImeByDateAndWorkplace(today, partTimerWorkplaceId);

                    // 데이터를 저장하기 위한 DTO 객체 생성
                    MyTodayWorkTimeDto myTodayWorkTimeDto = new MyTodayWorkTimeDto(
                            myTotalWorkTimeDto.getId(), timeSettingDto.getWorkStartTime(), timeSettingDto.getWorkFinishTime(), timeSettingDto.getBreakStartTime(), timeSettingDto.getBreakFinishTime(),
                            timeSettingDto.getTotalWorkTime(), timeSettingDto.getTotalBreakTime(), today, minimumWage, createdAt, updatedAt
                    );
                    workTimeManager.create(myTodayWorkTimeDto, myTotalWorkTimeDto, partTimerWorkplaceId);

                    return "/worktime/worktimeResult.jsp";
                } catch (NumberFormatException exception) {
                    return "/error/IllegalArgumentExceptionPage.jsp";
                }

            }
        }

        if (request.getServletPath().equals("/worktime/date")) {
            if (request.getMethod().equals("GET")) {

            }
        }
        return "/error/noRequestError.jsp";
    }
}