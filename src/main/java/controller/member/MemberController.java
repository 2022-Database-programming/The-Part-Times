package controller.member;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.dto.MyTotalWorkTimeDto;
import model.service.PartTimerWorkplaceManager;
import model.service.WorkTimeManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import controller.Controller;
import model.dto.MemberDto;
import model.dto.MemberUpdateDto;
import model.exception.ExistingMemberException;
import model.service.MemberManager;

public class MemberController implements Controller {
    private final Logger LOG = LoggerFactory.getLogger(MemberController.class);
    private final MemberSessionUtils MEMBER_SESSION_UTILS = new MemberSessionUtils();
    private final MemberManager MEMBER_MANAGER = MemberManager.getInstance();
    private final WorkTimeManager WORK_TIME_MANAGER = WorkTimeManager.getInstance();
    private final PartTimerWorkplaceManager PART_TIMER_WORKPLACE_MANAGER = PartTimerWorkplaceManager.getInstance();
    private final int START_INDEX = 0;
    private final int END_INDEX = 7;

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        if (request.getServletPath().equals("/member/mypage")) {
            if (MEMBER_SESSION_UTILS.hasLogined(request.getSession())) {
                String memberId = MEMBER_SESSION_UTILS.getLoginUserId(request.getSession());
                MemberDto member = MEMBER_MANAGER.findMember(memberId);
                request.setAttribute("member", member);

                return "/member/myPage.jsp";
            }

            return "redirect:/";
        }

        if (request.getServletPath().equals("/member/update")) {
            if (request.getMethod().equals("GET")) {
                if (MEMBER_SESSION_UTILS.hasLogined(request.getSession())) {
                    String memberId = MEMBER_SESSION_UTILS.getLoginUserId(request.getSession());
                    MemberDto member = MEMBER_MANAGER.findMember(memberId);
                    request.setAttribute("member", member);

                    return "/member/myPageForm.jsp";
                }
                return "redirect:/";
            }

            if (request.getMethod().equals("POST")) {
                if (MEMBER_SESSION_UTILS.hasLogined(request.getSession())) {
                    String memberId = MEMBER_SESSION_UTILS.getLoginUserId(request.getSession());

                    MemberUpdateDto updateUser = new MemberUpdateDto(
                            memberId,
                            request.getParameter("password"),
                            request.getParameter("name"),
                            Date.valueOf(request.getParameter("birth")),
                            request.getParameter("phoneNumber"),
                            request.getParameter("type"));

                    LOG.debug("Update User : {}", updateUser);
                    MEMBER_MANAGER.update(updateUser);

                    return "redirect:/member/mypage";
                }

                return "redirect:/";
            }
        }

        if (request.getServletPath().equals("/member/signin")) {
            if (request.getMethod().equals("POST")) {
                String userId = request.getParameter("memberId");
                String password = request.getParameter("password");

                try {
                    MEMBER_MANAGER.login(userId, password);

                    HttpSession session = request.getSession();
                    session.setAttribute(MemberSessionUtils.USER_SESSION_KEY, userId);

                    return "redirect:/member/main?page=main&id="+userId;
                } catch (Exception e) {
                    e.printStackTrace();
                    request.setAttribute("loginFailed", true);
                    request.setAttribute("exception", e);
                    return "/index.jsp";
                }
            }
        }

        if (request.getServletPath().equals("/member/signup")) {
            if (request.getMethod().equals("POST")) {
                MemberDto member = new MemberDto(
                        request.getParameter("memberId"),
                        request.getParameter("password"),
                        request.getParameter("name"),
                        Date.valueOf(request.getParameter("birth")),
                        request.getParameter("phone_number"),
                        request.getParameter("type"));

                LOG.debug("Create User : {}", member);

                try {
                    MEMBER_MANAGER.create(member);

                    return "redirect:/";
                } catch (ExistingMemberException e) {
                    request.setAttribute("registerFailed", true);
                    request.setAttribute("exception", e);
                    request.setAttribute("member", member);

                    return "/member/register.jsp";
                }
            }
        }

        if (request.getServletPath().equals("/member/signout")) {
            if (request.getMethod().equals("POST")) {
                HttpSession session = request.getSession();
                session.removeAttribute(MEMBER_SESSION_UTILS.USER_SESSION_KEY);
                session.invalidate();
            }

            return "redirect:/";
        }

        if (request.getServletPath().equals("/member/main")) {
            if (request.getParameter("page").equals("main")) {
                LocalDate today = LocalDate.now();
                String month = String.valueOf(today).substring(START_INDEX, END_INDEX);

                String memberId = MEMBER_SESSION_UTILS.getLoginUserId(request.getSession());
                MemberDto member = MEMBER_MANAGER.findMember(memberId);
                request.setAttribute("member", member);

                List<Integer> partTimerWorkplaceIds = WORK_TIME_MANAGER.findAllPartTimerWorkplaceIdsByMemberId(member.getId());
                List<MyTotalWorkTimeDto> myTotalWorkTimes = WORK_TIME_MANAGER.findAllTotalWorkTimesByPartTimerWorkplaceIdAndWorkDate(month, partTimerWorkplaceIds);
                // myTotal에 저장된 workplace id값을 기반으로 직장 이름을 찾아온다.
                List<String> workplaceNames = PART_TIMER_WORKPLACE_MANAGER.findThisMonthWorkplaceNamesByPartTimerWorkplace(myTotalWorkTimes, member.getId());

                request.setAttribute("myTotalWorkTimes", myTotalWorkTimes);
                request.setAttribute("workplaceNames", workplaceNames);

                System.out.println(myTotalWorkTimes);
                System.out.println(workplaceNames);

                return "/content/mainMenu.jsp?page=main";
            }

            if (request.getParameter("page").equals("record")) {
                return "redirect:/worktime/today";
            }
        }

        return "redirect:/error/noRequestError";
    }
}