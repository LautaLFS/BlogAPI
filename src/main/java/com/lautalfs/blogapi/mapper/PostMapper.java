package com.lautalfs.blogapi.mapper;

import com.lautalfs.blogapi.dto.PostDTO;
import com.lautalfs.blogapi.entity.PostEntity;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class PostMapper extends ModelMapper {
    public PostDTO toDto(PostEntity postEntity) {
        return map(postEntity, PostDTO.class);
    }

    public PostEntity toEntity(PostDTO postDTO) {
        return map(postDTO, PostEntity.class);
    }
}
