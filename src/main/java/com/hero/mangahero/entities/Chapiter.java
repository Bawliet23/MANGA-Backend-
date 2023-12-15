package com.hero.mangahero.entities;


import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Data
public class Chapiter {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long number;
    private String name;
    @ElementCollection
    List<String> images = new ArrayList<String>();
    @OneToMany(mappedBy = "Chapiter")
    private List<Comment> comments = new ArrayList<>();
    @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private Manga manga;
    @Column(name = "added_at")
    @CreationTimestamp
    private Date addedAt;

}
