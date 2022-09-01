package com.lautalfs.blogapi.repository;

import com.lautalfs.blogapi.entity.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<CategoryEntity, String> {
}
