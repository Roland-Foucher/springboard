package co.simplon.p16.springboard.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import co.simplon.p16.springboard.entity.Track;
import co.simplon.p16.springboard.repository.ITrackRepository;

@Controller
public class test {

    @Autowired
    ITrackRepository trackRepository;
    
    @GetMapping
    public String testControlleer(Model model){
        List <Track> list = trackRepository.findAll();
        System.out.println(list);
        model.addAttribute("tracks", trackRepository.findAll());
        
        return "index";
    }
}
