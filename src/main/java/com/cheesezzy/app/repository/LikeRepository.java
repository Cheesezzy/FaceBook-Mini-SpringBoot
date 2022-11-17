package com.cheesezzy.app.repository;

import com.cheesezzy.app.entity.Like;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LikeRepository extends JpaRepository<Like, Long> {
    @Query(value = "SELECT * FROM likes WHERE post_id=? AND user_id=?", nativeQuery = true)
    Optional<Like> findByPostIdAndId(Long postId, Long userId);

    @Query(value = "DELETE FROM likes WHERE post_id=? AND user_id=?", nativeQuery = true)
    void deleteByIdAndPostId(Long postId, Long userId);
}
