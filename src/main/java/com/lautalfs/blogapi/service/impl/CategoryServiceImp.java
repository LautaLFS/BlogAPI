package com.lautalfs.blogapi.service.impl;

import com.lautalfs.blogapi.dto.CategoryDTO;
import com.lautalfs.blogapi.entity.CategoryEntity;
import com.lautalfs.blogapi.exception.ResourceNotFoundException;
import com.lautalfs.blogapi.mapper.CategoryMapper;
import com.lautalfs.blogapi.repository.CategoryRepository;
import com.lautalfs.blogapi.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryServiceImp implements CategoryService {

    private static final String RESOURCE_NAME = "Category";

    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    @Override
    public CategoryDTO createCategory(CategoryDTO category) {
        return categoryMapper.toDto(categoryRepository.save(categoryMapper.toEntity(category)));
    }

    @Override
    public CategoryDTO updateCategory(CategoryDTO category, String id) {
        CategoryEntity categoryEntity = categoryRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException(RESOURCE_NAME, "id", id));
        categoryEntity.setCategoryTitle(category.getCategoryTitle());
        categoryEntity.setCategoryDescription(category.getCategoryDescription());
        return categoryMapper.toDto(categoryRepository.save(categoryEntity));
    }

    @Override
    public CategoryDTO getCategory(String id) {
        CategoryEntity categoryEntity = categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(RESOURCE_NAME, "id", id));
        return categoryMapper.toDto(categoryEntity);
    }

    @Override
    public List<CategoryDTO> getAllCategories() {
        List<CategoryEntity> categories = this.categoryRepository.findAll();
        return categories.stream().map(categoryMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteCategory(String id) {
        CategoryEntity category = this.categoryRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException(RESOURCE_NAME, "id", id));
        this.categoryRepository.delete(category);
    }
}
