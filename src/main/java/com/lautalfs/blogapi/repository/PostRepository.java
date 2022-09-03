package com.lautalfs.blogapi.repository;

import com.lautalfs.blogapi.entity.CategoryEntity;
import com.lautalfs.blogapi.entity.PostEntity;
import com.lautalfs.blogapi.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<PostEntity, String> {
    List<PostEntity> findByCategory(CategoryEntity category);
    List<PostEntity> findByUser(UserEntity user);

    List<PostEntity> findByTitleContaining(String title);
}
