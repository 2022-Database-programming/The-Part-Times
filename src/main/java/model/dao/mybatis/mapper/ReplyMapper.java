package model.dao.mybatis.mapper;

import java.util.List;

import model.dto.ReplyDto;

public interface ReplyMapper { 
	
	public int insertFirstReply(ReplyDto replayDto);   
	
	public int insertReply(ReplyDto replayDto);   
 
	public int updateReply(ReplyDto replayDto, int findReplyId);
	
	public int updateLikesReply(ReplyDto replayDto, int findReplyId);

	public int deleteReply(int id);

	public ReplyDto selectReply(int id);
	
	public List<ReplyDto> selectAllReply(int postId);

	public int countReply(int postId);
}
