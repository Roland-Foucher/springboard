package co.simplon.p16.springboard.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import co.simplon.p16.springboard.entity.User;
import co.simplon.p16.springboard.repository.UserRepository;

@Controller
public class AuthController {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/register")
    public String showRegister(Model model) {
        model.addAttribute("user", new User());

        return "login/register";
    }

    @PostMapping("/register")
    public String registerUser(@Valid User user, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "login/register";
        }

        if (userRepository.findByEmail(user.getEmail()) != null) {
            model.addAttribute("feedback", "L'email est déjà utilisé");
            return "login/register";
        }

        String hashedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(hashedPassword);
        user.setRole("ROLE_USER");
        if (userRepository.save(user)){
            return "redirect:/login";
        }else{
            model.addAttribute("feedback", "Une erreur technique s'est produite, merci de réessayer ultérieurement");
            return "login/register";
        }
    }

    @GetMapping("/login")
    public String showLogin(){
        return "login/login";
    }
    @GetMapping("/logout")
    public String showLogout(){
        return "login/logout";
    }


}
