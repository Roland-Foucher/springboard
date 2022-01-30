package co.simplon.p16.springboard.Controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import co.simplon.p16.springboard.entity.User;
import co.simplon.p16.springboard.repository.ArtistRepository;
import co.simplon.p16.springboard.services.ArtistService;

@Controller
public class AccountController {

    @Autowired
    ArtistService artistService;

    @Autowired
    ArtistRepository artistRepository;

    
    
    @GetMapping("/myAccount")
    public String showMyAccountPage(Model model, @AuthenticationPrincipal User user){

        if(user.getRole() == "ROLE_ARTIST"){
            model.addAttribute("artistId", artistRepository.findByUserId(user.getId()).getId());
        }
       
        model.addAttribute("artists", artistService.displayFavoritArtistCards(user.getId()));
        return "myAccount";
    }
}
