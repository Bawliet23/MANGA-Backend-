package com.hero.mangahero.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hero.mangahero.dto.MangaDto;
import com.hero.mangahero.services.IMangaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/v1/manga")
@CrossOrigin("*")
public class MangaController {

    @Autowired
    private IMangaService mangaService;

    @GetMapping("/lastUpdate")
    public ResponseEntity<?> getLastModifiedMangas(@PageableDefault(size = 16) Pageable page){
        Page<MangaDto> mangaDtos = mangaService.getMangaByUpdateTime(page);
        if (mangaDtos==null)
            return ResponseEntity.badRequest().body("No Manga was found");
        return  ResponseEntity.ok().body(mangaDtos);
    }
    @GetMapping("/{id}")
    public MangaDto getMangaById(@PathVariable("id") Long id){
        return mangaService.getMangaById(id);
    }

    @PostMapping("/add")
    public ResponseEntity<?> addManga(@RequestParam("cover")MultipartFile cover, @RequestParam("manga") String mangaDto) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        MangaDto manga = objectMapper.readValue(mangaDto,MangaDto.class);
        MangaDto mangaDto1 = mangaService.addManga(cover,manga);
        if (mangaDto1==null)
            return ResponseEntity.badRequest().body("manga not added");
        return  ResponseEntity.ok().body(mangaDto1);
    }
    @GetMapping("/random")
    public ResponseEntity<?> randomManga(){
        MangaDto mangaDto1 = mangaService.randomManga();
        if (mangaDto1==null)
            return ResponseEntity.badRequest().body("manga not showing");
        return  ResponseEntity.ok().body(mangaDto1);

    }
    @GetMapping("/search/{name}")
    public Page<MangaDto> search(@PathVariable("name") String name,@PageableDefault(size = 5) Pageable page){
        return mangaService.search(name,page);
    }

    @GetMapping("/number")
    public long mangaNumber(){
        return mangaService.getManganumber();
    }
    @GetMapping("/top/{time}")
    public List<MangaDto> top(@PathVariable String time){
       return mangaService.top5(time);
    }

}
