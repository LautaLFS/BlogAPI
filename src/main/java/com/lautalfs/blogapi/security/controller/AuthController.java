package com.lautalfs.blogapi.security.controller;

import com.lautalfs.blogapi.dto.UserDTO;
import com.lautalfs.blogapi.security.dto.JwtAuthRequest;
import com.lautalfs.blogapi.security.dto.JwtAuthResponse;
import com.lautalfs.blogapi.security.utils.JwtUtils;
import com.lautalfs.blogapi.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserDetailsService userDetailsService;
    private final JwtUtils jwtUtil;
    private final AuthenticationManager authenticationManager;

    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<UserDTO> registerUser(@Valid @RequestBody UserDTO userDTO) {

        return ResponseEntity.status(HttpStatus.CREATED).body(
                userService.registerUser(userDTO)
        );
    }

    @PostMapping("/login")
    public ResponseEntity<JwtAuthResponse> createToken(
            @RequestBody JwtAuthRequest request
    ) throws BadCredentialsException {
        this.authenticate(request.getUsername(), request.getPassword());

        JwtAuthResponse jwtAuthResponse = new JwtAuthResponse();
        jwtAuthResponse.setJwt(this.jwtUtil.generateToken(userDetailsService.loadUserByUsername(request.getUsername())));
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(jwtAuthResponse);
    }

    private void authenticate(String username, String password) throws BadCredentialsException {
        UsernamePasswordAuthenticationToken authenticate = new UsernamePasswordAuthenticationToken(username, password);
        try {
            authenticationManager.authenticate(authenticate);
        }catch(BadCredentialsException ex){
            throw new BadCredentialsException("Authentication failed: invalid username or password");
        }

    }
}
