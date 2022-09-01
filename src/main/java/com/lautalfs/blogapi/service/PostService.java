package com.lautalfs.blogapi.service;


import com.lautalfs.blogapi.dto.PostDTO;

import java.util.List;

public interface PostService {

    PostDTO createPost(PostDTO category, String userId, String categoryId);

    PostDTO updatePost(PostDTO category, String id);

    PostDTO getPostById(String id);

    List<PostDTO> getAllPosts();

    void deletePost(String id);

    List<PostDTO> getAllPostsByCategory(String id);

    List<PostDTO> getAllPostsByUser(String id);
}
