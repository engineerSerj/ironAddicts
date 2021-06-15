package org.serj.controllers;

import org.serj.domains.Role;
import org.serj.domains.User;
import org.serj.repositoryes.UserRepository;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/user")
@PreAuthorize("hasAuthority('ADMIN')")
public class UserController {

    private UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping()
    public String userList (@AuthenticationPrincipal User user, Model model){
        model.addAttribute("users", userRepository.findAll());
        model.addAttribute("isAdmin", user.isAdmin());
        return "userList";
    }
    @GetMapping("/{user}")
    public String userEditForm(@PathVariable User user, Model model) {
        model.addAttribute("userEdit", user);
        model.addAttribute("roles", Role.values());
        model.addAttribute("isAdmin", user.isAdmin());
        return "userEdit";
    }
    @PostMapping
    public String userSave(
            @RequestParam String name,
            @RequestParam Map <String, String> form,
            @RequestParam("userId") User user,
            Model model){
        model.addAttribute("isAdmin", user.isAdmin());
        Set<String> roles = Arrays.stream(Role.values())
                .map(Role::name)
                .collect(Collectors.toSet());

        user.getRoles().clear();

        for(String key : form.keySet()){
            if(roles.contains(key)) {
                user.getRoles().add(Role.valueOf(key));
            }
        }

        user.setName(name);

        userRepository.save(user);
        return "redirect:/user";
    }
}
