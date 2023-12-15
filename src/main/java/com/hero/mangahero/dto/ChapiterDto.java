package com.hero.mangahero.dto;



import lombok.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
public class ChapiterDto {
    private Long id;
    private Long number;
    private String name;
    private Long views;
    List<String> images = new ArrayList<String>();
    private List<CommentDto> comments = new ArrayList<>();
    private Date addedAt;
    private MangaDto manga;


    public List<String> getImages() {
        for (int i = 0; i < images.size() ; i++) {
            images.set(i, "http://localhost:8080/chapters/".concat(images.get(i)));
        }
        return images;
    }
}
