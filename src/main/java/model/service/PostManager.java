package model.service;

import java.sql.SQLException;
import java.util.List;

import model.dto.PageDto;
import model.dto.PostDto;
import model.exception.PostListNotFoundException;
import model.exception.PostNotFoundException;
import model.dao.PostDao;

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
    
    public List<PostDto> getPostAllList(int pageNum, int amount) throws SQLException, PostListNotFoundException{
    	List<PostDto> list = postDao.getList(pageNum, amount);
    	
    	return list;
    }
    
    public PageDto getPost(int pageNum, int amount) throws SQLException {
    	int total = postDao.countPost();
    	PageDto pageDto = new PageDto(pageNum, amount, total);
    	
    	return pageDto;
    }
    

    public void delete(int id) throws SQLException, PostNotFoundException {
        PostDto post = findPost(id);
 
        if(post == null) {
            throw new PostNotFoundException("존재하지 않는 게시글입니다.");
        }
    }

    public PostDao getPostDAO() {
        return this.postDao;
    }
}