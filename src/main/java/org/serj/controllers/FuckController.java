package org.serj.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FuckController {

    @GetMapping("/fuck")
    public String fuck() {
        return "test";
    }
}