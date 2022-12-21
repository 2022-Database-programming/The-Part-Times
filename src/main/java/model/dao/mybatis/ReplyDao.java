package model.dao.mybatis;

import model.dto.ReplyDto;
//import util.JDBCUtil;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.sql.Timestamp;
//import java.util.ArrayList;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import model.dao.mybatis.mapper.ReplyMapper;

public class ReplyDao {
	private SqlSessionFactory sqlSessionFactory;

	public ReplyDao() {
		String resource = "mybatis-config.xml";
		InputStream inputStream;
		try {
			inputStream = Resources.getResourceAsStream(resource);
		} catch (IOException e) {
			throw new IllegalArgumentException(e);
		}
		sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
	}

	public int create(ReplyDto replyDto) {
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
	public int firstCreateReply(ReplyDto replyDto){
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			int result = sqlSession.getMapper(ReplyMapper.class).insertFirstReply(replyDto);
			if (result > 0) {
				sqlSession.commit();
			} 
			return result;
		} finally {
			sqlSession.close();
		}
	}


	public int createReply(ReplyDto replyDto) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			int result = sqlSession.getMapper(ReplyMapper.class).insertReply(replyDto);
			if (result > 0) {
				sqlSession.commit();
			} 
			return result;
		} finally {
			sqlSession.close();
		}
	}


	//댓글 수정
	public int updateReply(ReplyDto replyDto) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		int result;
		
		try {
			ReplyDto findReplyDto = findReply(replyDto.getId());
			
			if(findReplyDto.getLikes() == replyDto.getLikes()) {
				result = sqlSession.getMapper(ReplyMapper.class).updateReply(replyDto, findReplyDto.getId());
			} else { // 좋아요 추가
				result = sqlSession.getMapper(ReplyMapper.class).updateLikesReply(replyDto, findReplyDto.getId());
			}
			
			if (result > 0) {
				sqlSession.commit();
			} 
			return result;
		} finally {
			sqlSession.close();
		}
	}

	//댓글 삭제
	public int deleteReply(int id) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			int result = sqlSession.getMapper(ReplyMapper.class).deleteReply(id);
			if (result > 0) {
				sqlSession.commit();
			} 
			return result;
		} finally {
			sqlSession.close();
		}
	}

	//댓글 조회
	public ReplyDto findReply(int id) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			return sqlSession.getMapper(ReplyMapper.class).selectReply(id);
		} finally {
			sqlSession.close();
		}
	}


	//댓글 전체 조회
	public List<ReplyDto> findAllReply(int postId) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			return sqlSession.getMapper(ReplyMapper.class).selectAllReply(postId);
		} finally {
			sqlSession.close();
		}
	}


	//댓글 개수 조회
	public int countReply(int postId) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			int result = sqlSession.getMapper(ReplyMapper.class).countReply(postId);
			return result;
		} finally {
			sqlSession.close();
		}
	}


}