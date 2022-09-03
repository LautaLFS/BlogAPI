package com.lautalfs.blogapi.repository;

import com.lautalfs.blogapi.entity.CommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<CommentEntity, String> {
}
