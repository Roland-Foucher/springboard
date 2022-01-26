package co.simplon.p16.springboard.Controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import co.simplon.p16.springboard.entity.Artist;
import co.simplon.p16.springboard.entity.MusicalStyle;
import co.simplon.p16.springboard.repository.IArtistRepository;
import co.simplon.p16.springboard.repository.IMusicalStyleRepository;
import co.simplon.p16.springboard.services.ArtistService;

@Controller
public class IndexController {

    @Autowired
    ArtistService artistService;

    @GetMapping("/")
    public String showIndexPage(Model model) {

        model.addAttribute("artists", artistService.display10ShuffleArtistsCards());

        return "index";
    }

}
