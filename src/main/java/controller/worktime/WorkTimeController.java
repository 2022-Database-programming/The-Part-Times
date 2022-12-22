package controller.worktime;

import com.fasterxml.jackson.databind.ObjectMapper;
import controller.Controller;
import controller.member.MemberSessionUtils;
import model.dto.*;
import model.service.MemberManager;
import model.service.PartTimerWorkplaceManager;
import model.service.WorkTimeManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
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
    private final int START_INDEX = 0;
    private final int END_INDEX = 7;

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        if (!MEMBER_SESSION_UTILS.hasLogined(request.getSession())) {
            return "redirect:/";
        }

        memberId = MEMBER_SESSION_UTILS.getLoginUserId(request.getSession());
        MemberDto member = MEMBER_MANAGER.findMember(memberId);

        if (request.getServletPath().equals("/worktime/today")) {
            if (request.getMethod().equals("GET")) {
                List<WorkplaceDto> myWorkplaces = PART_TIMER_WORKPLACE_MANAGER.findAllWorkplaceNamesByPartTimerWorkplace(member.getId());
                System.out.println(myWorkplaces);
                request.setAttribute("myWorkplaces", myWorkplaces);

                return "/worktime/worktime.jsp";
            }

            if (request.getMethod().equals("POST")) {
                // 13시46분
                int partTimerWorkplaceId = Integer.parseInt(request.getParameter("workplaceId"));
                int minimumWage = Integer.parseInt(request.getParameter("minimumWage"));
                int workStartHour = Integer.parseInt(request.getParameter("workStartTime").substring(0, 2));
                int workStartMinute = Integer.parseInt(request.getParameter("workStartTime").substring(3, 5));
                int workFinishHour = Integer.parseInt(request.getParameter("workFinishTime").substring(0, 2));
                int workFinishMinute = Integer.parseInt(request.getParameter("workFinishTime").substring(3, 5));
                int breakStartHour = Integer.parseInt(request.getParameter("breakStartTime").substring(0, 2));
                int breakStartMinute = Integer.parseInt(request.getParameter("breakStartTime").substring(3, 5));
                int breakFinishHour = Integer.parseInt(request.getParameter("breakFinishTime").substring(0, 2));
                int breakFinishMinute = Integer.parseInt(request.getParameter("breakFinishTime").substring(3, 5));
                TimeSettingDto timeSettingDto = new TimeSettingDto(workStartHour, workStartMinute, workFinishHour, workFinishMinute, breakStartHour, breakStartMinute, breakFinishHour, breakFinishMinute);

                System.out.println(timeSettingDto);
                Date today = Date.valueOf(LocalDate.now());
                String month = String.valueOf(today).substring(START_INDEX, END_INDEX);
                Timestamp createdAt = new Timestamp(System.currentTimeMillis());
                Timestamp updatedAt = new Timestamp(System.currentTimeMillis());

                MyTotalWorkTimeDto myTotalWorkTimeDto = WORK_TIME_MANAGER.findMyTotalWorkTimeByDateAndTotalWorkTimeId(month, partTimerWorkplaceId);

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
                Date today = Date.valueOf(request.getParameter("today"));
                String month = String.valueOf(today).substring(START_INDEX, END_INDEX);

                // partTimerWorkplaceId List
                List<Integer> partTimerWorkplaceIds = WORK_TIME_MANAGER.findAllPartTimerWorkplaceIdsByMemberId(member.getId());
                // myTotalWorkTimeId List
                List<Integer> myTotalWorkTimeIds = WORK_TIME_MANAGER.findAllTotalWorkTimeIdByPartTimerWorkplaceIdAndWorkDate(month, partTimerWorkplaceIds);
                // myTodayWorkTime Map
                HashMap<Integer, List<MyTodayWorkTimeDto>> myTodayWorkTimes = WORK_TIME_MANAGER.findAllMyTodayWorkTimeByDateAndTotalWorkTime(today, myTotalWorkTimeIds);

                ObjectMapper mapper = new ObjectMapper();
                String jsonString = mapper.writeValueAsString(myTodayWorkTimes);
                System.out.println(myTodayWorkTimes);

                response.setContentType("application/json;charset=utf-8");
                PrintWriter out = response.getWriter();

                out.println(jsonString);

                // request.setAttribute("myTodayWorkTime", myTodayWorkTimes);

                return null;
            }
        }

        return "redirect:/error/noRequestError.jsp";
    }
}
