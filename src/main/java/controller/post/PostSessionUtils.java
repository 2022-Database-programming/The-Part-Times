package controller.post;

import javax.servlet.http.HttpSession;

public class PostSessionUtils {
    public static final String POST_SESSION_KEY = "id";

    /* 현재 post의 ID를 구함 */
    public static int getPostId(HttpSession session) {
        int postId = (int)session.getAttribute(POST_SESSION_KEY);
        return postId;
    }

}