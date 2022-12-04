package controller.worktime;

import controller.Controller;
import controller.member.MemberSessionUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class WorkTimeController implements Controller {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        MemberSessionUtils memberSessionUtils = new MemberSessionUtils();
        String sessionForGetId = memberSessionUtils.getLoginUserId(request.getSession());

        if (request.getServletPath().equals("/worktime/today")) {
            if (request.getMethod().equals("POST")) {

            }
        }


        
        return "error.jsp";
    }
}
