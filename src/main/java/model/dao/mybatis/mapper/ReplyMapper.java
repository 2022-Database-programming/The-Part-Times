package model.dao.mybatis.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;

import model.dto.ReplyDto;

public interface ReplyMapper { 
	
	public int insertFirstReply(ReplyDto replayDto);   
	
	public int insertReply(ReplyDto replayDto);   
 
	public int updateReply(@Param("replyDto") ReplyDto replayDto, @Param("findReplyId") int findReplyId);
	
	public int updateLikesReply(@Param("replyDto") ReplyDto replayDto, @Param("findReplyId") int findReplyId);

	public int deleteReply(int id);

	public ReplyDto selectReply(int id);
	
	public List<ReplyDto> selectAllReply(int postId);

	public int countReply(int postId);
}
