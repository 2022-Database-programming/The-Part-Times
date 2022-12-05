package controller.member;

import java.sql.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import controller.Controller;

import model.dto.MemberDto;
import model.dto.MemberUpdateDto;
import model.exception.ExistingMemberException;
import model.exception.MemberNotFoundException;
import model.service.MemberManager;

public class MemberController implements Controller {
    private static final Logger log = LoggerFactory.getLogger(MemberController.class);
    private static final MemberSessionUtils memberSessionUtils = new MemberSessionUtils();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        /* 사용자가 로그아웃하지 않았으면 세션에서 아이디를 꺼내온다. */
        if (request.getSession() == null) {
            return "로그아웃된 유저입니다.";
        }

        String memberId = memberSessionUtils.getLoginUserId(request.getSession());
        System.out.println(memberId);

        if(request.getServletPath().equals("/member/update")) {    // 회원정보 관련 컨트롤러
            if (request.getMethod().equals("GET")) {
                log.debug("UpdateForm Request : {}", memberId);

                MemberManager manager = MemberManager.getInstance();
                MemberDto member = manager.findMember(memberId);   // 수정하려는 사용자 정보 검색
                request.setAttribute("member", member);   // DTO 값을 통째로 넣어줌

                return "/member/mypageForm.jsp";   // 사용자 보기 화면으로 이동 (forwarding)
            }

            if (request.getMethod().equals("POST")) {   // 마이페이지 수정 요청
                MemberUpdateDto updateUser = new MemberUpdateDto(
                        memberId,
                        request.getParameter("password"),
                        request.getParameter("name"),
                        Date.valueOf(request.getParameter("birth")),
                        request.getParameter("phoneNumber"),
                        request.getParameter("type"));

                log.debug("Update User : {}", updateUser);

                MemberManager manager = MemberManager.getInstance();
                manager.update(updateUser);

                return "redirect:/member/myPage.jsp";
            }
        }

        if (request.getServletPath().equals("/member/signin")) {    // 로그인
            if (request.getMethod().equals("POST")) {
                String userId = request.getParameter("memberId");
                String password = request.getParameter("password");

                try {
                    // 모델에 로그인 처리를 위임
                    MemberManager manager = MemberManager.getInstance();
                    manager.login(userId, password);

                    // 세션에 사용자 이이디 저장
                    HttpSession session = request.getSession();
                    session.setAttribute(MemberSessionUtils.USER_SESSION_KEY, userId);

                    System.out.println(userId);
                    System.out.println(password);
                    return "redirect:/member/main.jsp";
                } catch (Exception e) {
                    /* UserNotFoundException이나 PasswordMismatchException 발생 시
                     * 다시 login form을 사용자에게 전송하고 오류 메세지도 출력
                     */
                    request.setAttribute("loginFailed", true);
                    request.setAttribute("exception", e);
                    return "/member/loginForm.jsp";
                }
            }
        }

        if (request.getServletPath().equals("/member/signup")) {    // 회원가입
            if (request.getMethod().equals("GET")) {
                return "/member/register.jsp";
            }

            if (request.getMethod().equals("POST")) {
                MemberDto member = new MemberDto(
                        request.getParameter("memberId"),
                        request.getParameter("password"),
                        request.getParameter("name"),
                        Date.valueOf(request.getParameter("birth")),
                        request.getParameter("phoneNumber"),
                        request.getParameter("type"));

                log.debug("Create User : {}", member);

                try {
                    MemberManager manager = MemberManager.getInstance();
                    manager.create(member);

                    return "redirect:index.jsp";   // 성공 시 로그인 화면으로 이동하기
                } catch (ExistingMemberException e) {   // 예외 발생 시 회원가입 form으로 forwarding
                    request.setAttribute("registerFailed", true);
                    request.setAttribute("exception", e);
                    request.setAttribute("member", member);

                    return "registerForm.jsp";
                }
            }
        }

        if (request.getServletPath().equals("/member/signout")) {   // 로그아웃
            if (request.getMethod().equals("POST")) {
                HttpSession session = request.getSession();
                session.removeAttribute(memberSessionUtils.USER_SESSION_KEY);
                session.invalidate();
            }
            return "index.jsp";
        }

        return "/error/error.jsp";
    }
}