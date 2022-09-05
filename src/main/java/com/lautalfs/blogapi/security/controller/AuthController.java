package com.lautalfs.blogapi.security.controller;

import com.lautalfs.blogapi.security.dto.JwtAuthRequest;
import com.lautalfs.blogapi.security.dto.JwtAuthResponse;
import com.lautalfs.blogapi.security.utils.JwtUtils;
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

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserDetailsService userDetailsService;
    private final JwtUtils jwtUtil;
    private final AuthenticationManager authenticationManager;

    @PostMapping("/login")
    public ResponseEntity<JwtAuthResponse> createToken(
            @RequestBody JwtAuthRequest request
    ) {
        this.authenticate(request.getUsername(), request.getPassword());

        JwtAuthResponse jwtAuthResponse = new JwtAuthResponse();
        jwtAuthResponse.setJwt(this.jwtUtil.generateToken(userDetailsService.loadUserByUsername(request.getUsername())));
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(jwtAuthResponse);
    }

    private void authenticate(String username, String password) {
        UsernamePasswordAuthenticationToken authenticate = new UsernamePasswordAuthenticationToken(username, password);
        try {
            authenticationManager.authenticate(authenticate);
        }catch(BadCredentialsException ex){
            logger.error("Authentication failed");
        }

    }
}
