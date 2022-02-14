package co.simplon.p16.springboard.controller;

import java.text.NumberFormat.Style;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

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

    @GetMapping("artistSearch/{page}")
    public String displayArtistSearchPage(Model model, @PathVariable int page,
            @RequestParam(required = false) String search) {

        model.addAttribute("styles", musicalStyleRepository.findAll());
        if (search == null|| search.isEmpty()) {
            List<Artist> artistList = artistRepository.findAllPagineList(page);
            model.addAttribute("artists", artistList);
            List<Integer> pages = new ArrayList<>();
            Integer numberOfPage = artistRepository.numberOfArtist() / 8;

            for (int i = 0; i <= numberOfPage; i++) {
                pages.add(i);
            }

            model.addAttribute("pages", pages);
        } else {
            List<Artist> searchList = artistRepository.findByArtistName("%" + search + "%");
            model.addAttribute("artists", searchList);
        }

        return "artistSearch/artistSearch";
    }
}
