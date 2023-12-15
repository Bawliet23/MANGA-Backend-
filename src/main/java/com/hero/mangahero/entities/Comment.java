package com.hero.mangahero.entities;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String content;
    private String user;
    @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private Manga manga;
    @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private Chapiter Chapiter;
    @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private Comment parentComment;

    @Column(name = "created_at")
    @CreationTimestamp
    private Date createdAt;

    @Column(name = "updated_at")
    @UpdateTimestamp
    private Date updatedAt;
}
