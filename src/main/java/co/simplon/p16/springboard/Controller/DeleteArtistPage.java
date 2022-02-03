package co.simplon.p16.springboard.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import co.simplon.p16.springboard.entity.User;
import co.simplon.p16.springboard.repository.UserRepository;
import co.simplon.p16.springboard.services.ArtistService;
import co.simplon.p16.springboard.services.UserService;

@RequestMapping("artist/")
@Controller()
public class DeleteArtistPage {

    @Autowired
    UserService userService;

    @Autowired
    ArtistService artistService;
    
    @GetMapping("deleteArtistPage")
    public String showDeletePage(){
        return "deleteArtistPage/delete";
    }
    @GetMapping("deleteArtistPage/error")
    public String showDeletePageError(Model model){
        model.addAttribute("error", "Une erreur est survenue en supprimant votre page");
        return "deleteArtistPage/delete";
    }

    @PostMapping("deleteArtistPage")
    public String deleteArtistPage(Authentication authentication){
        if(artistService.deleteArtistPage(authentication)){
            userService.setNewRole("ROLE_USER", authentication);
            return "redirect:/";
        }else{
            return "deleteArtistPage/error";

        }
        
        
        
    }
}
