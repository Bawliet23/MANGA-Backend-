package com.hero.mangahero.dto;

import lombok.Data;
import java.util.ArrayList;
import java.util.List;
@Data
public class ChapiterDtoAdd {
    private Long number;
    private String name;
    List<String> images = new ArrayList<String>();
    private  MangaDto manga;

}
