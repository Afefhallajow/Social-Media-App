package com.example.SocialMediaApplication.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface BaseRepository<T> extends JpaRepository<T, Long> {
    default T findOne(Long id) {
        return this.findById(id).orElse((T) null);
    }
}
