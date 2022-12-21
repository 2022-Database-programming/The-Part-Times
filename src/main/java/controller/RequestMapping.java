package controller;

import controller.post.PostController;
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
        mappings.put("/post/post", new PostController());
        mappings.put("/post/update", new PostController());
        mappings.put("/post/delete", new PostController());
        mappings.put("/post/create", new PostController());
        
    }

    public Controller findController(String uri) {
        // 주어진 uri에 대응되는 controller 객체를 찾아 반환
        return mappings.get(uri);
    }
}