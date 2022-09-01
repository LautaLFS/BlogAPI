package com.lautalfs.blogapi.repository;

import com.lautalfs.blogapi.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, String> {
}
