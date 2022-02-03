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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import co.simplon.p16.springboard.entity.Artist;
import co.simplon.p16.springboard.entity.MusicalStyle;
import co.simplon.p16.springboard.entity.User;
import co.simplon.p16.springboard.repository.ArtistRepository;
import co.simplon.p16.springboard.repository.IMusicalStyleRepository;
import co.simplon.p16.springboard.repository.TrackRepository;
import co.simplon.p16.springboard.services.FormArtistPageService;
import co.simplon.p16.springboard.services.UploadFile;

@RequestMapping("artist/")
@Controller
public class ModifyArtistPageController {

    @Autowired
    IMusicalStyleRepository musicalStyleRepository;
    @Autowired
    UploadFile uploadFile;
    @Autowired
    FormArtistPageService formArtistPageService;
    @Autowired
    ArtistRepository artistRepository;


    @GetMapping("modifyArtistPage")
    public String modifyArtistPage(@AuthenticationPrincipal User user, Model model) {
        List<MusicalStyle> styleList = musicalStyleRepository.findAll();
        Artist artist = formArtistPageService.setListToUpdateArtistPage(user);

        model.addAttribute("musicalStyles", styleList);
        model.addAttribute("artist", artist);
      
        return "modifyArtistPage/modifyArtistPage";
    }

    @GetMapping("modifyArtistPage/modifyOk/{id}")
    public String saveArtsitPageOk(@PathVariable int id, Model model){
        model.addAttribute("status", "Votre page à bien été Modifiée !");
        model.addAttribute("id", id);
        return "newArtistPage/savePageOk";
    }

    @PostMapping("modifyArtistPage")
    public String saveNewArtistPage(Model model,
            @AuthenticationPrincipal User user,
            MultipartFile imageFile,
            MultipartFile[] audioFiles,
            @RequestParam(defaultValue = "false") Boolean modifyTracks,
            @RequestParam(defaultValue = "false") Boolean modifyCover,
            @Valid Artist newArtist,
            BindingResult bindingResult) {
        
        // réinjection de l'id
        int id = artistRepository.findByUserId(user.getId()).getId();
        newArtist.setId(id);
        // réinjection des styles
        List<MusicalStyle> styleList = musicalStyleRepository.findAll();
        model.addAttribute("musicalStyles", styleList);
                
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

        // enregistrement des fichiers s'il ont changés, sinon récupérationd des anciens
        // fichiers.
        List<String> urlAudioFileList = formArtistPageService.updateAudioFiles(modifyTracks, audioFiles, newArtist);
        String urlCoverFile = formArtistPageService.updateCoverFile(modifyCover, imageFile, newArtist);

        // création des éléments dans la database
        if (!urlAudioFileList.isEmpty() && !urlCoverFile.isEmpty()) {
            if (formArtistPageService.updateArtistPage(newArtist, urlAudioFileList, urlCoverFile, user.getId())) {

                return "redirect:/artist/modifyArtistPage/modifyOk/" + id;
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
