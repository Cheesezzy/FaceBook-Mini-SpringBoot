package com.cheesezzy.app.service.serviceimpl;

import com.cheesezzy.app.repository.CommentRepository;
import com.cheesezzy.app.repository.PostRepository;
import com.cheesezzy.app.repository.UserRepository;
import com.cheesezzy.app.entity.ApiResponse;
import com.cheesezzy.app.entity.Comment;
import com.cheesezzy.app.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Objects;
import java.util.Optional;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    CommentRepository commentRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    PostRepository postRepository;
    @Override
    public ApiResponse<String> updateComment(Long postId, Long userId, Long commentId, String comment) {
        Optional<Comment> postOptional = commentRepository.findById(commentId);
        Comment commentObj = postOptional.get();
        if (Objects.nonNull(comment) && !comment.isBlank()) {
            commentObj.setComment(comment);
            commentObj.setTimestamp(new Timestamp(System.currentTimeMillis()));
        }
        commentRepository.save(commentObj);
        return new ApiResponse<>("Comment Update Successful", true, "UPDATE SUCCESSFUL");
//        return new ApiResponse<>("Post Update Failed", false, "UPDATE FAILED");
    }

    @Override
    public ApiResponse<String> deleteCommentById(Long userId, Long commentId) {
        commentRepository.deleteById(commentId);
        return new ApiResponse<>("FORBIDDEN", true, "Signup successful");
    }

    @Override
    public void addNewComment(Long postId, Long userId, String comment) {
        Comment comment1 = Comment.builder()
                .user(userRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("User does not exist")))
                .post(postRepository.findById(postId).orElseThrow(() -> new IllegalArgumentException("Post does not exist")))
                .comment(comment)
                .timestamp(new Timestamp(System.currentTimeMillis()))
                .build();
        commentRepository.save(comment1);
    }
}
