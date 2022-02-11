package co.simplon.p16.springboard.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import co.simplon.p16.springboard.entity.Artist;
import co.simplon.p16.springboard.entity.User;
import co.simplon.p16.springboard.services.ArtistService;
import co.simplon.p16.springboard.services.UserService;

@Controller
public class ArtistPageController {

    @Autowired
    ArtistService artistService;

    @Autowired
    UserService userService;

    

    @GetMapping("/artistPage/{id}")
    public String getArtistPageById(@PathVariable int id, Model model, @AuthenticationPrincipal User user) {

        Artist artist = artistService.displayArtistPage(id);
        model.addAttribute("artist", artist);
        if (user != null) {
            model.addAttribute("favorite", userService.checkIfArtistIsFavorite(user.getId(), id));
            model.addAttribute("upVote", userService.checkIfArtistIsUpvoted(user.getId(), id));
        }
        return "artistPage/artistPage";
    }

}
