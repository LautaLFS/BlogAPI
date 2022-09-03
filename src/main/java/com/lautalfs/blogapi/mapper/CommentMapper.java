package com.lautalfs.blogapi.mapper;

import com.lautalfs.blogapi.dto.CommentDTO;
import com.lautalfs.blogapi.entity.CommentEntity;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class CommentMapper extends ModelMapper {
    public CommentDTO toDto(CommentEntity comment) {
        return map(comment, CommentDTO.class);
    }

    public CommentEntity toEntity(CommentDTO comment) {
        return map(comment, CommentEntity.class);
    }
}
