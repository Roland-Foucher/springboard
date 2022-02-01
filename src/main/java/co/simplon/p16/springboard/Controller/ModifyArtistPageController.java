package co.simplon.p16.springboard.Controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import co.simplon.p16.springboard.entity.Artist;
import co.simplon.p16.springboard.entity.MusicalStyle;
import co.simplon.p16.springboard.entity.User;
import co.simplon.p16.springboard.repository.IMusicalStyleRepository;
import co.simplon.p16.springboard.repository.TrackRepository;
import co.simplon.p16.springboard.repository.UserRepository;
import co.simplon.p16.springboard.services.ArtistService;
import co.simplon.p16.springboard.services.UploadFile;

@RequestMapping("artist/")
@Controller
public class ModifyArtistPageController {

    @Autowired
    IMusicalStyleRepository musicalStyleRepository;
    @Autowired
    ArtistService artistService;
    @Autowired
    UploadFile uploadFile;
    @Autowired
    TrackRepository trackRepository;

    @GetMapping("modifyArtistPage/{id}")
    public String modifyArtistPage(@PathVariable int id, Model model) {
        List<MusicalStyle> styleList = musicalStyleRepository.findAll();
        Artist artist = artistService.setListToUpdateArtistPage(id);
        
        String modifyTracksString = "";
        String modifyCoverString = "";

        model.addAttribute("musicalStyles", styleList);
        model.addAttribute("artist", artist);
        model.addAttribute("modifyTracksString", modifyTracksString);
        model.addAttribute("modifyCoverString", modifyCoverString);

        return "modifyArtistPage/modifyArtistPage";
    }

    @PostMapping("modifyArtistPage/{id}")
    public String saveNewArtistPage(Model model,
            @PathVariable int id,
            @AuthenticationPrincipal User user,
            MultipartFile imageFile,
            MultipartFile[] audioFiles,
            String modifyTracksString,
            String modifyCoverString,
            @Valid Artist newArtist,
            BindingResult bindingResult) {

        // réinjection des styles
        List<MusicalStyle> styleList = musicalStyleRepository.findAll();
        model.addAttribute("musicalStyles", styleList);

        boolean modifyCover = modifyCoverString.equals("true") ? true : false;
        boolean modifyTracks = modifyTracksString.equals("true") ? true : false;
                
        // validation des données utilisateur
        if (bindingResult.hasErrors()) {
            return "modifyArtistPage/modifyArtistPage";
        }
        if (imageFile.getSize() == 0 && modifyCover) {
            model.addAttribute("savePageError", "Merci de loader une image");
            return "modifyArtistPage/modifyArtistPage";
        }
        if (audioFiles[0].getSize() == 0 && modifyTracks) {
            model.addAttribute("savePageError", "Merci de loader au moins un titre musical sur la première entrée");
            return "modifyArtistPage/modifyArtistPage";
        }

        // enregistrement des fichiers s'il ont changés, sinon récupérationd es anciens
        // fichiers.
        List<String> urlAudioFileList = artistService.updateAudioFiles(modifyTracks, audioFiles, newArtist.getId());
        String urlCoverFile = artistService.updateCoverFile(modifyCover, imageFile, newArtist.getId());

        // création des éléments dans la database
        if (!urlAudioFileList.isEmpty() && !urlCoverFile.isEmpty()) {
            if (artistService.updateArtistPage(newArtist, urlAudioFileList, urlCoverFile, user.getId())) {

                return "redirect:/artistPage/" + newArtist.getId();
            } else {
                model.addAttribute("savePageError", "Une erreur est survenue lors de la création de la page");
                return "modifyArtistPage/modifyArtistPage";
            }
        } else {
            model.addAttribute("savePageError", "Un problème est survenu lors de la sauvegarde de vos fichier");
            return "modifyArtistPage/modifyArtistPage";
        }
    }

}
