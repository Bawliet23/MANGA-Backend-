package com.hero.mangahero.controllers;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hero.mangahero.dto.ChapiterDto;
import com.hero.mangahero.dto.ChapiterDtoAdd;
import com.hero.mangahero.dto.MangaDto;
import com.hero.mangahero.services.IChapiterServise;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/v1/chapters")
@CrossOrigin("*")
public class ChapiterController {

    @Autowired
    private IChapiterServise chapiterServise;


    @PostMapping("/add")
    public ResponseEntity<?> addChapters(@RequestParam("images")List<MultipartFile> images , @RequestParam("chapter") String chapter) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        ChapiterDtoAdd chap = objectMapper.readValue(chapter, ChapiterDtoAdd.class);
        boolean added= chapiterServise.addChapiter(chap,images);
        if (added)
            return ResponseEntity.badRequest().body("chapter not added");
        return  ResponseEntity.ok().body("chapter added");
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getChapterById(@PathVariable("id") Long id){
        ChapiterDto chapiterDto = chapiterServise.getChapiterById(id);
        if (chapiterDto == null)
            return ResponseEntity.badRequest().body("chapter not found");
        return  ResponseEntity.ok().body(chapiterDto);
    }

}
