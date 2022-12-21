package model.dao;

import model.dto.PageDto;
import model.dto.PostDto;
import util.JDBCUtil;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PostDao {
	private JDBCUtil jdbcUtil;

	public PostDao() {
		jdbcUtil = new JDBCUtil();
	}

	//게시글 추가
	public int createPost(PostDto postDto) throws SQLException {
		String sql = "INSERT INTO post VALUES (post_seq.nextval, ?, ?, ?, ?, ?, ?, ?, DEFAULT, DEFAULT)";

		int rs = 0;
		Object[] param = new Object[] { postDto.getMemberId(), postDto.getIsAnonymous(), postDto.getType(),
				postDto.getTitle(), postDto.getContent(), postDto.getLikes(), postDto.getViews() };

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

	//게시글 수정
	public int updatePost(PostDto postDto) throws SQLException {
		String sql;
		Object[] param;
		
		PostDto findPostDto = findPost(postDto.getId());
		
		if(findPostDto.getLikes() == postDto.getLikes()) {
			sql = "UPDATE post " + "SET title=?, content=?, views=?, updated_at=? " + "WHERE id=?";
			param = new Object[] {postDto.getTitle(), postDto.getContent(), postDto.getViews(), new Timestamp(System.currentTimeMillis()), findPostDto.getId()};
		} else { // 좋아요 추가
			sql = "UPDATE post " + "SET title=?, content=?, likes=?, views=?, updated_at=? " + "WHERE id=?";
			param = new Object[] {postDto.getTitle(), postDto.getContent(), postDto.getLikes(), postDto.getViews(), new Timestamp(System.currentTimeMillis()), findPostDto.getId()};
		}

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
	

	//게시글 삭제
	public List<PostDto> deletePost(int id) throws SQLException {
		String sql = "DELETE FROM post WHERE id=?";
		
		Object[] param = new Object[] {id};

		jdbcUtil.setSqlAndParameters(sql, param);
		try {
			jdbcUtil.executeUpdate();
			System.out.println("delete success");
			return findAllPost();
		} catch(Exception e) {
			jdbcUtil.rollback();
			e.printStackTrace();
		} finally {
			jdbcUtil.commit();
			jdbcUtil.close();
		}
		return null;
	}


	//게시글 조회
	public PostDto findPost(int id) throws SQLException {
		String selectSql = "SELECT p.id, p.member_id, is_anonymous, p.type, title, content, likes, views, p.created_at, p.updated_at, m.name AS name " 
							+ "FROM post p LEFT OUTER JOIN member m ON p.member_id = m.id " + "WHERE id=?";
		PostDto post = null;
		
		jdbcUtil.setSqlAndParameters(selectSql, new Object[] { id });	// JDBCUtil에 query문과 매개 변수 설정

		try {
			ResultSet rs = jdbcUtil.executeQuery();		// query 실행
			if (rs.next()) {
				post = new PostDto(
						rs.getInt("id"),
						rs.getInt("member_id"),
						rs.getInt("is_anonymous"),
						rs.getString("type"),
						rs.getString("title"),
						rs.getString("content"),
						rs.getInt("likes"),
						rs.getInt("views"),
						rs.getTimestamp("created_at"),
						rs.getTimestamp("updated_at"),
						rs.getString("name")
				);
			} 
			
			if(post != null && addViews(post) != 0) { // 조회수
				jdbcUtil.setSqlAndParameters(selectSql, new Object[] { id });
				ResultSet result = jdbcUtil.executeQuery();
				if (result.next()) {
					post = new PostDto(
							result.getInt("id"),
							result.getInt("member_id"),
							result.getInt("is_anonymous"),
							result.getString("type"),
							result.getString("title"),
							result.getString("content"),
							result.getInt("likes"),
							result.getInt("views"),
							result.getTimestamp("created_at"),
							result.getTimestamp("updated_at"),
							result.getString("name")
					);
				} 
			}
			return post;
		} catch (Exception ex) {
			jdbcUtil.rollback();
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();		// resource 반환
		}
		return null;
	}
	
	// 조회수 추가
	public int addViews(PostDto post) throws SQLException {
		String updateSql = "UPDATE post SET views=? WHERE id=?";
		jdbcUtil.setSqlAndParameters(updateSql, new Object[] { post.getViews() + 1, post.getId() });
		
		try {
			int result = jdbcUtil.executeUpdate();
			
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

	//게시글 전체 조회
	public List<PostDto> findAllPost() throws SQLException {
		String sql = "SELECT p.id, p.member_id, is_anonymous, p.type, title, content, likes, views, p.created_at, p.updated_at, m.name AS name " 
				+ "FROM post p LEFT OUTER JOIN member m ON p.member_id = m.id " + "ORDER BY created_at DESC";

		jdbcUtil.setSqlAndParameters(sql, null);

		try {
			ResultSet rs = jdbcUtil.executeQuery();
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
						rs.getTimestamp("updated_at"),
						rs.getString("name"));
				postList.add(post);
			}
			
			return postList;

		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();		// resource 반환
		}
		return null;
	}
	
	//게시글 패이징
	public List<PostDto> getList(int pageNum, int amount) {
		List<PostDto> postList = new ArrayList<>();
		
		String sql = "SELECT p.id, p.member_id, is_anonymous, p.type, title, content, likes, views, p.created_at, p.updated_at, m.name AS name " + 
					"FROM (SELECT ROWNUM RN, a.* FROM (SELECT * FROM post ORDER BY created_at DESC) a) p LEFT OUTER JOIN member m ON p.member_id = m.id "
					+ "WHERE RN > ? AND RN <= ?";
		
		jdbcUtil.setSqlAndParameters(sql, new Object[] { ((pageNum - 1) * amount), (pageNum * amount)});
		
		try {
			ResultSet rs = jdbcUtil.executeQuery();		// query 실행
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
						rs.getTimestamp("updated_at"),
						rs.getString("name"));
				postList.add(post);
			}
			return postList;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			jdbcUtil.close();		// resource 반환
		}
		return null;
	}


	//게시글 개수 조회
	public int countPost() throws SQLException {
		String sql = "SELECT count(*) FROM post";
		jdbcUtil.setSqlAndParameters(sql, null);	// JDBCUtil에 query문과 매개 변수 설정

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