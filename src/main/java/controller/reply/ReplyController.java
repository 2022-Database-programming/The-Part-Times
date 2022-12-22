package controller.reply;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import controller.member.MemberSessionUtils;
import controller.Controller;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import model.dto.ReplyDto;
import model.dao.mybatis.ReplyDao;
import model.dao.mybatis.mapper.ReplyMapper;
import model.dto.MemberDto;
import model.service.MemberManager;
import model.dto.PostDto;
import model.dto.PageDto;

public class ReplyController implements Controller {
	private final Logger log = LoggerFactory.getLogger(ReplyController.class);
	private final MemberSessionUtils MEMBER_SESSION_UTILS = new MemberSessionUtils();
	private final MemberManager MEMBER_MANAGER = MemberManager.getInstance();
	//private final ReplyManager REPLY_MANAGER = ReplyManager.getInstance();
	private ReplyMapper replyMapper;
	
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
		
		
		if (request.getServletPath().equals("/reply/create")) {	// reply 작성 
			int postId = Integer.parseInt(request.getParameter("postId"));
			if (request.getMethod().equals("POST")) {
				ReplyDto reply;
				if(request.getParameter("replyId") != null) {
					reply = new ReplyDto(
						postId,
						Integer.parseInt(request.getParameter("replyId")),
						Integer.parseInt(request.getParameter("isAnonymous")),
						request.getParameter("content"),
						Integer.parseInt(request.getParameter("likes")),
						Integer.parseInt(request.getParameter("layer")),
						memberDto.getId(),
						memberDto.getName()
						);
					replyMapper.insertReply(reply);
				} else {
					reply = new ReplyDto(
							postId,
							Integer.parseInt(request.getParameter("isAnonymous")),
							request.getParameter("content"),
							Integer.parseInt(request.getParameter("likes")),
							Integer.parseInt(request.getParameter("layer")),
							memberDto.getId(),
							memberDto.getName()
					);

					replyMapper.insertFirstReply(reply);
				}
				log.debug("Create Reply : {}", reply);
			
				
				System.out.println(postId);
				System.out.println(reply.getId());
				//System.out.println(reply.getReplyId());
				System.out.println(reply.getContent());
				
				return "redirect:/post/postDetail?id=" + postId;
			}
		}
		
		if (request.getServletPath().equals("/reply/update")) {
			int postId = Integer.parseInt(request.getParameter("postId"));
			if(request.getMethod().equals("POST")) {
				ReplyDto updateReply;
				if(request.getParameter("replyId") != null) {
					updateReply = new ReplyDto(
						postId,
						Integer.parseInt(request.getParameter("replyId")),
						Integer.parseInt(request.getParameter("isAnonymous")),
						request.getParameter("content"),
						Integer.parseInt(request.getParameter("likes")),
						Integer.parseInt(request.getParameter("layer")),
						memberDto.getId(),
						memberDto.getName()
						);
				} else {
					updateReply = new ReplyDto(
							postId,
							Integer.parseInt(request.getParameter("isAnonymous")),
							request.getParameter("content"),
							Integer.parseInt(request.getParameter("likes")),
							Integer.parseInt(request.getParameter("layer")),
							memberDto.getId(),
							memberDto.getName()
					);
					
				}
				
				log.debug("Update Reply : {}", updateReply);
				
//				replyMapper.updateLikesReply(updateReply,);
				
				System.out.println("update");
				System.out.println(postId);
				System.out.println(updateReply.getId());
				System.out.println(updateReply.getReplyId());
				System.out.println(updateReply.getContent());
				
				return "redirect:/post/postDetail?id=" + postId; 
			}
		}
		
		if (request.getServletPath().equals("/reply/delete")) {
			int postId = Integer.parseInt(request.getParameter("postId"));
			int id = Integer.parseInt(request.getParameter("id"));
			if (request.getMethod().equals("POST")) {
				try {
					replyMapper.deleteReply(id);
					
					System.out.println("1");
					
					return "redirect:/post/postDetail?id=" + postId;
				} catch (Exception e) {
					request.setAttribute("deleteFailed", true);
					request.setAttribute("exception", e);
					return "/post/postDetail.jsp";
				}
			}
		}
		
		return "/error/error.jsp";
	}
}