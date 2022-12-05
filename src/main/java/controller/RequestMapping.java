package controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import controller.member.MemberController;
import controller.worktime.WorkTimeController;

import java.util.HashMap;
import java.util.Map;

public class RequestMapping {
    private static final Logger logger = LoggerFactory.getLogger(DispatcherServlet.class);

    // 각 요청 uri에 대한 controller 객체를 저장할 HashMap 생성
    private Map<String, Controller> mappings = new HashMap<String, Controller>();

    public void initMapping() {
    	System.out.println("mapping 받음");
        // 각 uri에 대응되는 controller 객체를 생성 및 저장
        mappings.put("/", new ForwardController("/index.jsp"));
        mappings.put("/member/signupForm", new ForwardController("/member/register.jsp"));
        mappings.put("/member/signinForm", new ForwardController("/member/loginForm.jsp"));
        mappings.put("/member/signin", new MemberController());
        mappings.put("/member/signup", new MemberController());
        mappings.put("/member/signout", new MemberController());
        mappings.put("/member/update", new MemberController());
        mappings.put("/member/updateForm", new ForwardController("/member/mypage.jsp"));
        mappings.put("/worktime/today", new WorkTimeController());
        mappings.put("/worktime/todayForm", new ForwardController("/worktime/workTimeForm.jsp"));
        
        // 회원 가입 폼 요청과 가입 요청 처리 병합 (폼에 커뮤니티 선택 메뉴 추가를 위함)
////      mappings.put("/user/register/form", new ForwardController("/user/registerForm.jsp"));
////      mappings.put("/user/register", new RegisterUserController());
//        mappings.put("/user/register", new RegisterUserController());
//
//        // 사용자 정보 수정 폼 요청과 수정 요청 처리 병합
////      mappings.put("/user/update/form", new UpdateUserFormController());
////      mappings.put("/user/update", new UpdateUserController());
//        mappings.put("/user/update", new UpdateUserController());
//
//        mappings.put("/user/delete", new DeleteUserController());
//
//        // 커뮤니티 관련 request URI 추가
//        mappings.put("/community/list", new ListCommunityController());
//        mappings.put("/community/view", new ViewCommunityController());
//        mappings.put("/community/create/form", new ForwardController("/community/creationForm.jsp"));
//        mappings.put("/community/create", new CreateCommunityController());
//        mappings.put("/community/update", new UpdateCommunityController());
//
//        logger.info("Initialized Request Mapping!");
    }

    public Controller findController(String uri) {
        // 주어진 uri에 대응되는 controller 객체를 찾아 반환
        return mappings.get(uri);
    }
}