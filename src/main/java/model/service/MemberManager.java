package model.service;

import java.sql.SQLException;
import model.dto.MemberDto;
import model.dto.MemberUpdateDto;
import model.dao.MemberDao;
import model.exception.ExistingMemberException;
import model.exception.MemberNotFoundException;
import model.exception.PasswordMismatchException;

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