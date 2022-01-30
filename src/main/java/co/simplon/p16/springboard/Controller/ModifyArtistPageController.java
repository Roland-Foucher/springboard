package co.simplon.p16.springboard.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import co.simplon.p16.springboard.entity.Artist;
import co.simplon.p16.springboard.entity.MusicalStyle;
import co.simplon.p16.springboard.repository.IMusicalStyleRepository;
import co.simplon.p16.springboard.services.ArtistService;

@RequestMapping("artist/")
@Controller
public class ModifyArtistPageController {
    
    @Autowired
    IMusicalStyleRepository musicalStyleRepository;
    @Autowired
    ArtistService artistService;
    
    @GetMapping("/modifyArtistPage/{id}")
    public String modifyArtistPage(@PathVariable int id, Model model){
        List<MusicalStyle> styleList = musicalStyleRepository.findAll();
        Artist artist = artistService.setListToUpdateArtistPage(id);

        model.addAttribute("musicalStyles", styleList);
        model.addAttribute("artist", artist);

        return "newArtistPage/newArtistPage";
    }
}
