package com.lautalfs.blogapi.security.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JwtAuthRequest {
    private String username;
    private String password;
}
