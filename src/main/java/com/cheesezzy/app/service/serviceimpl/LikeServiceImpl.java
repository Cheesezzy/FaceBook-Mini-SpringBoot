package com.cheesezzy.app.service.serviceimpl;

import com.cheesezzy.app.repository.LikeRepository;
import com.cheesezzy.app.repository.PostRepository;
import com.cheesezzy.app.repository.UserRepository;
import com.cheesezzy.app.entity.Like;
import com.cheesezzy.app.service.LikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LikeServiceImpl implements LikeService {
    @Autowired
    LikeRepository likeRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    PostRepository postRepository;

    @Override
    public void likePost(Long postId, Long userId) {
//        if(likeRepository.findByPostidAndId(postId, userId).isEmpty()) {
//            addLike(postId, userId);
//        }else {
//            deleteLike(postId, userId);
//        }
    }

    public void addLike(Long postId, Long userId) {
        Like like= Like.builder()
                .user(userRepository.findById(userId).get())
                .post(postRepository.findById(postId).get())
                .build();
        likeRepository.save(like);
    }

    public void deleteLike(Long postId, Long userId) {
//        likeRepository.deleteByIdAndPostid(postId, userId);
    }
}
