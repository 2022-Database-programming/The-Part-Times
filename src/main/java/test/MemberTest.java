package test;

import java.text.SimpleDateFormat;
import java.util.Scanner;
import java.sql.Date;
import java.sql.SQLException;

import model.dao.MemberDao;
import model.dto.MemberDto;

public class MemberTest {

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
					
					//사용자 존재 확인 후 추가
//					if(memberDao.existedMember("abcdef") == false) {
//						memberDto = new MemberDto("abcdef", "bbb", "acbd", Date.valueOf("2001-02-03") , "01056342123", "PARTTIMER");
//						System.out.println("result: " + memberDao.insertMember(memberDto));
//					} else {
//						System.out.println("existed");
//					}
					
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
					
					//사용자 존재 확인 후 update
//					if(memberDao.existedMember("abcdef")) {
//						memberDto = memberDao.findMember("abcdef");
//						String pwd = "skfslfsf";
//						String phone = "01055667893";
//						
//						memberDto.setPassword(pwd);
//						memberDto.setPhoneNumber(phone);
//						
//						System.out.println("result : " + memberDao.updateMember(memberDto));
//					} else {
//						System.out.println("unexisted");
//					}
					
					//로그인
//					if(memberDao.findByMemberIdAndPassword("aaa", "bbb")) {
//						System.out.println("로그인 성공");
//					} else {
//						System.out.println("로그인 실패");
//					}	
					
					
				}catch (Exception e) {
					e.printStackTrace();
				}

	}

}
