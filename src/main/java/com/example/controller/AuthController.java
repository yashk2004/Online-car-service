package com.example.controller;

import com.example.model.User;
import com.example.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class AuthController {

    @Autowired
    private UserService userService;

    // ── SHOW LOGIN PAGE ──
    @GetMapping("/login")
    public String loginPage(@RequestParam(required = false) String error,
                            @RequestParam(required = false) String registered,
                            Model model) {
        if (error != null)      model.addAttribute("error", "Invalid email or password.");
        if (registered != null) model.addAttribute("success", "Account created! Please log in.");
        return "login";
    }

    // ── HANDLE LOGIN FORM ──
    @PostMapping("/login")
    public String loginSubmit(@RequestParam String email,
                              @RequestParam String password,
                              HttpSession session) {
        User user = userService.login(email, password);
        if (user == null) {
            return "redirect:/login?error";
        }
        session.setAttribute("loggedInUser", user);
        return "redirect:/";
    }

    // ── SHOW SIGNUP PAGE ──
    @GetMapping("/signup")
    public String signupPage(@RequestParam(required = false) String error, Model model) {
        if (error != null) model.addAttribute("error", "Email already registered. Try logging in.");
        return "signup";
    }

    // ── HANDLE SIGNUP FORM ──
    @PostMapping("/signup")
    public String signupSubmit(@RequestParam String name,
                               @RequestParam String email,
                               @RequestParam String password) {
        User user = userService.register(name, email, password);
        if (user == null) {
            return "redirect:/signup?error";
        }
        return "redirect:/login?registered";
    }

    // ── LOGOUT ──
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }
}
