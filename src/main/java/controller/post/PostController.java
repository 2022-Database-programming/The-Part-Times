package controller.post;

import controller.Controller;
import controller.member.MemberController;
import controller.member.MemberSessionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class PostController implements Controller {
    private static final Logger log = LoggerFactory.getLogger(MemberController.class);
    private static final MemberSessionUtils memberSessionUtils = new MemberSessionUtils();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        /* 사용자가 로그아웃하지 않았으면 세션에서 아이디를 꺼내온다. */
        if (request.getSession() == null) {
            return "로그아웃된 유저입니다.";
        }

        String memberId = memberSessionUtils.getLoginUserId(request.getSession());

        return null;
    }
}
