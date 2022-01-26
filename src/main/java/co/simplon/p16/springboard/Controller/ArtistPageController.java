package co.simplon.p16.springboard.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import co.simplon.p16.springboard.entity.Artist;
import co.simplon.p16.springboard.services.ArtistService;

@Controller
public class ArtistPageController {

    @Autowired
    ArtistService artistService;
    
    
    @GetMapping("/artistPage/{id}")
    public ModelAndView getArtistPageById(@PathVariable(name = "id") int id){
        ModelAndView modelAndView = new ModelAndView("artistPage");
        Artist artist = artistService.displayArtistPage(id);
        modelAndView.addObject("artist", artist);
        
        return modelAndView;
    }
}
