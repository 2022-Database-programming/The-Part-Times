package controller.post;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import controller.member.MemberSessionUtils;
import controller.Controller;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import model.dto.PostDto;
import model.dao.PostDao;
import model.dto.MemberDto;
import model.dto.PageDto;
import model.service.MemberManager;
import model.service.PostManager;

public class PostController implements Controller {
	private final Logger LOG = LoggerFactory.getLogger(PostController.class);
	private final MemberSessionUtils MEMBER_SESSION_UTILS = new MemberSessionUtils();
	private final MemberManager MEMBER_MANAGER = MemberManager.getInstance();
	private final PostManager POST_MANAGER = PostManager.getInstance();
	private int pageNum = 1; // 첫페이지 경우
	private int amount = 10;

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		/* 사용자가 로그아웃하지 않았으면 세션에서 아이디를 꺼내온다. */
		if (request.getSession() == null) {
			return "redirect:/";
		}

		//memberId 세션 찾기
		String memberId = MEMBER_SESSION_UTILS.getLoginUserId(request.getSession());
		//memberId의 해당하는 ID 찾기
		MemberDto memberDto = MEMBER_MANAGER.findMember(memberId);

		if(request.getServletPath().equals("/post/postList")) {
			// 페이지 번호 클릭하는 경우
			if (request.getParameter("pageNum") != null && request.getParameter("amount") != null) {
				pageNum = Integer.parseInt(request.getParameter("pageNum"));
				amount = Integer.parseInt(request.getParameter("amount"));
			}

			List<PostDto> posts = POST_MANAGER.getPostAllList(pageNum, amount);
			PageDto pageDto = POST_MANAGER.getPost(pageNum, amount);

			request.setAttribute("pageDto", pageDto);
			request.setAttribute("posts", posts);

			return "/post/postView.jsp";
		}

		if(request.getServletPath().equals("/post/update")) {
			int postId = Integer.parseInt(request.getParameter("postId"));

			if (request.getMethod().equals("GET")) {  // 조회
				LOG.debug("UpdateForm Request : {}", postId);

				PostDto post = POST_MANAGER.findPost(postId);   // 수정하려는 post 정보 검색
				request.setAttribute("post", post);   // DTO 값을 통째로 넣어줌

				return "/post/postDetailForm.jsp";
			}

			if (request.getMethod().equals("POST")) { 
				//post update
				PostDto updatePost = new PostDto(
						postId,
						memberDto.getId(),
						Integer.parseInt(request.getParameter("isAnonymous")),
						memberDto.getType(),
						request.getParameter("title"),
						request.getParameter("content"),
						Integer.parseInt(request.getParameter("likes")),
						Integer.parseInt(request.getParameter("views")),
						memberDto.getName()
						);

				LOG.debug("Update Post : {}", updatePost);

				POST_MANAGER.update(updatePost);

				return "redirect:/post/postDetail";
			}
		}

		if (request.getServletPath().equals("/post/postDetail")) {
			int postId = Integer.parseInt(request.getParameter("postId"));

			if (request.getMethod().equals("GET")) {  // 조회
				PostDto post = POST_MANAGER.findPost(postId);   // 수정하려는 post 정보 검색
				request.setAttribute("post", post);   // DTO 값을 통째로 넣어줌

				return "/post/postDetailForm.jsp";
			}
		}

		if (request.getServletPath().equals("/post/delete")) {
			int postId = Integer.parseInt(request.getParameter("postId"));

			System.out.println(postId);

			if (request.getMethod().equals("POST")) { 
				try {
					PostManager deletemanager = PostManager.getInstance();
					deletemanager.delete(postId);

					System.out.println("1");

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

		if (request.getServletPath().equals("/post/create")) {    // post 작성
			if (request.getMethod().equals("POST")) {
				PostDto post = new PostDto(
						memberDto.getId(),
						Integer.parseInt(request.getParameter("isAnonymous")),
						memberDto.getType(),
						request.getParameter("title"),
						request.getParameter("content"),
						memberDto.getName()
						);


				LOG.debug("Create Post : {}", post);
				System.out.println(post.getId());
				System.out.println(post.getMemberId());
				System.out.println(post.getTitle());
				System.out.println(post.getName());

				PostManager createmanager = PostManager.getInstance();
				createmanager.create(post);

				return "redirect:/post/postView.jsp";   // 성공 시 게시글 main 화면으로
			}
		}        

		return "/error/error.jsp";
	}
}