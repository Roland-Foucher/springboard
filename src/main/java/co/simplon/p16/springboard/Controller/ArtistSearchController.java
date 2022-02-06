package co.simplon.p16.springboard.Controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonCreator.Mode;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import co.simplon.p16.springboard.entity.Artist;
import co.simplon.p16.springboard.repository.ArtistRepository;
import co.simplon.p16.springboard.repository.MusicalStyleRepository;
import co.simplon.p16.springboard.services.ArtistService;

@Controller
public class ArtistSearchController {

    @Autowired
    MusicalStyleRepository musicalStyleRepository;

    @Autowired
    ArtistService artistService;

    @Autowired
    ArtistRepository artistRepository;
    
    @GetMapping("artistSearch")
    public String displayArtistSearchPage(Model model){
        model.addAttribute("styles", musicalStyleRepository.findAll());
        List<Artist> artists = artistService.displayAllArtistCards();
        model.addAttribute("artists", artists);

        return "artistSearch/artistSearch";
    }

    @GetMapping("search")
    public String displayArtistsfiltred(Model model, int[] styleId){
        model.addAttribute("styles", musicalStyleRepository.findAll());
        Set <Artist> artists = new HashSet<>();
        for (int id : styleId) {
            artists.addAll(artistRepository.findByMusicalStyle(id));
            
        }
        model.addAttribute("artists", artists);
        return "artistSearch/artistSearch";
    }
}
