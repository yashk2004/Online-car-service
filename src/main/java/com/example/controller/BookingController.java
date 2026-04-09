package com.example.controller;

import com.example.model.Booking;
import com.example.model.User;
import com.example.service.BookingService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Random;

@Controller
public class BookingController {

    @Autowired
    private BookingService service;

    // Service buddy pool — realistic Indian names + phones
    private static final String[][] BUDDIES = {
        {"Arjun Sharma",   "98201 34567", "AS"},
        {"Ravi Kumar",     "91765 22891", "RK"},
        {"Suresh Patel",   "87654 10923", "SP"},
        {"Manish Verma",   "99887 56412", "MV"},
        {"Deepak Singh",   "93456 78012", "DS"},
        {"Anil Yadav",     "98123 45670", "AY"},
        {"Kiran Nair",     "76543 21098", "KN"},
        {"Vikram Mehta",   "90001 23456", "VM"},
        {"Rohit Tiwari",   "88901 34521", "RT"},
        {"Santosh Joshi",  "99034 56781", "SJ"}
    };

    // Estimated service times
    private static String estimatedTime(String serviceType) {
        if (serviceType == null) return "2–3 hours";
        return switch (serviceType) {
            case "Oil Change"          -> "45–60 mins";
            case "Full Service"        -> "4–6 hours";
            case "Tyre Rotation"       -> "30–45 mins";
            case "Brake Service"       -> "1.5–2 hours";
            case "AC Service"          -> "2–3 hours";
            case "Diagnostics"         -> "1–2 hours";
            case "Gear Oil Change"     -> "45–60 mins";
            case "Suspension Service"  -> "2–3 hours";
            case "Windshield & Lights" -> "1–2 hours";
            default              -> "2–3 hours";
        };
    }

    @GetMapping("/")
    public String home(HttpSession session, Model model) {
        User user = (User) session.getAttribute("loggedInUser");
        if (user == null) return "redirect:/login";
        model.addAttribute("user", user);
        return "home";
    }

    @GetMapping("/booking")
    public String booking(HttpSession session, Model model) {
        User user = (User) session.getAttribute("loggedInUser");
        if (user == null) return "redirect:/login";
        model.addAttribute("user", user);
        model.addAttribute("booking", new Booking());
        model.addAttribute("list", service.getAll());
        return "index";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute Booking booking, HttpSession session) {
        User user = (User) session.getAttribute("loggedInUser");
        if (user == null) return "redirect:/login";

        booking.setUserId(user.getId());

        String now = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd MMM yyyy, hh:mm a"));
        booking.setBookedAt(now);

        // Assign estimated time based on service
        booking.setEstimatedTime(estimatedTime(booking.getServiceType()));

        // Auto-assign a service buddy
        Random rng = new Random();
        String[] buddy = BUDDIES[rng.nextInt(BUDDIES.length)];
        booking.setServiceBuddyName(buddy[0]);
        booking.setServiceBuddyPhone(buddy[1]);
        booking.setServiceBuddyPic(buddy[2]);

        service.save(booking);
        return "redirect:/booking";
    }

    @GetMapping("/dashboard")
    public String dashboard(HttpSession session, Model model) {
        User user = (User) session.getAttribute("loggedInUser");
        if (user == null) return "redirect:/login";

        List<Booking> bookings = service.getByUserId(user.getId());

        int total = 0;
        for (Booking b : bookings) {
            if (b.getPrice() != null && !b.getPrice().isEmpty()) {
                try { total += Integer.parseInt(b.getPrice()); } catch (NumberFormatException ignored) {}
            }
        }

        model.addAttribute("user", user);
        model.addAttribute("bookings", bookings);
        model.addAttribute("totalAmount", total);

        return "dashboard";
    }
}