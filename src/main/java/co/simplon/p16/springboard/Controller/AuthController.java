package co.simplon.p16.springboard.Controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
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
    public String registerUser(@ModelAttribute @Valid User user, BindingResult bindingResult, Model model) {
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
        userRepository.save(user);
        return "redirect:/login/login";
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
