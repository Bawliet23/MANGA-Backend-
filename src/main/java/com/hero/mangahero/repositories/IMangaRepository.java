package com.hero.mangahero.repositories;

import com.hero.mangahero.entities.Category;
import com.hero.mangahero.entities.Manga;
import com.hero.mangahero.entities.View;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
@Repository
public interface IMangaRepository extends JpaRepository<Manga,Long> {
    Page<Manga> findAll(Pageable page);
    Page<Manga> findAllByNameContaining(String name,Pageable page);
    List<Manga> findAllByUpdatedAtBetween(Date from, Date to);
    Page<Manga> findMangaByCategoriesIn(List<Category> categories , Pageable page);
    @Query(value="select * from manga m INNER JOIN (SELECT v.manga_id FROM `view` v where v.created_at between :d1 and :d2 group BY v.manga_id ORDER by COUNT(v.manga_id) DESC limit 10 ) m1 where m.id =m1.manga_id ",nativeQuery=true)
    List<Manga> getTopMangas(@Param("d2") Date d2,@Param("d1") Date d1);
}
