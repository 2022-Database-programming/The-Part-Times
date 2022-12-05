package model.service;

import java.sql.SQLException;
import java.util.List;

import model.dto.PostDto;
import model.exception.PostListNotFoundException;
import model.exception.PostNotFoundException;
import model.dao.PostDao;

/**
 * 사용자 관리 API를 사용하는 개발자들이 직접 접근하게 되는 클래스.
 * MemberDAO를 이용하여 데이터베이스에 데이터 조작 작업이 가능하도록 하며,
 * 데이터베이스의 데이터들을 이용하여 비지니스 로직을 수행하는 역할을 한다.
 * 비지니스 로직이 복잡한 경우에는 비지니스 로직만을 전담하는 클래스를
 * 별도로 둘 수 있다.
 */
public class PostManager {
	private static PostManager postManager = new PostManager();
	private PostDao postDao;

	private PostManager() {
		try {
			postDao = new PostDao();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static PostManager getInstance() {
		return postManager;
	}

	public int create(PostDto post) throws SQLException {
		return postDao.createPost(post);
	}

	public int update(PostDto post) throws SQLException {
		return postDao.updatePost(post);
	}

	public PostDto findPost(int id) throws SQLException, PostNotFoundException {
		PostDto post = postDao.findPost(id);

		if (post == null) {
			throw new PostNotFoundException("존재하지 않는 게시글입니다.");
		}
		return post;
	}
	
	public List<PostDto> findAllPost() throws SQLException, PostListNotFoundException{
		List<PostDto> list = postDao.findAllPost();
		
		if(list == null) {
			throw new PostListNotFoundException("게시글이 비었습니다.");
		}
		return list;
	}
	
	public int delete(int id) throws SQLException, PostNotFoundException {
		PostDto post = findPost(id);
		
		if(post != null) {
			throw new PostNotFoundException("존재하지 않는 게시글입니다.");
		}
		return postDao.deletePost(id);
	}

	public PostDao getPostDAO() {
		return this.postDao;
	}
}