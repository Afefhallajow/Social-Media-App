package com.example.SocialMediaApplication.Entity;

import com.example.SocialMediaApplication.Extra.IdSerializer;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import jakarta.persistence.*;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Date;

@MappedSuperclass
public class BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column
    Date creationDate;

    @Column
    Date modificationDate;

    @Column
    int version;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonSerialize(using = IdSerializer.class)
    private User user;

    public Long getId() {return id;}

    public void setId(Long id) {this.id = id;}

    public Date getCreationDate() {return creationDate;}

    public void setCreationDate(Date creationDate) { this.creationDate = creationDate;}

    public Date getModificationDate() {return modificationDate;}

    public void setModificationDate(Date modificationDate) {this.modificationDate = modificationDate;}

    public int getVersion() {return version;}

    public void setVersion(int version) {this.version = version;}

    public User getUser() {return user;}

    public void setUser(User user) {this.user = user;}

    @PrePersist
    void beforeInsert(){
        this.version = 0;
        this.creationDate = new Date();
        this.modificationDate = new Date();
        this.user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    @PreUpdate
    void beforeUpdate(){
        this.version = this.version + 1;
        this.modificationDate = new Date();
    }
}