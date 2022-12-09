package controller.worktime;

import controller.Controller;
import controller.member.MemberSessionUtils;
import model.service.WorkTimeManager;
import model.dto.MyTotalWorkTimeDto;
import model.dto.TimeSettingDto;
import model.dto.MyTodayWorkTimeDto;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDate;

public class WorkTimeController implements Controller {
    private final MemberSessionUtils MEMBER_SESSION_UTILS = new MemberSessionUtils();
    private final WorkTimeManager WORK_TIME_MANAGER = WorkTimeManager.getInstance();
    private String memberId;

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        if (MEMBER_SESSION_UTILS.hasLogined(request.getSession())) {
            memberId = MEMBER_SESSION_UTILS.getLoginUserId(request.getSession());
        }

        if (request.getServletPath().equals("/worktime/today")) {
            if (request.getMethod().equals("POST")) {
                int partTimerWorkplaceId = Integer.parseInt(request.getParameter("workplaceId"));
                int minimumWage = Integer.parseInt(request.getParameter("minimumWage"));
                int workStartHour = Integer.parseInt(request.getParameter("workStartHour"));
                int workStartMinute = Integer.parseInt(request.getParameter("workStartMinute"));
                int workFinishHour = Integer.parseInt(request.getParameter("workFinishHour"));
                int workFinishMinute = Integer.parseInt(request.getParameter("workFinishMinute"));
                int breakStartHour = Integer.parseInt(request.getParameter("breakStartHour"));
                int breakStartMinute = Integer.parseInt(request.getParameter("breakStartMinute"));
                int breakFinishHour = Integer.parseInt(request.getParameter("breakFinishHour"));
                int breakFinishMinute = Integer.parseInt(request.getParameter("breakFinishMinute"));
                TimeSettingDto timeSettingDto = new TimeSettingDto(workStartHour, workStartMinute, workFinishHour, workFinishMinute, breakStartHour, breakStartMinute, breakFinishHour, breakFinishMinute);
                Date today = Date.valueOf(LocalDate.now());
                Timestamp createdAt = new Timestamp(System.currentTimeMillis());
                Timestamp updatedAt = new Timestamp(System.currentTimeMillis());

                MyTotalWorkTimeDto myTotalWorkTimeDto = WORK_TIME_MANAGER.findMyWorkTimeByDateAndPartTimerWorkplaceId(today, partTimerWorkplaceId);

                MyTodayWorkTimeDto myTodayWorkTimeDto = new MyTodayWorkTimeDto(
                        myTotalWorkTimeDto.getId(), timeSettingDto, today, minimumWage, createdAt, updatedAt
                );

                WORK_TIME_MANAGER.create(myTodayWorkTimeDto, myTotalWorkTimeDto, partTimerWorkplaceId);

                return "/worktime/worktimeResult.jsp";
            }
        }

        return "redirect:/error/noRequestError.jsp";
    }
}
