package model.dao;
import model.dto.ReplyDto;
import util.JDBCUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ReplyDao {
	private final String TABLE_NAME = "comment";
	private JDBCUtil jdbcUtil;
	private Connection conn;
	public ReplyDao() {
		jdbcUtil = new JDBCUtil();
	}
	
	//댓글 추가
	public int create(ReplyDto commentDto) throws SQLException{
		String sql = "INSERT INTO " + TABLE_NAME + " VALUES (?, ?, ?, DEFAULT, ?, DEFAULT, DEFAULT, DEFAULT, DEFUALT)";
		int rs = 0;
		
		Object[] param = new Object[] { commentDto.getId(), commentDto.getPostId(),
				commentDto.getCommentId(), commentDto.getIsAnonymous(),
				commentDto.getContent(), commentDto.getLikes(), commentDto.getLayer(),
				commentDto.getCreatedAt(), commentDto.getUpdatedAt()};
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
	public int update(int id, String content) {
		String sql = "UPDATE INTO " + TABLE_NAME + " VALUES (?, ?)";
		
		try {
			PreparedStatement pstmt= conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			pstmt.setString(2, content);
			pstmt.executeUpdate();
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1;
	}
	
	//댓글 삭제
	public int delete(int id) {
		String sql = "DELETE FROM " + TABLE_NAME + " VALUES (?)";
		int rs = 0;
		Object[] param = new Object[] {id};
		jdbcUtil.setSqlAndParameters(sql, param);
		try {
			rs = jdbcUtil.executeUpdate();
			System.out.println("delete success");
		} catch(Exception e) {
			jdbcUtil.rollback();
			e.printStackTrace();
		} finally {
			jdbcUtil.commit();
			jdbcUtil.close();
		}
		return rs;
	}
}
