package controller.post;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import controller.member.MemberSessionUtils;
import controller.Controller;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import model.dto.PostDto;
import model.dao.PostDao;
import model.dto.MemberDto;
import model.service.MemberManager;
import model.service.PostManager;

public class PostController implements Controller {
    private final Logger LOG = LoggerFactory.getLogger(PostController.class);
    private final MemberSessionUtils MEMBER_SESSION_UTILS = new MemberSessionUtils();
    private final MemberManager MEMBER_MANAGER = MemberManager.getInstance();
    private final PostManager POST_MANAGER = PostManager.getInstance();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        if (request.getSession() == null) {
            return "redirect:/";
        }

        String memberId = MEMBER_SESSION_UTILS.getLoginUserId(request.getSession());
        MemberDto memberDto = MEMBER_MANAGER.findMember(memberId);

        if(request.getServletPath().equals("/post/update")) {
            int postId = Integer.parseInt(request.getParameter("id"));

            if (request.getMethod().equals("GET")) {
                LOG.debug("UpdateForm Request : {}", postId);

                PostDto post = POST_MANAGER.findPost(postId);
                request.setAttribute("post", post);

                return "/post/postViewForm.jsp";   // 사용자 보기 화면으로 이동 (forwarding)
            }

            if (request.getMethod().equals("POST")) { //post 수정
                PostDto updatePost = new PostDto(
                        postId,
                        memberDto.getId(),
                        Integer.parseInt(request.getParameter("isAnonymous")),
                        request.getParameter("type"),
                        request.getParameter("title"),
                        request.getParameter("content")
                );

                LOG.debug("Update Post : {}", updatePost);

                POST_MANAGER.update(updatePost);

                return "redirect:/post/postView?id=" + postId;
            }
        }

        if (request.getServletPath().equals("/post/create")) {    // post 작성
            if (request.getMethod().equals("POST")) {
                PostDto post = new PostDto(
                        memberDto.getId(),
                        Integer.parseInt(request.getParameter("isAnonymous")),
                        memberDto.getType(),
                        request.getParameter("title"),
                        request.getParameter("content")
                );

                LOG.debug("Create Post : {}", post);

                POST_MANAGER.create(post);

                return "redirect:/post/postList";   // 성공 시 게시글 main 화면으로
            }
        }

        if (request.getServletPath().equals("/post/delete")) {
            int postId = Integer.parseInt(request.getParameter("postId"));

            if (request.getMethod().equals("POST")) {
                try {
                    POST_MANAGER.delete(postId);

                    return "redirect:/post/postViewFrom.jsp";
                } catch (Exception e) {
                    /* PostNotFoundException 발생 시
                     * 다시 post form을 사용자에게 전송하고 오류 메세지도 출력
                     */
                    request.setAttribute("deleteFailed", true);
                    request.setAttribute("exception", e);
                    return "/post/postView.jsp";
                }
            }
        }

        if (request.getServletPath().equals("/post/postList")) {
            POST_MANAGER.findAllPost();
        }

        return "/error/error.jsp";
    }
}