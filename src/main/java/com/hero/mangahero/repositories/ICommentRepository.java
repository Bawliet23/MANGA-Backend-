package com.hero.mangahero.repositories;

import com.hero.mangahero.entities.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICommentRepository extends JpaRepository<Comment,Long> {
}
