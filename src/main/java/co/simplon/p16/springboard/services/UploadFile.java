package co.simplon.p16.springboard.services;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import co.simplon.p16.springboard.entity.Artist;

@Service
public class UploadFile {
    private String path = System.getProperty("user.dir") + "/src/main/resources/static";
    private String photoPath = "/img/covers/";
    private String audioPath = "/sound/";

    /**
     * Methode to saveFile in server folder. File will be stre with the date, artist
     * name and file name
     * 
     * @param file     file to save on server : MultiPartFile format
     * @param relative path from static folder (will be save in database
     *                 entity) ex: /img/covers/
     * @return relative path with fileName to store in database artist entity
     */
    public String saveFile(MultipartFile file, String relativePath, Artist artist) {

        try {
            String fullPath = path + relativePath;
            String fileName = LocalDate.now() + "_" + artist.getArtistName() + "_";
            fileName += StringUtils.cleanPath(file.getOriginalFilename());

            Path path = Paths.get(fullPath + fileName);
            Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
            return relativePath + fileName;

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;

    }

    public List<String> SaveAudioFiles(MultipartFile[] audioFiles, Artist artist) {

        List<String> urlAudioFileList = new ArrayList<>();

        for (MultipartFile audioFile : audioFiles) {

            if (audioFile.getContentType().matches("^audio/.*") && audioFile.getSize() != 0) {
                urlAudioFileList.add(saveFile(audioFile, audioPath, artist));
            }
        }
        return urlAudioFileList;
    }

    public String saveImageFile(MultipartFile imageFile, Artist artist) {

        if (imageFile.getContentType().matches("^image/.*") && imageFile.getSize() != 0) {
            String urlImageFile = saveFile(imageFile, photoPath, artist);
            return urlImageFile;
        }
        return null;
    }

    public void deleteFile(String url) {

        Path fileToDeletePath = Paths.get(this.path + url);
        try {
            Files.delete(fileToDeletePath);
        } catch (IOException e) {
            System.out.println("error when deleting file");
            e.printStackTrace();
        }
    }
}
