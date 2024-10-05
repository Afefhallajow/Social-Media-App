package com.example.SocialMediaApplication.Controller;

import com.example.SocialMediaApplication.Entity.Post;
import com.example.SocialMediaApplication.Extra.ApiResponse;
import com.example.SocialMediaApplication.Repository.BaseRepository;
import com.example.SocialMediaApplication.Repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/posts")
public class PostController extends BaseController<Post> {
    @Autowired
    private PostRepository postRepository;
    @Override
    public BaseRepository<Post> getRepository() {return postRepository;}
    @Cacheable("posts")
    @Override
    public ResponseEntity<?> getAll(Pageable pageable) {
        return ResponseEntity.ok(postRepository.findAllByOrderByCreationDateDesc(pageable));
    }

    @Override
    public ResponseEntity<?> update(Post temp) {
        Post post = postRepository.findOne(temp.getId());
        if (post == null) return ResponseEntity.badRequest().body(new ApiResponse(false,"Post not found"));
        if (temp.getContent() == null) {
            return ResponseEntity.badRequest().body(new ApiResponse(false, "Content should not be null"));
        }
        post.setContent(temp.getContent());
        getRepository().save(post);
        return ResponseEntity.ok(new ApiResponse(true, "done"));
    }

    @Cacheable(value = "posts", key = "#id")
    @Override
    public ResponseEntity<?> getOne(Long id){
        return super.getOne(id);
    }
}
