package co.simplon.p16.springboard.Controller;


import java.util.ArrayList;
import java.util.List;
import java.util.Random;



import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import co.simplon.p16.springboard.entity.Artist;
import co.simplon.p16.springboard.entity.MusicalStyle;

import co.simplon.p16.springboard.entity.User;
import co.simplon.p16.springboard.repository.IMusicalStyleRepository;

import co.simplon.p16.springboard.repository.UserRepository;
import co.simplon.p16.springboard.services.ArtistService;
import co.simplon.p16.springboard.services.UploadFile;

@RequestMapping("user/")
@Controller
public class NewArtistPageController {

    private String photoPath = "/Volumes/DATA/Simplon_Java/chef_doeuvre/SpringBoard/springboard/src/main/resources/static/img/covers/";
    private String soundPath = "/Volumes/DATA/Simplon_Java/chef_doeuvre/SpringBoard/springboard/src/main/resources/static/sound/";
    private String shortPhotoPath = "/img/covers/";
    private String shortAudioPath = "/sound/";
    private Artist artist;

    @Autowired
    IMusicalStyleRepository musicalStyleRepository;
    @Autowired
    ArtistService artistService;
    @Autowired
    UploadFile uploadFile;
    @Autowired
    UserRepository userRepository;

    @GetMapping("newArtistPage/")
    public String showNewArtistPageForm(Model model) {

        List<MusicalStyle> styleList = musicalStyleRepository.findAll();
        Artist artist = artistService.setListInUser();

        model.addAttribute("musicalStyles", styleList);
        model.addAttribute("artist", artist);

        return "newArtistPage";
    }

    @GetMapping("newArtistPage/succes")
    public String postArtistPageSucces() {
        return "newArtistPageSucces";
    }

    @GetMapping("nawArtistPAge/error")
    public String postArtsitPageError(Model model) {
        List<MusicalStyle> styleList = musicalStyleRepository.findAll();
        model.addAttribute("musicalStyles", styleList);
        model.addAttribute("artist", artist);
        return "newArtistPage";
    }

    @PostMapping("newArtistPage/upload")
    public String saveNewArtistPage(@ModelAttribute Artist artist,
            @RequestParam MultipartFile imageFile,
            @RequestParam MultipartFile[] audioFiles) {
        this.artist = artist;
        List<String> urlFileList = new ArrayList<>();
        for (MultipartFile audioFile : audioFiles) {
            System.out.println(audioFile.getSize());
            if (audioFile.getContentType().equals("audio/mpeg") || audioFile.getSize() != 0) {

                urlFileList.add(uploadFile.saveFile(audioFile, soundPath, shortAudioPath));

            }
        }

        if (imageFile.getContentType().equals("image/jpeg") || imageFile.getContentType().equals("image/png")
                || imageFile.getContentType().equals("image/tiff") || imageFile.getContentType().equals("image/bmp")
                || imageFile.getSize() != 0) {
            urlFileList.add(uploadFile.saveFile(imageFile, photoPath, shortPhotoPath));
        }

        // A VIRER //
        Random random = new Random();

        User user = new User("firstName", "lastName", String.valueOf(random.nextInt(10000)), "password", "role");
        userRepository.save(user);
        // ATTENTTION //

        boolean allOk = artistService.saveArtistPage(artist, urlFileList, user.getId());
        if (allOk) {
            return "redirect:/user/newArtistPage/succes";
        } else {
            return "newArtistPage";
        }
    }

}// TODO changer user ID!!!
