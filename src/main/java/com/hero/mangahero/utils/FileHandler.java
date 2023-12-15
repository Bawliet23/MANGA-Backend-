package com.hero.mangahero.utils;

import org.apache.commons.io.FileUtils;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.apache.commons.io.FilenameUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Random;

import static java.nio.file.Files.copy;
import static java.nio.file.Paths.get;
import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

public class FileHandler {
    private final static String MANGADIRECTORY = "src/main/webapp/mangas/";
        private final static String CHAPTERSDIRECTORY = "src/main/webapp/chapters/";

    public  static String  uploadFile(MultipartFile file) throws IOException {
        Path fileStorage=null;
        String filename = StringUtils.cleanPath(file.getOriginalFilename());
        String ext = FilenameUtils.getExtension(filename);
        filename = getSaltString()+"."+ext;
        fileStorage = get(MANGADIRECTORY, filename).toAbsolutePath().normalize();
        copy(file.getInputStream(), fileStorage, REPLACE_EXISTING);
        return filename;
    }
    public  static String  uploadChapters(MultipartFile file) throws IOException {
        Path fileStorage=null;
        String filename = StringUtils.cleanPath(file.getOriginalFilename());
        String ext = FilenameUtils.getExtension(filename);
        filename = getSaltString()+"."+ext;
        fileStorage = get(CHAPTERSDIRECTORY, filename).toAbsolutePath().normalize();
        copy(file.getInputStream(), fileStorage, REPLACE_EXISTING);
        return filename;
    }

    public static Resource downloadFile(String filename)  throws IOException {
        Path filePath = get(MANGADIRECTORY).toAbsolutePath().normalize().resolve(filename);
        if(!Files.exists(filePath)) {
            throw new FileNotFoundException(filename + " was not found on the server");
        }
        return new UrlResource(filePath.toUri());
    }
    public static void deleteFile(String filename){
        String ext = FilenameUtils.getExtension(filename);
        File fileToDelete = FileUtils.getFile(MANGADIRECTORY+filename);
        FileUtils.deleteQuietly(fileToDelete);

    }

    public static String getSaltString() {
        String SALTCHARS = "abcdefghijklmnopqrstuvyz1234567890";
        StringBuilder salt = new StringBuilder();
        Random rnd = new Random();
        while (salt.length() < 9) { // length of the random string.
            int index = (int) (rnd.nextFloat() * SALTCHARS.length());
            salt.append(SALTCHARS.charAt(index));
        }
        return salt.toString();

    }
}

