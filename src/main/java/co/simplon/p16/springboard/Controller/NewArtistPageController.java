package co.simplon.p16.springboard.Controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
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
import co.simplon.p16.springboard.repository.IMusicalStyleRepository;

import co.simplon.p16.springboard.repository.UserRepository;
import co.simplon.p16.springboard.services.FormArtistPageService;
import co.simplon.p16.springboard.services.UploadFile;
import co.simplon.p16.springboard.services.UserService;

@RequestMapping("user/")
@Controller
public class NewArtistPageController {

    @Autowired
    IMusicalStyleRepository musicalStyleRepository;
    @Autowired
    UploadFile uploadFile;
    @Autowired
    UserService userService;
    @Autowired
    FormArtistPageService formArtistPageService;

    @GetMapping("newArtistPage")
    public String showNewArtistPageForm(Model model) {

        List<MusicalStyle> styleList = musicalStyleRepository.findAll();
        Artist artist = formArtistPageService.setListInNewArtist();

        model.addAttribute("musicalStyles", styleList);
        model.addAttribute("artist", artist);

        return "newArtistPage/newArtistPage";
    }

    @GetMapping("newArtistPage/saveOk/{id}")
    public String saveArtsitPageOk(Model model, @PathVariable int id, Authentication authentication){
       
        userService.setNewRole("ROLE_ARTIST", authentication);
        
        model.addAttribute("status", "Votre page à bien été créée !");
        model.addAttribute("id", id);
        return "newArtistPage/savePageOk";
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
                
        // user form validation

        // validation constraints starter validation I/O 
        if (bindingResult.hasErrors()) { 
            return "newArtistPage/newArtistPage";
        }

        // check image file not null
        if (imageFile.getSize() == 0) { 
            model.addAttribute("savePageError", "Merci de loader une image");
            return "newArtistPage/newArtistPage";
        }

        // check first audio file not null
        if (audioFiles[0].getSize() == 0) { 
            model.addAttribute("savePageError", "Merci de loader au moins un titre musical sur la première entrée");
            return "newArtistPage/newArtistPage";
        }

        // enregistrement des fichiers
        String urlCoverFile = "";
        List<String> urlAudioFileList = new ArrayList<>();

        if(uploadFile.checkFile(imageFile, "^image/.*")){
            try {
                urlCoverFile = uploadFile.saveFile(imageFile, artist);
            } catch (IOException e) {
                e.printStackTrace();
                model.addAttribute("savePageError", "Un problème est survenu lors de la sauvegarde de vos fichier");
                return "newArtistPage/newArtistPage";
            }
        }
        for (MultipartFile audioFile : audioFiles) {
            if(uploadFile.checkFile(audioFile, "^audio/.*")){
                try {
                    urlAudioFileList.add(uploadFile.saveFile(audioFile, artist));
                } catch (IOException e) {
                    e.printStackTrace();
                    model.addAttribute("savePageError", "Un problème est survenu lors de la sauvegarde de vos fichier");
                    return "newArtistPage/newArtistPage";
                }
            }
        }

        // check files are save on server
        if (!urlAudioFileList.isEmpty() || !urlCoverFile.isEmpty()) {
            // save artist in databse
            if (formArtistPageService.saveArtistPage(artist, urlAudioFileList, urlCoverFile, user.getId())) {
                
                //redirect to success page if OK
                return "redirect:/user/newArtistPage/saveOk/" + artist.getId();
            } else {
                // display error if not OK
                model.addAttribute("savePageError", "Une erreur est survenue lors de la création de la page");
                return "newArtistPage/newArtistPage";
            }
        } else {
            // display error if url are empty
            model.addAttribute("savePageError", "Un problème est survenu lors de la sauvegarde de vos fichier");
            return "newArtistPage/newArtistPage";
        }
    }

}
