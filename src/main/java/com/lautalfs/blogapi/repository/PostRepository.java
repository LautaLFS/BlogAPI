package com.lautalfs.blogapi.repository;

import com.lautalfs.blogapi.entity.PostEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<PostEntity, String> {
}
