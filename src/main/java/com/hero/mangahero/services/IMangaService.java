package com.hero.mangahero.services;


import com.hero.mangahero.dto.CategoryDto;
import com.hero.mangahero.dto.MangaDto;
import com.hero.mangahero.entities.Category;
import com.hero.mangahero.entities.Manga;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface IMangaService {
    public Page<MangaDto> getMangaByUpdateTime(Pageable pageable);
    public MangaDto getMangaById(Long id);
    public MangaDto addManga(MultipartFile image, MangaDto manga) throws IOException;
    public Page<MangaDto> getMangasByCategories(List<CategoryDto> categories,Pageable page);
    public MangaDto randomManga();
    public long getManganumber();
    public Page<MangaDto> search(String name,Pageable page);
    public List<MangaDto> top5(String str);

}
