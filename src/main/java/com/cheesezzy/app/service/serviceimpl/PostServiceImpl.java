package com.cheesezzy.app.service.serviceimpl;

import com.cheesezzy.app.repository.PostRepository;
import com.cheesezzy.app.repository.UserRepository;
import com.cheesezzy.app.entity.ApiResponse;
import com.cheesezzy.app.entity.Post;
import com.cheesezzy.app.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.*;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    PostRepository postRepository;
    @Autowired
    UserRepository userRepository;

    @Override
    public List<Post> getPosts() {
        List<Post> posts = postRepository.findAll();
        Collections.sort(posts, (o1, o2) -> o2.getPostId().compareTo(o1.getPostId()));
        return  posts;
    }

    @Override
    public ApiResponse<String> deletePostById(Long postId, Long userId) {
        if(validatePostOwner(postId, userId)) {
            postRepository.deleteById(postId);
            return new ApiResponse<>("Post Deleted", true, "STATUS OK");
        }
       return new ApiResponse<>("Post Delete Failed", false, "DELETE  FAILED");
    }

    @Override
    public ApiResponse<String> updatePost(Long postId, Long userId, String post) {
        if(validatePostOwner(postId, userId)) {
            Optional<Post> postOptional = postRepository.findById(postId);
            Post postObj = postOptional.get();
            String postBody = post;

            if(Objects.nonNull(postBody) && !postBody.isBlank()) {
                postObj.setPost(postBody);
                postObj.setTimestamp(new Timestamp(System.currentTimeMillis()));
            }
            postRepository.save(postObj);
            return new ApiResponse<>("Post Update Successful", true, "UPDATE SUCCESSFUL");
        }
        return new ApiResponse<>("Post Update Failed", false, "UPDATE FAILED");
    }

    @Override
    public Post addNewPost(String post, Long userId) {
        Post newPost = Post.builder()
                .post(post)
                .user(userRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("User does not exist")))
                .timestamp(new Timestamp(System.currentTimeMillis()))
                .build();
        return postRepository.save(newPost);
    }

    public boolean validatePostOwner(Long postId, Long userId) {
        Optional<Post> postOptional = postRepository.findById(postId);
        return postOptional.map(post -> post.getUser().getId().equals(userId)).orElse(false);
    }
}
