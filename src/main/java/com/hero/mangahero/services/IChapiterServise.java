package com.hero.mangahero.services;

import com.hero.mangahero.dto.ChapiterDto;
import com.hero.mangahero.dto.ChapiterDtoAdd;
import com.hero.mangahero.dto.MangaDto;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface IChapiterServise {
    ChapiterDto getChapiterById(Long id);
    List<ChapiterDto> getAllChapitersManga(MangaDto manga);
    boolean addChapiter(ChapiterDtoAdd chapiterDtoAdd, List<MultipartFile> images) throws IOException;
}
