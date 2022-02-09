package co.simplon.p16.springboard.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import co.simplon.p16.springboard.services.ArtistService;

@Controller
public class IndexController {

    @Autowired
    ArtistService artistService;

   

    @GetMapping("/")
    public String showIndexPage(Model model) {

        model.addAttribute("artists", artistService.display8ShuffleArtistsCards());
       

        return "index";
    }

}
