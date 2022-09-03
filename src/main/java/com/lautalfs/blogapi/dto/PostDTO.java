package com.lautalfs.blogapi.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Date;

@Getter
@Setter
public class PostDTO {
    @NotEmpty
    @Size(min = 4, max=12, message = "Min must have 4 characters and max 12")
    private String postTitle;

    @NotEmpty
    private String content;

    private String imageName;

    private Date addedDate;

    private UserDTO user;

    private CategoryDTO category;
}
