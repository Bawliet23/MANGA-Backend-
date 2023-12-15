package com.hero.mangahero.entities;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;


import javax.persistence.*;
import java.util.Date;

@Entity
@Data
public class View {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private Manga manga;
    @Column(name = "created_at")
    @CreationTimestamp
    private Date createdAt;
}
