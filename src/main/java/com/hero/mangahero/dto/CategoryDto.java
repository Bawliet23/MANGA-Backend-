package com.hero.mangahero.dto;

import com.hero.mangahero.entities.Manga;

import java.util.ArrayList;
import java.util.List;

public class CategoryDto {
    private Long Id;
    private String name;
    private List<MangaDto> mangas = new ArrayList<MangaDto>();

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<MangaDto> getMangas() {
        return mangas;
    }

    public void setMangas(List<MangaDto> mangas) {
        this.mangas = mangas;
    }
}
