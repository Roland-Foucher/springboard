package co.simplon.p16.springboard.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import co.simplon.p16.springboard.entity.User;
import co.simplon.p16.springboard.repository.ArtistRepository;
import co.simplon.p16.springboard.services.ArtistService;
import co.simplon.p16.springboard.services.UserService;

@Controller
public class AccountController {

    @Autowired
    ArtistService artistService;

    
    
    @GetMapping("/myAccount")
    public String showMyAccountPage(Authentication authentication, Model model){

        User user = (User)authentication.getPrincipal();
        
        model.addAttribute("artists", artistService.displayFavoritArtistCards(user.getId()));
        return "myAccount";
    }
}
