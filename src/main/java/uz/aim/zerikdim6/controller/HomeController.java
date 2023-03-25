package uz.aim.zerikdim6.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import uz.aim.zerikdim6.domains.User;
import uz.aim.zerikdim6.repository.UserRepository;

import java.util.List;

@Controller
public class HomeController {
    @Autowired
    private UserRepository userRepository;
    @GetMapping("/")
    public String homePage() {
        return "index";
    }

    @GetMapping("/dashboard")
    public String dashboard(Model model) {
        List<User> users = userRepository.findAll();
        model.addAttribute("users", users);
        return "dashboard";
    }
}
