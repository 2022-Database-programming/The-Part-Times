package model.dto;

import java.sql.Timestamp;

public class PostDto {
    private int id;
    private int memberId;
    private int isAnonymous;
    private String type;
    private String title;
    private String content;
    private int likes;
    private int views;
    private Timestamp createdAt;
    private Timestamp updatedAt;

    public PostDto() {
    }
    
    public PostDto(int memberId, int isAnonymous, String type, String title, String content, int likes, int views) {
		super();
		this.memberId = memberId;
		this.isAnonymous = isAnonymous;
		this.type = type;
		this.title = title;
		this.content = content;
		this.likes = likes;
		this.views = views;
	}
    
    public PostDto(int id, int memberId, int isAnonymous, String type, String title, String content, int likes, int views) {
		super();
		this.id = id;
		this.memberId = memberId;
		this.isAnonymous = isAnonymous;
		this.type = type;
		this.title = title;
		this.content = content;
		this.likes = likes;
		this.views = views;
	}

    public PostDto(int id, int memberId, int isAnonymous, String type, String title, String content, int likes,
			int views, Timestamp createdAt, Timestamp updatedAt) {
		super();
		this.id = id;
		this.memberId = memberId;
		this.isAnonymous = isAnonymous;
		this.type = type;
		this.title = title;
		this.content = content;
		this.likes = likes;
		this.views = views;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
	}

	public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public int getViews() {
        return views;
    }

    public void setViews(int views) {
        this.views = views;
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}