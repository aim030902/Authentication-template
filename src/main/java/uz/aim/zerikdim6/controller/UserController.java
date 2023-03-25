package uz.aim.zerikdim6.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import uz.aim.zerikdim6.domains.User;
import uz.aim.zerikdim6.dtos.ApiResponse;
import uz.aim.zerikdim6.dtos.LoginDTO;
import uz.aim.zerikdim6.dtos.RegisterDTO;
import uz.aim.zerikdim6.repository.UserRepository;
import uz.aim.zerikdim6.services.ReCaptchaService;
import uz.aim.zerikdim6.services.UserService;

import java.util.List;

@Controller
@RequestMapping("/api/auth")
public class UserController {
    @Autowired
    private ReCaptchaService reCaptchaService;
    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/register")
    public String register() {
        return "register";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @PostMapping("/register")
    public String register(@ModelAttribute RegisterDTO dto, @RequestParam(name="g-recaptcha-response") String captcha, Model model) {
        if(reCaptchaService.validateCaptcha(captcha))
        {
            ApiResponse apiResponse = userService.register(dto);
            model.addAttribute("apiResponse", apiResponse);
            model.addAttribute("message", "Employee added!!");
        }
        else {
            model.addAttribute("message", "Please Verify Captcha");
        }
        return "redirect:/api/auth/login";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute LoginDTO dto) {
        ApiResponse apiResponse = userService.login(dto);
        return "redirect:/dashboard";
    }
}
