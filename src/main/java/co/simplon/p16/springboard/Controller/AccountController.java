package co.simplon.p16.springboard.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import co.simplon.p16.springboard.entity.User;
import co.simplon.p16.springboard.repository.ArtistRepository;
import co.simplon.p16.springboard.services.ArtistService;

@RequestMapping("myAccount")
@Controller
public class AccountController {

    @Autowired
    ArtistService artistService;

    @Autowired
    ArtistRepository artistRepository;

    
    
    @GetMapping("")
    public String showMyAccountPage(Model model, @AuthenticationPrincipal User user){
        System.out.println(user.getRole());
        if(user.getRole().equals("ROLE_ARTIST")){
            model.addAttribute("artistId", artistRepository.findByUserId(user.getId()).getId());
        }
       
        model.addAttribute("artists", artistService.displayFavoritArtistCards(user.getId()));
        return "account/myAccount";
    }

    @GetMapping("/profil")
    public String showProfil(Model model, @AuthenticationPrincipal User user){
        model.addAttribute("user", user);
        return "account/profil";
    }

    @GetMapping("/modify")
    public String modifyProfil(Model model, @AuthenticationPrincipal User user){
        model.addAttribute("user", user);
        return "login/register";
    }

}
