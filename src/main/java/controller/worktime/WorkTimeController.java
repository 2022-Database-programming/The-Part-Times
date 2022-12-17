package controller.worktime;

import controller.Controller;
import controller.member.MemberSessionUtils;
import model.dto.*;
import model.service.MemberManager;
import model.service.PartTimerWorkplaceManager;
import model.service.WorkTimeManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;

public class WorkTimeController implements Controller {
    private final MemberSessionUtils MEMBER_SESSION_UTILS = new MemberSessionUtils();
    private final WorkTimeManager WORK_TIME_MANAGER = WorkTimeManager.getInstance();
    private final MemberManager MEMBER_MANAGER = MemberManager.getInstance();
    private final PartTimerWorkplaceManager PART_TIMER_WORKPLACE_MANAGER = PartTimerWorkplaceManager.getInstance();
    private String memberId;

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        if (!MEMBER_SESSION_UTILS.hasLogined(request.getSession())) {
            return "redirect:/index.jsp";
        }

        memberId = MEMBER_SESSION_UTILS.getLoginUserId(request.getSession());
        System.out.println(memberId);
        MemberDto member = MEMBER_MANAGER.findMember(memberId);
        System.out.println(member.getId());

        if (request.getServletPath().equals("/worktime/today")) {
            if (request.getMethod().equals("GET")) {
                List<PartTimerWorkplaceDto> myWorkplaces = PART_TIMER_WORKPLACE_MANAGER.findAllPartTimerWorkplace(member.getId());
                request.setAttribute("myWorkplaces", myWorkplaces);

                return "/worktime/workTime.jsp";
            }

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

                MyTotalWorkTimeDto myTotalWorkTimeDto = WORK_TIME_MANAGER.findMyTotalWorkTimeByDateAndTotalWorkTimeId(today, partTimerWorkplaceId);

                System.out.println(myTotalWorkTimeDto);

                MyTodayWorkTimeDto myTodayWorkTimeDto = new MyTodayWorkTimeDto(
                        myTotalWorkTimeDto.getId(), timeSettingDto, today, minimumWage, createdAt, updatedAt
                );

                WORK_TIME_MANAGER.create(myTodayWorkTimeDto, myTotalWorkTimeDto, partTimerWorkplaceId);

                request.setAttribute("todayWorkTime", myTodayWorkTimeDto);

                return "/worktime/worktime.jsp";
            }
        }

        if (request.getServletPath().equals("/worktime/day")) {
            if (request.getMethod().equals("GET")) {
                System.out.println(request.getParameter("today"));
                Date date = Date.valueOf(request.getParameter("today"));

                // partTimerWorkplaceId List
                List<Integer> partTimerWorkplaceIds = WORK_TIME_MANAGER.findAllPartTimerWorkplaceIdByMemberId(member.getId());
                System.out.println("Controller-------------------" + partTimerWorkplaceIds);

                // myTotalWorkTimeId List
                List<Integer> myTotalWorkTimeIds = WORK_TIME_MANAGER.findAllTotalWorkTimeIdByPartTimerWorkplaceIdAndWorkDate(date, partTimerWorkplaceIds);

                // myTodayWorkTime Map
                HashMap<Integer, List<MyTodayWorkTimeDto>> myTodayWorkTimes = WORK_TIME_MANAGER.findAllMyTodayWorkTimeByDateAndTotalWorkTime(date, myTotalWorkTimeIds);

                System.out.println(myTodayWorkTimes);

                request.setAttribute("myTodayWorkTime", myTodayWorkTimes);

                return "/worktime/main.jsp";
            }
        }

        return "redirect:/error/noRequestError.jsp";
    }
}
