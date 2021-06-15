package org.serj.controllers;

import org.serj.domains.User;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class GreetingController {

    @GetMapping("/greeting")
    public String greeting(@AuthenticationPrincipal User user, @RequestParam(name="name", required=false, defaultValue="World") String name, Model model) {
        model.addAttribute("name", name);
        model.addAttribute("isAdmin", user.isAdmin());
        return "greeting";
    }

    @PostMapping("/greeting")
    public String greetingUser (@AuthenticationPrincipal User user, @RequestParam(name="name", required=false, defaultValue="World") String name, Model model) {
        model.addAttribute("name", name);
        model.addAttribute("isAdmin", user.isAdmin());
        return "greeting";
    }

}
