package com.hero.mangahero.entities;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Data
public class Manga {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Column(length = 3000)
    private String description;
    private String cover;
    private Long rates;
    private String auteur;
    @Transient
    private int numberOfChapiters;
    private Long views;
    @Column(name = "added_at")
    @CreationTimestamp
    private Date addedAt;
    @CreationTimestamp
    private Date updatedAt;
    @OneToMany(mappedBy = "manga",cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    List<Chapiter> chapiters;
    @ManyToMany(mappedBy = "mangas")
    List<Category> categories =  new ArrayList<Category>();
    @OneToMany(mappedBy = "manga",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<Comment> comments = new ArrayList<>();
    @OneToMany(mappedBy = "manga",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<View> v;
    public int getNumberOfChapiters() {
        return chapiters.size();
    }
    public int getViews() {
        return v.size();
    }

}
