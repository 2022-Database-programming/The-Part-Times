package model.dto;

import java.io.Serializable;
import java.sql.Timestamp;

@SuppressWarnings("serial")
public class ReplyDto implements Serializable{
    private int id;
    private int postId;
    private int replyId;
    private int isAnonymous;
    private String content;
    private int likes;
    private int layer;
    private Timestamp createdAt;
    private Timestamp updatedAt;
    private int memberId;
    private String name;
    
    public ReplyDto() {
    	
    }
    
    public ReplyDto(int postId, int replyId, int isAnonymous, String content, int likes, int layer, int memberId, String name) {
		super();
		this.postId = postId;
		this.replyId = replyId;
		this.isAnonymous = isAnonymous;
		this.content = content;
		this.likes = likes;
		this.layer = layer;
		this.memberId = memberId;
		this.name = name;
	}
    

	public ReplyDto(int postId, int isAnonymous, String content, int likes, int layer, int memberId, String name) {
		super();
		this.postId = postId;
		this.isAnonymous = isAnonymous;
		this.content = content;
		this.likes = likes;
		this.layer = layer;
		this.memberId = memberId;
		this.name = name;
	}

	public ReplyDto(int id, int postId, int replyId, int isAnonymous, String content, int likes, int layer, Timestamp createdAt, Timestamp updatedAt, int memberId, String name) {
        this.id = id;
        this.postId = postId;
        this.replyId = replyId;
        this.isAnonymous = isAnonymous;
        this.content = content;
        this.likes = likes;
        this.layer = layer;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.memberId = memberId;
        this.name = name;
    }

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getPostId() {
		return postId;
	}

	public void setPostId(int postId) {
		this.postId = postId;
	}

	public int getReplyId() {
		return replyId;
	}

	public void setReplyId(int replyId) {
		this.replyId = replyId;
	}

	public int getMemberId() {
		return memberId;
	}

	public void setMemberId(int memberId) {
		this.memberId = memberId;
	}

	public int getIsAnonymous() {
		return isAnonymous;
	}

	public void setIsAnonymous(int isAnonymous) {
		this.isAnonymous = isAnonymous;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getLikes() {
		return likes;
	}

	public void setLikes(int likes) {
		this.likes = likes;
	}

	public int getLayer() {
		return layer;
	}

	public void setLayer(int layer) {
		this.layer = layer;
	}

	public Timestamp getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}

	public Timestamp getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Timestamp updatedAt) {
		this.updatedAt = updatedAt;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}