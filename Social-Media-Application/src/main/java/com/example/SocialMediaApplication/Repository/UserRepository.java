package com.example.SocialMediaApplication.Repository;

import com.example.SocialMediaApplication.Entity.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends BaseRepository<User>{
    User findByUserName(String username);
}
