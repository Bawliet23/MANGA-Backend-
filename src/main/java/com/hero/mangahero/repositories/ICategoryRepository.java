package com.hero.mangahero.repositories;

import com.hero.mangahero.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICategoryRepository extends  JpaRepository<Category,Long> {
}
