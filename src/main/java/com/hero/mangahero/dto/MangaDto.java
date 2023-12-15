package com.hero.mangahero.dto;

import com.hero.mangahero.entities.Category;
import lombok.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
public class MangaDto {
    private Long id;
    private String name;
    private String auteur;
    private String description;
    private String cover;
    private Long rates;
    private Date addedAt;
    private Long numberOfChapiters;
    private Long views;
    List<CategoryDto> categories =  new ArrayList<CategoryDto>();
    List<ChapterNameId> chapiters =  new ArrayList<ChapterNameId>();





    public String getCover() {
        return "http://localhost:8080/mangas/"+cover;
    }

}
