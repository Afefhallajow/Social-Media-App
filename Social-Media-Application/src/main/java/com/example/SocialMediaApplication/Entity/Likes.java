package com.example.SocialMediaApplication.Entity;

import com.example.SocialMediaApplication.Extra.IdSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import jakarta.persistence.*;

@Entity
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = {"post_id", "user_id"})})
public class Likes extends BaseEntity {
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonSerialize(using = IdSerializer.class)
    private Post post;

    public Post getPost() {return post;}

    public void setPost(Post post) {this.post = post;}
}