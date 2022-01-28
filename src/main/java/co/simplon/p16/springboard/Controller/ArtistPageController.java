package co.simplon.p16.springboard.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


import co.simplon.p16.springboard.entity.Artist;
import co.simplon.p16.springboard.services.ArtistService;

@Controller
public class ArtistPageController {

    @Autowired
    ArtistService artistService;
    
    
    @GetMapping("/artistPage/{id}")
    public String getArtistPageById(@PathVariable(name = "id") int id, Model model){
        
        Artist artist = artistService.displayArtistPage(id);
        model.addAttribute("artist", artist);
        
        return "artistPage";
    }
}
