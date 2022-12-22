package model.service;

import java.sql.SQLException;
import java.util.List;

import model.dao.mybatis.ReplyDao;
import model.dto.ReplyDto;
import model.exception.ReplyListNotFoundException;
import model.exception.ReplyNotFoundException;



public class ReplyManager {
	private static ReplyManager replyManager = new ReplyManager();
    private ReplyDao replyDao;

    private ReplyManager() {
        try {
            replyDao = new ReplyDao();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static ReplyManager getInstance() {
        return replyManager;
    }

    public int create(ReplyDto replyDto) throws SQLException {
    	System.out.println("-----manager");
        return replyDao.create(replyDto);
    }

    public int update(ReplyDto replyDto) throws SQLException {
        return replyDao.updateReply(replyDto);
    }

    public ReplyDto findReply(int id) throws SQLException, ReplyNotFoundException {
    	ReplyDto reply = replyDao.findReply(id);
        
        if (reply == null) {
            throw new ReplyNotFoundException("존재하지 않는 댓글입니다.");
        }
        return reply;
    }

    public List<ReplyDto> findAllReply(int postId) throws SQLException, ReplyListNotFoundException{
        List<ReplyDto> list = replyDao.findAllReply(postId);

        if(list == null) {
            throw new ReplyListNotFoundException("댓글이 비었습니다.");
        }
        return list;
    }
    
    public void delete(int id) throws SQLException, ReplyNotFoundException {
        ReplyDto reply = findReply(id);
 
        if(reply == null) {
            throw new ReplyNotFoundException("존재하지 않는 게시글입니다.");
        }
        
        replyDao.deleteReply(id);
    }
    
    public ReplyDao getReplyDAO() {
        return this.replyDao;
    }
}
