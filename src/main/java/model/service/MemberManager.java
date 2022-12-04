package model.service;

import java.sql.SQLException;

//import model.Community;
import model.dto.MemberDto;
//import model.dao.CommunityDAO;
import model.dao.MemberDao;
import model.exception.ExistingMemberException;
import model.exception.MemberNotFoundException;
import model.exception.PasswordMismatchException;

/**
 * 사용자 관리 API를 사용하는 개발자들이 직접 접근하게 되는 클래스.
 * MemberDAO를 이용하여 데이터베이스에 데이터 조작 작업이 가능하도록 하며,
 * 데이터베이스의 데이터들을 이용하여 비지니스 로직을 수행하는 역할을 한다.
 * 비지니스 로직이 복잡한 경우에는 비지니스 로직만을 전담하는 클래스를 
 * 별도로 둘 수 있다.
 */
public class MemberManager {
	private static MemberManager memberMan = new MemberManager();
	private MemberDao memberDao;

	private MemberManager() {
		try {
			memberDao = new MemberDao();
		} catch (Exception e) {
			e.printStackTrace();
		}			
	}
	
	public static MemberManager getInstance() {
		return memberMan;
	}
	
	public int create(MemberDto member) throws SQLException, ExistingMemberException {
		if (memberDao.existedMember(member.getMemberId()) == true) {
			throw new ExistingMemberException(member.getMemberId() + "는 존재하는 아이디입니다.");
		}
		return memberDao.insertMember(member);
	}

	public int update(MemberUpdateDto updateUser) throws SQLException, MemberNotFoundException {
		String memberId = memberDao.findMember(updateUser.getMemberId()).getMemberId();
		if (!updateUser.getMemberId().equals(memberId)) { 
			throw new MemberNotFoundException(updateUser.getMemberId() + "는 일치하지 않는 아이디입니다.");
		}
		return memberDao.updateMember(updateUser);
	}	

	public MemberDto findMember(String memberId) throws SQLException, MemberNotFoundException {
		MemberDto member = memberDao.findMember(memberId);
		
		if (member == null) {
			throw new MemberNotFoundException(memberId + "는 존재하지 않는 아이디입니다.");
		}		
		return member;
	}

	public MemberDto login(String memberId, String password) throws SQLException, MemberNotFoundException, PasswordMismatchException {
		MemberDto member = findMember(memberId);
		
		if(memberId.equals(member.getMemberId()) && password.equals(member.getPassword()))
			return member;
		return null;
	}

	public MemberDao getMemberDAO() {
		return this.memberDao;
	}
}
