package controller.member;

import java.sql.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import controller.Controller;
import model.dto.MemberDto;
import model.exception.ExistingMemberException;
import model.exception.MemberNotFoundException;
import model.service.MemberManager;

public class MemberController implements Controller {
    private static final Logger log = LoggerFactory.getLogger(MemberController.class);
    
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
    	if(request.getServletPath().equals("/member/update")) {
    		if (request.getMethod().equals("GET")) {	
        		// GET request: 회원정보 수정 form 요청	
        		// 원래는 UpdateUserFormController가 처리하던 작업을 여기서 수행
        		String updateId = request.getParameter("memberId");

        		log.debug("UpdateForm Request : {}", updateId);
        		
        		MemberManager manager = MemberManager.getInstance();
    			MemberDto member = manager.findMember(updateId);	// 수정하려는 사용자 정보 검색
    			request.setAttribute("member", member);			

    			HttpSession session = request.getSession();
    			if (!MemberSessionUtils.isLoginUser(updateId, session)) {
    				request.setAttribute("updateFailed", true);
        			request.setAttribute("exception", 
        					new IllegalStateException("타인의 정보는 수정할 수 없습니다.")); 
    			}
    			return "/member/mypageForm.jsp";	// 사용자 보기 화면으로 이동 (forwarding)
    	    }	
  
        	// POST request (회원정보가 parameter로 전송됨)
        	MemberDto updateUser = new MemberDto(
        		request.getParameter("memberId"),
        		request.getParameter("password"),
        		request.getParameter("name"),
        		Date.valueOf(request.getParameter("birth")),
        		request.getParameter("phoneNumber"),
        		request.getParameter("type"));

        	log.debug("Update User : {}", updateUser);

        	MemberManager manager = MemberManager.getInstance();
    		manager.update(updateUser);			
            return "redirect:/member/myPage.jsp";			
        } else if(request.getServletPath().equals("/member/signin")) {
    		String userId = request.getParameter("userId");
    		String password = request.getParameter("password");
    		
    		try {
    			// 모델에 로그인 처리를 위임
    			MemberManager manager = MemberManager.getInstance();
    			manager.login(userId, password);
    	
    			// 세션에 사용자 이이디 저장
    			HttpSession session = request.getSession();
                session.setAttribute(MemberSessionUtils.USER_SESSION_KEY, userId);
                
                return "redirect:/member/main.jsp";			
    		} catch (Exception e) {
    			/* UserNotFoundException이나 PasswordMismatchException 발생 시
    			 * 다시 login form을 사용자에게 전송하고 오류 메세지도 출력
    			 */
                request.setAttribute("loginFailed", true);
    			request.setAttribute("exception", e);
                return "/member/loginForm.jsp";			
    		}	
    		
    	} else {
    		   if (request.getMethod().equals("GET")) {
    			// 로그인 여부 확인
    		    	if (!MemberSessionUtils.hasLogined(request.getSession())) {
    		            return "redirect:/member/loginForm.jsp";		// login form 요청으로 redirect
    		        }
    		    	
    				MemberManager manager = MemberManager.getInstance();
    				String memberId = request.getParameter("memberId");
    				
    		    	MemberDto member = null;
    				try {
    					member = manager.findMember(memberId);	// 사용자 정보 검색
    				} catch (MemberNotFoundException e) {				
    			        return "redirect:/member/loginForm.jsp";
    				}	
    				
    				request.setAttribute("member", member);		// 사용자 정보 저장				
    				return "/member/mypage.jsp";				// 사용자 보기 화면으로 이동
    		   }
    		   else {
    		    	// POST request (회원정보가 parameter로 전송됨)
    		       	MemberDto member = new MemberDto(
    					request.getParameter("memberId"),
    					request.getParameter("password"),
    					request.getParameter("name"),
    					Date.valueOf(request.getParameter("birth")),
    					request.getParameter("phone_number"),
    					request.getParameter("type"));
    				
    		        log.debug("Create User : {}", member);

    				try {
    					MemberManager manager = MemberManager.getInstance();
    					manager.create(member);
    			        return "redirect:/member/login.jsp";	// 성공 시 사용자 리스트 화면으로 redirect
    			        
    				} catch (ExistingMemberException e) {	// 예외 발생 시 회원가입 form으로 forwarding
    		            request.setAttribute("registerFailed", true);
    					request.setAttribute("exception", e);
    					request.setAttribute("member", member);
    					return "/member/registerForm.jsp";
    				}
    		   }
    	}
    }
}
