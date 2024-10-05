package com.example.SocialMediaApplication.Repository;

import com.example.SocialMediaApplication.Entity.Post;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends BaseRepository<Post>{
    List<Post> findAllByOrderByCreationDateDesc(Pageable pageable);
}
