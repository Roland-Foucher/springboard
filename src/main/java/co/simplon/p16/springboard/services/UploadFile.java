package co.simplon.p16.springboard.services;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import co.simplon.p16.springboard.entity.Artist;

@Service
public class UploadFile {
    
    private String staticPath = "src/main/resources/static";


    /**
     * Methode to saveFile in server folder. File will be stre with the date, artist
     * name and file name
     * 
     * @param file     file to save on server : MultiPartFile format
     * @param relative path from static folder (will be save in database
     *                 entity) ex: /img/covers/
     * @return relative path with fileName to store in database artist entity
     */
    public String saveFile(MultipartFile file, Artist artist) throws IOException {

            String fileName = "/upload/" + UUID.randomUUID() +"_";
            fileName += "." + StringUtils.getFilenameExtension(file.getOriginalFilename());

            Path path = Paths.get(staticPath + fileName);
            Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
            return fileName;

    }

    public boolean checkFile(MultipartFile file, String pattern){
        return file.getContentType().matches(pattern) && file.getSize() != 0;
    }

    public void deleteFile(String url) {

        Path fileToDeletePath = Paths.get(this.staticPath + url);
        try {
            Files.delete(fileToDeletePath);
        } catch (IOException e) {
            System.out.println("error when deleting file");
            e.printStackTrace();
        }
    }
}
