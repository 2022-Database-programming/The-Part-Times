package model.dto;

import java.sql.Timestamp;

public class ReplyDto {
    private int id;
    private int postId;
    private int commentId;
    private int isAnonymous;
    private String content;
    private int likes;
    private int layer;
    private Timestamp createdAt;
    private Timestamp updatedAt;

    public ReplyDto() {
    }

    public ReplyDto(int id, int postId, int commentId, int isAnonymous, String content, int likes, int layer, Timestamp createdAt, Timestamp updatedAt) {
        this.id = id;
        this.postId = postId;
        this.commentId = commentId;
        this.isAnonymous = isAnonymous;
        this.content = content;
        this.likes = likes;
        this.layer = layer;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public int getId() {
        return id;
    }

    public int getPostId() {
        return postId;
    }

    public int getCommentId() {
        return commentId;
    }

    public int getIsAnonymous() {
        return isAnonymous;
    }

    public String getContent() {
        return content;
    }

    public int getLikes() {
        return likes;
    }

    public int getLayer() {
        return layer;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public Timestamp getUpdatedAt() {
        return updatedAt;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }

    public void setCommentId(int commentId) {
        this.commentId = commentId;
    }

    public void setIsAnonymous(int isAnonymous) {
        this.isAnonymous = isAnonymous;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public void setLayer(int layer) {
        this.layer = layer;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public void setUpdatedAt(Timestamp updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public String toString() {
        return "CommentDto{" +
                "id=" + id +
                ", postId=" + postId +
                ", commentId=" + commentId +
                ", isAnonymous=" + isAnonymous +
                ", content='" + content + '\'' +
                ", likes=" + likes +
                ", layer=" + layer +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}