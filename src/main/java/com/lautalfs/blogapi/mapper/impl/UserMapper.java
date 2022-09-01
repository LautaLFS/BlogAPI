package com.lautalfs.blogapi.mapper.impl;

import com.lautalfs.blogapi.dto.UserDTO;
import com.lautalfs.blogapi.entity.UserEntity;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserMapper extends ModelMapper{
    public UserDTO toDto(UserEntity userEntity) {
        return map(userEntity, UserDTO.class);
    }
    public UserEntity toEntity(UserDTO userDTO) {
        return map(userDTO, UserEntity.class);
    }
}
