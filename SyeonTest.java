package test;

import java.util.*;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;

import model.dao.MemberDao;
import model.dto.MemberDto;
import model.dto.MemberUpdateDto;
import model.dto.PostDto;
import model.dao.MyTotalIncomeDao;
import model.dao.ReplyDao;
import model.dao.PostDao;
import model.dto.MyTotalIncomeDto;
import model.dto.ReplyDto;

public class SyeonTest {

	public static void main(String[] args) throws SQLException{
		Scanner sc = new Scanner(System.in);
		
		
		MemberDto memberDto;
		MemberDao memberDao;
		
		MemberDao memberDao2;
		
		MyTotalIncomeDao myTotalIncomeDao;
		MyTotalIncomeDto myTotalIncomeDto;
		
		ReplyDao replyDao;
		ReplyDto replyDto;
		
		PostDao postDao;
		PostDto postDto;
		
		// 날짜 형식 변환시 parsing 오류를 try catch로 체크
				try {
//					strDate = "20200806";
//					
//					dtFormat = new SimpleDateFormat("yyyyMMdd");
//					newDtFormat = new SimpleDateFormat("yy/MM/dd");
//					
//					// String 타입을 Date 타입으로 변환
//					formatDate = (Date) dtFormat.parse(strDate);
//					
//					// Date타입의 변수를 새롭게 지정한 포맷으로 변환
//					strNewDtFormat = newDtFormat.format(formatDate);
//					newFormatDate = (Date) newDtFormat.parse(strNewDtFormat);
					
					
					memberDao = new MemberDao();
					
					//insert
//					memberDto = new MemberDto("mina", "mina9875", "mina", Date.valueOf("1999-12-17") , "01098761234", "PARTTIMER");
//					System.out.println("result: " + memberDao.insertMember(memberDto));
					
//					
					//사용자 정보 상세 조회
//					memberDto = memberDao.findMember("ddegs");
//					
//					if (memberDto != null) {
//						System.out.println("아이디 : " + memberDto.getMemberId());
//						System.out.println("비밀번호 : " + memberDto.getPassword());
//						System.out.println("이름 : " + memberDto.getName());
//						System.out.println("생일 : " + memberDto.getBirth());
//						System.out.println("전화번호 : " + memberDto.getPhoneNumber());
//						System.out.println("타입 : " + memberDto.getType());
//						System.out.println();
//					} else {
//						System.out.println("사용자를 찾지 못했음");
//						return;
//					}
					
					//update
//					MemberUpdateDto memberUpdateDto = new MemberUpdateDto("john1230", "j1h2n3", "john", Date.valueOf("1994-08-17") , "01044981377", "EMPLOYER");
//					System.out.println("result: " + memberDao.updateMember(memberUpdateDto));
					
//					memberDto = new MemberDto("john1230", "jj1230nn" , "john", Date.valueOf("1994-08-17") , "01048791362", "EMPLOYER");
//					System.out.println("result: " + memberDao.insertOrUpdate(memberDto));
					
//					memberDto = new MemberDto("john1230", null , "john", Date.valueOf("1994-08-17") , "01044981388", "EMPLOYER");
//					System.out.println("result: " + memberDao.insertOrUpdate(memberDto));
					
					//로그인 -> memberId 리턴 아님 dto 리턴이 낫나?
//					String ID = null;
//					if((ID = memberDao.findByMemberIdAndPassword("aaa", "bbb")) != null) {			
//						System.out.println(ID + " 로그인 성공");
//					} else {
//						System.out.println("로그인 실패");
//					}	
					
//					
//					newDtFormat = new SimpleDateFormat("yy-MM-dd");
////					
////					// String 타입을 Date 타입으로 변환
//					formatDate = (Date) newDtFormat.parse("22-11-01");
					
//					SimpleDateFormat sdf = new SimpleDateFormat("yy-MM-dd");
//			        String strDate = "22-11-01";
//			        Date date = new Date(sdf.parse(strDate).getTime());
//			        System.out.println(date);
					
//			        SimpleDateFormat newFormat = new SimpleDateFormat("yy/MM/dd");
//					String to = newFormat.format(date);
//			        
//					System.out.println(to);
//					System.out.println(Date.valueOf("2022-11-01"));
//					System.out.println(Date.valueOf("2022-11-01").getClass().getSimpleName());
					
					myTotalIncomeDao = new MyTotalIncomeDao();
					
					//insert
//					myTotalIncomeDto = new MyTotalIncomeDto(3, Date.valueOf("2022-08-01"), 336000);
//					System.out.println("result: " + myTotalIncomeDao.insertOrUpdate(myTotalIncomeDto));
					
//					myTotalIncomeDto = new MyTotalIncomeDto(4, Date.valueOf("2022-06-01"), 154880);
//					System.out.println("result: " + myTotalIncomeDao.insertOrUpdate(myTotalIncomeDto));
					
					//update
//					myTotalIncomeDto = new MyTotalIncomeDto(4, 1000, Date.valueOf("2022-11-01"), 4200000, new Timestamp(System.currentTimeMillis()), new Timestamp(System.currentTimeMillis()));
//					System.out.println("result: " + myTotalIncomeDao.insertOrUpdate(myTotalIncomeDto));
					
					//find
//					myTotalIncomeDao = new MyTotalIncomeDao();
//					
//					myTotalIncomeDto = myTotalIncomeDao.findMyWorkPlaceTotalIncome(Date.valueOf("2022-11-01"), 1000);
//					
//					if (myTotalIncomeDto != null) {
//						System.out.println("근무정보번호 : " + myTotalIncomeDto.getId());
//						System.out.println("나의 근무지 번호 : " + myTotalIncomeDto.getEmployerWorkplaceId());
//						System.out.println("연월 : " + myTotalIncomeDto.getIncomeDateOfMonth());
//						System.out.println("이번달 수익 : " + myTotalIncomeDto.getIncome());
//						System.out.println();
//					} else {
//						System.out.println("찾지 못했음");
//						return;
//					}
					
					
					replyDao = new ReplyDao();
					
					//<Reply>
					//첫댓글 생성
//					replyDto = new ReplyDto(2, 0, "sfskfjdljflsf", 0, 0);	
//					System.out.println("result: " + replyDao.create(replyDto));
	
					//n번째 댓글 생성
//					replyDto = new ReplyDto(2, 4, 0, "mollsldfksjlsnc", 0, 1);	
//					System.out.println("result: " + replyDao.create(replyDto));
								
					//find
					ReplyDto replyDto1 = replyDao.findReply(6);
//					if (replyDto1 != null) {
//						System.out.println("댓글 번호 : " + replyDto1.getId());
//						System.out.println("소속 글 번호 : " + replyDto1.getPostId());
//						System.out.println("소속 댓글 번호 : " + replyDto1.getReplyId());
//						System.out.println("댓글 계층 : " + replyDto1.getLayer());
//						System.out.println();
//					} else {
//						System.out.println("찾지 못했음");
//						return;
//					}
					
					
					//update
//					replyDto = new ReplyDto(6, 2, 4, 0, "poiuyrewsgjxnjsfdljfsdljfsd", 0, 1, replyDto1.getCreatedAt(), replyDto1.getUpdatedAt());	
//					System.out.println("result: " + replyDao.updateReply(replyDto));
					
					//delete
//					System.out.println("result: " + replyDao.delete(774));
				
					
					//댓글 전체 조회
//					List<ReplyDto> list = new ArrayList<ReplyDto>();
//					list = replyDao.findAllReply(2);
//					
//					for (int i = 0; i < list.size(); i++) {
//						System.out.println(list.get(i).getId());
//					}
					
					//count
//					System.out.println("result: " + replyDao.countReply(1));
					
					
					//<Post>
					postDao = new PostDao();
					
					//게시글 create
//					postDto = new PostDto(1, 0, "PARTTIMER", "html", "hard", 0, 0);
//					System.out.println("result: " + postDao.createPost(postDto));
					
					//게시글 find
					PostDto postDto1 = postDao.findPost(3);
//					if (postDto1 != null) {
//						System.out.println("글 번호 : " + postDto1.getId());
//						System.out.println("유저 번호 : " + postDto1.getMemberId());
//						System.out.println("커뮤니티 타입 : " + postDto1.getType());
//						System.out.println("글 제목 : " + postDto1.getTitle());
//						System.out.println();
//					} else {
//						System.out.println("찾지 못했음");
//						return;
//					}
					
					//게시글 update
//					postDto = new PostDto(3, 1, 0, "PARTTIMER", "JSP", "HELLO WORLD", 0, 0, postDto1.getCreatedAt(), postDto1.getUpdatedAt());
//					System.out.println("result: " + postDao.updatePost(postDto));
//					
					//게시글 delete
//					System.out.println("result: " + postDao.deletePost(3));
					
					//게시글 개수 조회
//					System.out.println("result: " + postDao.countPost());
					
					//게시글 전체 조회
//					List<PostDto> list = new ArrayList<PostDto>();
//					list = postDao.findAllPost();
					
//					for (int i = 0; i < list.size(); i++) {
//						System.out.println(list.get(i).getId());
//					}
					
				}catch (Exception e) {
					e.printStackTrace();
				}

				
	}

}
