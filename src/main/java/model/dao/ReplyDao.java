package model.dao;

import model.dto.ReplyDto;
import util.JDBCUtil;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ReplyDao {
	private final String TABLE_NAME = "reply";
	private JDBCUtil jdbcUtil;

	public ReplyDao() {
		jdbcUtil = new JDBCUtil();
	}

	public int create(ReplyDto replyDto) throws SQLException {
		int result = -1;
//		ReplyDto topReply;
		if(replyDto.getLayer() == 0) {
			result = firstCreateReply(replyDto);
		} else {
			result = createReply(replyDto);
		}
		return result;
	}

	//reply_id -> 프론트에서 받아옴

	//댓글 추가
	public int firstCreateReply(ReplyDto replyDto) throws SQLException {
		int rs = 0;
		String sql = "INSERT INTO " + TABLE_NAME + " (id, post_id, reply_id, is_anonymous, content, likes, layer, created_at, updated_at)" + " VALUES (comment_seq.nextval, ?, NULL, ?, ?, ?, ?, DEFAULT, DEFAULT)";

		Object[] param = new Object[] { replyDto.getPostId(),
				replyDto.getIsAnonymous(),
				replyDto.getContent(), replyDto.getLikes(), replyDto.getLayer()};
		jdbcUtil.setSqlAndParameters(sql, param);

		try {
			rs = jdbcUtil.executeUpdate();
			System.out.println("insert success");
		} catch(Exception e) {
			jdbcUtil.rollback();
			e.printStackTrace();
		} finally {
			jdbcUtil.commit();
			jdbcUtil.close();
		}
		return rs;
	}


	public int createReply(ReplyDto replyDto) throws SQLException {
		int rs = 0;
		String sql = "INSERT INTO " + TABLE_NAME + " VALUES (comment_seq.nextval, ?, ?, ?, ?, ?, ?, DEFAULT, DEFAULT)";

		Object[] param = new Object[] { replyDto.getPostId(),
				replyDto.getReplyId(), replyDto.getIsAnonymous(),
				replyDto.getContent(), replyDto.getLikes(), replyDto.getLayer()};
		jdbcUtil.setSqlAndParameters(sql, param);

		try {
			rs = jdbcUtil.executeUpdate();
			System.out.println("insert success");
		} catch(Exception e) {
			jdbcUtil.rollback();
			e.printStackTrace();
		} finally {
			jdbcUtil.commit();
			jdbcUtil.close();
		}
		return rs;
	}


	//댓글 수정
	public int updateReply(ReplyDto replyDto) throws SQLException {
		ReplyDto findReplyDto = findReply(replyDto.getId());
		String sql = "UPDATE reply " + "SET content=?, likes=?, updated_at=? " + "WHERE id=?";

		Object[] param = new Object[] {replyDto.getContent(), replyDto.getLikes(), new Timestamp(System.currentTimeMillis()),findReplyDto.getId()};;
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

	//댓글 삭제
	public int delete(int id) throws SQLException {
		String sql = "DELETE FROM " + TABLE_NAME + " WHERE id=?";

		Object[] param = new Object[] {id};
		jdbcUtil.setSqlAndParameters(sql, param);
		try {
			int result = jdbcUtil.executeUpdate();
			System.out.println("delete success");
			return result;
		} catch(Exception e) {
			jdbcUtil.rollback();
			e.printStackTrace();
		} finally {
			jdbcUtil.commit();
			jdbcUtil.close();
		}
		return 0;
	}

	//댓글 조회
	public ReplyDto findReply(int id) throws SQLException {
		String sql = "SELECT * " + "FROM reply " + "WHERE id=?";

		jdbcUtil.setSqlAndParameters(sql, new Object[] {id});	// JDBCUtil에 query문과 매개 변수 설정

		try {
			ResultSet rs = jdbcUtil.executeQuery();		// query 실행
			if (rs.next()) {
				ReplyDto reply = new ReplyDto(
						rs.getInt("id"),
						rs.getInt("post_id"),
						rs.getInt("reply_id"),
						rs.getInt("is_anonymous"),
						rs.getString("content"),
						rs.getInt("likes"),
						rs.getInt("layer"),
						rs.getTimestamp("created_at"),
						rs.getTimestamp("updated_at")
				);
				return reply;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();		// resource 반환
		}
		return null;
	}


	//댓글 전체 조회
	public List<ReplyDto> findAllReply(int postId) throws SQLException {
		String sql = "SELECT * " + "FROM reply " + "WHERE post_id=? " + "ORDER BY created_at DESC";

		jdbcUtil.setSqlAndParameters(sql, new Object[] {postId});

		try {
			ResultSet rs = jdbcUtil.executeQuery();
			List<ReplyDto> replyList = new ArrayList<ReplyDto>();
			while (rs.next()) {
				ReplyDto reply = new ReplyDto(
						rs.getInt("id"),
						rs.getInt("post_id"),
						rs.getInt("reply_id"),
						rs.getInt("is_anonymous"),
						rs.getString("content"),
						rs.getInt("likes"),
						rs.getInt("layer"),
						rs.getTimestamp("created_at"),
						rs.getTimestamp("updated_at"));
				replyList.add(reply);
			}
			return replyList;

		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();		// resource 반환
		}
		return null;
	}


	//댓글 개수 조회
	public int countReply(int postId) throws SQLException {
		String sql = "SELECT count(*) FROM reply WHERE post_id=?";
		jdbcUtil.setSqlAndParameters(sql, new Object[] {postId});	// JDBCUtil에 query문과 매개 변수 설정

		try {
			ResultSet rs = jdbcUtil.executeQuery();		// query 실행
			if (rs.next()) {
				int count = rs.getInt(1);
				return count;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();		// resource 반환
		}
		return 0;
	}


}