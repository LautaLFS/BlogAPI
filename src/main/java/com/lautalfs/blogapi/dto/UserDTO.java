package com.lautalfs.blogapi.dto;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;
import org.springframework.data.annotation.Id;

import javax.validation.constraints.Email;

@Getter
@Setter
@NoArgsConstructor
public class UserDTO {
    @Id
    private String id;
    @NonNull
    private String name;
    @Email
    private String email;
    @NonNull
    private String password;
    private String about;
}
