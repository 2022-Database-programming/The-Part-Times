package controller;

import controller.post.PostController;
import controller.member.MemberController;
import controller.worktime.WorkTimeController;
import java.util.HashMap;
import java.util.Map;

public class RequestMapping {
    private Map<String, Controller> mappings = new HashMap<String, Controller>();

    public void initMapping() {
        mappings.put("/", new ForwardController("/index.jsp"));
        mappings.put("/member/signupForm", new ForwardController("/member/register.jsp"));
        mappings.put("/member/signinForm", new ForwardController("/member/loginForm.jsp"));
        mappings.put("/member/signin", new MemberController());
        mappings.put("/member/signup", new MemberController());
        mappings.put("/member/signout", new MemberController());
        mappings.put("/member/update", new MemberController());
        mappings.put("/member/mypage", new MemberController());
        mappings.put("/member/main", new MemberController());

        mappings.put("/content/mainMenu.jsp?page=record", new WorkTimeController());
        mappings.put("/worktime/today", new WorkTimeController());
        mappings.put("/worktime/day", new WorkTimeController());

        mappings.put("/post/update", new PostController());
        mappings.put("/post/delete", new PostController());
        mappings.put("/post/create", new PostController());
        mappings.put("/post/postList", new PostController());
        mappings.put("/post/postDetail", new PostController());
        
    }

    public Controller findController(String uri) {
        // 주어진 uri에 대응되는 controller 객체를 찾아 반환
        return mappings.get(uri);
    }
}