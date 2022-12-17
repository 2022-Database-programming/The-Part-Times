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
    private static final Logger log = LoggerFactory.getLogger(PostController.class);
    private static final MemberSessionUtils memberSessionUtils = new MemberSessionUtils();
    private static final PostSessionUtils postSessionUtils = new PostSessionUtils();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        /* 사용자가 로그아웃하지 않았으면 세션에서 아이디를 꺼내온다. */
        if (request.getSession() == null) {
            System.out.print("로그아웃된 유저입니다.");
            return "index.jsp";
        }

        String memberId = memberSessionUtils.getLoginUserId(request.getSession());
        System.out.println(memberId);

        MemberManager membermanager = MemberManager.getInstance();
        MemberDto memberDto = membermanager.findMember(memberId);
        System.out.println(memberDto.getId());


        if(request.getServletPath().equals("/post/update")) {
            int postId = Integer.parseInt(request.getParameter("id"));

            if (request.getMethod().equals("GET")) {
                log.debug("UpdateForm Request : {}", postId);

                PostManager manager = PostManager.getInstance();
                PostDto post = new PostDao().findPost(postId);   // 수정하려는 post 정보 검색
                request.setAttribute("post", post);   // DTO 값을 통째로 넣어줌

                System.out.println(post.getId());
                System.out.println(post.getMemberId());

                return "/post/postViewForm.jsp";   // 사용자 보기 화면으로 이동 (forwarding)
            }

            if (request.getMethod().equals("POST")) { //post 수정
                PostDto updatePost = new PostDto(
                        postId,
                        memberDto.getId(),
                        Integer.parseInt(request.getParameter("isAnonymous")),
                        request.getParameter("type"),
                        request.getParameter("title"),
                        request.getParameter("content"),
                        Integer.parseInt(request.getParameter("likes")),
                        Integer.parseInt(request.getParameter("views"))
                );

                log.debug("Update Post : {}", updatePost);

                PostManager updatemanager = PostManager.getInstance();
                updatemanager.update(updatePost);

                return "redirect:/post/postView.jsp";
            }

            if (request.getMethod().equals("DELETE")) {  //post 삭제
                try {
                    PostManager deletemanager = PostManager.getInstance();
                    deletemanager.delete(postId);

                    System.out.println("1");

                    return "redirect:/post/postView.jsp";
                } catch (Exception e) {
                    /* PostNotFoundException 발생 시
                     * 다시 post form을 사용자에게 전송하고 오류 메세지도 출력
                     */
                    request.setAttribute("deleteFailed", true);
                    request.setAttribute("exception", e);
                    return "redirect:/post/postViewForm.jsp";
                }
            }
        }

        if (request.getServletPath().equals("/post/create")) {    // post 작성
            if (request.getMethod().equals("POST")) {
                System.out.println("-------------------------");
                Integer.parseInt(request.getParameter("memberId"));
                System.out.println("-------------------------");
                Integer.parseInt(request.getParameter("isAnonymous"));
                System.out.println("-------------------------");
                request.getParameter("type");
                request.getParameter("title");
                request.getParameter("content");
                Integer.parseInt(request.getParameter("likes"));
                System.out.println("-------------------------");
                Integer.parseInt(request.getParameter("views"));
                System.out.println("-------------------------");


                PostDto post = new PostDto(
                        memberDto.getId(),
                        Integer.parseInt(request.getParameter("isAnonymous")),
                        request.getParameter("type"),
                        request.getParameter("title"),
                        request.getParameter("content"),
                        Integer.parseInt(request.getParameter("likes")),
                        Integer.parseInt(request.getParameter("views"))
                );


                log.debug("Create Post : {}", post);
                System.out.println(post.getId());
                System.out.println(post.getMemberId());
                System.out.println(post.getTitle());

                PostManager createmanager = PostManager.getInstance();
                createmanager.create(post);

                return "redirect:/post/postView.jsp";   // 성공 시 게시글 main 화면으로
            }
        }


        //post 조회


        return "/error/error.jsp";
    }
}