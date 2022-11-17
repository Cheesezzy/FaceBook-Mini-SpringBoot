package com.cheesezzy.app.entity;

import lombok.*;

import javax.persistence.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@Entity
@Table(name = "likes")
public class Like {
    @Id
    @SequenceGenerator(name = "like_sequence", sequenceName = "like_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "like_sequence")
    private Long likeId;
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "post_id", referencedColumnName = "postId")
    private Post post;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    User user;
}
