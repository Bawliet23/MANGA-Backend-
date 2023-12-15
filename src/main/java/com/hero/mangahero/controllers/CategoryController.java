package com.hero.mangahero.controllers;

import com.hero.mangahero.dto.CategoryDto;
import com.hero.mangahero.services.ICategoryServise;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/categories")
public class CategoryController {


    private final  ICategoryServise categoryServise;

    public CategoryController(ICategoryServise categoryServise) {
        this.categoryServise = categoryServise;
    }

    @PostMapping("/add")
    public ResponseEntity<?> addCategorie(@RequestBody CategoryDto categoryDto){
        categoryServise.addCategory(categoryDto);
        return ResponseEntity.ok("added ");
    }
}
