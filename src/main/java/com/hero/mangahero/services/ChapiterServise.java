package com.hero.mangahero.services;

import com.hero.mangahero.dto.ChapiterDto;
import com.hero.mangahero.dto.ChapiterDtoAdd;
import com.hero.mangahero.dto.MangaDto;
import com.hero.mangahero.entities.Chapiter;
import com.hero.mangahero.entities.Manga;
import com.hero.mangahero.repositories.IChapiterRepository;
import com.hero.mangahero.repositories.IMangaRepository;
import com.hero.mangahero.utils.FileHandler;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;


@Service
public class ChapiterServise implements IChapiterServise {


    private final IChapiterRepository chapiterRepository;
    private final IMangaRepository mangaRepository;
    private final ModelMapper modelMapper;

    public ChapiterServise(IChapiterRepository chapiterRepository, IMangaRepository mangaRepository, ModelMapper modelMapper) {
        this.chapiterRepository = chapiterRepository;
        this.mangaRepository = mangaRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public ChapiterDto getChapiterById(Long id) {
        Optional<Chapiter> isExist = chapiterRepository.findById(id);
        if (isExist.isPresent()){
            Chapiter chapiter = isExist.get();
            return modelMapper.map(chapiter,ChapiterDto.class);
        }
        return null;
    }

    @Override
    public List<ChapiterDto> getAllChapitersManga(MangaDto manga) {
        return null;
    }

    @Override
    public boolean addChapiter(ChapiterDtoAdd chapiterDtoAdd, List<MultipartFile> images) throws IOException {
        Chapiter chapiter = modelMapper.map(chapiterDtoAdd,Chapiter.class);
        Optional<Manga> isExist = mangaRepository.findById(chapiter.getManga().getId());
        if (isExist.isPresent()){
            chapiter.setManga(isExist.get());
            for (MultipartFile image : images) {
                chapiter.getImages().add(FileHandler.uploadChapters(image));
            }
            chapiterRepository.save(chapiter);
        }
        return false;
    }


}
