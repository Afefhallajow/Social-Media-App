package com.example.SocialMediaApplication.Entity;

import com.example.SocialMediaApplication.Extra.CommentSerializer;
import com.example.SocialMediaApplication.Extra.LikeSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class Post extends BaseEntity {

    @Lob
    private String content;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
    @JsonSerialize(using = CommentSerializer.class)
    private List<Comment> comments;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
    @JsonSerialize(using = LikeSerializer.class)
    private List<Likes> likes;

    // Getters, Setters, etc.

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public List<Likes> getLikes() {
        return likes;
    }

    public void setLikes(List<Likes> likes) {
        this.likes = likes;
    }
}
