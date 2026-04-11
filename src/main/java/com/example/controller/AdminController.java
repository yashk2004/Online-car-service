package com.example.controller;

import com.example.model.Booking;
import com.example.model.User;
import com.example.service.BookingService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class AdminController {

    @Autowired
    private BookingService bookingService;

    // ── HARDCODED ADMIN CREDENTIALS ──
    private static final String ADMIN_EMAIL    = "admin@autocare.com";
    private static final String ADMIN_PASSWORD = "admin123";

    // ── SHOW ADMIN LOGIN PAGE ──
    @GetMapping("/admin/login")
    public String adminLoginPage(@RequestParam(required = false) String error, Model model) {
        if (error != null) model.addAttribute("error", "Invalid admin credentials.");
        return "admin-login";
    }

    // ── HANDLE ADMIN LOGIN ──
    @PostMapping("/admin/login")
    public String adminLoginSubmit(@RequestParam String email,
                                   @RequestParam String password,
                                   HttpSession session) {
        if (ADMIN_EMAIL.equals(email) && ADMIN_PASSWORD.equals(password)) {
            session.setAttribute("adminLoggedIn", true);
            return "redirect:/admin/dashboard";
        }
        return "redirect:/admin/login?error";
    }

    // ── ADMIN DASHBOARD ──
    @GetMapping("/admin/dashboard")
    public String adminDashboard(HttpSession session, Model model) {
        if (session.getAttribute("adminLoggedIn") == null) {
            return "redirect:/admin/login";
        }

        List<Booking> allBookings = bookingService.getAll();

        // Stats
        int totalRevenue = 0;
        int onlinePayments = 0;
        int cashPayments = 0;
        int homePickups = 0;

        for (Booking b : allBookings) {
            if (b.getPrice() != null && !b.getPrice().isEmpty()) {
                try { totalRevenue += Integer.parseInt(b.getPrice()); }
                catch (NumberFormatException ignored) {}
            }
            if ("online".equalsIgnoreCase(b.getPaymentMethod())) onlinePayments++;
            if ("cash".equalsIgnoreCase(b.getPaymentMethod()))   cashPayments++;
            if ("pickup".equalsIgnoreCase(b.getPickupOption()))  homePickups++;
        }

        model.addAttribute("bookings",       allBookings);
        model.addAttribute("totalBookings",  allBookings.size());
        model.addAttribute("totalRevenue",   totalRevenue);
        model.addAttribute("onlinePayments", onlinePayments);
        model.addAttribute("cashPayments",   cashPayments);
        model.addAttribute("homePickups",    homePickups);

        return "admin-dashboard";
    }

    // ── ADMIN LOGOUT ──
    @GetMapping("/admin/logout")
    public String adminLogout(HttpSession session) {
        session.removeAttribute("adminLoggedIn");
        return "redirect:/admin/login";
    }
}
