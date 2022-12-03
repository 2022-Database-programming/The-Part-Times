package model.dao;

import model.dto.MemberDto;

import java.sql.*;
import java.time.LocalDate;

import util.JDBCUtil;

public class MemberDao {
	private JDBCUtil jdbcUtil = null;

	//MemberDAO 생성자
	public MemberDao() {
		jdbcUtil = new JDBCUtil();
	}

//	public int insertOrUpdate(MemberDto memberDto) throws SQLException {
//		int result = -1;
//		if(existedMember(memberDto.getMemberId())) {
//			result = updateMember(memberDto);
//		} else {
//			result = insertMember(memberDto);
//		}
//		return result;
//	}


	//사용자 정보 확인 (회원가입시)
	public boolean existedMember(String memberId) throws SQLException {
		String sql = "SELECT count(*) FROM member WHERE member_id=?";
		jdbcUtil.setSqlAndParameters(sql, new Object[] {memberId});
//		String result = null;

		try {
			ResultSet rs = jdbcUtil.executeQuery();		// query 실행
			if (rs.next()) {
				int count = rs.getInt(1);
				return (count == 1 ? true : false);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();
		}
		return false;
	}

	//회원가입 (사용자 정보 등록)
	public int insertMember(MemberDto memberDto) throws SQLException {
		String sql = "INSERT INTO member (id, name, member_id, password, birth, phone_number, type, is_active, created_at, updated_at) VALUES (member_seq.nextval, ?, ?, ?, ?, ?, ?, DEFAULT, DEFAULT, DEFAULT)";
		Object[] param = new Object[] {memberDto.getName(), memberDto.getMemberId(), memberDto.getPassword(),
				memberDto.getBirth(), memberDto.getPhoneNumber(), memberDto.getType()};

		jdbcUtil.setSqlAndParameters(sql, param);

		try {
			int result = jdbcUtil.executeUpdate();
			return result;
		} catch (Exception ex) {
			jdbcUtil.rollback();
			ex.printStackTrace();
		} finally {
			jdbcUtil.commit();
			jdbcUtil.close();
		}

		return 0;
	}

	//마이페이지 또는 로그인 시 사용자 정보 상세 조회
	public MemberDto findMember(String memberId) throws SQLException {
		String sql = "SELECT * "
				+ "FROM member "
				+ "WHERE member_id=?";

		jdbcUtil.setSqlAndParameters(sql, new Object[] {memberId});	// JDBCUtil에 query문과 매개 변수 설정

		try {
			ResultSet rs = jdbcUtil.executeQuery();		// query 실행
			if (rs.next()) {
				MemberDto member = new MemberDto(
						rs.getString("member_id"),
						rs.getString("password"),
						rs.getString("name"),
						rs.getDate("birth"),
						rs.getString("phone_number"),
						rs.getString("type"));
				return member;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();		// resource 반환
		}
		return null;
	}

	//마이페이지 (사용자 정보 수정)
	public int updateMember(MemberDto memberDto) throws SQLException {
		MemberDto findmemberDto = findMember(memberDto.getMemberId());

		String sql = "UPDATE member "
				+ "SET phone_number=?, birth=?, updated_at=? "
				+ "WHERE member_id=?";
		
			Object[] param = new Object[] {memberDto.getPhoneNumber(), memberDto.getBirth(), Date.valueOf(LocalDate.now()), findmemberDto.getMemberId()};
			jdbcUtil.setSqlAndParameters(sql, param);	// JDBCUtil에 update문과 매개 변수 설정
	
		try {
			int result = jdbcUtil.executeUpdate();	// update 문 실행
			return result;
		} catch (Exception ex) {
			jdbcUtil.rollback();
			ex.printStackTrace();
		}
		finally {
			jdbcUtil.commit();
			jdbcUtil.close();	// resource 반환
		}
		return 0;
	}

}