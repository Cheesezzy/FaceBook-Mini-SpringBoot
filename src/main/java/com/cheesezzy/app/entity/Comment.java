package com.cheesezzy.app.entity;

import lombok.*;

import javax.persistence.*;
import java.sql.Timestamp;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Entity
@Table(name = "comments")
public class Comment {
    @Id
    @SequenceGenerator(name = "comment_sequence", sequenceName = "comment_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "comment_sequence")
    private Long commentId;
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "post_uuid", referencedColumnName = "postId")
    private Post post;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    User user;

    @Column(length=5000)
    private String comment;
    private Timestamp timestamp;
}
