package com.example.SocialMediaApplication.Controller;

import com.example.SocialMediaApplication.Entity.BaseEntity;
import com.example.SocialMediaApplication.Extra.ApiResponse;
import com.example.SocialMediaApplication.Repository.BaseRepository;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

public abstract class BaseController<T extends BaseEntity> {
    public abstract BaseRepository<T> getRepository();


    @GetMapping("/")
    public ResponseEntity<?> getAll(Pageable pageable){
       return ResponseEntity.ok(getRepository().findAll(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getOne(@PathVariable("id") Long id){
        return ResponseEntity.ok(getRepository().findOne(id));
    }

    @CacheEvict(cacheNames = "posts", allEntries = true)
    @PostMapping("/add")
    public ResponseEntity<?> addNewItem(@RequestBody T temp){
        if (temp != null && temp.getId() != null) {
            return new ResponseEntity<>(new ApiResponse(false, "Id must be null"), HttpStatus.BAD_REQUEST);
        }
        try {
            getRepository().save(temp);
            return ResponseEntity.ok(new ApiResponse(true, "done"));

        } catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

  //  @CachePut(value = "posts")
    @CacheEvict(cacheNames = "posts", allEntries = true)
    @PostMapping("/update")
    public ResponseEntity<?> update(@RequestBody T temp){
        T old = getRepository().findOne(temp.getId());
        if(old == null) new ResponseEntity<>(new ApiResponse(false, "record not found"), HttpStatus.BAD_REQUEST);
        getRepository().save(temp);
        return ResponseEntity.ok(new ApiResponse(true, "done"));
    }

    @CacheEvict(cacheNames = "posts", allEntries = true)
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteEntity(@PathVariable("id") Long id) {
        T old = getRepository().findOne(id);
        if(old == null) new ResponseEntity<>(new ApiResponse(false, "record not found"), HttpStatus.BAD_REQUEST);
        getRepository().deleteById(id);
        return ResponseEntity.ok(new ApiResponse(true, "done"));
    }
}