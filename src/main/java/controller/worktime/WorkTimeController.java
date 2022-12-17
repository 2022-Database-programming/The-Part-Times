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
    private final MemberSessionUtils memberSessionUtils = new MemberSessionUtils();
    private final WorkTimeManager workTimeManager = WorkTimeManager.getInstance();

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

                MyTotalWorkTimeDto myTotalWorkTimeDto = workTimeManager.findMyTotalWorkTImeByDateAndWorkplace(today, partTimerWorkplaceId);

                // 데이터를 저장하기 위한 DTO 객체 생성
                MyTodayWorkTimeDto myTodayWorkTimeDto = new MyTodayWorkTimeDto(
                        myTotalWorkTimeDto.getId(), timeSettingDto.getWorkStartTime(), timeSettingDto.getWorkFinishTime(), timeSettingDto.getBreakStartTime(), timeSettingDto.getBreakFinishTime(),
                        timeSettingDto.getTotalWorkTime(), timeSettingDto.getTotalBreakTime(), today, minimumWage, createdAt, updatedAt
                );

                workTimeManager.create(myTodayWorkTimeDto, myTotalWorkTimeDto, partTimerWorkplaceId);

                return "/worktime/worktimeResult.jsp";

            }
        }

//        if (request.getServletPath().equals("/worktime/date")) {
//            if (request.getMethod().equals("GET")) {
//                Date date = Date.valueOf(request.getParameter("date"));
//                MemberDto memberDto = memberDao.findMember(memberId);
//
//                // 사용자의 workplace를 죄다 가져온다.  (사용자의 근무지 id를 가져오기 위함)
//                List<PartTimerWorkplaceDto> workplaces = partTimerWorkplaceDao.findAllWorkplace(memberDto.getId());
//
//                // 사용자가 일한 곳을 기반으로 이번달 일한 날짜 total을 모두 찾아준다.
//                List<MyTotalWorkTimeDto> myTotalWorkTimeDtos = new ArrayList<>();
//                for (int i = 0; i < workplaces.size(); i++) {
//                    myTotalWorkTimeDtos.add(myTotalWorkTimeDao.findMyTotalWorkTImeByDateAndWorkplace(date, workplaces.get(i).getId()));
//                }
//
//                // 해당 토탈 아이디를 참고해 오늘 일한 시간을 가져온다.
//                List<MyTodayWorkTimeDto> myTodayWorkTimeDtos = new ArrayList<>();
//                for (int i = 0; i < myTotalWorkTimeDtos.size(); i++) {
//                    myTodayWorkTimeDtos.add(myTodayWorkTimeDao.findMyWorkTimeByDateAndTotalWorkTimeId(date, myTotalWorkTimeDtos.get(i).getId()));
//                    System.out.println(myTodayWorkTimeDtos.get(i));
//                }
//            }
//        }
        return "redirect:/error/noRequestError.jsp";
    }
}
