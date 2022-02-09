package co.simplon.p16.springboard.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.simplon.p16.springboard.entity.User;
import co.simplon.p16.springboard.services.ArtistService;
import co.simplon.p16.springboard.services.UserService;

@RestController
@RequestMapping("/api")
public class ArtistPageRestController {

    @Autowired
    UserService userService;

    @Autowired
    ArtistService artistService;

    @GetMapping("/artistPage/{id}/favorite")
   
    public ResponseEntity<String> takeFavoritArtist(@PathVariable int id, @AuthenticationPrincipal User user) {
        if(userService.takeFavoritArtist(user.getId(), id)){
            return ResponseEntity.ok().build();
        }else{
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/artistPage/{id}/upVote")
    public ResponseEntity<String> likeArtist(@PathVariable int id, @AuthenticationPrincipal User user) {
        if (userService.takeUpVoteToArtist(user.getId(), id)) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/artistPage/{id}/listenCount")
    public ResponseEntity<String> listenCountIncrease(@PathVariable int id, @AuthenticationPrincipal User user) {
        if (artistService.addListenCount(id)) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.internalServerError().build();
        }
    }

}
