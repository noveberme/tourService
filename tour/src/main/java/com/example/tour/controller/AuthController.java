package com.example.tour.controller;

import com.example.tour.entity.User;
import com.example.tour.enums.Role;
import com.example.tour.service.UserService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AuthController { //контроллер аутентификации
    @Autowired
    private UserService userService;

    @GetMapping("/login")
    public String showLoginForm(@RequestParam(required = false) String error, Model model) {
        if (error != null) {
            model.addAttribute("error", "Неверный email или пароль");
        }
        return "login";
    }

    @GetMapping("/register")
    public String showRegisterForm(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("roles", Role.values());
        return "register";
    }

    @PostMapping("/register") //обработка регистрации
    public String register(@Valid @ModelAttribute User user, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("roles", Role.values());
            return "register";
        }

        if (userService.existByEmail(user.getEmail())) {
            model.addAttribute("error", "Email уже используется");
            model.addAttribute("roles", Role.values());
            return "register";
        }

        if (userService.existByPhone(user.getNumberPhone())) {
            model.addAttribute("error", "Номер телефона уже используется");
            model.addAttribute("roles", Role.values());
            return "register";
        }

        user.setUserRole(Role.USER);
        userService.saveUser(user);
        return "redirect:/login";
    }
}
