package com.hero.mangahero.dto;

import lombok.Data;

import java.util.Date;

@Data
public class ChapterNameId {
    private Long id;
    private Long number;
    private String name;
    private Date addedAt;

}
