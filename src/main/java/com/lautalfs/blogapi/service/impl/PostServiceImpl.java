package com.lautalfs.blogapi.service.impl;

import com.lautalfs.blogapi.dto.PostDTO;
import com.lautalfs.blogapi.entity.CategoryEntity;
import com.lautalfs.blogapi.entity.PostEntity;
import com.lautalfs.blogapi.exception.ResourceNotFoundException;
import com.lautalfs.blogapi.mapper.CategoryMapper;
import com.lautalfs.blogapi.mapper.PostMapper;
import com.lautalfs.blogapi.mapper.UserMapper;
import com.lautalfs.blogapi.repository.CategoryRepository;
import com.lautalfs.blogapi.repository.PostRepository;
import com.lautalfs.blogapi.repository.UserRepository;
import com.lautalfs.blogapi.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {

    private static final String RESOURCE_NAME = "Post";
    private final PostMapper postMapper;
    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;

    @Override
    public PostDTO createPost(PostDTO postDTO, String userId, String categoryId) {
        var user = userRepository.findById(userId)
                .orElseThrow(()-> new ResourceNotFoundException(RESOURCE_NAME, "id", userId));
        var category = categoryRepository.findById(userId)
                .orElseThrow(()-> new ResourceNotFoundException(RESOURCE_NAME, "id", userId));
        PostEntity postEntity = postMapper.toEntity(postDTO);
        postEntity.setAddedDate(new Date());
        postEntity.setImageName("imagen.png");
        postEntity.setUser(user);
        postEntity.setCategory(category);

        return postMapper.toDto(postRepository.save(postEntity));
    }

    @Override
    public PostDTO updatePost(PostDTO post, String id) {
        return  null;
    }

    @Override
    public PostDTO getPostById(String id) {
        return  null;
    }

    @Override
    public List<PostDTO> getAllPosts() {
        return  null;
    }

    @Override
    public void deletePost(String id) {

    }

    @Override
    public List<PostDTO> getAllPostsByCategory(String id) {
        return null;
    }

    @Override
    public List<PostDTO> getAllPostsByUser(String id) {
        return null;
    }
}
