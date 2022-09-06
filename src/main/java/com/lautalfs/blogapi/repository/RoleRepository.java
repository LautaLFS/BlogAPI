package com.lautalfs.blogapi.repository;

import com.lautalfs.blogapi.entity.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<RoleEntity, String> {
}
