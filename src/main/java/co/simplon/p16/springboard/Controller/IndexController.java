package co.simplon.p16.springboard.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import co.simplon.p16.springboard.repository.IArtistRepository;

@Controller
public class IndexController {
    
    @Autowired
    IArtistRepository artistRepository;

    @GetMapping("/")
    public String showIndexPage(Model model){
        model.addAttribute("artists", artistRepository.findAll());
        
        return "index";
    }

}
