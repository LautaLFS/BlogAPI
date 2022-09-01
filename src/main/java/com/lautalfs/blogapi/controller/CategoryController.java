package com.lautalfs.blogapi.controller;

import com.lautalfs.blogapi.dto.ApiResponse;
import com.lautalfs.blogapi.dto.CategoryDTO;
import com.lautalfs.blogapi.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/categories")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @PostMapping("/save")
    public ResponseEntity<CategoryDTO> createCategory(@Valid @RequestBody CategoryDTO category){
        CategoryDTO categoryDTO = this.categoryService.createCategory(category);
        return ResponseEntity.ok(categoryDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryDTO> findById(@PathVariable String id){
        CategoryDTO categoryDTO = this.categoryService.getCategory(id);
        return ResponseEntity.ok(categoryDTO);
    }

    @GetMapping()
    public ResponseEntity<List<CategoryDTO>> findById(){
        List<CategoryDTO> categoryDTOs = this.categoryService.getAllCategories();
        return ResponseEntity.ok(categoryDTOs);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<CategoryDTO> updateCategory(@Valid @PathVariable String id, @RequestBody CategoryDTO category){
        CategoryDTO categoryDTO = this.categoryService.updateCategory(category, id);
        return ResponseEntity.ok(categoryDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteUser(@PathVariable String id){
        this.categoryService.deleteCategory(id);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(new ApiResponse("User deleted successfully", true));
    }
}
