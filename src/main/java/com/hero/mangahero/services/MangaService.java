package com.hero.mangahero.services;

import com.hero.mangahero.dto.CategoryDto;
import com.hero.mangahero.dto.MangaDto;
import com.hero.mangahero.entities.Category;
import com.hero.mangahero.entities.Manga;
import com.hero.mangahero.entities.View;
import com.hero.mangahero.repositories.ICategoryRepository;
import com.hero.mangahero.repositories.IMangaRepository;
import com.hero.mangahero.repositories.IViewRepository;
import com.hero.mangahero.utils.FileHandler;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class MangaService implements IMangaService {

    private final IMangaRepository mangaRepository;
    private final ICategoryRepository categoryRepository;
    private final IViewRepository viewRepository;
    private final ModelMapper modelMapper;

    public MangaService(IMangaRepository mangaRepository, ICategoryRepository categoryRepository, IViewRepository viewRepository, ModelMapper modelMapper) {
        this.mangaRepository = mangaRepository;
        this.categoryRepository = categoryRepository;
        this.viewRepository = viewRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public Page<MangaDto> getMangaByUpdateTime(Pageable pageable) {
        Pageable p = PageRequest.of(pageable.getPageNumber(),pageable.getPageSize(),(Sort.by( Sort.Order.desc("updatedAt"))));
        System.out.println(p.getSort());
        Page<Manga > mangas = mangaRepository.findAll(p);
        return mangas.map(manga -> modelMapper.map(manga,MangaDto.class));
    }

    @Override
    public MangaDto getMangaById(Long id) {
        Optional<Manga> exi = mangaRepository.findById(id);
        View view = new View();
        view.setManga(exi.get());
        viewRepository.save(view);
        return exi.map(manga -> modelMapper.map(manga, MangaDto.class)).orElse(null);
    }

    @Override
    public MangaDto addManga(MultipartFile image, MangaDto manga) throws IOException {
        Manga manga1 = modelMapper.map(manga,Manga.class);
        manga1.setCover(FileHandler.uploadFile(image));
        List<Category> categories = new ArrayList<Category>();
        for (CategoryDto category : manga.getCategories()){
            Optional<Category> byId = categoryRepository.findById(category.getId());
            byId.ifPresent(categories::add);
        }
        manga1.setCategories(categories);
        Manga man = mangaRepository.save(manga1);
        return modelMapper.map(man,MangaDto.class);
    }

    @Override
    public Page<MangaDto> getMangasByCategories(List<CategoryDto> categories,Pageable page) {
    List<Category> cat = new ArrayList<>();
    for (CategoryDto category : categories){
       Category category1 = categoryRepository.findById(category.getId()).get();
        cat.add(category1);
    }

        return mangaRepository.findMangaByCategoriesIn(cat,page).map(manga -> modelMapper.map(manga,MangaDto.class));
    }

    @Override
    public MangaDto randomManga() {
        long max = mangaRepository.count();
        long  min = 1;
        long get = (long)Math.floor(Math.random()*(max-min+1)+min);
        System.out.println( "random number "+get);
        return this.getMangaById(get);
    }

    @Override
    public long getManganumber() {
        return mangaRepository.count();
    }

    @Override
    public Page<MangaDto> search(String name,Pageable page) {
        return mangaRepository.findAllByNameContaining(name,page).map(manga -> modelMapper.map(manga,MangaDto.class));
    }

    @Override
    public List<MangaDto> top5(String str) {
        LocalDate currentDate = LocalDate.now();
        currentDate=currentDate.plus(1,ChronoUnit.DAYS);
        LocalDate result;
        if (str.equals("week")){
             result = currentDate.minus(1, ChronoUnit.WEEKS);
        }else if(str.equals("month")){
             result = currentDate.minus(1, ChronoUnit.MONTHS);
        }else if(str.equals("day")){
            result = currentDate.minus(1, ChronoUnit.DAYS);
        }
        else{
            String d = "31-Aug-2020";
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MMM-yyyy", Locale.US);
             result =LocalDate.parse(d, dtf).atStartOfDay().toLocalDate();
        }

        Date date = Date.from(currentDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
        Date date2 = Date.from(result.atStartOfDay(ZoneId.systemDefault()).toInstant());
        List<Manga> m = mangaRepository.getTopMangas(date,date2);

        for (Manga manga :m){
            System.out.println(manga.getId());
        }
       return m.stream().map(manga -> modelMapper.map(manga,MangaDto.class)).collect(Collectors.toList());
    }


}
