package com.lautalfs.blogapi.service;

import com.lautalfs.blogapi.dto.UserDTO;

import java.util.List;

public interface UserService {

    UserDTO createUser(UserDTO user);

    UserDTO updateUser(UserDTO user, String id);

    UserDTO getUserById(String id);

    List<UserDTO> getAllUsers();

    void deleteUser(String id);
}
