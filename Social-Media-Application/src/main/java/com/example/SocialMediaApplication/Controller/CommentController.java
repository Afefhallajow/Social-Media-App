package com.example.SocialMediaApplication.Controller;

import com.example.SocialMediaApplication.Entity.Comment;
import com.example.SocialMediaApplication.Extra.ApiResponse;
import com.example.SocialMediaApplication.Repository.BaseRepository;
import com.example.SocialMediaApplication.Repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/comments")
public class CommentController extends BaseController<Comment> {

    @Autowired
    private CommentRepository commentRepository;
    @Override
    public BaseRepository<Comment> getRepository() {return commentRepository;}

    @Override
    public ResponseEntity<?> addNewItem(Comment temp) {
        if (temp.getPost() == null || temp.getContent() == null) {
            return ResponseEntity.badRequest().body(new ApiResponse(false, "Post and Content should not be null"));
        }
        return super.addNewItem(temp);
    }

    @Override
    public ResponseEntity<?> update(Comment temp) {
        Comment comment = commentRepository.findOne(temp.getId());
        if (comment == null) return ResponseEntity.badRequest().body(new ApiResponse(false,"comment not found"));
        if (temp.getContent() == null) {
            return ResponseEntity.badRequest().body(new ApiResponse(false,"Content should not be null"));
        }
        comment.setContent(temp.getContent());
        getRepository().save(comment);
        return ResponseEntity.ok(new ApiResponse(true,"done"));
    }
}