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
    @Column(name = "PostID")
    private Long PostID;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "UserID")
    private User user;

    @Column(nullable = false)
    private String PostName;

    @Column(nullable = false)
    private String PostContent;

    @Column
    private String ImageLink;

    @Column(nullable = false)
    private LocalDateTime PostDate;
}
