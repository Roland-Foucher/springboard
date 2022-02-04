package co.simplon.p16.springboard.Controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import co.simplon.p16.springboard.entity.Artist;
import co.simplon.p16.springboard.entity.User;
import co.simplon.p16.springboard.repository.ArtistRepository;
import co.simplon.p16.springboard.repository.UserRepository;
import co.simplon.p16.springboard.services.ArtistService;

@RequestMapping("myAccount")
@Controller
public class AccountController {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    ArtistService artistService;

    @Autowired
    ArtistRepository artistRepository;

    @Autowired
    UserRepository userRepository;

    @GetMapping("")
    public String showMyAccountPage(Model model, @AuthenticationPrincipal User user) {
        System.out.println(user.getRole());
        if (user.getRole().equals("ROLE_ARTIST")) {
            Artist artist = artistRepository.findByUserId(user.getId());
            
            model.addAttribute("artist", artist);

        }
        model.addAttribute("artists", artistService.displayFavoritArtistCards(user.getId()));
        return "account/myAccount";
    }

    @GetMapping("/profil")
    public String showProfil(Model model, @AuthenticationPrincipal User user) {
        model.addAttribute("user", user);
        return "account/profil";
    }

    @GetMapping("/modify")
    public String showModifyProfilForm(Model model, @AuthenticationPrincipal User user) {
        model.addAttribute("user", user);
        return "account/modifyAccount";
    }

    @PostMapping("/modify")
    public String modifyProfil(@Valid User newUser, Model model, BindingResult bindingResult,
            @AuthenticationPrincipal User user, Authentication authentication) {
        
        // check valid user entries
        if (bindingResult.hasErrors()) {
            return "account/modifyAccount";
        }

        // check mail is null in database or email don't have any changes
        if (!newUser.getEmail().equalsIgnoreCase(user.getEmail()) && userRepository.findByEmail(newUser.getEmail()) != null) {
            model.addAttribute("feedback", "L'email est déjà utilisé");
            return "account/modifyAccount";
        }
        // change user properties
        user.setEmail(newUser.getEmail());
        user.setLastName(newUser.getLastName());
        user.setFirstName(newUser.getFirstName());

        String hashedPassword = passwordEncoder.encode(newUser.getPassword());
        user.setPassword(hashedPassword);

        // update user in database and logout
        userRepository.update(user);
        SecurityContextHolder.clearContext();
        return "redirect:/login";
    }

    
}
