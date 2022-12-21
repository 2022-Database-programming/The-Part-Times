//package controller.reply;
//
//import java.util.List;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import controller.member.MemberSessionUtils;
//import controller.Controller;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import model.dto.ReplyDto;
//import model.dao.mybatis.ReplyDao;
//import model.dto.MemberDto;
//import model.service.MemberManager;
//import model.dto.PostDto;
//import model.service.PostManager;
//import model.service.ReplyManager;
//
//public class ReplyController implements Controller {
//	private static final Logger log = LoggerFactory.getLogger(ReplyController.class);
//	private static final MemberSessionUtils memberSessionUtils = new MemberSessionUtils();
//
//	@Override
//	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
//		/* 사용자가 로그아웃하지 않았으면 세션에서 아이디를 꺼내온다. */
//		if (request.getSession() == null) {
//			System.out.print("로그아웃된 유저입니다.");
//			return "/error/logoutError.jsp";
//		}
//
//		//memberId 세션 찾기
//		String memberId = memberSessionUtils.getLoginUserId(request.getSession());
//		System.out.println(memberId);
//
//		//memberId의 해당하는 ID 찾기
//		MemberManager membermanager = MemberManager.getInstance();
//		MemberDto memberDto = membermanager.findMember(memberId);
//		System.out.println(memberDto.getId());
//
//
//		if(request.getServletPath().equals("/post/post")) {
//			// 페이지 단위 조회
//			int pageNum = 1; // 첫페이지 경우
//			int amount = 10;
//
//			// 페이지 번호 클릭하는 경우
//			if (request.getParameter("pageNum") != null && request.getParameter("amount") != null) {
//				pageNum = Integer.parseInt(request.getParameter("pageNum"));
//				amount = Integer.parseInt(request.getParameter("amount"));
//			}
//
//			PostManager manager = PostManager.getInstance();
//
//			List<PostDto> list = manager.getPostAllList(pageNum, amount);
//			PageDto pageDto = manager.getPost(pageNum, amount);
//
//			request.setAttribute("pageDto", pageDto);
//			request.setAttribute("list", list);
//
//			System.out.println(pageDto.getStartPageNum());
//			System.out.println(pageDto.getEndPageNum());
//
//			System.out.println(pageNum);
//			System.out.println(amount);
//
//			for (int i = 0; i < list.size(); i++) {
//				System.out.println(list.get(i).getTitle() + ", " + list.get(i).getName());
//			}
//
//			return "/post/postView.jsp";
//		}
//
//
//		if(request.getServletPath().equals("/post/update")) {
//			int postId = Integer.parseInt(request.getParameter("postId"));
//
//			if (request.getMethod().equals("GET")) {  // 조회
//				log.debug("UpdateForm Request : {}", postId);
//
//				PostManager manager = PostManager.getInstance();
//				PostDto post = manager.findPost(postId);   // 수정하려는 post 정보 검색
//				request.setAttribute("post", post);   // DTO 값을 통째로 넣어줌
//
//				System.out.println(post.getId());
//				System.out.println(post.getMemberId());
//				System.out.println(post.getTitle());
//				System.out.println(post.getContent());
//				System.out.println(post.getViews());
//				System.out.println(post.getName());
//
//
//				return "/post/postViewForm.jsp";   // 해당 게시글 화면으로 이동 (forwarding)
//			}
//
//			if (request.getMethod().equals("POST")) { 
//				//post update
//				PostDto updatePost = new PostDto(
//						postId,
//						memberDto.getId(),
//						Integer.parseInt(request.getParameter("isAnonymous")),
//						memberDto.getType(),
//						request.getParameter("title"),
//						request.getParameter("content"),
//						Integer.parseInt(request.getParameter("likes")),
//						Integer.parseInt(request.getParameter("views")),
//						memberDto.getName()
//						);
//
//				log.debug("Update Post : {}", updatePost);
//
//				PostManager updatemanager = PostManager.getInstance();
//				updatemanager.update(updatePost);
//
//				return "redirect:/post/postView.jsp";
//			}
//
//		}
//
//
//		if (request.getServletPath().equals("/post/delete")) {
//			int postId = Integer.parseInt(request.getParameter("postId"));
//
//			System.out.println(postId);
//
//			if (request.getMethod().equals("POST")) { 
//				try {
//					PostManager deletemanager = PostManager.getInstance();
//					deletemanager.delete(postId);
//
//					System.out.println("1");
//
//					return "redirect:/post/postViewFrom.jsp";
//				} catch (Exception e) {
//					/* PostNotFoundException 발생 시
//					 * 다시 post form을 사용자에게 전송하고 오류 메세지도 출력
//					 */
//					request.setAttribute("deleteFailed", true);
//					request.setAttribute("exception", e);
//					return "/post/postView.jsp";
//				}
//			}
//		}
//
//
//
//		if (request.getServletPath().equals("/post/create")) {    // post 작성
//			if (request.getMethod().equals("POST")) {
//				PostDto post = new PostDto(
//						memberDto.getId(),
//						Integer.parseInt(request.getParameter("isAnonymous")),
//						memberDto.getType(),
//						request.getParameter("title"),
//						request.getParameter("content"),
//						memberDto.getName()
//						);
//
//
//				log.debug("Create Post : {}", post);
//				System.out.println(post.getId());
//				System.out.println(post.getMemberId());
//				System.out.println(post.getTitle());
//				System.out.println(post.getName());
//
//				PostManager createmanager = PostManager.getInstance();
//				createmanager.create(post);
//
//				return "redirect:/post/postView.jsp";   // 성공 시 게시글 main 화면으로
//			}
//		}        
//
//		return "/error/error.jsp";
//	}
//}