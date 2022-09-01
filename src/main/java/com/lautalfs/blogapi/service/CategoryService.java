package com.lautalfs.blogapi.service;

import com.lautalfs.blogapi.dto.CategoryDTO;

import java.util.List;

public interface CategoryService {

    CategoryDTO createCategory(CategoryDTO category);

    CategoryDTO updateCategory(CategoryDTO category, String id);

    CategoryDTO getCategory(String id);

    List<CategoryDTO> getAllCategories();

    void deleteCategory(String id);
}
