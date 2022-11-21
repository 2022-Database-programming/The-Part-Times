package test;

import java.text.SimpleDateFormat;
import java.util.Scanner;
import java.sql.Date;
import java.sql.SQLException;

import model.dao.MemberDao;
import model.dto.MemberDto;
import model.dao.MyTotalIncomeDao;
import model.dto.MyTotalIncomeDto;

public class Test {

	public static void main(String[] args) throws SQLException{
		Scanner sc = new Scanner(System.in);
		
//		String strDate;
//		SimpleDateFormat dtFormat;
//		SimpleDateFormat newDtFormat;
//		
//		Date formatDate;
//		String strNewDtFormat;
//		Date newFormatDate;
		
		MemberDto memberDto;
		MemberDao memberDao;
		
		MemberDao memberDao2;
		
		MyTotalIncomeDao myTotalIncomeDao;
		
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
//					memberDto = new MemberDto("john1230", "jhn1230", "john", Date.valueOf("1994-08-17") , "01044981362", "EMPLOYER");
//					System.out.println("result: " + memberDao.insertOrUpdate(memberDto));
					
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
//					memberDto = new MemberDto("john1230", "j1h2n307", "john", Date.valueOf("1994-08-17") , "01044981362", "EMPLOYER");
//					System.out.println("result: " + memberDao.insertOrUpdate(memberDto));
					
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
					
					
					
					
				}catch (Exception e) {
					e.printStackTrace();
				}

	}

}
