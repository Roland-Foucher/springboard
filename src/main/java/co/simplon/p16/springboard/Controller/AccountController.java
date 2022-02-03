package co.simplon.p16.springboard.Controller;

import org.springframework.beans.factory.annotation.Autowired;
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
        System.out.println(user.getRole());
        if(user.getRole().equals("ROLE_ARTIST")){
            model.addAttribute("artistId", artistRepository.findByUserId(user.getId()).getId());
        }
       
        model.addAttribute("artists", artistService.displayFavoritArtistCards(user.getId()));
        return "account/myAccount";
    }

    @GetMapping("/myAccount/profil")
    public String showProfil(Model model, @AuthenticationPrincipal User user){
        model.addAttribute("user", user);
        return "account/profil";
    }

    @GetMapping("/myAccount/modify")
    public String modifyProfil(Model model, @AuthenticationPrincipal User user){
        model.addAttribute("user", user);
        return "login/register";
        // TODO redirect to other page or post diffente if user connected or not
    }

}
