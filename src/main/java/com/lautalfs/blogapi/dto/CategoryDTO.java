package com.lautalfs.blogapi.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
public class CategoryDTO {


    private String id;
    @NotBlank
    @Size(max=10, message = "Must have max 10 characters")
    private String categoryTitle;
    @NotBlank
    @Size(min=4, message = "Must have min 4 characters")
    private String categoryDescription;
}
