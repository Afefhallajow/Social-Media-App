package com.example.SocialMediaApplication.Entity;

import com.example.SocialMediaApplication.Extra.IdSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import jakarta.persistence.*;

@Entity
public class Comment extends BaseEntity{

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonSerialize(using = IdSerializer.class)
    private Post post;

    @Lob
    private String content;

    public Post getPost() {return post;}

    public void setPost(Post post) {this.post = post;}

    public String getContent() {return content;}

    public void setContent(String content) {this.content = content;}
}