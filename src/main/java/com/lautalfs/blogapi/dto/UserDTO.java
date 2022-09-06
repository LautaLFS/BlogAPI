package com.lautalfs.blogapi.dto;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
public class UserDTO {
    @Id
    @JsonIgnore
    private String id;

    @NotEmpty(message = "must not be empty")
    @Size(min = 4, message = "Must have 4 characters")
    private String name;

    @Email(message = "must be a valid e-mail address")
    private String email;

    @NotEmpty(message = "must not be empty")
    @Size(min = 4,max = 10, message = "Al least 4 character, max 10")
    private String password;

    @NotEmpty
    private String about;
}
