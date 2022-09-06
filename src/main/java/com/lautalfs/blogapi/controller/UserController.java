package com.lautalfs.blogapi.controller;

import com.lautalfs.blogapi.dto.ApiResponse;
import com.lautalfs.blogapi.dto.UserDTO;
import com.lautalfs.blogapi.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<UserDTO> createUser(@Valid @RequestBody UserDTO user){
        UserDTO userDTO = this.userService.createUser(user);
        return ResponseEntity.ok(userDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> findById(@PathVariable String id){
        UserDTO userDTO = this.userService.getUserById(id);
        return ResponseEntity.ok(userDTO);
    }

    @GetMapping()
    public ResponseEntity<List<UserDTO>> findById(){
        List<UserDTO> userDTOs = this.userService.getAllUsers();
        return ResponseEntity.ok(userDTOs);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<UserDTO> updateUser(@Valid @PathVariable String id, @RequestBody UserDTO user){
        UserDTO userDTO = this.userService.updateUser(user, id);
        return ResponseEntity.ok(userDTO);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteUser(@PathVariable String id){
        this.userService.deleteUser(id);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(new ApiResponse("User deleted successfully", true));
    }
}
