package com.hero.mangahero.services;

import com.hero.mangahero.dto.CategoryDto;
import com.hero.mangahero.entities.Category;
import com.hero.mangahero.repositories.ICategoryRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryServise implements ICategoryServise {

    private final ModelMapper modelMapper;
    private final ICategoryRepository categoryRepository;

    public CategoryServise(ModelMapper modelMapper, ICategoryRepository categoryRepository) {
        this.modelMapper = modelMapper;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public void addCategory(CategoryDto categoryDto) {
        Category category = modelMapper.map(categoryDto,Category.class);
        categoryRepository.save(category);
    }
}
