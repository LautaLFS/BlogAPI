package com.lautalfs.blogapi.controller;

import com.lautalfs.blogapi.config.AppConstants;
import com.lautalfs.blogapi.dto.ApiResponse;
import com.lautalfs.blogapi.dto.PostDTO;
import com.lautalfs.blogapi.dto.PostResponse;
import com.lautalfs.blogapi.service.FileService;
import com.lautalfs.blogapi.service.PostService;
import lombok.RequiredArgsConstructor;
import org.hibernate.engine.jdbc.StreamUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@RestController
@RequestMapping("/api/post")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;
    private final FileService fileService;
    @Value("${project.image}")
    private String path;

    @PostMapping("/user/{userId}/category/{categoryId}/post")
    public ResponseEntity<PostDTO> createPost(@Valid @RequestBody PostDTO postDTO,
                                        @PathVariable String categoryId,
                                        @PathVariable String userId){
        return ResponseEntity.status(HttpStatus.CREATED).body(
                postService.createPost(postDTO, userId, categoryId)
        );
    }

    @GetMapping("/user/{userId}/post")
    public ResponseEntity<List<PostDTO>> getAllPostsByUser(@PathVariable String userId){
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(
                postService.getAllPostsByUser(userId)
        );
    }

    @GetMapping("/category/{categoryId}/post")
    public ResponseEntity<List<PostDTO>> getAllPostsByCategory(@PathVariable String categoryId){
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(
                postService.getAllPostsByCategory(categoryId)
        );
    }

    @GetMapping()
    public ResponseEntity<PostResponse> getAllPosts(
            @RequestParam(value = "pageNumber", defaultValue = AppConstants.PAGE_NUMBER, required = false) int pageNumber,
            @RequestParam(value = "pageSize", defaultValue = AppConstants.PAGE_SIZE, required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = AppConstants.SORT_BY, required = false) String sortBy){
        return ResponseEntity.status(HttpStatus.OK).body(
                postService.getAllPosts(pageNumber, pageSize, sortBy)
        );
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<PostDTO> getAllPostsById(@PathVariable String id){
        return ResponseEntity.status(HttpStatus.OK).body(
                postService.getPostById(id)
        );
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<PostDTO> updatePost(@Valid @PathVariable String id, @RequestBody PostDTO postDTO){
        var post = this.postService.updatePost(postDTO, id);
        return ResponseEntity.ok(post);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deletePost(@PathVariable String id){
        this.postService.deletePost(id);
        return ResponseEntity.status(HttpStatus.ACCEPTED)
                .body(new ApiResponse("Post deleted successfully", true));
    }

    @GetMapping("/search/{keywords}")
    public ResponseEntity<List<PostDTO>> searchPost(@PathVariable String keywords){
        return ResponseEntity.status(HttpStatus.ACCEPTED)
                .body(postService.searchPosts(keywords));
    }

    @PostMapping("/upload/{id}")
    public ResponseEntity<PostDTO> uploadImage(@RequestParam("image") MultipartFile upload,
                                                     @PathVariable String id) throws IOException {
        var fileName = this.fileService.uploadImage(path, upload);
        var postById = this.postService.getPostById(id);
        postById.setImageName(fileName);
        return ResponseEntity.status(HttpStatus.OK).body(
                postService.updatePost(postById, id)
        );
    }

    @GetMapping(value = "/image/{imageName}",produces = MediaType.IMAGE_JPEG_VALUE)
    public void downloadImage(@PathVariable("imageName") String imageName,
                              HttpServletResponse response) throws IOException{
        InputStream resource = fileService.getResource(path, imageName);
        response.setContentType(MediaType.IMAGE_JPEG_VALUE);
        StreamUtils.copy(resource, response.getOutputStream());
    }
}
