package com.cheesezzy.app.service;

import com.cheesezzy.app.entity.ApiResponse;

public interface CommentService {
    ApiResponse<String> updateComment(Long postId, Long userId, Long commentId, String comment);
    ApiResponse<String> deleteCommentById(Long userId, Long commentId);
    void addNewComment(Long postId, Long userId, String comment);
}
