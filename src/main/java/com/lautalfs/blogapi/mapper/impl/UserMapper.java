package com.lautalfs.blogapi.mapper.impl;

import com.lautalfs.blogapi.dto.UserDTO;
import com.lautalfs.blogapi.entity.UserEntity;
import com.lautalfs.blogapi.mapper.Mapper;
import org.springframework.stereotype.Component;

@Component
public class UserMapper implements Mapper<UserDTO, UserEntity> {
    @Override
    public UserDTO toDto(UserEntity userEntity) {
        var userDTO = new UserDTO();
        userDTO.setId(userEntity.getId());
        userDTO.setName(userEntity.getName());
        userDTO.setEmail(userEntity.getEmail());
        userDTO.setPassword(userEntity.getPassword());
        userDTO.setAbout(userEntity.getAbout());
        return userDTO;
    }

    @Override
    public UserEntity toEntity(UserDTO userDTO) {
        var userEntity = new UserEntity();
        userEntity.setId(userEntity.getId());
        userEntity.setName(userDTO.getName());
        userEntity.setEmail(userDTO.getEmail());
        userEntity.setPassword(userDTO.getPassword());
        userEntity.setAbout(userDTO.getAbout());
        return userEntity;
    }

    @Override
    public UserEntity toUpdate(UserDTO userDTO, UserEntity user) {
        user.setId(userDTO.getId());
        user.setName(userDTO.getName());
        user.setEmail(userDTO.getEmail());
        user.setPassword(userDTO.getPassword());
        user.setAbout(userDTO.getAbout());
        return user;
    }

}
