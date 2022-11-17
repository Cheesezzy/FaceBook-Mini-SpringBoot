package com.cheesezzy.app.service;


import com.cheesezzy.app.entity.Post;
import com.cheesezzy.app.entity.ApiResponse;

import java.util.List;

public interface PostService {
    List<Post> getPosts();

    ApiResponse<String> deletePostById(Long postId, Long userId);

    ApiResponse<String> updatePost(Long postId, Long userId, String post);

    Post addNewPost(String post, Long userId);
}
