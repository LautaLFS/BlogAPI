package com.lautalfs.blogapi.mapper;

import com.lautalfs.blogapi.dto.CategoryDTO;
import com.lautalfs.blogapi.entity.CategoryEntity;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CategoryMapper extends ModelMapper {

    public CategoryDTO toDto(CategoryEntity categoryEntity) {
        return map(categoryEntity, CategoryDTO.class);
    }

    public CategoryEntity toEntity(CategoryDTO categoryEntity) {
        return map(categoryEntity, CategoryEntity.class);
    }
}
