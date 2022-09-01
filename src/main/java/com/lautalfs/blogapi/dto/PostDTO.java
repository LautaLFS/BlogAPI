package com.lautalfs.blogapi.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class PostDTO {

    private String title;

    private String content;

    private String imageName;

    private Date addedDate;

    private UserDTO user;

    private CategoryDTO category;
}
