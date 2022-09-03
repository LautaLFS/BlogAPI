package com.lautalfs.blogapi.service.impl;

import com.lautalfs.blogapi.dto.PostDTO;
import com.lautalfs.blogapi.dto.PostResponse;
import com.lautalfs.blogapi.entity.PostEntity;
import com.lautalfs.blogapi.exception.ResourceNotFoundException;
import com.lautalfs.blogapi.mapper.PostMapper;
import com.lautalfs.blogapi.repository.CategoryRepository;
import com.lautalfs.blogapi.repository.PostRepository;
import com.lautalfs.blogapi.repository.UserRepository;
import com.lautalfs.blogapi.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {
    private final PostMapper postMapper;
    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;

    @Override
    public PostDTO createPost(PostDTO postDTO, String userId, String categoryId) {
        var user = userRepository.findById(userId)
                .orElseThrow(()-> new ResourceNotFoundException("User", "id", userId));
        var category = categoryRepository.findById(categoryId)
                .orElseThrow(()-> new ResourceNotFoundException("Category", "id", userId));
        PostEntity postEntity = postMapper.toEntity(postDTO);
        postEntity.setAddedDate(new Date());
        postEntity.setImageName("imagen.png");
        postEntity.setUser(user);
        postEntity.setCategory(category);

        return postMapper.toDto(postRepository.save(postEntity));
    }

    @Override
    public PostDTO updatePost(PostDTO postDTO, String id) {
        var post = postRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Post", "id", id));
        post.setTitle(postDTO.getPostTitle());
        post.setContent(postDTO.getContent());
        if(postDTO.getImageName() != null && !postDTO.getImageName().isEmpty()){
            post.setImageName(postDTO.getImageName());
        }
        return postMapper.toDto(postRepository.save(post));
    }

    @Override
    public PostDTO getPostById(String id) {
        return postMapper.toDto(postRepository.findById(id).orElseThrow(
                ()-> new ResourceNotFoundException("Post", "id", id)));
    }

    @Override
    public PostResponse getAllPosts(int pageNumber, int pageSize, String sortBy) {

        Pageable p = PageRequest.of(pageNumber, pageSize, Sort.by(sortBy));
        var posts = postRepository.findAll(p);
        var allPost = posts.getContent();
        var postDTO =allPost.stream().map(post-> postMapper.map(post, PostDTO.class)).collect(Collectors.toList());
        PostResponse postResponse = new PostResponse();
        postResponse.setContent(postDTO);
        postResponse.setPageNumber(posts.getNumber());
        postResponse.setPageSize(posts.getSize());
        postResponse.setTotalElements(posts.getTotalElements());
        postResponse.setTotalPages(posts.getTotalPages());
        postResponse.setLastPage(posts.isLast());
        return postResponse;
    }

    @Override
    public void deletePost(String id) {
        var post = this.postRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Post", "id", id));
        this.postRepository.delete(post);
    }

    @Override
    public List<PostDTO> getAllPostsByCategory(String id) {
        var category = categoryRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Category", "id", id));
        var posts = postRepository.findByCategory(category);

        return posts.stream().map(post->postMapper
                .map(post, PostDTO.class)).collect(Collectors.toList());
    }

    @Override
    public List<PostDTO> getAllPostsByUser(String id) {
        var user = userRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("user", "id", id));
        var posts = postRepository.findByUser(user);

        return posts.stream().map(post->postMapper
                .map(post, PostDTO.class)).collect(Collectors.toList());
    }

    @Override
    public List<PostDTO> searchPosts(String keyword) {
        var posts = postRepository.findByTitleContaining(keyword);
        return posts.stream().map(post -> postMapper.map(post, PostDTO.class)).collect(Collectors.toList());
    }
}
