package co.simplon.p16.springboard.Controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
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

    @Autowired
    IMusicalStyleRepository musicalStyleRepository;
    @Autowired
    ArtistService artistService;
    @Autowired
    UploadFile uploadFile;
    @Autowired
    UserRepository userRepository;

    @GetMapping("newArtistPage")
    public String showNewArtistPageForm(Model model) {

        List<MusicalStyle> styleList = musicalStyleRepository.findAll();
        Artist artist = artistService.setListInNewArtist();

        model.addAttribute("musicalStyles", styleList);
        model.addAttribute("artist", artist);

        return "newArtistPage/newArtistPage";
    }

    @PostMapping("newArtistPage/upload")
    public String saveNewArtistPage(Model model,
            @AuthenticationPrincipal User user,
            @RequestParam MultipartFile imageFile,
            @RequestParam MultipartFile[] audioFiles,
            @Valid Artist artist,
            BindingResult bindingResult) {

        // réinjection des styles
        List<MusicalStyle> styleList = musicalStyleRepository.findAll();
        model.addAttribute("musicalStyles", styleList);

        // validation des données utilisateur
        if (bindingResult.hasErrors()) {
            return "newArtistPage/newArtistPage";
        }
        if (imageFile.getSize() == 0) {
            model.addAttribute("savePageError", "Merci de loader une image");
            return "newArtistPage/newArtistPage";
        }
        if (audioFiles[0].getSize() == 0) {
            model.addAttribute("savePageError", "Merci de loader au moins un titre musical sur la première entrée");
            return "newArtistPage/newArtistPage";
        }

        // enregistrement des fichiers
        List<String> urlAudioFileList = uploadFile.SaveAudioFiles(audioFiles);
        String urlCoverFile = uploadFile.saveImageFile(imageFile);

        if (!urlAudioFileList.isEmpty() && !urlCoverFile.isEmpty()) {
            if (artistService.saveArtistPage(artist, urlAudioFileList, urlCoverFile, user.getId())) {
                user.setRole("ROLE_ARTIST");
                userRepository.update(user);
                return "redirect:/artistPage/" + artist.getId();
            } else {
                model.addAttribute("savePageError", "Une erreur est survenue lors de la création de la page");
                return "newArtistPage/newArtistPage";
            }
        } else {
            model.addAttribute("savePageError", "Un problème est survenu lors de la sauvegarde de vos fichier");
            return "newArtistPage/newArtistPage";
        }
    }// TODO redirect + refresh

}
