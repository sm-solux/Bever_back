package com.example.bever.domain;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "Community")
public class Community {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "postID")
    private Long postID;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userID")
    private User user;

    @Column(nullable = false)
    private String postName;

    @Column(nullable = false)
    private String postContent;

    @Column
    private String imageLink;

    @Column(nullable = false)
    private LocalDateTime postDate;
}
