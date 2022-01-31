package co.simplon.p16.springboard.services;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@Service
public class UploadFile {

    private String path = "/Volumes/DATA/Simplon_Java/chef_doeuvre/SpringBoard/springboard/src/main/resources/static";
    
    private String photoPath = "/img/covers/";
    private String audioPath = "/sound/";

    /**
     * Methode to saveFile in server folder
     * 
     * @param file     file to save in databade in MultiPartFile format
     * @param uri      real path from drive (ex :
     *                 c:/SpringBoard/springboard/src/main/resources/static/img/covers/)
     * @param shortUri short path from static folder (will be save in database
     *                 entity) ex: /img/covers/
     * @return the shortUri whith fileName to store in database entity
     */
    public String saveFile(MultipartFile file, String uri, String shortUri) {

        try {
            String fileName = StringUtils.cleanPath(file.getOriginalFilename());
            Path path = Paths.get(uri + fileName);
            Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
            return shortUri + fileName;

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;

    }

    public List<String> SaveAudioFiles(MultipartFile[] audioFiles) {

        List<String> urlAudioFileList = new ArrayList<>();

        for (MultipartFile audioFile : audioFiles) {

            if (audioFile.getContentType().matches("^audio/.*") && audioFile.getSize() != 0) {
                urlAudioFileList.add(saveFile(audioFile, path + audioPath, audioPath));
            }
        }
        return urlAudioFileList;
    }
    public String saveImageFile(MultipartFile imageFile) {

        if (imageFile.getContentType().matches("^image/.*") && imageFile.getSize() != 0) {
            String urlImageFile = saveFile(imageFile, path + photoPath, photoPath);
            return urlImageFile;
        }
        return null;
    }

    //TODO check path

}
