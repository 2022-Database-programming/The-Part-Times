package controller.member;

import java.sql.Date;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
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
    private String memberId;

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        if (MEMBER_SESSION_UTILS.hasLogined(request.getSession())) {
            memberId = MEMBER_SESSION_UTILS.getLoginUserId(request.getSession());
        }

        if(request.getServletPath().equals("/member/update")) {
            if (request.getMethod().equals("GET")) {
                LOG.debug("UpdateForm Request : {}", memberId);

                MemberDto member = MEMBER_MANAGER.findMember(memberId);
                request.setAttribute("member", member);

                return "/member/mypageForm.jsp";
            }

            if (request.getMethod().equals("POST")) {
                MemberUpdateDto updateUser = new MemberUpdateDto(
                        memberId,
                        request.getParameter("password"),
                        request.getParameter("name"),
                        Date.valueOf(request.getParameter("birth")),
                        request.getParameter("phoneNumber"),
                        request.getParameter("type"));

                LOG.debug("Update User : {}", updateUser);

                MEMBER_MANAGER.update(updateUser);

                return "redirect:/member/myPage.jsp";
            }
        }

        if (request.getServletPath().equals("/member/signin")) {    // 로그인
            if (request.getMethod().equals("POST")) {
                String userId = request.getParameter("memberId");
                String password = request.getParameter("password");

                try {
                    MEMBER_MANAGER.login(userId, password);

                    HttpSession session = request.getSession();
                    session.setAttribute(MemberSessionUtils.USER_SESSION_KEY, userId);

                    return "redirect:/member/main.jsp";
                } catch (Exception e) {
                    request.setAttribute("loginFailed", true);
                    request.setAttribute("exception", e);
                    return "/index.jsp";
                }
            }
        }

        if (request.getServletPath().equals("/member/signup")) {    // 회원가입
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

                    return "/index.jsp";
                } catch (ExistingMemberException e) {
                    request.setAttribute("registerFailed", true);
                    request.setAttribute("exception", e);
                    request.setAttribute("member", member);

                    return "/member/register.jsp";
                }
            }
        }

        if (request.getServletPath().equals("/member/signout")) {   // 로그아웃
            if (request.getMethod().equals("POST")) {
                HttpSession session = request.getSession();
                session.removeAttribute(MEMBER_SESSION_UTILS.USER_SESSION_KEY);
                session.invalidate();
            }
            return "/index.jsp";
        }

        return "/error/noRequestError.jsp";
    }
}