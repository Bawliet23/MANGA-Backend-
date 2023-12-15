package com.hero.mangahero.repositories;

import com.hero.mangahero.entities.View;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IViewRepository extends JpaRepository<View,Long> {
}
