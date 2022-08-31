package com.lautalfs.blogapi.service.impl;

import com.lautalfs.blogapi.dto.UserDTO;
import com.lautalfs.blogapi.entity.UserEntity;
import com.lautalfs.blogapi.exception.ResourceNotFoundException;
import com.lautalfs.blogapi.mapper.impl.UserMapper;
import com.lautalfs.blogapi.repository.UserRepository;
import com.lautalfs.blogapi.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    @Override
    public UserDTO createUser(UserDTO user) {
        return userMapper.toDto(this.userRepository.save(userMapper.toEntity(user)));
    }

    @Override
    public UserDTO updateUser(UserDTO user, String id) {
        UserEntity userEntity = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", id));
        return userMapper.toDto(userRepository
                .save(userMapper.toUpdate(user, userEntity)));
    }

    @Override
    public UserDTO getUserById(String id) {
        UserEntity userEntity = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", id));
        return userMapper.toDto(userEntity);
    }

    @Override
    public List<UserDTO> getAllUsers() {
        List<UserEntity> users = this.userRepository.findAll();
        return users.stream().map(user -> userMapper.toDto(user))
                .collect(Collectors.toList());
    }

    @Override
    public void deleteUser(String id) {
        UserEntity user = this.userRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("user", "id", id));
        this.userRepository.delete(user);
    }
}
