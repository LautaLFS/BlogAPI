package com.lautalfs.blogapi.service;


import com.lautalfs.blogapi.dto.PostDTO;
import com.lautalfs.blogapi.dto.PostResponse;

import java.util.List;

public interface PostService {

    PostDTO createPost(PostDTO category, String userId, String categoryId);

    PostDTO updatePost(PostDTO category, String id);

    PostDTO getPostById(String id);

    PostResponse getAllPosts(int pageNumber, int pageSize, String sortBy);

    void deletePost(String id);

    List<PostDTO> getAllPostsByCategory(String id);

    List<PostDTO> getAllPostsByUser(String id);

    List<PostDTO> searchPosts(String keyword);

}
