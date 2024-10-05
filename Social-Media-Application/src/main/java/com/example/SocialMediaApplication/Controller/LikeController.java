package com.example.SocialMediaApplication.Controller;

import com.example.SocialMediaApplication.Entity.Likes;
import com.example.SocialMediaApplication.Entity.Post;
import com.example.SocialMediaApplication.Extra.ApiResponse;
import com.example.SocialMediaApplication.Repository.BaseRepository;
import com.example.SocialMediaApplication.Repository.LikeRepository;
import com.example.SocialMediaApplication.Repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/likes")
public class LikeController extends BaseController<Likes> {

    @Autowired
    private LikeRepository likeRepository;
    @Autowired
    private PostRepository postRepository;
    @Override
    public BaseRepository<Likes> getRepository() {return likeRepository;}

    @Override
    public ResponseEntity<?> addNewItem(Likes temp) {
        if (temp.getPost() == null) return ResponseEntity.badRequest().body(new ApiResponse(false, "Post should not be null"));
        Post oldPost = postRepository.findOne(temp.getPost().getId());
        if(oldPost == null) return ResponseEntity.badRequest().body(new ApiResponse(false,"Post not found"));
        return super.addNewItem(temp);
    }
}
