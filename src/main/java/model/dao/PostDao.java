package model.dao;

import model.dto.PostDto;
import util.JDBCUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class PostDao {
	private final JDBCUtil JDBC_UTIL;

	public PostDao() {
		JDBC_UTIL = new JDBCUtil();
	}

	//게시글 추가
	public int createPost(PostDto postDto) throws SQLException {
		String sql = "INSERT INTO post VALUES (post_seq.nextval, ?, ?, ?, ?, ?, ?, ?, DEFAULT, DEFAULT)";

		int rs = 0;
		Object[] param = new Object[] { postDto.getMemberId(), postDto.getIsAnonymous(), postDto.getType(),
				postDto.getTitle(), postDto.getContent()};

		JDBC_UTIL.setSqlAndParameters(sql, param);

		try {
			rs = JDBC_UTIL.executeUpdate();
		} catch(Exception e) {
			JDBC_UTIL.rollback();
			e.printStackTrace();
		} finally {
			JDBC_UTIL.commit();
			JDBC_UTIL.close();
		}
		return rs;
	}

	//게시글 수정
	public int updatePost(PostDto postDto) throws SQLException {
		PostDto findPostDto = findPost(postDto.getId());

		String sql = "UPDATE post " + "SET title=?, content=?, likes=?, views=?, updated_at=? " + "WHERE id=?";
		Object[] param = new Object[] {postDto.getTitle(), postDto.getContent(), postDto.getLikes(), postDto.getViews(), new Timestamp(System.currentTimeMillis()),findPostDto.getId()};

		JDBC_UTIL.setSqlAndParameters(sql, param);	// JDBCUtil에 update문과 매개 변수 설정

		try {
			int result = JDBC_UTIL.executeUpdate();	// update 문 실행
			return result;
		} catch (Exception ex) {
			JDBC_UTIL.rollback();
			ex.printStackTrace();
		}
		finally {
			JDBC_UTIL.commit();
			JDBC_UTIL.close();	// resource 반환
		}
		return 0;
	}


	//게시글 삭제
	public int deletePost(int id) throws SQLException {
		String sql = "DELETE FROM post WHERE id=?";

		Object[] param = new Object[] {id};

		JDBC_UTIL.setSqlAndParameters(sql, param);

		try {
			int result = JDBC_UTIL.executeUpdate();

			return result;
		} catch(Exception e) {
			JDBC_UTIL.rollback();
			e.printStackTrace();
		} finally {
			JDBC_UTIL.commit();
			JDBC_UTIL.close();
		}
		return 0;
	}


	//게시글 조회
	public PostDto findPost(int id) throws SQLException {
		String sql = "SELECT * " + "FROM post " + "WHERE id=?";

		JDBC_UTIL.setSqlAndParameters(sql, new Object[] { id });	// JDBCUtil에 query문과 매개 변수 설정

		try {
			ResultSet rs = JDBC_UTIL.executeQuery();		// query 실행
			if (rs.next()) {
				PostDto post = new PostDto(
						rs.getInt("id"),
						rs.getInt("member_id"),
						rs.getInt("is_anonymous"),
						rs.getString("type"),
						rs.getString("title"),
						rs.getString("content"),
						rs.getInt("likes"),
						rs.getInt("views"),
						rs.getTimestamp("created_at"),
						rs.getTimestamp("updated_at")
				);
				return post;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			JDBC_UTIL.close();		// resource 반환
		}
		return null;
	}


	//게시글 전체 조회
	public List<PostDto> findAllPost() throws SQLException {
		String sql = "SELECT * " + "FROM post " + "ORDER BY created_at DESC";

		JDBC_UTIL.setSqlAndParameters(sql, null);

		try {
			ResultSet rs = JDBC_UTIL.executeQuery();
			List<PostDto> postList = new ArrayList<PostDto>();
			while (rs.next()) {
				PostDto post = new PostDto(
						rs.getInt("id"),
						rs.getInt("member_id"),
						rs.getInt("is_anonymous"),
						rs.getString("type"),
						rs.getString("title"),
						rs.getString("content"),
						rs.getInt("likes"),
						rs.getInt("views"),
						rs.getTimestamp("created_at"),
						rs.getTimestamp("updated_at"));
				postList.add(post);
			}
			return postList;

		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			JDBC_UTIL.close();		// resource 반환
		}
		return null;
	}


	//게시글 개수 조회
	public int countPost() throws SQLException {
		String sql = "SELECT count(*) FROM post";
		JDBC_UTIL.setSqlAndParameters(sql, null);	// JDBCUtil에 query문과 매개 변수 설정

		try {
			ResultSet rs = JDBC_UTIL.executeQuery();		// query 실행
			if (rs.next()) {
				int count = rs.getInt(1);
				return count;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			JDBC_UTIL.close();		// resource 반환
		}
		return 0;
	}
}